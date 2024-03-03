package me.nadetdev.petowner.utils;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.domaine.Address;
import me.nadetdev.petowner.domaine.Owner;
import me.nadetdev.petowner.domaine.Pet;
import me.nadetdev.petowner.domaine.Visit;
import me.nadetdev.petowner.service.owner.OwnerService;
import me.nadetdev.petowner.service.pet.PetService;
import me.nadetdev.petowner.service.type.PetTypeService;
import me.nadetdev.petowner.service.visit.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class PetOwnerCliClient {
    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;
    private final PetTypeService petTypeService;

    private final Logger log = LoggerFactory.getLogger(PetOwnerCliClient.class);

    public PetOwnerCliClient(OwnerService ownerService, PetService petService, VisitService visitService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.visitService = visitService;
        this.petTypeService = petTypeService;
    }

    public void performDatabaseOperations(){
        performFindAll();
        Owner owner = performSave();
        performDelete(owner);
        performFindAll();
    }

    protected void performFindAll(){
        log.info("----------------------------------------------");
        log.info("Request to performFindAll");
        log.info("----------------------------------------------");
        Page<Owner> pOwners = ownerService.findAll(Pageable.unpaged());

        if(CollectionUtils.isNotEmpty(pOwners.getContent())){
            List<Owner> owners = pOwners.getContent();
            owners.forEach(owner ->{
                log.info("Owner: {}, {}", owner.getFirstName(), owner.getLastName());
            });
        }
    }

    protected void performFindOne(Long id){
        log.info("----------------------------------------------");
        log.info("Request to performFindOne for id: {}", id);
        log.info("----------------------------------------------");
        Optional<Owner> oOwner = ownerService.findOne(id);
        oOwner.ifPresent(owner -> log.info("Owner: {}, {}", owner.getFirstName(), owner.getLastName()));
    }

    protected  Owner performSave(){
        Owner owner = initOwner();
        log.info("----------------------------------------------");
        log.info("Request to performSave for owner: {}", owner);
        log.info("----------------------------------------------");
        return ownerService.save(owner);
    }

    protected void performDelete(Owner owner){
        log.info("----------------------------------------------");
        log.info("Request to performDelete for owner: {}", owner);
        log.info("----------------------------------------------");
        Set<Pet> pets = owner.getPets();

        if(CollectionUtils.isNotEmpty(pets)){
            for(Pet pet: pets) {
                Set<Visit> visits = pet.getVisits();
                if(CollectionUtils.isNotEmpty(visits)){
                    for(Visit visit: visits){
                        visitService.delete(visit.getId());
                    }
                }
                petService.delete(pet.getId());
            }
        }
    }

    private Owner initOwner(){
        Owner owner = new Owner();
        owner.setFirstName("Monsieur");
        owner.setLastName("Dev");

        Address address = new Address();
        address.setAddress("rue des programmeurs");
        address.setCity("CodeCity");
        address.setTelephone("0102030405");
        address.setUser(owner);

        owner.setAddress(address);

        Pet myPet = new Pet();
        myPet.setType(petTypeService.findAll(Pageable.unpaged()).getContent().get(1));
        myPet.setName("Petty");
        myPet.setBirthDate(LocalDate.of(2024, 01, 01));
        myPet.setOwner(owner);

        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Breathing problem");
        visit.setPet(myPet);

        return owner;
    }
}
