package com.ederfmatos.pokedex.service.impl;

import com.ederfmatos.pokedex.exception.enumeration.NotFoundErrorEnum;
import com.ederfmatos.pokedex.model.Pokemon;
import com.ederfmatos.pokedex.repository.PokemonRepository;
import com.ederfmatos.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Override
    public Flux<Pokemon> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Pokemon> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(NotFoundErrorEnum.POKEMON_NAO_ENCONTRADO.getException()));
    }

    @Override
    public Mono<Pokemon> save(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    @Override
    public Mono<Pokemon> update(String id, Pokemon pokemon) {
        return findById(id)
                .flatMap(existingPokemon -> repository.save(existingPokemon
                        .withNome(pokemon.getNome())
                        .withTipo(pokemon.getTipo())
                        .withHabilidades(pokemon.getHabilidades())
                        .withPeso(pokemon.getPeso())))
                .switchIfEmpty(Mono.error(NotFoundErrorEnum.POKEMON_NAO_ENCONTRADO.getException()));
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.existsById(id)
                .and((a) -> repository.deleteById(id))
                .switchIfEmpty(Mono.error(NotFoundErrorEnum.POKEMON_NAO_ENCONTRADO.getException()));
    }
}
