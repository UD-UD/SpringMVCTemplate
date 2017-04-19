package com.template.springMVCtemplate.model.mappingExample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ud on 19/4/17.
 *
 * Here @OneToOne mapping is used.The id generated for the parent object(SampleModel) is used as the id of ModelForOneToOne.
 * This Model has no knowledge of it's parent object.
 */
@Entity
@Table(name="MODEL_ONE_TO_ONE")
public class ModelForOneToOne {
    @Id
    @Column
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
    public String toString() {
        return "ModelForOneToOne{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelForOneToOne)) return false;

        ModelForOneToOne that = (ModelForOneToOne) o;

        if (getId() != that.getId()) return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
