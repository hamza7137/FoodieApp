package com.niit.CustomerService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cuisine {

    @Id
    private String cuisineName;
    private String decription;
    private float price;
    private float rating;
    private  String image;


}
