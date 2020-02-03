package com.aram.picsartexam.ui


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aram.picsartexam.ExamApplication
import com.aram.picsartexam.R
import com.aram.picsartexam.databinding.FragmentItemsBinding
import com.aram.picsartexam.viewmodel.ItemsViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class ItemsFragment : Fragment() {

    private lateinit var binding: FragmentItemsBinding
    private lateinit var adapter: RVAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private val itemsList: MutableList<Item> = mutableListOf()
    private val filteredList: MutableList<Item> = mutableListOf()
    private var sharedPreferances: SharedPreferences? = null
    private lateinit var swipeContainer: SwipeRefreshLayout


    private val viewModel: ItemsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_items, container, false)
        sharedPreferances = activity?.getSharedPreferences("login_info", Context.MODE_PRIVATE)

        val user =  sharedPreferances?.getString("user","")

        val gson = Gson()

       // binding.user = gson.fromJson(user,User::class.java)



        val bundle = arguments
        bundle?.let {
            binding.user = ItemsFragmentArgs.fromBundle(it).user
        }

        handleInternetSatus()
        swipeRefresh()
        getItemsList()
        search()
        logOut()


        gridLayoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        adapter = RVAdapter()
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = adapter
        return binding.root
    }




    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null){
                itemsList.forEach {
                    if (it.title != null  && it.title.contains(query)) {
                       filteredList.add(it)
                    }
                }
                }
//                else if(query){
//                    adapter.itemsList = itemsList
//                }


                if(filteredList.isEmpty()){
                    binding.errorMesageTv.text = "No result"
                    binding.errorMesageTv.visibility = View.VISIBLE
                    //binding.recyclerView.visibility = View.GONE
                    adapter.itemsList = mutableListOf()
                } else {
                    adapter.itemsList = filteredList
                    binding.errorMesageTv.visibility = View.GONE
                }

                filteredList.clear()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun getItemsList() {
        viewModel.responce.observe(this, Observer {
            itemsList.clear()
            var temp: MutableList<Item> = it?.itemsList?.toMutableList() ?: mutableListOf()
            itemsList.addAll(temp)
            swipeContainer.isRefreshing = false
        })
    }

    fun logOut(){
        binding.logOutButton.setOnClickListener{
            sharedPreferances?.edit()?.apply{
                putBoolean("isLogged",false)
                putString("user","")
                apply()
            }
            findNavController().navigate(ItemsFragmentDirections.actionItemsFragmentToLoginFragment())
        }
    }
    fun swipeRefresh(){
        swipeContainer = binding.swipeContainer

        swipeContainer.setOnRefreshListener {

            if (ExamApplication.isNetworkConnected()){
                viewModel.getItems()
                binding.errorMesageTv.visibility = View.GONE
            } else if(itemsList.isEmpty()) {
                binding.errorMesageTv.visibility = View.VISIBLE
                binding.errorMesageTv.text = "No iternet connection swipe down to refresh"
                swipeContainer.isRefreshing = false
            }

        }
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }
   fun handleInternetSatus(){
       viewModel.networkStatus.observe(this, Observer {
           if(!it){
               binding.errorMesageTv.visibility = View.VISIBLE
               binding.errorMesageTv.text = "No iternet connection swipe down to refresh"

           } else {
               binding.errorMesageTv.visibility = View.GONE
           }
       })
   }

}
