import com.mxz.Application;
import com.mxz.common.mq.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

}
