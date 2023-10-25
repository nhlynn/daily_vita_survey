package com.nhlynn.daily_vita_survey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.nhlynn.daily_vita_survey.R
import com.nhlynn.daily_vita_survey.adapter.ConcernAdapter
import com.nhlynn.daily_vita_survey.data.ConcernVO
import com.nhlynn.daily_vita_survey.databinding.FragmentConcernBinding
import com.nhlynn.daily_vita_survey.utils.loadConcernList
import java.util.Collections

class ConcernFragment : Fragment() {
    private var _binding: FragmentConcernBinding? = null
    private val binding get() = _binding!!

    private lateinit var mConcernAdapter: ConcernAdapter

    private var concernList = arrayListOf<ConcernVO>()
    private val selectedConcernList = arrayListOf<ConcernVO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConcernBinding.inflate(inflater, container, false)
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

        concernList = loadConcernList(requireContext())

        concernChip()
        setUpAdapter()
        addOnClickListener(viewPager)
    }

    private fun concernChip() {
        concernList.forEach {
            val chip = LayoutInflater.from(requireContext())
                .inflate(R.layout.concern_chip, binding.cgConcern, false) as Chip
            chip.id = it.id
            chip.text = it.name

            chip.setOnCheckedChangeListener { _, isChecked ->
                val concern = concernList.first { concern -> concern.id == chip.id }
                if (isChecked) {
                    if (selectedConcernList.size == 5) {
                        chip.isChecked = false
                        Toast.makeText(
                            requireContext(),
                            "Your selected concern is reached maximum count.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        selectedConcernList.add(concern)
                    }
                } else {
                    selectedConcernList.remove(concern)
                }
                mConcernAdapter.setData(selectedConcernList)
            }
            binding.cgConcern.addView(chip)
        }
    }

    private fun setUpAdapter() {
        mConcernAdapter = ConcernAdapter()
        binding.rvPrioritize.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPrioritize.setHasFixedSize(true)
        binding.rvPrioritize.adapter = mConcernAdapter

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                source: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val sourcePosition = source.adapterPosition
                val targetPosition = target.adapterPosition

                Collections.swap(concernList, sourcePosition, targetPosition)
                mConcernAdapter.notifyItemMoved(sourcePosition, targetPosition)

                return true

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

        })
        itemTouchHelper.attachToRecyclerView(binding.rvPrioritize)
    }

    private fun addOnClickListener(viewPager: ViewPager2?) {
        binding.btnNext.setOnClickListener {
            if (selectedConcernList.isNotEmpty()) {
                viewPager?.currentItem = 2
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please select at least one concern.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.btnBack.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConcernFragment()
    }
}