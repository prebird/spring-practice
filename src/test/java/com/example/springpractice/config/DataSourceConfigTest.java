package com.example.springpractice.config;

import com.example.springpractice.SpringPracticeApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DataSourceConfigTest {

    @Test
    void addDataSourceBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringPracticeApplication.class);
        DataSource dataSource = context.getBean(DataSource.class);
        assertThat(dataSource).isNotNull();
    }
}
