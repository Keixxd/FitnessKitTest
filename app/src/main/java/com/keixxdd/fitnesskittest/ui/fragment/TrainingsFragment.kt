package com.keixxdd.fitnesskittest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.keixxdd.fitnesskittest.R
import com.keixxdd.fitnesskittest.databinding.FragmentTrainingsBinding
import com.keixxdd.fitnesskittest.domain.model.training.Lesson
import com.keixxdd.fitnesskittest.domain.model.training.Training
import com.keixxdd.fitnesskittest.ui.adapters.TrainingsAdapter
import com.keixxdd.fitnesskittest.ui.sorting.DataSorting
import com.keixxdd.fitnesskittest.ui.viewmodels.MainActivityViewModel
import com.keixxdd.fitnesskittest.utils.Constants
import kotlinx.coroutines.flow.collect

class TrainingsFragment : Fragment() {

    private lateinit var binding: FragmentTrainingsBinding
    private val viewModel: MainActivityViewModel by activityViewModels()
    private val trainingsAdapter = TrainingsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTrainingsRv()

        lifecycleScope.launchWhenCreated {
            viewModel.training.collect {
                when{
                    it.data != null -> {
                        showTrainingsList(it.data)
                    }
                    it.error!!.isNotEmpty() -> Snackbar.make(binding.root, it.error, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Повтор") {
                            fetchData()
                        }.show()
                    it.isLoading -> {
                        showLoadingBar()
                    }
                }
            }
        }

    }

    private fun fetchData() {
        viewModel.getTrainings()
    }

    private fun showLoadingBar() {
        binding.trainingsRv.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showTrainingsList(data: Training){
        binding.progressBar.visibility = View.GONE
        binding.trainingsRv.visibility = View.VISIBLE
        val map = makeLessonsMap(data.lessons)
        Constants.employeeList = data.trainers
        trainingsAdapter.submitData(map)
    }

    private fun setupTrainingsRv() {
        binding.trainingsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trainingsAdapter
        }
    }

    private fun makeLessonsMap(lessons: List<Lesson>) : Map<String, List<Lesson>> {
        val dates = lessons.map { lesson -> lesson.date }.toSet()
        val sortedDates = DataSorting().sortDateList(dates.toList(), DataSorting.ASC)
        val map = mutableMapOf<String, List<Lesson>>()
        sortedDates.forEach { date ->
            map[date] = DataSorting().sortLessonsByTime(
                lessons.filter { lesson -> lesson.date == date },
                DataSorting.ASC
            )
        }
        return map
    }

}