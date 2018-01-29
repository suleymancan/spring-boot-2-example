package com.suleymancan.myblog.repository;

import com.suleymancan.myblog.model.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntryRepository extends CrudRepository<Entry,Integer> {

}

