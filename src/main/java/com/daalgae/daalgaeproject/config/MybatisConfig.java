package com.daalgae.daalgaeproject.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.daalgae.daalgaeproject", annotationClass = Mapper.class)
public class MybatisConfig {
}
