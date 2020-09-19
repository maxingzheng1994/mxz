import com.mxz.Application;
import com.mxz.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/17 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {
    @Resource
    private RedisUtil redisUtil;
    @Test
    void redis1() {
        Object asdsa = redisUtil.get("asdsa");
        System.out.println("asdsa = " + asdsa);
    }
}
