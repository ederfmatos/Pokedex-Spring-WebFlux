package com.ederfmatos.pokedex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@With
@NoArgsConstructor
public class Pokemon {

    @Id
    private String id;

    private int numero;
    private String nome;
    private List<Integer> tipo;
    private List<String> habilidades;
    private double peso;
    private String imagem;

    public Pokemon(int numero, String nome, Integer tipo, String habilidade, double peso, String imagem) {
        this(null, numero, nome, List.of(tipo), List.of(habilidade), peso, imagem);
    }

}
