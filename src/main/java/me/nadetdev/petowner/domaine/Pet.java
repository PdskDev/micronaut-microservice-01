package me.nadetdev.petowner.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pets", schema = "petowner")
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "type_id")
    private Long typeId;

   @ManyToOne
   @JoinColumn(name = "owner_id")
   private Owner owner;

    public Pet() {
    }

    public Pet(String name, Date birthDate, Long typeId, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.typeId = typeId;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwnerId(Owner owner) {
        this.owner = owner;
    }
}
