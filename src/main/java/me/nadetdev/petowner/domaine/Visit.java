package me.nadetdev.petowner.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "visits", schema = "petowner")
public class Visit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @Column(name = "description")
    private String description;

    public Visit() {
    }

    public Visit(Pet pet, LocalDate visitDate, String description) {
        this.pet = pet;
        this.visitDate = visitDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visit visitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
        return this;
    }

    public Visit description(String description) {
        this.description = description;
        return this;
    }

    public Visit pet(Pet pet) {
        this.pet = pet;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit visit)) return false;
        return Objects.equals(getId(), visit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Visit.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pet=" + pet)
                .add("visitDate=" + visitDate)
                .add("description='" + description + "'")
                .toString();
    }
}
