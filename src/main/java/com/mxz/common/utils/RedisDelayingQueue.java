package com.mxz.common.utils;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.UUID;

/**
 *
 * redis 延迟队列
 *
 * zset 存入， score 代表到期处理时间多个线程轮询zset 获取任务进行处理， 防止单个线程挂掉，但应考虑并发重复消费问题
 * @author mxz on 2020/09/18 14:12
 */
public class RedisDelayingQueue<T> {
    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 10016);
        for (int i = 0; i < 100000; i++) {
            jedis.pfadd("user", i + "");
        }
        long user = jedis.pfcount("user");
        System.out.println("user = " + user);
        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "hello");
        Runnable target;
        Thread producer = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.delay("clon" + i);
                }
            }
        };

        Thread consumer = new Thread(){
            @Override
            public void run() {
                queue.loop();
            }
        };

        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    public void delay(T msg) {
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        String s = JSON.toJSONString(task);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
    }

    public void loop() {
        while(!Thread.interrupted()) {
            // 只取一条, 取值score 范围0， 当前毫秒
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
// 歇会继续
                }
                catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            // 移除成功
            if (jedis.zrem(queueKey, s) > 0) { // 抢到了
                TaskItem<String> task = JSON.parseObject(s, TaskItem.class); // fastjson 反序列化
                this.handleMsg(task.msg);
            }
        }
    }

    public void handleMsg(String msg) {
        System.out.println("msg = " + msg);
    }


}
