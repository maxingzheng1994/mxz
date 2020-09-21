import com.alibaba.fastjson.JSONObject;
import com.mxz.Application;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.example.MQProducer;
import com.mxz.common.mq.proxy.MQProducerProxy;
import com.mxz.common.mq.util.MQProducerHolder;
import com.mxz.service.article.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/16 20:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MqTest {
    @Resource
    private MQProducer mqProducer;

    @Test
    public void name() {
        for (int i = 0; i < 10; i++) {
            mqProducer.sendMessage("Hello ", "Topictext", "TagTest", "key"+i);
        }
    }

    @Test
    public void mq2() {
        Map<String, MQProducerProxy> allProducer = MQProducerHolder.getAllProducer();
        MQProducerProxy orderProducer = MQProducerHolder.getMqProducer("orderProducer");
        MQMessageBean mqMessageBean = new MQMessageBean();
        mqMessageBean.setTopic("topicA");
        Article article = Article.builder().name("我是一篇文章").build();
        mqMessageBean.setTag("tag1");
        mqMessageBean.setBody(JSONObject.toJSONString(article).getBytes());
        orderProducer.send(mqMessageBean);
    }

    @Test
    public void consumer() {
        Map<String, MQProducerProxy> allProducer = MQProducerHolder.getAllProducer();
        MQProducerProxy orderProducer = MQProducerHolder.getMqProducer("orderProducer");
        MQMessageBean mqMessageBean = new MQMessageBean();
        mqMessageBean.setTopic("topicA");
        mqMessageBean.setBody("aaaaa".getBytes());
        orderProducer.send(mqMessageBean);
    }
}
