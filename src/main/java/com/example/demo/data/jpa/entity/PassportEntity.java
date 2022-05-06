package com.example.demo.data.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "passport")
//@NamedEntityGraph(name = "passport-user-graph", attributeNodes = @NamedAttributeNode(value = "user"))
public class PassportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String UID;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", unique = true)
    private UserEntity user;
}