package me.nadetdev.petowner;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import me.nadetdev.petowner.service.owner.OwnerServiceImpl;
import me.nadetdev.petowner.utils.PetOwnerCliClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Application {
    private final PetOwnerCliClient petOwnerCliClient;

    public Application(PetOwnerCliClient petOwnerCliClient) {
        this.petOwnerCliClient = petOwnerCliClient;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    void init(StartupEvent event){
        petOwnerCliClient.performDatabaseOperations();
    }
}