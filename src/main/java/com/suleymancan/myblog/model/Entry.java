package com.suleymancan.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ENTRIES")
public class Entry {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(name = "TITLE")
    private String title;

    @NotEmpty
    @Column(name = "BODY")
    private String body;

    @NotNull
    @Column(name = "CREATE_DATE")
   // @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate = LocalDate.now();

    @NotEmpty
    @Column(name = "WRİTER")
    private String writer;


    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    //orphanRemoval, likeları da siler.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ENTRY_ID")
    List<Like> likes=new ArrayList<>();

    @ManyToMany
    private List<Category> categories;
}


