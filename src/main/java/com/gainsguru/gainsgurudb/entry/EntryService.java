package com.gainsguru.gainsgurudb.entry;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
  @Autowired
  private EntryRepository entryRepository;

  public Iterable<Entry> list() {
    return entryRepository.findAll();
  }

  public Optional<Entry> findById(Long id) {
    return entryRepository.findById(id);
  }

  public Entry create(Entry entry) {
    return entryRepository.save(entry);
  }

  public Optional<Entry> update(Entry entry) {
    Optional<Entry> foundEntry = entryRepository.findById(entry.getId());

    if (foundEntry.isPresent()) {
      Entry updatedEntry = foundEntry.get();
      updatedEntry.setName(entry.getName());
      updatedEntry.setFat(entry.getFat());
      updatedEntry.setProtein(entry.getProtein());
      updatedEntry.setCarbs(entry.getCarbs());
      updatedEntry.setCalories(entry.getCalories());
      updatedEntry.setDate(entry.getDate());

      entryRepository.save(updatedEntry);
      return Optional.of(updatedEntry);
    } else {
      return Optional.empty();
    }
  }

  public void deleteById(Long id) {
    entryRepository.deleteById(id);
  }
}