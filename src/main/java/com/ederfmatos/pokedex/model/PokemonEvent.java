package com.ederfmatos.pokedex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class PokemonEvent {

    private long eventId;
    private String eventType;

}
