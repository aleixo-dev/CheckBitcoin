package br.com.nicolas.checkbitcoin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.checkbitcoin.data.repository.BitcoinDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataSource: BitcoinDataSource
) : ViewModel() {

    private val _state = MutableLiveData<HomeFragmentState>()
    val state: LiveData<HomeFragmentState> get() = _state

    fun interact(event: HomeFragmentEvent) {
        when (event) {
            HomeFragmentEvent.OnOpened,
            HomeFragmentEvent.OnSwipeRefresh,
            HomeFragmentEvent.OnSendAgain -> {
                loadCoins()
            }
        }
    }

    private fun loadCoins() = viewModelScope.launch {
        dataSource.getBitcoins()
            .onStart { setState(HomeFragmentState.Loading) }
            .catch {
                setState(HomeFragmentState.Error(it.message.toString()))
            }
            .collect {
                setState(HomeFragmentState.Success(it.coins))
            }
    }

    private fun setState(newState: HomeFragmentState) {
        _state.value = newState
    }
}