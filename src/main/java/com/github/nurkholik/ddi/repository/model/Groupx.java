package com.github.nurkholik.ddi.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groupx {
    @Id
    int id;
    int category_id;
    String name;
    @Lob
    String detail;
    String image_link;
}