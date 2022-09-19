package br.com.nicolas.checkbitcoin.ui.home

sealed class HomeFragmentEvent {
    object OnOpened : HomeFragmentEvent()
    object OnSendAgain : HomeFragmentEvent()
    object OnSwipeRefresh : HomeFragmentEvent()
}