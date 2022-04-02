package com.keixxdd.fitnesskittest.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keixxdd.fitnesskittest.databinding.ParentItemBinding
import com.keixxdd.fitnesskittest.domain.model.training.Lesson
import com.keixxdd.fitnesskittest.utils.extensions.beautyDate

class TrainingsAdapter : RecyclerView.Adapter<TrainingsAdapter.ItemViewHolder>() {

    private var adapterMap = emptyMap<String, List<Lesson>>()

    inner class ItemViewHolder (
        private val binding: ParentItemBinding,
        private val context: Context
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                dateTv.text = adapterMap.keys.toList()[adapterPosition].beautyDate()
                lessonsRv.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = LessonsAdapter().also { adapter ->
                        adapterMap[adapterMap.keys.toList()[adapterPosition]]?.let { lessons ->
                            adapter.submitLessonsList(
                                lessons
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ParentItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = adapterMap.keys.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(map: Map<String, List<Lesson>>){
        adapterMap = map
        notifyDataSetChanged()
    }
}