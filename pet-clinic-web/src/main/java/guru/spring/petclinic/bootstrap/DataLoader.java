package guru.spring.petclinic.bootstrap;

import guru.spring.petclinic.model.*;
import guru.spring.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;
  private final VisitService visitService;

  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
    this.visitService = visitService;
  }

  @Override
  public void run(String... args) throws Exception {
    int count = petTypeService.findAll().size();
    if(count == 0){
      loadData();}
  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Speciality radiology = new Speciality();
    radiology.setDescription("Radiology");
    Speciality savedRadiology = specialtyService.save(radiology);

    Speciality surgery = new Speciality();
    surgery.setDescription("Surgery");
    Speciality savedSurgery = specialtyService.save(surgery);

    Speciality dentistry = new Speciality();
    dentistry.setDescription("dentistry");
    Speciality savedDentistry = specialtyService.save(dentistry);

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

    Visit catVisit = new Visit();
    catVisit.setPet(irinasPet);
    catVisit.setDate(LocalDate.now());
    catVisit.setDescription("Sneezy kitty");
    visitService.save(catVisit);

    System.out.println("Loaded Owners....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");
    vet1.getSpecialities().add(savedRadiology);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Oscar");
    vet2.setLastName("Porter");
    vet2.getSpecialities().add(savedDentistry);

    vetService.save(vet2);

    System.out.println("Loaded Vets....");
  }
}
