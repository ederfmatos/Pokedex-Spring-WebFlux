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
    private int numero;
    private String nome;
    private List<Integer> tipo;
    private List<String> habilidades;
    private double peso;
    private String imagem;

    public Pokemon() {
    }

    public Pokemon(String id, int numero, String nome, List<Integer> tipo, List<String> habilidades, double peso, String imagem) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.peso = peso;
        this.imagem = imagem;
    }

    public Pokemon(int numero, String nome, Integer tipo, String habilidade, double peso, String imagem) {
        this(null, numero, nome, List.of(tipo), List.of(habilidade), peso, imagem);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getTipo() {
        return tipo;
    }

    public void setTipo(List<Integer> tipo) {
        this.tipo = tipo;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
