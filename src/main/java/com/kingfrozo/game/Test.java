package com.kingfrozo.game;

import com.kingfrozo.inv.RClasses.RPlayer;
import com.kingfrozo.inv.Redis.RLORetrieval;
import com.kingfrozo.inv.Redis.RedisClient;
import org.redisson.api.RBucket;
import org.redisson.api.RLiveObjectService;

import java.io.IOException;
import java.util.Random;

public class Test {

    // INFO:
    // THIS IS A FILE TO TESTING OUT FUNCTIONALITY BEFORE IMPLEMENTING
    // THEM INTO BUKKIT COMMANDS EVENTS

    public static void main(String[] args) throws IOException {


//        if(!service.isClassRegistered(RPlayer.class)) {
//            System.out.println("RPlayer not registered to Redisson RLO!");
//            service.registerClass(RPlayer.class);
//            System.out.println("Registered RPlayer on RLO.");
//        }

        // RPlayer random = new RPlayer("123214145", "jacasfsak");

        // RLORetrieval.rlo = service;

        // RLORetrieval.rlo.persist(random);

        // RPlayer jack = RLORetrieval.getLivePlayerProfileOnLogin("123214145", "jack");

        // System.out.println(jack.getTitle());

        ///////
//        RedisClient redis = new RedisClient("redis://127.0.0.1:6379");
//        // redis.loadConfig("config.yml");
//        redis.start();
//
//        RLiveObjectService service = redis.redisson.getLiveObjectService();
//
//        RPlayer xShambles = service.get(RPlayer.class, "90e937fa-462d-3b31-9a52-f4384ab79c51");
//        if(xShambles == null) System.out.println("xshambles not found");
//        xShambles.setTitle("splashy");
//        System.out.println(xShambles.getInv());
//        RBucket<String> bucket = redis.redisson.getBucket("test");
//        bucket.set("tester");
//        System.out.println(bucket.get());
//
//        redis.shutdown();
        /////////

        Random random = new Random(System.currentTimeMillis());

        for(int i =0; i < 100; i++ )
            System.out.println(random.nextInt(29)-14); // gen a random no. from 4-10
    }

}