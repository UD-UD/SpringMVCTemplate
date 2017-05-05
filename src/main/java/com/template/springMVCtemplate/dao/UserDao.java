package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.User;

import java.util.List;

/**
 * Created by ud on 28/4/17.
 */
public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
