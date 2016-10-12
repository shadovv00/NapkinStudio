package com.napkinstudio.dao;

import com.napkinstudio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User1 on 20.07.2016.
 */
@Repository
public interface IUserDao extends JpaRepository<User,Integer> {


    public List<User> findAllByFirstName(@Param(value = "firstName")String firstName);

    public List<User> findAllByLastName(@Param(value = "lastName")String lastName);

    public User findByLogin(@Param("login")String login);

    public void deleteByLogin(String login);

    public void deleteById(Integer userId);

   public User findByEmail(@Param(value = "email")String email);

//    @Modifying
//    public void deactivateById(Integer userId);
//
//    @Modifying
//    public void activateById(Integer userId);

//    public User findByEmail(@Param(value = "email")String email);
}
