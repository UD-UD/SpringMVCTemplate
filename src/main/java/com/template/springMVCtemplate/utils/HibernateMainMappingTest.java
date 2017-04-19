package com.template.springMVCtemplate.utils;

import com.template.springMVCtemplate.model.mappingExample.ModelForManyToManyUnidirectional;
import com.template.springMVCtemplate.model.mappingExample.ModelForOneToOne;
import com.template.springMVCtemplate.model.mappingExample.ModelForOneToOneBidirectional;
import com.template.springMVCtemplate.model.SampleModel;
import com.template.springMVCtemplate.model.mappingExample.ModelForOneToOneForiegnKey;
import org.hibernate.Session;

/**
 * Created by ud on 19/4/17.
 */
public class HibernateMainMappingTest {
    public static void main(String args[]){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

    //-------------------------------ONE TO ONE UNIDIRECTIONAL MAPPING------------------------------------
       /* ModelForOneToOne oneToOne=new ModelForOneToOne();
        SampleModel sampleModel=new SampleModel();
        oneToOne.setName("Ujjal is good");
        sampleModel.setName("Ujjal Dutta");
        session.persist(sampleModel);        //on persisting the object we generate the ID of the object which we can use in setting foriegn key of other table.
        oneToOne.setId(sampleModel.getId());
        sampleModel.setOneToOne(oneToOne);
        session.save(sampleModel);           //writing to data base
        session.getTransaction().commit();   //commiting changes
        session.close();                     //closeing the connection*/

     //---------------------------------ONE TO ONE BIDIRECTIONAL------------------------------------------
        ModelForOneToOne oneToOne=new ModelForOneToOne();
        SampleModel sampleModel=new SampleModel();
        ModelForOneToOneBidirectional oneToOneBidirectional=new ModelForOneToOneBidirectional();
        ModelForOneToOneForiegnKey oneToOneForiegnKey=new ModelForOneToOneForiegnKey();
        oneToOneForiegnKey.setName("Foriegn walla ujjal");
        sampleModel.setName("Ujjal Dutta");
        oneToOne.setName("Ujjal is good");
        session.persist(sampleModel);
        oneToOne.setId(sampleModel.getId());
        sampleModel.setOneToOne(oneToOne);
        oneToOneBidirectional.setName("Ujjal Dutta Majama che");
        sampleModel.setOneToOneBidirectional(oneToOneBidirectional);
        oneToOneBidirectional.setSampleModel(sampleModel);
        session.persist(oneToOneForiegnKey);  //Persisting foreign object
        sampleModel.setOneToOneForiegnKey(oneToOneForiegnKey);
                 //writing to data base

    //-----------------------------------MANY TO MANY--------------------------------------
        ModelForManyToManyUnidirectional modelForManyToManyUnidirectional=new ModelForManyToManyUnidirectional();
        ModelForManyToManyUnidirectional modelForManyToManyUnidirectional1=new ModelForManyToManyUnidirectional();
        ModelForManyToManyUnidirectional modelForManyToManyUnidirectional2=new ModelForManyToManyUnidirectional();

        modelForManyToManyUnidirectional.setName("Ujjal");
        modelForManyToManyUnidirectional1.setName("Ujjal1");
        modelForManyToManyUnidirectional2.setName("Ujjal3");
        sampleModel.getManyUnidirectionals().add(modelForManyToManyUnidirectional);
        sampleModel.getManyUnidirectionals().add(modelForManyToManyUnidirectional2);
        sampleModel.getManyUnidirectionals().add(modelForManyToManyUnidirectional2);

        SampleModel sampleModel2=new SampleModel();
        sampleModel2.getManyUnidirectionals().add(modelForManyToManyUnidirectional);
        sampleModel2.getManyUnidirectionals().add(modelForManyToManyUnidirectional2);
        sampleModel2.getManyUnidirectionals().add(modelForManyToManyUnidirectional2);
        session.save(sampleModel);
        session.save(sampleModel2);
        session.getTransaction().commit();   //commiting changes
        session.close();                     //closeing the connection*/
    }
}
