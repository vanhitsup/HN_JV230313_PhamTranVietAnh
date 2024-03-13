package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
@PropertySource("classpath:application.properties")
@Controller
public class HomeController {
    @Value("${path}")
    private String path;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/")
    public String home(Model model){
        List<Student> students=studentService.getAll();
        model.addAttribute("students",students);
        return "home";
    }
    @GetMapping("/")
    public String index(Model model){
        List<Student> students=studentService.getAll();
        model.addAttribute("students",students);
        return "home";
    }

    @GetMapping("/add-student")
    public String add(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "add";
    }

    @PostMapping("/add-student")
    public String create(@ModelAttribute("student") Student student,
                         @RequestParam("image_url") MultipartFile file){
        String image_url=file.getOriginalFilename();
        File destination= new File(path+"/"+image_url);
        try {
            Files.write(destination.toPath(),file.getBytes(), StandardOpenOption.CREATE);
            student.setImage_url(image_url);
        }
        catch (IOException e){
            throw  new RuntimeException();
        }

        studentService.saveOrUpdate(student);
        return "redirect:/home";
    }
    @GetMapping("/delete-student/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("success","Xoa thanh cong !");
        studentService.delete(id);
        return "redirect:/home";
    }
}
