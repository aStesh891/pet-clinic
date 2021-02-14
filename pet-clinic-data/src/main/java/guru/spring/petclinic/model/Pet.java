package guru.spring.petclinic.model;

import java.time.LocalDate;

public class Pet {

  private Owner owner;
  private PetType petType;
  private LocalDate birthDay;


  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public PetType getPetType() {
    return petType;
  }

  public void setPetType(PetType petType) {
    this.petType = petType;
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(LocalDate birthDay) {
    this.birthDay = birthDay;
  }
}
