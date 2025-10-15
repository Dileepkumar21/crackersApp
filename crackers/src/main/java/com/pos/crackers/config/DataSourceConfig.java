package com.pos.crackers.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){
        String url = "jdbc:postgresql://crackerspos-dilipcse15-35cb.b.aivencloud.com:19980/defaultdb?ssl=require&user=avnadmin&AVNS_fS2zNSXfggxhv0ETOqP";
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName("org.postgresql.Driver")
                .username("avnadmin")
                .password("AVNS_fS2zNSXfggxhv0ETOqP")
                //.password("jelly22fi$h")
                .build();
    }
}
