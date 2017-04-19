package com.template.springMVCtemplate.model.mappingExample;

import javax.persistence.*;

/**
 * Created by ud on 20/4/17.
 */
@Entity
@Table(name = "MODEL_MANY_TO_MANY")
public class ModelForManyToManyUnidirectional {
    @Id
    @GeneratedValue
    @Column(name="MANY_ID")
    private int id;

    @Column
    private String name;


    /**
     * To make this model as bidirectional we just add the folloeing code and nothing changes.
     *
     *  @ManyToMany(mappedBy="subjects")
     *  private List<Student> students = new ArrayList<Student>(); and its getter and setter.
     */

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
        if (!(o instanceof ModelForManyToManyUnidirectional)) return false;

        ModelForManyToManyUnidirectional that = (ModelForManyToManyUnidirectional) o;

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
        return "ModelForManyToManyUnidirectional{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
