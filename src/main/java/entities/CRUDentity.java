package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crud_entity")
@NamedQuery(name = "crud_entity.deleteAllRows", query = "DELETE from CRUDentity")

public class CRUDentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    public CRUDentity() {
    }

    public CRUDentity(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}