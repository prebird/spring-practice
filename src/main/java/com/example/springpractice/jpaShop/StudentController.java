package com.example.springpractice.jpaShop;

import com.example.springpractice.jpaShop.domain.StudentDto;
import com.example.springpractice.jpaShop.domain.StudentService;
import com.example.springpractice.jpaShop.dto.UpdateIdListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> searchAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    /**
     * 나이로 학생 조회, Page, Sort 기능 반영
     * http://localhost:8080/students/byAge?age=10&page=1&sortType=DESC
     * page, sorType nullable
     * @param age
     * @param page
     * @param sortType
     * @return
     */
    @GetMapping("/byAge")
    public ResponseEntity<Page<StudentDto>> searchByAge(
            @RequestParam Integer age,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Sort.Direction> sortType) {
        return ResponseEntity.ok(studentService.getByAge(age, page, sortType));
    }

    /**
     * 나이로 학생 조회, Slice 반환
     * http://localhost:8080/students/slice?age=10&page=1&sortType=DESC
     * @param age
     * @param page
     * @param sortType
     * @return
     */
    @GetMapping("/slice")
    public ResponseEntity<Slice<StudentDto>> searchByAgeSlice(
            @RequestParam Integer age,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Sort.Direction> sortType) {
        return ResponseEntity.ok(studentService.getByAgeSlice(age, page, sortType));
    }

    /**
     * 나이로 학생 조회, CountQuery 추가
     * http://localhost:8080/students/countQuery?age=10&page=0&sortType=DESC
     * @param age
     * @param page
     * @param sortType
     * @return
     */
    @GetMapping("/countQuery")
    public ResponseEntity<Page<StudentDto>> searchByAgeCountQuery(
            @RequestParam Integer age,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Sort.Direction> sortType) {
        return ResponseEntity.ok(studentService.getByAgeCountQuery(age, page, sortType));
    }

    /**
     * 학생들의 반을 일괄 변경함 (벌크 수정 쿼리)
     * {
     *   "idList": [1, 2, 3],
     *   "className" : "자바반"
     * }
     * @param request
     * @return
     */
    @PostMapping(value = "/updateClassByIdList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateClassByIdList(@RequestBody UpdateIdListRequest request) {
        studentService.updateClassByIdList(request.getIdList(), request.getClassName());
        return ResponseEntity.ok().build();
    }
}
