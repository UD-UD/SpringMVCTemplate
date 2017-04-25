package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;

import java.util.List;
import java.util.Set;

/**
 * Created by ud on 25/4/17.
 */
public interface ManytoManyDao {
    ModelForManyToManyUnidirectional findById(int id);

    /*ModelForManyToManyUnidirectional findByName(String sso);
    void save(ModelForManyToManyUnidirectional sampleModel);

    void deleteByName(String sso);*/

    List<ModelForManyToManyUnidirectional> findAllSampleModel();

}
