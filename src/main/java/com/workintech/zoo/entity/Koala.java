package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Koala {
    private int id;
    private String name;
    private double sleepHour;
    private double weight;
    private String gender;
}
