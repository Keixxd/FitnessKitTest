package com.keixxdd.fitnesskittest.data.repository

import com.keixxdd.fitnesskittest.data.ResponseWrapper
import com.keixxdd.fitnesskittest.domain.model.training.Training

interface FitnessKitRepository {

    suspend fun getTrainingsData() : ResponseWrapper<Training?>

}