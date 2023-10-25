package com.nhlynn.daily_vita_survey.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nhlynn.daily_vita_survey.R
import com.nhlynn.daily_vita_survey.data.DietVO
import com.nhlynn.daily_vita_survey.databinding.DietItemBinding
import com.nhlynn.daily_vita_survey.delegate.DietDelegate
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec

class DietViewHolder(
    private val binding: DietItemBinding,
    private val delegate: DietDelegate
) : RecyclerView.ViewHolder(binding.root) {
    private var diet: DietVO? = null

    init {
        binding.dietLayout.setOnClickListener {
            delegate.setCheckedChange(diet!!)
        }

        binding.btnTooltip.setOnClickListener {
            val balloon = Balloon.Builder(binding.btnTooltip.context)
                .setWidth(200)
                .setHeight(BalloonSizeSpec.WRAP)
                .setText(diet?.tool_tip.toString())
                .setTextColorResource(R.color.black)
                .setTextSize(15f)
                .setArrowPositionRules(ArrowPositionRules.ALIGN_BALLOON)
                .setArrowSize(10)
                .setPadding(12)
                .setCornerRadius(8f)
                .setArrowOrientation(ArrowOrientation.START)
                .setBackgroundColorResource(R.color.white)
                .setBalloonAnimation(BalloonAnimation.ELASTIC)
                .build()
            balloon.showAlignEnd(binding.btnTooltip)
        }
    }

    fun bindData(diet: DietVO) {
        this.diet = diet
        binding.cbDiet.text = diet.name

        binding.cbDiet.isChecked = diet.isChecked

        if (diet.tool_tip.isNullOrEmpty()) {
            binding.btnTooltip.visibility = View.GONE
        } else {
            binding.btnTooltip.visibility = View.VISIBLE
        }
    }
}