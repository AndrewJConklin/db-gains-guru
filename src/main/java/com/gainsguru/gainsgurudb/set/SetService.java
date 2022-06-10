package com.gainsguru.gainsgurudb.set;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {
  @Autowired
  private SetRepository setRepository;

  public Iterable<Set> list() {
    return setRepository.findAll();
  }

  public Optional<Set> findById(Long id) {
    return setRepository.findById(id);
  }

  public Set create(Set set) {
    return setRepository.save(set);
  }

  public Optional<Set> update(Set set) {
    Optional<Set> foundSet = setRepository.findById(set.getId());

    if (foundSet.isPresent()) {
      Set updatedSet = foundSet.get();
      updatedSet.setExerciseID(set.getExerciseID());
      updatedSet.setDate(set.getDate());
      updatedSet.setReps(set.getReps());
      updatedSet.setWeight(set.getWeight());

      setRepository.save(updatedSet);
      return Optional.of(updatedSet);
    } else {
      return Optional.empty();
    }
  }

  public void deleteById(Long id) {
    setRepository.deleteById(id);
  }
}