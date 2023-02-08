package br.com.nicolas.checkbitcoin.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.checkbitcoin.data.remote.BitcoinDataSource
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.model.CoinList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataSource: BitcoinDataSource
) : ViewModel() {

    private val _state = MutableLiveData<HomeFragmentState>()
    val state: LiveData<HomeFragmentState> get() = _state

    private var localCoins: Coin? = null
        set(value) {
            field = value
            value?.let { checkIsHasSameCoins(it) }
        }

    fun interact(event: HomeFragmentEvent) {
        when (event) {
            HomeFragmentEvent.OnOpened,
            HomeFragmentEvent.OnSwipeRefresh,
            HomeFragmentEvent.OnSendAgain -> {
                loadCoins()
            }
        }
    }

    fun saveCoin(newCoin: Coin) = viewModelScope.launch { localCoins = newCoin }

    private fun checkIsHasSameCoins(newCoin: Coin) = viewModelScope.launch {
        dataSource.getAllCoinInDatabase().collect { myCoins ->
            val result = myCoins.coins.filter { it.name == newCoin.name }
            if(result.isEmpty()) dataSource.saveCoin(newCoin)
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