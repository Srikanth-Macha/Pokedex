package com.application.pokedex.screens.pokemons_list_screen.pagination

import com.application.pokedex.util.Response

class PaginatorImpl<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Response<Item>,
    private inline val getNextKey: suspend () -> Key,
    private inline val onError: (String?) -> Unit,
    private inline val onSuccess: (newKey: Key, item: Item?) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadMorePokemons() {
        if (isMakingRequest) {
            return
        }

        isMakingRequest = true
        onLoadUpdated(true)

        val response = onRequest(currentKey)
        isMakingRequest = false

        when (response) {
            is Response.Error -> {
                onError(response.error)
                onLoadUpdated(false)
                return
            }

            is Response.Success -> {
                currentKey = getNextKey()
                onSuccess(currentKey, response.data)
                onLoadUpdated(false)
            }

            else -> {
                onLoadUpdated(false)
            }
        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}