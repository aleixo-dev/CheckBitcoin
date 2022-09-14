package br.com.nicolas.checkbitcoin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.FragmentHomeBinding
import br.com.nicolas.checkbitcoin.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel : HomeViewModel by viewModel()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}