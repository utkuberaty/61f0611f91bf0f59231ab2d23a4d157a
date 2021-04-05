package com.path.stardelivery.ui.home.favourites

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.path.stardelivery.data.entities.Station
import com.path.stardelivery.data.remote.Result
import com.path.stardelivery.databinding.FragmentFavouritesBinding
import com.path.stardelivery.ui.base.BaseFragment

class FavouritesFragment : BaseFragment<FavouritesViewModel, FragmentFavouritesBinding>
    ({ FragmentFavouritesBinding.inflate(it) }) {

    private val favouriteStationsList: MutableList<Station> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFavouriteStationsRecyclerView()
        viewModel.getFavouriteStations().observe(this) {
            if(it is Result.Success && it.data != null){
                favouriteStationsList.addAll(it.data)
                viewBinding.favouriteStationsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }


    private fun setFavouriteStationsRecyclerView() {
        viewBinding.favouriteStationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = FavouriteStationsAdapter(favouriteStationsList)
        }
    }


}