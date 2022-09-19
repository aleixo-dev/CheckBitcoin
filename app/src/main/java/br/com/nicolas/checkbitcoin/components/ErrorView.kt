package br.com.nicolas.checkbitcoin.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.nicolas.checkbitcoin.databinding.NetworkErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributes, defStyleAttr) {

    private val binding = NetworkErrorBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setupButtonAction(retry: OnClickListener) {
        binding.buttonRetry.run {
            setOnClickListener(retry)
        }
    }
}