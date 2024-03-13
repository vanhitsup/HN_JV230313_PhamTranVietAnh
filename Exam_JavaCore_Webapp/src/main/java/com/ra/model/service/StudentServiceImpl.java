package com.ra.model.service;

import com.ra.model.dao.StudentDao;
import com.ra.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Repository
public class StudentServiceImpl implements StudentService{
   @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        return studentDao.saveOrUpdate(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
    studentDao.delete(id);
    }
}
