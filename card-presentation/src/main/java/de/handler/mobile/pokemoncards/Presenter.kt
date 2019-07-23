package de.handler.mobile.pokemoncards

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class Presenter(
    private val contract: Contract,
    private val repository: Repository
) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    var sorted = false

    fun loadPokemonCards() {
        launch {
            val pokemonCards = repository
                .getAllPokemonCardsAsync()
                .await()
                .filterNotNull()
            contract.onDisplayPokemonCards(pokemonCards)
        }
    }

    fun sortList(): Boolean {
        launch {
            val sortedList = repository
                .getAllPokemonCardsAsync()
                .await()
                .filterNotNull()
                .sortedBy { pokemonCard ->
                    when {
                        sorted -> pokemonCard.id
                        else -> pokemonCard.name
                    }
                }
            sorted = !sorted
            contract.onDisplayPokemonCards(sortedList)
        }
        return true
    }
}
