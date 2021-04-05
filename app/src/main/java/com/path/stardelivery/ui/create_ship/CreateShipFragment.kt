package com.path.stardelivery.ui.create_ship

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import com.path.stardelivery.R
import com.path.stardelivery.data.entities.SpaceShip
import com.path.stardelivery.data.remote.Result
import com.path.stardelivery.databinding.FragmentCreateShipBinding

import com.path.stardelivery.ui.base.BaseFragment
import com.path.stardelivery.util.DS_UNIT
import com.path.stardelivery.util.EUS_UNIT
import com.path.stardelivery.util.UGS_UNIT

class CreateShipFragment : BaseFragment<CreateShipViewModel, FragmentCreateShipBinding>
    ({ FragmentCreateShipBinding.inflate(it) }) {

    private var spaceshipPoint: Int = 15

    private val spaceshipName
        get() = viewBinding.spaceShipNameTextView.editText?.text.toString()

    private var stability: Int = 0
    private var speed: Int = 0
    private var capacity: Int = 0

    private val isSkillsZero
        get() = speed == 0 || capacity == 0 || stability == 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSliders()
        setContinueButton()
    }

    private fun setSliders() {
        viewBinding.apply {
            spaceShipPointTextView.text = spaceshipPoint.toString()
            stabilitySlider.addOnChangeListener(sliderListener())
            capacitySlider.addOnChangeListener(sliderListener())
            speedSlider.addOnChangeListener(sliderListener())
        }
    }

    private fun setContinueButton() {
        viewBinding.continueButton.setOnClickListener {
            if (spaceshipPoint >= 0 && !isSkillsZero && spaceshipName.isNotBlank()) {
                viewModel.getAllStations().observe(this) {
                    if (it is Result.Success && it.data != null) saveSpaceship(it.data.first().id)
                }
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Skill point need to be split equally, skill point can not be below than zero and Spaceship name must be fill")
                    .setPositiveButton("Okey") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }

    private fun saveSpaceship(stationId: Int) {
        viewModel.saveSpaceship(
            SpaceShip(
                name = spaceshipName,
                speed = speed * EUS_UNIT,
                capacity = capacity * UGS_UNIT,
                stability = stability * DS_UNIT,
                currentStationId = stationId
            )
        ).observe(this) {
            findNavController().navigate(CreateShipFragmentDirections.actionCreateShipFragmentToHomeFragment())
        }
    }

    private fun sliderListener() = object : Slider.OnChangeListener {

        override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {

            when (slider.id) {
                R.id.stabilitySlider -> {
                    calculateSpaceshipPoint(slider.value.toInt(), stability)
                    stability = slider.value.toInt()
                }

                R.id.speedSlider -> {
                    calculateSpaceshipPoint(slider.value.toInt(), speed)
                    speed = slider.value.toInt()
                }

                R.id.capacitySlider -> {
                    calculateSpaceshipPoint(slider.value.toInt(), capacity)
                    capacity = slider.value.toInt()
                }
            }
            viewBinding.spaceShipPointTextView.text = spaceshipPoint.toString()

        }

        private fun calculateSpaceshipPoint(sliderValue: Int, skillPoint: Int) {
            if (sliderValue > skillPoint)
                spaceshipPoint -= sliderValue - skillPoint
            else spaceshipPoint += skillPoint - sliderValue

        }
    }

}