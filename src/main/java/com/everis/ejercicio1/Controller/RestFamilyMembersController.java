package com.everis.ejercicio1.Controller;

import com.everis.ejercicio1.models.FamilyMembers;
import com.everis.ejercicio1.service.IFamilyMembersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Family members microservice", description = "This API has a CRUD for family members")
@RequestMapping("/api/v1/familyMembers")
public class RestFamilyMembersController {

  @Autowired
  private IFamilyMembersService serv;

  /**
   * List of the FamilyMembers.
   * @return list of the FamilyMembers.
   */
  @ApiOperation(value = "Return list of family")
  @GetMapping
  public ResponseEntity<List<FamilyMembers>> listar() {

    return new ResponseEntity<List<FamilyMembers>>(serv.list(), HttpStatus.OK);

  }

  /**
   * this function is responsible for making a record of a FamilyMembers.
   * @param famMembers
   * @return object.
   */
  @ApiOperation(value = "Create new family")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void insertar(@RequestBody FamilyMembers famMembers) {
    serv.create(famMembers);
  }
  
  /**
   * this function is responsible for updating an existing record.
   * @param famMembers
   * @return modified object.
   */
  @ApiOperation(value = "Update family")
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String modificar(@RequestBody FamilyMembers famMembers) {
    String mensaje = "";
    Optional<FamilyMembers> obj = serv.listId(famMembers.getFamilyMemberId());

    if (obj.isPresent()) {
      serv.update(famMembers);
      mensaje = "Modificado con éxito!!";
    } else {
      mensaje = "Pariente no existe";
    }

    return mensaje;
  }

  /**
   * this function is responsible for deleting an existing record.
   * @param id
   */
  @ApiOperation(value = "Delete family members by id")
  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable("id") Integer id) {
    serv.delete(id);

  }

}
