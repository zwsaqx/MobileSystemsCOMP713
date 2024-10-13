package com.example.gradeestimatorapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GradeViewModel : ViewModel() {

    private val _gradeResponse = MutableLiveData<List<Grade>>()  // Holds the list of grades
    val gradeResponse: LiveData<List<Grade>> get() = _gradeResponse

    // To hold the average grade
    private val _averageGrade = MutableLiveData<Double>()
    val averageGrade: LiveData<Double> get() = _averageGrade

    // Function to fetch grades
    fun fetchGrades() {
        // Simulating fetching data
        val sampleGrades = listOf(
            Grade("Paper 1", 4),
            Grade("Paper 2", 5),
            Grade("Paper 3", 3)
        )
        _gradeResponse.value = sampleGrades  // Assign the fetched data to LiveData
        calculateAverageGrade(sampleGrades)  // Calculate average after fetching grades
    }

    // Function to calculate the average grade
    private fun calculateAverageGrade(grades: List<Grade>) {
        val total = grades.sumOf { it.score }  // Sum of scores
        val average = if (grades.isNotEmpty()) total.toDouble() / grades.size else 0.0
        _averageGrade.value = average  // Update LiveData with the average
    }

    // Data class to represent a grade
    data class Grade(val paperName: String, val score: Int)
}
