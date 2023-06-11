package com.example.springpractice.jpaShop;

import com.example.springpractice.jpaShop.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class InitJpaData implements ApplicationListener<ContextRefreshedEvent> {

    private final InitService initService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initService.initStudent();
    }

    // 따로 클래스를 분리해서 Transactional을 달아줘야 flush 되어 변경사항이 반영되는 것 같다.
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final StudentRepository studentRepository;
        private final ClassRoomRepository classRoomRepository;
        public void initStudent() {
            // 학생
            Student jinju = Student.of("김진주", 10);
            Student jinju2 = Student.of("김진주", 20);
            Student jiyu = Student.of("김지유", 20);
            Student semi = Student.of("박세미", 10);
            Student hei = Student.of("최희령", 10);
            Student young = Student.of("김영희", 10);

            studentRepository.save(jinju);
            studentRepository.save(jinju2);
            studentRepository.save(jiyu);
            studentRepository.save(semi);
            studentRepository.save(hei);
            studentRepository.save(young);

            // 반
            ClassRoom java = ClassRoom.of("자바반");
            ClassRoom design = ClassRoom.of("디자인반");
            classRoomRepository.save(java);
            classRoomRepository.save(design);

            // 학생에 반 정보 매핑
            jinju.setClassRoom(java);
            jinju2.setClassRoom(design);
            jiyu.setClassRoom(java);
            semi.setClassRoom(java);
            hei.setClassRoom(design);
            young.setClassRoom(java);
        }
    }
}
