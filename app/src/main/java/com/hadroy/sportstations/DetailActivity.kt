package com.hadroy.sportstations

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.hadroy.sportstations.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
        private const val TAG = "DetailActivity"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.title = "Detail Product"
        }

        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Product>(EXTRA_PRODUCT, Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        }

        if (product != null) {
            binding.imgDetailPhoto.setImageResource(product.photo)
            binding.tvDetailName.text = product.name
            binding.tvDetailPrice.text = product.price
            binding.tvDetailHighlightTitle.text = "Highlight Product"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                binding.tvDetailHighlight.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
            binding.tvDetailHighlight.text = product.highlight
            binding.tvFeatureProduct.text = product.feature
            binding.tvProductNumber.text = "Product Number: ${product.number}"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}