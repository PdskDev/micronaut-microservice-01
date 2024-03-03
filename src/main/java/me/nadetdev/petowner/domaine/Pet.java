package me.nadetdev.petowner.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private LocalDate birthDate;

   @ManyToOne
   @JoinColumn(name = "type_id")
    private PetType type;

   @ManyToOne
   @JoinColumn(name = "owner_id")
   private Owner owner;

   @OneToMany(mappedBy = "pet", orphanRemoval = true, cascade = CascadeType.ALL)
   private Set<Visit> visits = new HashSet<>();

    public Pet() {
    }

    public Pet(String name, LocalDate birthDate, PetType type, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwnerId(Owner owner) {
        this.owner = owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public Pet name(String name) {
        this.name = name;
        return this;
    }

    public Pet birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Pet visits(Set<Visit> visits) {
        this.visits = visits;
        return this;
    }

    public Pet addVisit(Visit visit) {
        this.visits.add(visit);
        visit.setPet(this);
        return this;
    }

    public Pet removeVisit(Visit visit) {
        this.visits.remove(visit);
        visit.setPet(null);
        return this;
    }

    public Pet type(PetType petType) {
        this.type = petType;
        return this;
    }

    public Pet owner(Owner owner) {
        this.owner = owner;
        return this;
    }
}
