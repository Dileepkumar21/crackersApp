package com.pos.crackers.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName("org.postgresql.Driver")
                .username("postgres")
                .password("postgres")
                //.password("jelly22fi$h")
                .build();
    }
}
