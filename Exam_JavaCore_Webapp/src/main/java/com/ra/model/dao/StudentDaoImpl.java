package com.ra.model.dao;

import com.ra.model.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentDaoImpl implements StudentDao{
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Student> getAll() {
        List<Student> students=new ArrayList<>();
        Session session=sessionFactory.openSession();
        try{
            students=session.createQuery("from Student ",Student.class).list();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return students;
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        Session session=sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e){
      session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
        return false;
    }

    @Override
    public Student findById(Integer id) {
        Session session=sessionFactory.openSession();
        try {

        }
        catch (Exception e){
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
