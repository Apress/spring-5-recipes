package com.apress.springrecipes.nosql;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.apress.springrecipes.nosql.config.MongoConfiguration;

import reactor.core.publisher.Flux;

/**
 * Created by marten on 22-09-14.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfiguration.class);
        VehicleRepository repository = ctx.getBean(VehicleRepository.class);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        repository.count().doOnSuccess(cnt -> System.out.println("Number of Vehicles: " + cnt))
                .thenMany(        repository.saveAll(
                        Flux.just(

                                new Vehicle("TEM0001", "RED", 4, 4),
                                new Vehicle("TEM0002", "RED", 4, 4))))
                .last()
                .then(repository.count()).doOnSuccess(cnt -> System.out.println("Number of Vehicles: " + cnt))
                .then(repository.findByVehicleNo("TEM0001")).doOnSuccess(System.out::println)
                .then(repository.deleteAll())
                    .doOnSuccess(x -> countDownLatch.countDown())
                    .doOnError(t -> countDownLatch.countDown())
                .then(repository.count()).subscribe(cnt -> System.out.println("Number of Vehicles: " + cnt));


        countDownLatch.await();
        ((AbstractApplicationContext) ctx).close();

    }
}
