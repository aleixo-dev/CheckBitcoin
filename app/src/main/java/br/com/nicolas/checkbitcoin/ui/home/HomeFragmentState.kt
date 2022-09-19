package br.com.nicolas.checkbitcoin.ui.home

import br.com.nicolas.checkbitcoin.model.Coin

sealed class HomeFragmentState {
    object Loading : HomeFragmentState()
    data class Success(val coins: List<Coin>) : HomeFragmentState()
    data class Error(val message: String) : HomeFragmentState()
}