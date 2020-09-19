package com.mxz.service.chekin.service;

import com.mxz.common.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mxz on 2020/09/17 11:54
 */
@Service
public class CheckInService {

    @Resource
    private RedisUtil redisUtil;

    public void check(String uid) {
        //每天一个key
        String key = "checkin_" +  "日期yyyy-m-d";
//        if(redisService.getbit(key, userid)){
//            //已签到
//        }else{
//            //签到
//            redisService.setbit(key, uid, 1);
//        }
    }

    public void getUserCheckinCount(String uuid) {
        String key = "checkin_" + uuid;
        // 查询昨日签到是否连续进行逻辑计算
    }

    public void getCheckinCount(Date date) {

        String key = "checkin_" + "yyyy-m-d";
//        Long count = redisService.BITCOUNT(key);
    }


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionL = lock.newCondition();
        Condition conditioni = lock.newCondition();
        Thread thread = new Thread();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable r1 = () -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println("A");
                    conditionL.signal();
                    conditionA.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Runnable r2 = () -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println("L");
                    conditioni.signal();
                    conditionL.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Runnable r3 = () -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println("i");
                    conditionA.signal();
                    conditioni.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        executorService.execute(r1);
        executorService.execute(r2);
        executorService.execute(r3);
    }
}

