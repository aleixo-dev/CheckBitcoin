package br.com.nicolas.checkbitcoin.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.ImageViewFormsBinding

class ImageViewForms @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    var image: Int? = null
        set(value) {
            field = value
            value?.let { setBackgroundImage(it) }
        }

    private val binding = ImageViewFormsBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.ImageViewForms, NORMAL_IMAGE, NORMAL_IMAGE)
            .use { style ->
                when (style.getInt(R.styleable.ImageViewForms_typeImage, 0)) {
                    ROUNDED_IMAGE -> {
                        image?.let { setBackgroundImage(it) }
                    }
                    else -> {}
                }
            }
    }

    private fun setBackgroundImage(drawable: Int) = binding.apply {
        binding.imageViewForms.background =
            ContextCompat.getDrawable(context, drawable)
    }

    companion object {
        const val NORMAL_IMAGE = 0
        const val ROUNDED_IMAGE = 1
    }
}