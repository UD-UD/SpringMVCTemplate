package com.template.springMVCtemplate.services;

import com.template.springMVCtemplate.dao.SampleDao;
import com.template.springMVCtemplate.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ud on 21/4/17.
 */
@Service("sampleModelService")
public class SampleModelServiceIMPL implements SampleModelService {

    @Autowired
    private SampleDao dao;  //Bean of SampleDaoImpl is autowired here

    @Override
    public SampleModel findById(int id) {
        return dao.findById(id);
    }

    @Override
    public SampleModel findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<SampleModel> findAll() {
        return dao.findAllSampleModel();
    }
}