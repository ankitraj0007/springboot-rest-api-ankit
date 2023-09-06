package com.learnwithankit.springbootrestapiankit.controller;

import com.learnwithankit.springbootrestapiankit.bean.Student;
import org.apache.el.util.ReflectionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping()
    public Student getStudent(){
        return new Student(1,"ankit", "raj");
    }

    //using response entity
    @GetMapping("responseEntity")
    public ResponseEntity<Student> getStudentResponseEntity(){
        Student student = new Student(1,"ankit", "raj");
//        return new ResponseEntity<>(student, HttpStatus.OK);
//        return ResponseEntity.ok(student);//same as above
        return ResponseEntity.ok().header("customHeader","ankit").body(student);//with header
    }

    @GetMapping("all")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"ankit", "raj"));
        students.add(new Student(2,"ankit", "raj"));
        students.add(new Student(3,"ankit", "raj"));

        return students;
    }

    //http://localhost:8080/student/1/ankit
    //path variable.  {id} -> uri template variable
    @GetMapping("{id}/{first-name}")
    public Student getStudentWithIdPathParam(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName){
        return new Student(studentId,firstName, "raj");
    }

    //http://localhost:8080/student/param?id=1&lastName=raj
    //query param
    @GetMapping("param")
    public Student getStudentQueryParam(@RequestParam int id, @RequestParam String lastName){
        return new Student(id,"ankit", lastName);
    }

    //create
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student);
        return student;
    }

    //update
    @PutMapping("update")
    public Student updateStudent(@RequestBody Student student){
        System.out.println(student);
        return student;
    }

    //partial update
    @PatchMapping("{id}/patch")
    public Student patchStudent(@PathVariable int id, @RequestBody Map<String,String> fields){
        Student student = new Student(id,"ankit", "raj");
        System.out.println(student);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Student.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, student, value);
        });

        return student;
    }

    //delete
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        System.out.println(id);
        return ResponseEntity.ok("student deleted successfully");
    }
}
