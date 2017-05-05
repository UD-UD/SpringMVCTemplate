package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.UserProfile;

import java.util.List;

/**
 * Created by ud on 28/4/17.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}