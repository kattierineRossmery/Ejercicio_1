package com.everis.ejercicio1.Controller;

import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.service.IParentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
@Api(value = "Parents microservice", tags = "This API has a CRUD for parents")
@RequestMapping("/api/v1/parents")

@ApiOperation(value = "Is Alive operation", 
    notes = "Return is the microservice is alive with a get operation returning the time")
@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
    @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    message = "INTERNAL ERROR SERVER"),
    @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
    @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "FORBIDDEN"),
    @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })

public class RestParentsController {

  @Autowired
  private IParentsService serv;
  
  /**
   * List of the Familie.
   * @return list Parents.
   */
  @ApiOperation(value = "Return list of parents")
  @GetMapping
  public ResponseEntity<List<Parents>> listar() {

    return new ResponseEntity<List<Parents>>(serv.list(), HttpStatus.OK);

  }
  
  /** 
   * this function is responsible for making a record of a 
   * Parents.
   * @param per 
   * @return object.
   */
  @ApiOperation(value = "Create new parent")
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, 
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Parents> insertar(@Valid @RequestBody Parents per) {

    Parents perCreated = serv.create(per);
    return new ResponseEntity<Parents>(perCreated, HttpStatus.CREATED);

  }
  
  /**
   * this function is responsible for updating an existing record.
   * @param per
   * @return modified object.
   */
  @ApiOperation(value = "Update parent")
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String modificar(@RequestBody Parents per) {
    String mensaje = "";
    Optional<Parents> obj = serv.listId(per.getParentId());

    if (obj.isPresent()) {
      serv.update(per);
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
  @ApiOperation(value = "Delete parent by id")
  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable("id") Integer id) {
    serv.delete(id);

  }

}
