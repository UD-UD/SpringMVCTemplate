package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by ud on 25/4/17.
 */
@Repository("manyTomanyDao")
public class ManytoManyDaoImpl extends AbstractDao<Integer,ModelForManyToManyUnidirectional> implements ManytoManyDao {
    @Override
    public List<ModelForManyToManyUnidirectional> findAllSampleModel() {
        Criteria criteria=createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //pulls only distinct objects
        List<ModelForManyToManyUnidirectional> list= (List<ModelForManyToManyUnidirectional>)criteria.list();
        return list;
    }

    @Override
    public ModelForManyToManyUnidirectional findById(int id) {
        ModelForManyToManyUnidirectional modelForManyToManyUnidirectional=getByKey(id);
        return modelForManyToManyUnidirectional;
    }
}
