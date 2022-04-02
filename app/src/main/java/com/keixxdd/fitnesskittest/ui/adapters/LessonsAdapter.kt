package com.keixxdd.fitnesskittest.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keixxdd.fitnesskittest.databinding.TrainingsItemBinding
import com.keixxdd.fitnesskittest.domain.model.training.Lesson
import com.keixxdd.fitnesskittest.utils.Constants
import com.keixxdd.fitnesskittest.utils.extensions.createEmptyString

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.TrainingItemViewHolder>() {

    private var adapterList = emptyList<Lesson>()

    inner class TrainingItemViewHolder(private val binding: TrainingsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            with(binding){
                itemCard.setCardBackgroundColor(Color.parseColor(adapterList[adapterPosition].color))
                startTimeTv.text = adapterList[adapterPosition].startTime
                endTimeTv.text = adapterList[adapterPosition].endTime
                trainingName.text = adapterList[adapterPosition].tab
                trainingTrainer.text = getCoach(adapterPosition)
                trainingLocation.text = adapterList[adapterPosition].place
            }
        }
    }

    private fun getCoach(position: Int): String {
        return try {
            Constants.employeeList.filter { it.id == adapterList[position].coach_id }[0].full_name
        }catch (e: IndexOutOfBoundsException){
            createEmptyString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TrainingsItemBinding.inflate(layoutInflater, parent, false)
        return TrainingItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = adapterList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitLessonsList(list: List<Lesson>){
        adapterList = list
        notifyDataSetChanged()
    }
}