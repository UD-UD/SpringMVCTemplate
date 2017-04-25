package com.template.springMVCtemplate.services;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;

import java.util.List;
import java.util.Set;

/**
 * Created by ud on 25/4/17.
 */
public interface MamytoManyServices {

    ModelForManyToManyUnidirectional findById(int id);

    /*ModelForManyToManyUnidirectional findByName(String type);*/

    List<ModelForManyToManyUnidirectional> findAll();

    //void saveModel(ModelForManyToManyUnidirectional ModelForManyToManyUnidirectional);
}
