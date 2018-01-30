package com.suleymancan.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LIKES")


public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @NotEmpty
    @Column(name="USERNAME")
    private String username;

    @NotNull
    @Column(name="ENTRY_ID")
    private Integer entryId;
}
