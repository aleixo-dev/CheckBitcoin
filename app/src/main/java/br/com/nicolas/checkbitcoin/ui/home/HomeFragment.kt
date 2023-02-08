package br.com.nicolas.checkbitcoin.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.FragmentHomeBinding
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.ui.home.adapter.BitcoinAdapter
import br.com.nicolas.checkbitcoin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvent(HomeFragmentEvent.OnOpened)
        render()
        setupSwipeRefresh()
    }

    private fun render() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeFragmentState.Loading -> setupVisibilities(loading = true)
                is HomeFragmentState.Error -> setupError()
                is HomeFragmentState.Success -> setupView(state.coins)
            }
        }
    }

    private fun setupView(coins: List<Coin>) {
        setupRecyclerView(coins)
        binding.swipeRefresh.isRefreshing = false
    }

    private fun setupRecyclerView(coins: List<Coin>) {
        with(binding.recyclerViewBitcoinList) {
            setHasFixedSize(true)
            adapter = BitcoinAdapter(
                coins,
                onClickFavorite = {saveCoin(it) }
            )
        }
        setupVisibilities(mainContent = true)
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            setEvent(HomeFragmentEvent.OnSwipeRefresh)
        }
    }

    private fun setupError() {
        setupVisibilities(error = true)
        binding.errorViewHome.apply {
            setupButtonAction {
                setEvent(HomeFragmentEvent.OnSendAgain)
            }
        }
    }

    private fun saveCoin(newCoin: Coin){
        viewModel.saveCoin(newCoin)
    }

    private fun setupVisibilities(
        mainContent: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.apply {
            progressLoading.isVisible = loading
            swipeRefresh.isVisible = mainContent
            errorViewHome.isVisible = error
        }
    }

    private fun setEvent(newEvent: HomeFragmentEvent) {
        viewModel.interact(newEvent)
    }
}