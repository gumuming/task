package com.gim.controller;

import com.gim.entity.ApiUserInfo;
import com.gim.entity.Student;
import com.gim.repo.DemoDao;
import com.gim.repo.StudentRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gim
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Resource
    private StudentRepo studentRepo;

    @Resource
    private DemoDao demoDao;

    @GetMapping("/deal")
    public String dealJpa(){

        Student build = Student.builder()
                .className("first class")
                .level(1)
                .sex(2)
                .type(1)
                .build();
        studentRepo.save(build);
        Student student = new Student();
        student.setId(build.getId());
        student.setName("gim");
        studentRepo.save(student);
        System.out.println(build);
        return "success";
    }

    @GetMapping("/userinfo")
    public String userinfo(){
        ApiUserInfo userInfo = demoDao.getUserInfo(7L);
        System.out.println(userInfo);
        return "success";
    }


}
