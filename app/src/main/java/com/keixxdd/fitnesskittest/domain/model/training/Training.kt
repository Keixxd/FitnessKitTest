package com.keixxdd.fitnesskittest.domain.model.training

data class Training(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)