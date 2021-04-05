package com.path.stardelivery.ui.home.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.path.stardelivery.data.entities.Station
import com.path.stardelivery.databinding.ItemFavouriteBinding

class FavouriteStationsAdapter(private val favouriteStationList: MutableList<Station>) :
    RecyclerView.Adapter<FavouriteStationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favouriteStationList[position])
    }

    override fun getItemCount(): Int = favouriteStationList.size

    class ViewHolder(private val viewBinding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(station: Station) {
            viewBinding.apply {
                stationDurationTextView.text = "0"
                stationNameTextView.text = station.name
            }
        }

    }
}