package com.nhlynn.daily_vita_survey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.nhlynn.daily_vita_survey.R
import com.nhlynn.daily_vita_survey.adapter.DietAdapter
import com.nhlynn.daily_vita_survey.data.DietVO
import com.nhlynn.daily_vita_survey.databinding.FragmentDietBinding
import com.nhlynn.daily_vita_survey.delegate.DietDelegate
import com.nhlynn.daily_vita_survey.utils.loadDietList

class DietFragment : Fragment(), DietDelegate {
    private var _binding: FragmentDietBinding? = null
    private val binding get() = _binding!!

    private lateinit var mDietAdapter: DietAdapter

    private var dietList = arrayListOf<DietVO>()
    private var selectedDiet: DietVO? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDietBinding.inflate(inflater, container, false)
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
        dietList = loadDietList(requireContext())
        dietList.add(0, DietVO(name = "None", isChecked = false))

        setUpAdapter()
        addOnClickListener(viewPager)
    }

    private fun setUpAdapter() {
        mDietAdapter = DietAdapter(this)
        binding.rvDiet.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDiet.setHasFixedSize(true)
        binding.rvDiet.adapter = mDietAdapter

        mDietAdapter.setData(dietList)
    }

    private fun addOnClickListener(viewPager: ViewPager2?) {
        binding.btnNext.setOnClickListener {
            if (selectedDiet == null) {
                Toast.makeText(requireContext(), "Please choose diet", Toast.LENGTH_LONG).show()
            } else {
                viewPager?.currentItem = 3
            }
        }

        binding.btnBack.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DietFragment()
    }

    override fun setCheckedChange(diet: DietVO) {
        selectedDiet = diet
        dietList.forEach {
            it.isChecked = it.id == diet.id
        }
        mDietAdapter.setData(dietList)
    }
}