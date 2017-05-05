package com.template.springMVCtemplate.services;

import com.template.springMVCtemplate.model.UserProfile;

import java.util.List;

/**
 * Created by ud on 28/4/17.
 */
public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}