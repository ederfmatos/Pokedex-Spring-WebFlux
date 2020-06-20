package com.ederfmatos.pokedex.model;

import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@With
public class Pokemon {

    @Id
    private String id;
    private int number;
    private String name;
    private List<Integer> types;
    private List<String> skills;
    private double weight;
    private String image;

    private boolean test;

    public Pokemon() {
    }

    public Pokemon(String id, int number, String name, List<Integer> types, List<String> skills, double weight, String image) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.types = types;
        this.skills = skills;
        this.weight = weight;
        this.image = image;
    }

    public Pokemon(int number, String name, Integer types, String habilidade, double weight, String image) {
        this(null, number, name, List.of(types), List.of(habilidade), weight, image);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", types=" + types +
                ", skills=" + skills +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                '}';
    }
}
