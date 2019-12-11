package com.mxz.task;

import com.mxz.model.Location;
import com.mxz.service.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Date 2019/12/11 21:17
 * @Author mxz
 */
@Component
public class InitCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("aaa");
//        locationRepository.save(new Location((long) 1,"1",38.998064, 117.317267));
//            repo.save(new Location((long)2,"2",38.997793, 117.317069));
//            repo.save(new Location((long)3,"3",38.998006, 117.317101));
//            repo.save(new Location((long)4,"4",38.997814, 117.317332));
    }
}
