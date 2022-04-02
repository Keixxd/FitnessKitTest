package com.keixxdd.fitnesskittest.ui.state

import com.keixxdd.fitnesskittest.domain.model.training.Training

data class FetchTrainingState(
    val isLoading: Boolean = false,
    val data: Training? = null,
    val error: String? = ""
)