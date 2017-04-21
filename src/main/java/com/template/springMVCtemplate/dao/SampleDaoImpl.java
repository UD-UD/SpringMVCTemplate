package com.template.springMVCtemplate.dao;

import com.template.springMVCtemplate.model.SampleModel;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ud on 20/4/17.
 * @Repository marks this class as a repository bean and is autowired in corresponding service class
 * or anywhere else it is required.
 *
 * Hibernate.initialize() : If you are lazy-loading a collection normally, but for a particular use,
 * you need to ensure the collection has been loaded before the session is closed,
 * you can use Hibernate.initialize(Object obj) as you noted.
 */

@Repository("userDao")
public class SampleDaoImpl extends AbstractDao<Integer,SampleModel> implements SampleDao {
    @Override
    public SampleModel findById(int id) {
        SampleModel sampleModel=getByKey(id);
        if(sampleModel!=null){
            Hibernate.initialize(sampleModel.getManyUnidirectionals());
        }
        return sampleModel;
    }

    @Override
    public SampleModel findByName(String sso) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("sso",sso));
        SampleModel sampleModel=(SampleModel)criteria.uniqueResult();
        if(sampleModel!=null){
            Hibernate.initialize(sampleModel.getManyUnidirectionals());
        }
        return sampleModel;
    }

    @Override
    public void save(SampleModel sampleModel) {
        persist(sampleModel);
    }

    @Override
    public void deleteByName(String sso) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("sso",sso));
        SampleModel sampleModel=(SampleModel)criteria.uniqueResult();
        if(sampleModel!=null){
            Hibernate.initialize(sampleModel.getManyUnidirectionals());
        }
        delete(sampleModel);
    }

    @Override
    public List<SampleModel> findAllSampleModel() {
        Criteria criteria=createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); //pulls only distinct objects
        List<SampleModel> sampleModels= (List<SampleModel>)criteria.list();
        return sampleModels;
    }
}
