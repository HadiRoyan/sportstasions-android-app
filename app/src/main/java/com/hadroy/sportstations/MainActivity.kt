package com.hadroy.sportstations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadroy.sportstations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProducts.setHasFixedSize(true)

        list.addAll(getListProducts())
        showRecyclerList()
    }

    private fun showDetailSelectedProduct(product: Product) {
//        Toast.makeText(this, "Product yang dipilih ${product.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PRODUCT, product)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListProducts(): ArrayList<Product> {
        val dataName = resources.getStringArray(R.array.data_name_product)
        val dataHighlight = resources.getStringArray(R.array.data_highlight_product)
        val dataPrice = resources.getStringArray(R.array.data_price_product)
        val dataFeature = resources.getStringArray(R.array.data_feature_product)
        val dataNumber = resources.getStringArray(R.array.data_number_produce)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listProduct = ArrayList<Product>()

        for (i in dataName.indices) {
            val product = Product(
                dataName[i],
                dataPrice[i],
                dataHighlight[i],
                dataFeature[i],
                dataNumber[i],
                dataPhoto.getResourceId(i, -1)
            )

            listProduct.add(product)
        }
        return listProduct
    }

    private fun showRecyclerList() {
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(list)
        binding.rvProducts.adapter = listProductAdapter

        listProductAdapter.setOnItemClickCallback(object: ListProductAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Product) {
                showDetailSelectedProduct(data)
            }
        })
    }
}