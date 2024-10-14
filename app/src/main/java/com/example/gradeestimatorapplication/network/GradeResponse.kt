package com.example.gradeestimatorapplication.network

data class GradeResponse(
    val studentId: String,
    val finalGrade: Double,
    val difficultyRating: Int,
    val workAmountRating: Int
)
