package com.template.springMVCtemplate.model.mappingExample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.springMVCtemplate.model.SampleModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by ud on 19/4/17.
 * @GenericGenerator : ensures that id value of ModelForOneToOneBidirectional property value will be taken from the id
 * of SampleModel table , Or else we have to it manually,As done in ONE-TO-ONE mapping
 *
 * @JasonIgnore : It ignore the field annotated with which helps in Json parsing. If not given this annotation json
 * parser will go in a infinite loop parsing each other.here it will parse Samplemodel.In SampleModel class it will
 * parse ModelForOneToOneBidirectional.Thus going in an infinite loop.
 *
 * @JsonManagedReference -> Manages the forward part of the reference and the fields marked by this annotation are the ones that get Serialised
 * @JsonBackReference -> Manages the reverse part of the reference and the fields/collections marked with this annotation are not serialised.
 * Use case: You have a one-many or many-many relationships in your entities/tables and not using the above would lead to errors like
 *
 * Infinite Recursion and hence stackoverflow - > Could not write content: Infinite recursion (StackOverflowError)
 * The above errors occurs because Jackson (or someother similiar) tries to serialise both ends of the relationship and ends up in a recursion.
 *
 * @JsonIgnore performs similiar functions but the above mentioned annotations are preferable.

 */
@Entity
@Table(name = "MODEL_ONE_TO_ONE_BIDIRECTIONAL")
public class ModelForOneToOneBidirectional {

    @Id
    @Column
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign",parameters=@Parameter(name="property", value="sampleModel"))
    private int id;

    @Column
    private String name;

    /**
     * As in SampleModel we have initialized a one to one mapping and initialized the mappedBy attribute to sampleModel,Hibernate
     * will come here and search for the given property in mappedBy(sampleModel) and validate the relationship.By the
     * annotations @OneToOne and @PrimaryKeyJoinColumn we ask hibernate the both tables shares same primarykey.
     */
    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn
    public SampleModel sampleModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SampleModel getSampleModel() {
        return sampleModel;
    }

    public void setSampleModel(SampleModel sampleModel) {
        this.sampleModel = sampleModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelForOneToOneBidirectional)) return false;

        ModelForOneToOneBidirectional that = (ModelForOneToOneBidirectional) o;

        if (getId() != that.getId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getSampleModel() != null ? getSampleModel().equals(that.getSampleModel()) : that.getSampleModel() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSampleModel() != null ? getSampleModel().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ModelForOneToOneBidirectional{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sampleModel=" + sampleModel +
                '}';
    }
}
