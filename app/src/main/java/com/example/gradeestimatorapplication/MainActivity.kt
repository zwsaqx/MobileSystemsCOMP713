package com.example.gradeestimatorapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.lifecycle.Observer
import com.example.gradeestimatorapplication.ui.theme.GradeEstimatorApplicationTheme
import com.example.gradeestimatorapplication.viewmodel.GradeViewModel
import smile.data.DataFrame
import smile.data.formula.Formula
import smile.regression.RandomForest
import smile.io.Read




class MainActivity : ComponentActivity() {

    private lateinit var gradeViewModel: GradeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the ViewModel
        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        setContent {
            GradeEstimatorApplicationTheme {
                Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
                    setContentView(R.layout.login_activity)
                }
            }
        }

        // Observe the API response
        gradeViewModel.gradeResponse.observe(this, Observer { response ->
            handleApiResponse(response)
        })
    }

    // Function to handle the login button click event
    fun loginButtonClick(view: View) {
        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginResultTextView = findViewById<TextView>(R.id.loginResultTextView)

        val enteredUsername = usernameEditText.text.toString()
        val enteredPassword = passwordEditText.text.toString()

        val correctCredentials = mapOf(
            "" to "",  //for testing, allows you to login when the fields are empty
            "john" to "password",
        )

        if (correctCredentials.containsKey(enteredUsername) && correctCredentials[enteredUsername] == enteredPassword) {
            loginResultTextView.text = "Login Successful"
            setContentView(R.layout.menu_activity)

            // Trigger API call to fetch grades
            gradeViewModel.fetchGrades()  // Step 4: Trigger the API call

        } else {
            loginResultTextView.text = "Login Failed"
        }
    }

    // Handle the API response in the UI
    private fun handleApiResponse(response: List<GradeViewModel.Grade>) {
        if (response != null) {
            // Successfully fetched data from the API
            val resultMessage = "Average Grade: ${response}"    // change response to response.averageGrade
            Toast.makeText(applicationContext, resultMessage, Toast.LENGTH_LONG).show()
        } else {
            // Failed to fetch data
            Toast.makeText(applicationContext, "Failed to fetch grades", Toast.LENGTH_LONG).show()
        }
    }

    // Other button click functions...
    fun forgotButtonClick(view: View) { setContentView(R.layout.forgot_password_activity) }
    fun myPapersButtonClick(view: View) { setContentView(R.layout.my_papers_activity) }
    fun menuButtonClick(view: View) { setContentView(R.layout.menu_activity) }
    fun signupButtonClick(view: View) { setContentView(R.layout.register_activity) }
    fun logoutButtonClick(view: View) { setContentView(R.layout.login_activity) }
    fun termButtonClick(view: View) { setContentView(R.layout.terms_activity) }
    fun privacyButtonClick(view: View) { setContentView(R.layout.privacy_activity) }
    fun languageButtonClick(view: View) { setContentView(R.layout.language_activity) }
    fun refreshButtonClick(view: View) {

        val df = loadData()
        val features = df.slice("attendance\n", "location", "workingtime").toArray()
        val labels = df.slice("grade").toArray()



        val updateMessage = "Refreshed Papers!"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.my_papers_activity)
    }

    fun trainModel(features: Array<DoubleArray>, labels: DoubleArray): RandomForest {
        return RandomForest.fit(features, labels) // Fit model
    }

    private fun loadData(): DataFrame {
        // Load CSV file from assets or raw resource folder
        val inputStream = assets.open("gradesData.csv")
        return RequiresPermission.Read.csv(inputStream)
    }

    fun predictGrade(model: RandomForest, attendance: Double, location: Double, workingTime: Double): Double {
        val input = doubleArrayOf(attendance, location, workingTime)
        return model.predict(input) // Predict final grade
    }


    fun feedbackButtonClick(view: View) { setContentView(R.layout.feedback_activity) }
    fun feedbackSubmitButtonClick(view: View) {
        val updateMessage = "Feedback Sent!"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.feedback_activity)
    }
    fun aboutButtonClick(view: View) { setContentView(R.layout.about_activity) }
    fun settingsButtonClick(view: View) { setContentView(R.layout.settings_activity) }


}