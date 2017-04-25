package com.template.springMVCtemplate.converters;

import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;
import com.template.springMVCtemplate.services.MamytoManyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by ud on 25/4/17.
 */

@Component
public class ManyToManyFilter implements Converter<Object, ModelForManyToManyUnidirectional> {

    @Autowired
    MamytoManyServices mamytoManyServices;

    public ModelForManyToManyUnidirectional convert(Object o){
        Integer id=Integer.parseInt((String)o);
        ModelForManyToManyUnidirectional modelForManyToManyUnidirectional=mamytoManyServices.findById(id);
        return modelForManyToManyUnidirectional;
    }
}
