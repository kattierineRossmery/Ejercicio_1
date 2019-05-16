package com.everis.ejercicio1.service;

import com.everis.ejercicio1.models.FamilyMembers;
import java.util.List;
import java.util.Optional;


public interface IFamilyMembersService {

  FamilyMembers create(FamilyMembers familyMembers);

  FamilyMembers update(FamilyMembers familyMembers);

  void delete(int id);

  Optional<FamilyMembers> listId(int id);

  List<FamilyMembers> list();

}
