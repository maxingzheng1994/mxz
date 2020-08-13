import com.mxz.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 15:03
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TaskTest {

    FutureTask futureTask;
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> stringFutureTask;
        stringFutureTask = new FutureTask<String>(() -> {
            Thread.sleep(10000);
            return "sadsad";
        });
        executorService.execute(stringFutureTask);
        System.out.println("sadsadsadsadsa===");
    }
}
