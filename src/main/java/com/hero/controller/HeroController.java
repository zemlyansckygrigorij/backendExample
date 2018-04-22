package com.hero.controller;


import java.security.Principal;
import java.util.Collection;

import com.hero.model.Hero;
import com.hero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero")
public class HeroController{
  @Autowired
  private HeroRepository heroRepo;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Hero>> getHeroes() {
    return new ResponseEntity<>(heroRepo.findAll(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Hero> getHero(@PathVariable long id) {
    Hero hero = heroRepo.findOne(id);

    if (hero != null) {
      return new ResponseEntity<>(heroRepo.findOne(id), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(/*null,*/ HttpStatus.NOT_FOUND);

    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> addPerson(@RequestBody Hero hero) {
    return new ResponseEntity<>(heroRepo.save(hero), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteHero(@PathVariable long id, Hero hero) {
    Hero currentHero = heroRepo.findByName(hero.getName());

    if (currentHero.getId() == id) {
      heroRepo.delete(id);
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }
  }





}
