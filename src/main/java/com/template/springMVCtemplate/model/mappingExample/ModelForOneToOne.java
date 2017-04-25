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
 * CascadeType.ALL : It persist both data parent and dependent.
 * CascadeType.PERSISTENT : Same As above
 * CascadeType.Merge : Does not persist child if child already present.
 *
 * for reference , look here : http://docs.oracle.com/javaee/6/api/javax/persistence/CascadeType.html
 *
 * CascadeType.PERSIST : means that save() or persist() operations cascade to related entities.
 * CascadeType.MERGE : means that related entities are merged when the owning entity is merged.
 * CascadeType.REFRESH : does the same thing for the refresh() operation.
 * CascadeType.REMOVE : removes all related entities association with this setting when the owning entity is deleted.
 * CascadeType.DETACH : detaches all related entities if a “manual detach” occurs.
 * CascadeType.ALL : is shorthand for all of the above cascade operations.

 *
 * **/
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
