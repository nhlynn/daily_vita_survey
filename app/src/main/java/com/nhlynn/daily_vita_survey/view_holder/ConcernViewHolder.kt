package com.nhlynn.daily_vita_survey.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.nhlynn.daily_vita_survey.data.ConcernVO
import com.nhlynn.daily_vita_survey.databinding.PrioritizeItemBinding

class ConcernViewHolder(
    private val binding: PrioritizeItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(concern: ConcernVO) {
        binding.chipPrioritize.id = concern.id
        binding.chipPrioritize.text = concern.name
    }
}