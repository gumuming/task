package com.gim.controller;

import com.gim.entity.ApiUserInfo;
import com.gim.entity.Student;
import com.gim.repo.DemoDao;
import com.gim.repo.StudentRepo;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        ApiUserInfo userInfo = demoDao.getUserInfo(10L);
        System.out.println(userInfo);
        return "success";
    }


    @PostMapping("/list")
    public void list(@ApiParam(name = "pageSize", value = "每页大小") @RequestParam(name = "pageSize") Integer pageSize,
                     @ApiParam(name = "pageNum", value = "pageNum") @RequestParam(name = "pageNum") Integer pageNum,
                     Student student){

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "id");
        Page<Student> bookPage1 = studentRepo.findAll((Specification<Student>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(!StringUtils.isEmpty(student.getClassName())){
                list.add(criteriaBuilder.like(root.get("className").as(String.class), "%"+student.getClassName()));
            }
            if(!StringUtils.isEmpty(student.getName())){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+student.getName()));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, pageable);
        final List<Student> content = bookPage1.getContent();
        content.forEach(System.out::println);

    }

}
