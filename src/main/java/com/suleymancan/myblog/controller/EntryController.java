package com.suleymancan.myblog.controller;

import com.suleymancan.myblog.model.Entry;
import com.suleymancan.myblog.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.xml.ws.Response;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/blogs")
public class EntryController {
    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("")
    public ResponseEntity<List<Entry>> listAllEntries(){
    List<Entry> entries=entryRepository.findAllEntries();
    return ResponseEntity.ok(entries);
    }

    @PostMapping("")
    //@RequestBody'nın olayı?
    public ResponseEntity<Entry> createNewEntry(@Validated @RequestBody Entry entry){
        entryRepository.save(entry);
        return ResponseEntity.ok(entry);
    }
    //her hangi bir url ile
    @PutMapping("")
    public ResponseEntity<Entry> updateEntry(@Validated @RequestBody Entry entry){
      Entry savedEntry=  entryRepository.save(entry);
      return ResponseEntity.ok(savedEntry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable("id") Integer id){
        Entry entry=entryRepository.findEntryById(id);
        return ResponseEntity.ok(entry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable("id") Integer id){
        entryRepository.deleteEntryById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/search")
    public ResponseEntity<List<Entry>> searchEntry(@NotEmpty @RequestParam("title") String title){
        List<Entry> entries=entryRepository.findByTitle(title);
        return ResponseEntity.ok(entries);
    }

}
