package com.example.springpractice.jpaShop.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.name = :name and s.age = :age")
    List<Student> findStudentCustom(@Param("name") String studentName, @Param("age") int age);

    @Query("select s.name from Student s")
    List<String> findStudentNameList();

    @Query("select new com.example.springpractice.jpaShop.domain.StudentDto(s.id, s.name, s.age, c.name) " +
            "from Student s join s.classRoom c")
    List<StudentDto> findStudentDto();

    @Query("select s from Student s where s.name like %:studentName%")
    List<Student> findByStudentName(@Param("studentName") String studentName);

    @Query("select s from Student s where s.name in :names")
    List<Student> findByStudentNames(@Param("names") List<String> names);

    Page<Student> findByAge(int age, Pageable pageable);

    Slice<Student> findSliceByAge(int age, Pageable pageable);

    @Query(value = "select s from Student s join s.classRoom c where s.age = :age",
    countQuery = "select count(s) from Student s where s.age = :age")
    Page<Student> findWithCountQueryByAge(Integer age, PageRequest pageRequest);

    /**
     * 학생들의 반을 일괄 수정
     * update 문 작성 시, @Modifying 필수
     * @param idList
     * @param newClassId
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.classRoom.id = :newClassId where s.id in :idList")
    Integer updateClassByIdList(List<Long> idList, Long newClassId);

    @Query("select s from Student s left join fetch s.classRoom where s.id = :id")
    Optional<Student> findWithFetchById(@Param("id") Long id);

    @EntityGraph(attributePaths = {"classRoom"})
    Optional<Student> findWithEntityGraphById(Long id);
}
