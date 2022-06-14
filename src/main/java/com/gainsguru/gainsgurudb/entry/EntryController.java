package com.gainsguru.gainsgurudb.entry;

import java.util.Map;
import java.util.HashMap;

import com.gainsguru.gainsgurudb.entry.EntryNotFoundException.EntryNotFoundException;

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
@RequestMapping("api/entries")
public class EntryController {
  @Autowired
  private EntryService entryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Entry>> list() {
    Iterable<Entry> entries = entryService.list();
    return createHashPlural(entries);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Entry> read(@PathVariable Long id) {
    Entry entry = entryService
        .findById(id)
        .orElseThrow(() -> new EntryNotFoundException("No entry with that ID"));
    return createHashSingular(entry);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Entry> create(@Validated @RequestBody Entry entry) {
    Entry createdEntry = entryService.create(entry);
    return createHashSingular(createdEntry);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Entry> update(@RequestBody Entry entry, @PathVariable Long id) {
    Entry updatedEntry = entryService
        .update(entry)
        .orElseThrow(() -> new EntryNotFoundException("No entry with that ID"));

    return createHashSingular(updatedEntry);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    entryService.deleteById(id);
  }

  private Map<String, Entry> createHashSingular(Entry entry) {
    Map<String, Entry> response = new HashMap<String, Entry>();
    response.put("entry", entry);

    return response;
  }

  private Map<String, Iterable<Entry>> createHashPlural(Iterable<Entry> entries) {
    Map<String, Iterable<Entry>> response = new HashMap<String, Iterable<Entry>>();
    response.put("entries", entries);

    return response;
  }
}