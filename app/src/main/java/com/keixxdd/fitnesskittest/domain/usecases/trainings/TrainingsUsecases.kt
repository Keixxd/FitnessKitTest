package com.keixxdd.fitnesskittest.domain.usecases.trainings

import com.keixxdd.fitnesskittest.data.ResponseWrapper
import com.keixxdd.fitnesskittest.domain.model.training.Training

interface TrainingsUsecases {

    suspend fun getPlan() : ResponseWrapper<Training?>

}