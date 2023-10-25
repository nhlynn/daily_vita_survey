package com.nhlynn.daily_vita_survey.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.nhlynn.daily_vita_survey.R
import com.nhlynn.daily_vita_survey.data.AllergiesVO
import com.nhlynn.daily_vita_survey.databinding.FragmentAllergeiesBinding
import com.nhlynn.daily_vita_survey.utils.loadAllergiesList

class AllergeiesFragment : Fragment() {
    private var _binding: FragmentAllergeiesBinding? = null
    private val binding get() = _binding!!

    private var allergiesList = arrayListOf<AllergiesVO>()
    private var selectedAllergiesList = arrayListOf<AllergiesVO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllergeiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderUI()
    }

    private fun renderUI() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        allergiesList = loadAllergiesList(requireContext())

        val allergiesAdapter: ArrayAdapter<AllergiesVO> = ArrayAdapter<AllergiesVO>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            allergiesList
        )

        binding.actFilter.setAdapter(allergiesAdapter)

        binding.actFilter.setOnItemClickListener { _, _, _, _ ->
            val allergie = allergiesList.first { it.name == binding.actFilter.text.toString() }
            val chip = LayoutInflater.from(requireContext())
                .inflate(R.layout.concern_chip, binding.chipGroup, false) as Chip
            chip.id = allergie.id
            chip.text = allergie.name
            chip.isChecked = true
            chip.isClickable = false
            binding.chipGroup.addView(chip)

            selectedAllergiesList.add(allergie)

            binding.actFilter.text = null
            binding.chipGroup.removeView(binding.actFilter)
            binding.chipGroup.addView(binding.actFilter)
        }

        addOnClickListener(viewPager)
    }

    private fun addOnClickListener(viewPager: ViewPager2?) {
        binding.actFilter.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                removeChip()
            }
            return@setOnKeyListener true
        }

        binding.btnNext.setOnClickListener {
            viewPager?.currentItem = 4
        }

        binding.btnBack.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }

    private fun removeChip() {
        if (selectedAllergiesList.isNotEmpty()) {
            selectedAllergiesList.removeAt(selectedAllergiesList.size - 1)
            binding.chipGroup.removeAllViews()

            selectedAllergiesList.forEach {
                val chip = LayoutInflater.from(requireContext())
                    .inflate(R.layout.concern_chip, binding.chipGroup, false) as Chip
                chip.id = it.id
                chip.text = it.name
                chip.isChecked = true
                chip.isClickable = false
                binding.chipGroup.addView(chip)
            }

            binding.actFilter.isEnabled = true
            binding.actFilter.isFocusableInTouchMode = true
            binding.chipGroup.addView(binding.actFilter)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllergeiesFragment()
    }
}