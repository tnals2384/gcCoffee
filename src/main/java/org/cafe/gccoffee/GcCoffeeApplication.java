package org.cafe.gccoffee;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class GcCoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcCoffeeApplication.class, args);
    }

    //이전 servlet-context에서 해주었던 mybatis 의존성 주입
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        Resource[] res = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/*.xml");

        return sessionFactory.getObject();
    }
}
