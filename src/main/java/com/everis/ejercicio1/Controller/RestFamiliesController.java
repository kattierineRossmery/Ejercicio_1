package com.everis.ejercicio1.Controller;

import java.util.List;

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

import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.FamilyMembers;
import com.everis.ejercicio1.service.IFamiliesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Families microservice", tags = "This API has a CRUD for families")
@RequestMapping("/api/v1/families")
public class RestFamiliesController {

  @Autowired
  private IFamiliesService serv;

  /**
   * GET - List of the Families.
   * @return list Families.
   */
  @ApiOperation(value = "Return list of family")
  @GetMapping
  public ResponseEntity<List<Families>> listar() {

    return new ResponseEntity<List<Families>>(serv.list(), HttpStatus.OK);

  }

  /**
   * List the members of the family by id.
   * @param family_id
   * @return a list of the members of that family.
   */
  @ApiOperation(value = "Return list of family by id members")
  @GetMapping(value = "/{family_id}/members")
  public ResponseEntity<List<FamilyMembers>> listarMembersId(@PathVariable("family_id") Integer family_id) {

    return new ResponseEntity<List<FamilyMembers>>(serv.findByFamiliesFamily_id(family_id), HttpStatus.OK);

  }

  /**
   * this function is responsible for making a record of a 
   * family.
   * @param fam 
   * @return object.
   */
  @ApiOperation(value = "Create new family")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Families> insertar(@RequestBody Families fam) {
    
    return new ResponseEntity<Families>(serv.create(fam), HttpStatus.CREATED);
  }

  /**
   * this function is responsible for updating an existing record.
   * @param fam the Families.
   * @return modified object.
   */
  @ApiOperation(value = "Update family")
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public void modificar(@RequestBody Families fam) {

    serv.update(fam);
  }

  /**
   * this function is responsible for deleting an existing record.
   * @param id
   */
  @ApiOperation(value = "Delete family by id")
  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable("id") Integer id) {
    serv.delete(id);
  }
}
