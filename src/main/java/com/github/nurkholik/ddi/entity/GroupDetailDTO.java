package com.github.nurkholik.ddi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDetailDTO {
    int id;
    String name;
    String image_link;
    String detail;
}
