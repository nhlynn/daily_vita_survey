package com.nhlynn.daily_vita_survey.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhlynn.daily_vita_survey.data.ConcernVO
import com.nhlynn.daily_vita_survey.databinding.PrioritizeItemBinding
import com.nhlynn.daily_vita_survey.view_holder.ConcernViewHolder

class ConcernAdapter : RecyclerView.Adapter<ConcernViewHolder>() {
    private var concernList = arrayListOf<ConcernVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcernViewHolder {
        val binding =
            PrioritizeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcernViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return concernList.size
    }

    override fun onBindViewHolder(holder: ConcernViewHolder, position: Int) {
        val item = concernList[position]
        holder.bindData(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(concernList: ArrayList<ConcernVO>) {
        this.concernList=concernList
        notifyDataSetChanged()
    }
}