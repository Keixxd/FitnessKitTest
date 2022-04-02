package com.keixxdd.fitnesskittest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keixxdd.fitnesskittest.data.ResponseWrapper
import com.keixxdd.fitnesskittest.domain.usecases.trainings.TrainingsInteractor
import com.keixxdd.fitnesskittest.ui.state.FetchTrainingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val trainingsInteractor: TrainingsInteractor
): ViewModel(){

    private val _training = MutableStateFlow(FetchTrainingState())
    val training = _training.asStateFlow()

    fun getTrainings(){
        viewModelScope.launch (Dispatchers.IO) {
            _training.value = FetchTrainingState(isLoading = true)
            when(val result = trainingsInteractor.getPlan()){
                is ResponseWrapper.Success ->
                    _training.value = FetchTrainingState(data = result.data)
                is ResponseWrapper.Failure ->
                    _training.value = FetchTrainingState(error = result.message)
            }
        }
    }

}