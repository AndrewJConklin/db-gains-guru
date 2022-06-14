package com.gainsguru.gainsgurudb.entry;

import com.gainsguru.gainsgurudb.entry.Entry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
}