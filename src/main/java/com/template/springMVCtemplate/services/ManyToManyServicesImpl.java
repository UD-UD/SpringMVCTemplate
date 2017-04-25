package com.template.springMVCtemplate.services;

import com.template.springMVCtemplate.dao.ManytoManyDao;
import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by ud on 25/4/17.
 */

@Service("manyToManyModelService")
@Transactional
public class ManyToManyServicesImpl implements MamytoManyServices{
    @Autowired
    ManytoManyDao manytoManyDao;

    @Override
    public List<ModelForManyToManyUnidirectional> findAll() {
        return manytoManyDao.findAllSampleModel();
    }

    @Override
    public ModelForManyToManyUnidirectional findById(int id) {
        return  manytoManyDao.findById(id);
    }
}
