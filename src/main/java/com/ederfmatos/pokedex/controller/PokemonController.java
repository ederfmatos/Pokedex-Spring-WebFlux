package com.ederfmatos.pokedex.controller;

import com.ederfmatos.pokedex.model.Pokemon;
import com.ederfmatos.pokedex.service.PokemonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@RestController("pokemon")
@RequestMapping("/pokemons")
@Api(tags = "Pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list of pokemons"),
            @ApiResponse(code = 500, message = "Internal error"),
    })
    @ApiOperation("List of pokemons")
    public Flux<Pokemon> findAll() {
        return pokemonService.findAll().sort(Comparator.comparingInt(Pokemon::getNumero));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Search details of a pokemon")
    public Mono<Pokemon> findById(@PathVariable("id") String id) {
        return pokemonService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creating a Pokemon")
    public Mono<Pokemon> save(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update a pokemon")
    public Mono<Pokemon> update(@PathVariable String id, @RequestBody Pokemon pokemon) {
        return pokemonService.update(id, pokemon);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete all pokemon")
    public Mono<Void> deleteAll() {
        return pokemonService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a pokemon")
    public Mono<Void> delete(@PathVariable String id) {
        return pokemonService.delete(id);
    }

}
