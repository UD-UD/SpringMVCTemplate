package com.template.springMVCtemplate.dao;


import com.template.springMVCtemplate.model.SampleModel;

import java.util.List;

/**
 * Created by ud on 20/4/17.
 *
 * Note : For every Model we will have a Dao interface and corresponding Dao implementation.
 * Here we will put all types of database queries we need for our implementation.
 *
 */
public interface SampleDao {
    SampleModel findById(int id);

    SampleModel findByName(String sso);
    void save(SampleModel sampleModel);

    void deleteByName(String sso);

    List<SampleModel> findAllSampleModel();
    void updateSample(SampleModel sampleModel);
}
