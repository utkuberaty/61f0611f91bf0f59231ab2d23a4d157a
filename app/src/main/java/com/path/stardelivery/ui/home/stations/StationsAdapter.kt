package com.path.stardelivery.ui.home.stations

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.path.stardelivery.R
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.entities.SpaceshipWithStation
import com.path.stardelivery.data.entities.Station
import com.path.stardelivery.databinding.ItemStationBinding
import com.path.stardelivery.util.distanceToStation

class StationsAdapter(
    private val stationList: List<Station>,
    private val spaceshipWithStation: SpaceshipWithStation?,
    private val onFavourite: (Station) -> Unit,
    private val travel: (Station) -> Unit
) : RecyclerView.Adapter<StationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stationList[position], spaceshipWithStation)
    }

    override fun getItemCount(): Int = stationList.size

    inner class ViewHolder(private val viewBinding: ItemStationBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(station: Station, spaceshipWithStation: SpaceshipWithStation?) {
            viewBinding.apply {
                favouriteImageView.apply {
                    imageTintList = favouriteTintColor(station.favourite)
                    setOnClickListener {
                        stationList[adapterPosition].isFavorite = if (station.favourite) 0 else 1
                        imageTintList = favouriteTintColor(station.favourite)
                        onFavourite(station)
                    }
                }
                travelButton.setOnClickListener { travel(station) }
                stationNameTextView.text = station.name
                capacityTextView.text = "${station.capacity}/${station.need}"
                distanceTextView.text = "${spaceshipWithStation?.distanceToStation(station)}EUS"

                notEnableView.visibility = if (station.need == 0) View.VISIBLE else View.GONE
            }

        }

        private fun favouriteTintColor(isFavourite: Boolean) = if (isFavourite)
            ColorStateList.valueOf(viewBinding.root.resources.getColor(R.color.yellow))
        else ColorStateList.valueOf(viewBinding.root.resources.getColor(android.R.color.darker_gray))
    }

}
