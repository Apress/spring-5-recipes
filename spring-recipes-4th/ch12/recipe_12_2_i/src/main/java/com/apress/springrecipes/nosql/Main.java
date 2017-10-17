package com.apress.springrecipes.nosql;

import redis.clients.jedis.Jedis;

public class Main {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("msg", "Hello World, from Redis!");
        System.out.println(jedis.get("msg"));
    }
}
