package com.suleymancan.myblog.repository;

import com.suleymancan.myblog.model.Entry;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Component
@Transactional
public interface EntryRepository extends CrudRepository<Entry,Integer> {

    @Query("Select e FROM Entry e")
    List<Entry> findAllEntries();

    @Query ("Select e FROM Entry e Where id=:id")
    Entry findEntryById(@Param("id") Integer id);

    @Modifying
    @Query ("Delete  FROM Entry e Where id=:id ")
    void deleteEntryById(@Param("id") Integer id);

    @Query("SELECT e FROM Entry e where e.title LIKE %:title%")
    List<Entry> findByTitle(@Param("title") String title);

    List<Entry> findByCreateDate(LocalDate localDate);

}

