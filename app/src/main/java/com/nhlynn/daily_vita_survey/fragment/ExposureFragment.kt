package com.nhlynn.daily_vita_survey.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nhlynn.daily_vita_survey.R
import com.nhlynn.daily_vita_survey.databinding.FragmentExposureBinding

class ExposureFragment : Fragment() {
    private var _binding: FragmentExposureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExposureBinding.inflate(inflater, container, false)
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
        binding.btnPersonalized.setOnClickListener {
            if (isValidate()){
                val exposureLimit = binding.rgExposureLimit.checkedRadioButtonId
                Toast.makeText(requireContext(),"Success",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(),"Please check all data",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidate(): Boolean {
        if (binding.rgExposureLimit.checkedRadioButtonId!=R.id.rb_exposure_limit_yes &&
            binding.rgExposureLimit.checkedRadioButtonId!=R.id.rb_exposure_limit_no){
            return false
        }
        if (binding.rgSmoke.checkedRadioButtonId!=R.id.rb_smoke_yes &&
            binding.rgSmoke.checkedRadioButtonId!=R.id.rb_smoke_no){
            return false
        }
        if (binding.rgAlcoholicBeverages.checkedRadioButtonId!=R.id.rb_zero_one &&
            binding.rgAlcoholicBeverages.checkedRadioButtonId!=R.id.rb_two_five &&
            binding.rgAlcoholicBeverages.checkedRadioButtonId!=R.id.rb_five_plus){
            return false
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExposureFragment()
    }
}