package com.gainsguru.gainsgurudb.set;

import java.util.Map;
import java.util.HashMap;

import main.java.com.gainsguru.gainsgurudb.set.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/set")
public class SetController {
  @Autowired
  private SetService setService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Set>> list() {
    Iterable<Set> sets = setService.list();
    return createHashPlural(sets);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Set> read(@PathVariable Long id) {
    Set set = setService
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No set with that ID"));
    return createHashSingular(set);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Set> create(@Validated @RequestBody Set set) {
    Set createdSet = setService.create(set);
    return createHashSingular(createdSet);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Set> update(@RequestBody Set set, @PathVariable Long id) {
    Set updatedSet = setService
        .update(set)
        .orElseThrow(() -> new ResourceNotFoundException("No set with that ID"));

    return createHashSingular(updatedSet);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    setService.deleteById(id);
  }

  private Map<String, Set> createHashSingular(Set set) {
    Map<String, Set> response = new HashMap<String, Set>();
    response.put("set", set);

    return response;
  }

  private Map<String, Iterable<Set>> createHashPlural(Iterable<Set> sets) {
    Map<String, Iterable<Set>> response = new HashMap<String, Iterable<Set>>();
    response.put("sets", sets);

    return response;
  }
}