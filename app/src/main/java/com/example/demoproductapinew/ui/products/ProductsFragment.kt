package com.example.demoproductapinew.ui.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproductapinew.R
import com.example.demoproductapinew.databinding.FragmentProductsBinding
import com.example.demoproductapinew.response.ProductsItem
import com.example.demoproductapinew.response.ProductsResponse
import com.example.demoproductapinew.ui.products.adapters.ProductsAdapter
import com.example.demoproductapinew.ui.roomdb.ItemDao
import com.example.demoproductapinew.ui.roomdb.ItemDatabase
import com.example.demoproductapinew.ui.roomdb.Product
import com.google.gson.Gson
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ProductsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProductsBinding

    private lateinit var myViewModel: ProductViewModel

    private var adapter: ProductsAdapter? = null

    private lateinit var itemDao: ItemDao

    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        // Get a reference to our ItemDao
        itemDao = ItemDatabase.getDatabase(requireContext()).itemDao()

        initViews()

        return binding.root
    }

    private fun initViews() {

        myViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        myViewModel.getMyProductsData().observe(viewLifecycleOwner) { it ->

            Log.e("hjdshfsdkjfhkjshjdf", "it = " + Gson().toJson(it))

            setRecyclerView(it)

        }

    }

    private fun setRecyclerView(it: ProductsResponse?) {

        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvProducts.layoutManager = layoutManager

        adapter = ProductsAdapter().apply {

            setItems(it?.products as ArrayList<ProductsItem>)

            setOnClickListener(this@ProductsFragment)

        }

        binding.rvProducts.adapter = adapter

    }

    override fun onClick(view: View?) {


        when (view?.id) {

            R.id.clRoot -> {

                val position = view.tag as Int
                val data = adapter?.getItems()?.get(position)

                startActivity(Intent(requireContext(), ProductDetailsActivity::class.java).apply {
                    // you can add values(if any) to pass to the next class or avoid using `.apply`
                    putExtra("data", data)
                })

            }

            R.id.ivFavIcon -> {

                val position = view.tag as Int
                val data = adapter?.getItems()?.get(position)

                data?.isFavorite = data?.isFavorite != true

                val id = data?.id?.toInt()
                val name = data?.title
                val imageUrL = data?.imageURL
                val price = data?.price?.get(0)?.value

                val product = id?.let { Product(it, name, imageUrL, price.toString()) }

                executorService = Executors.newSingleThreadExecutor()

                executorService.execute {

                    product?.let { itemDao.insertItem(it) }

                    val allItems = itemDao.getAllItems()

                    Log.e(
                        "allItemsallItems",
                        "allItems isFavorite false + ${Gson().toJson(allItems)}"
                    )

                }


                adapter?.notifyDataSetChanged()


            }


        }

    }

    override fun onResume() {
        super.onResume()

        adapter?.notifyDataSetChanged()

    }

}