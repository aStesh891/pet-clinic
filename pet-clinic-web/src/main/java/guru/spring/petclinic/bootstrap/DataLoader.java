package guru.spring.petclinic.bootstrap;

import guru.spring.petclinic.model.Owner;
import guru.spring.petclinic.model.Pet;
import guru.spring.petclinic.model.PetType;
import guru.spring.petclinic.model.Vet;
import guru.spring.petclinic.services.OwnerService;
import guru.spring.petclinic.services.PetTypeService;
import guru.spring.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) throws Exception {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Owner owner1 = new Owner();
    owner1.setFirstName("Anna");
    owner1.setLastName("Steshenko");
    owner1.setAddress("132 Brickerel");
    owner1.setCity("Dnipro");
    owner1.setTelephone("380507078804");

    Pet annsPet = new Pet();
    annsPet.setPetType(savedDogPetType);
    annsPet.setOwner(owner1);
    annsPet.setBirthDay(LocalDate.now());
    annsPet.setName("Oscar");

    owner1.getPets().add(annsPet);
    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Irina");
    owner2.setLastName("Glenanne");
    owner2.setAddress("132 Brickerel");
    owner2.setCity("Dnipro");
    owner2.setTelephone("380507078804");

    Pet irinasPet = new Pet();
    irinasPet.setPetType(savedCatPetType);
    irinasPet.setOwner(owner2);
    irinasPet.setBirthDay(LocalDate.now());
    irinasPet.setName("Mart");

    owner2.getPets().add(irinasPet);
    ownerService.save(owner2);

    System.out.println("Loaded Owners....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Oscar");
    vet2.setLastName("Porter");

    vetService.save(vet2);

    System.out.println("Loaded Vets....");
  }
}
