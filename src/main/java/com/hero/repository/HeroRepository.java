package com.hero.repository;

import java.util.Collection;

import com.hero.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository  extends CrudRepository<Hero, Long>{
  Collection<Hero> findAll();

  Hero findByName(String heroName);

}
