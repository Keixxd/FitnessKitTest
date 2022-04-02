package com.keixxdd.fitnesskittest.ui.sorting

import android.annotation.SuppressLint
import com.keixxdd.fitnesskittest.domain.model.training.Lesson
import java.text.SimpleDateFormat

class DataSorting {

    companion object{
        const val ASC = 0
        const val DESC = 1

    }

    @SuppressLint("SimpleDateFormat")
    fun sortDateList(list: List<String>, order: Int): List<String>{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return when(order){
            ASC -> {
                list.sortedBy { sdf.parse(it) }
            }
            DESC ->{
                list.sortedByDescending { sdf.parse(it) }
            }
            else -> list
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun sortLessonsByTime(list: List<Lesson>, order: Int) : List<Lesson> {
        val sdf = SimpleDateFormat("HH:mm")
        return when(order){
            ASC -> {
                list.sortedBy { sdf.parse(it.startTime) }
            }
            DESC ->{
                list.sortedByDescending { sdf.parse(it.startTime) }
            }
            else -> list
        }
    }

}