package com.example.demoproductapinew.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproductapinew.R
import com.example.demoproductapinew.databinding.FragmentFavoritesBinding
import com.example.demoproductapinew.ui.favorites.adapters.ProductFavoriteAdapter
import com.example.demoproductapinew.ui.products.ProductDetailsActivity
import com.example.demoproductapinew.ui.roomdb.ItemDao
import com.example.demoproductapinew.ui.roomdb.ItemDatabase
import com.example.demoproductapinew.ui.roomdb.Product
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoritesFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var itemDao: ItemDao

    private var adapter: ProductFavoriteAdapter? = null

    private var allItems: List<Product>? = null

    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        executorService = Executors.newSingleThreadExecutor()

        executorService.execute {

            // Get a reference to our ItemDao
            itemDao = ItemDatabase.getDatabase(requireContext()).itemDao()

            allItems = itemDao.getAllItems()

            Log.e("safdsadfsdf", "allItemsallItems null  = + ${Gson().toJson(allItems)}")

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

    }

    private fun setData() {

        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvProducts.layoutManager = layoutManager

        Handler(Looper.getMainLooper()).postDelayed({

            adapter = ProductFavoriteAdapter().apply {

                setItems(allItems as ArrayList<Product>)

                setOnClickListener(this@FavoritesFragment)

            }

            binding.rvProducts.adapter = adapter


        }, 1000)


    }

    private fun updateData() {

        executorService = Executors.newSingleThreadExecutor()

        executorService.execute {

            // Get a reference to our ItemDao
            itemDao = runBlocking { ItemDatabase.getDatabase(requireContext()).itemDao() }

            allItems = runBlocking { itemDao.getAllItems() }

            adapter = ProductFavoriteAdapter().apply {

                setItems(allItems as ArrayList<Product>)

                setOnClickListener(this@FavoritesFragment)

            }

            adapter?.notifyDataSetChanged()

        }

        binding.rvProducts.adapter = adapter

    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.btnRemoveFromCart -> {

                val position = view.tag as Int

                val product = adapter?.getItems()?.get(position)

                executorService = Executors.newSingleThreadExecutor()
                executorService.execute {

                    product?.let { itemDao.deleteItem(it) }

                }

                adapter?.getItems()?.removeAt(position)

                adapter?.notifyDataSetChanged()

                updateData()

            }

        }

    }

}