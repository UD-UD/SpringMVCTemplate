package com.template.springMVCtemplate.model.mappingExample;

import javax.persistence.*;

/**
 * Created by ud on 19/4/17.
 * this class can be used for many to one relationship also.Suppose many SampleModel is reffering to
 * one object of this class
 */
@Entity
@Table(name="MODEL_ONE_TO_ONE_FOREIGN_KEY")
public class ModelForOneToOneForiegnKey {

    @Id
    @GeneratedValue
    @Column(name = "FOREIGN_ID")
    private int id;

    @Column
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelForOneToOneForiegnKey)) return false;

        ModelForOneToOneForiegnKey that = (ModelForOneToOneForiegnKey) o;

        if (getId() != that.getId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ModelForOneToOneForiegnKey{" +
                "id=" + id +
                '}';
    }
}
