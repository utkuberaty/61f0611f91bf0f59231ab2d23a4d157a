package com.path.stardelivery.ui.home.stations

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.path.stardelivery.R
import com.path.stardelivery.data.entities.SpaceshipWithStation
import com.path.stardelivery.data.entities.Station
import com.path.stardelivery.data.remote.Result
import com.path.stardelivery.databinding.FragmentStationsBinding
import com.path.stardelivery.ui.base.BaseFragment
import com.path.stardelivery.ui.root.RootActivity
import com.path.stardelivery.util.TAG
import com.path.stardelivery.util.distanceToStation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StationsFragment : BaseFragment<StationsViewModel, FragmentStationsBinding>
    ({ FragmentStationsBinding.inflate(it) }) {

    private val stations: MutableList<Station> = mutableListOf()

    private var spaceShipWithStation: SpaceshipWithStation? = null

    private val rootActivity get() = (activity as RootActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                setSpaceshipData()
                setStationRecyclerView()
                setCountDown()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchStations()
        lifecycleScope.launch { setSpaceshipData() }
    }

    private suspend fun setSpaceshipData() {
        spaceShipWithStation = viewModel.getSpaceShip()
        viewBinding.apply {
            clothesCountTextView.text = spaceShipWithStation?.spaceShip?.capacity.toString()
            spaceTimeTextView.text = spaceShipWithStation?.spaceShip?.speed?.toString()
            stabilityTimeTextView.text = spaceShipWithStation?.spaceShip?.stability.toString()
            spaceshipNameTextView.text = spaceShipWithStation?.spaceShip?.name.toString()
            currentStationName.text = spaceShipWithStation?.currentStation?.name
            spaceshipHealthTextView.text = spaceShipWithStation?.spaceShip?.health.toString()
        }
    }

    private fun setCountDown() {
        val starTime = spaceShipWithStation?.spaceShip?.stability
        val isFinish = rootActivity.viewModel.onCountDownFinish.value
        val health = spaceShipWithStation?.spaceShip?.health
        if (starTime != null) {
            viewModel.apply {
                if (isFinish == true || isFinish == null)
                    rootActivity.viewModel.countDown(starTime).start()

                rootActivity.viewModel.onTick.observe(viewLifecycleOwner) {
                    viewBinding.timeTextView.text = "${((it / 1000))}s"
                }
                rootActivity.viewModel.onCountDownFinish.observe(viewLifecycleOwner) {
                    if (it) {
                        lifecycleScope.launch {
                            withContext(Dispatchers.Main) {
                                if (spaceShipWithStation != null) {
                                    viewModel.updateSpaceshipHealth(
                                        spaceShipWithStation?.spaceShip?.id ?: 0,
                                        health!! - 10
                                    )
                                    setSpaceshipData()
                                    if (health > 0 && canSpaceShipTravelAnyStation()) {
                                        rootActivity.viewModel.countDown(starTime).start()
                                    } else gameOver()
                                }
                            }
                        }
                    }
                }
            }

        }
    }


    private fun canSpaceShipTravelAnyStation() = stations.any {
        spaceShipWithStation?.distanceToStation(it) ?: 0 <= spaceShipWithStation?.spaceShip?.speed ?: 0
    }


    private fun gameOver() {
        Log.i(TAG, "game over")
        lifecycleScope.launch {
            viewModel.deleteAllTable()
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Game Over")
                .setMessage("Game is over do you wanna play again?")
                .setPositiveButton("Restart") { dialog, _ ->
                    rootActivity.findNavController(R.id.nav_root_host_fragment)
                        .navigate(R.id.splashFragment)
                    dialog?.dismiss()
                }.setCancelable(true)
                .show()
        }
    }

    private fun fetchStations() {
        viewModel.getStations().observe(this) {
            if (it is Result.Success && it.data != null) {
                stations.clear()
                stations.addAll(it.data.drop(1))
                viewBinding.stationsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun setStationRecyclerView() {
        viewBinding.stationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = StationsAdapter(stations, spaceShipWithStation, {
                lifecycleScope.launch {
                    viewModel.updateIsFavourite(it.id, it.favourite)
                }
            }, { lifecycleScope.launch { travel(it) } })

            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    private suspend fun travel(station: Station) {
        spaceShipWithStation?.run {
            val capacity =
                if (spaceShip.capacity > station.need) spaceShip.capacity - station.need else 0
            viewModel.updateSpaceshipTravel(
                spaceShipWithStation?.spaceShip?.id ?: 0,
                spaceShip.speed - distanceToStation(station),
                capacity
            )
            val need = if (spaceShip.capacity > station.need) 0
            else station.need - spaceShip.capacity
            viewModel.updateStationNeed(station.id, need)
            setSpaceshipData()
            fetchStations()

            if (spaceShipWithStation?.spaceShip?.capacity == 0L) {
                gameOver()
            }
        }
    }
}