package com.example.springpractice.configTest;

import com.example.springpractice.SpringPracticeApplication;
import com.example.springpractice.config.MyServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConfigurationTest {

    @Test
    @DisplayName("빈으로 등록된 MyServiceImplBean을 가져옵니다.")
    void getMyServiceImplBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringPracticeApplication.class);

        // 모든 빈 가져오기
        String[] beans = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beans));

        // 클래스에 해당하는 빈 꺼내기
        MyServiceImpl myService = context.getBean(MyServiceImpl.class);
        assertThat(myService).isNotNull();
    }
}
