package com.ederfmatos.pokedex.controller;

import com.ederfmatos.pokedex.model.Pokemon;
import com.ederfmatos.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@RestController("pokemon")
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Pokemon> findAll() {
        return pokemonService.findAll().sort(Comparator.comparingInt(Pokemon::getNumero));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Pokemon> findById(@PathVariable("id") String id) {
        return pokemonService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> save(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Pokemon> update(@PathVariable String id, @RequestBody Pokemon pokemon) {
        return pokemonService.update(id, pokemon);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return pokemonService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return pokemonService.delete(id);
    }

}
