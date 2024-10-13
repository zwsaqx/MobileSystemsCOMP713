package com.example.gradeestimatorapplication.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("grades") // Example endpoint, replace with your actual endpoint
    suspend fun getGrades(
        @Query("studentId") studentId: String
    ): GradeResponse
}
