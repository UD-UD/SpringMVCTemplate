package com.template.springMVCtemplate.services;

import com.template.springMVCtemplate.model.SampleModel;

import java.util.List;

/**
 * Created by ud on 21/4/17.
 *
 * The service module is basically the business logic layer (BLL).The controllers in controller
 * module will access this module to perform business logic.
 *
 * This module will further communicated with tha DAO module for data persistence an data retrieval.
 */
public interface SampleModelService {
    SampleModel findById(int id);

    SampleModel findByName(String type);

    List<SampleModel> findAll();

    void saveModel(SampleModel sampleModel);

}
