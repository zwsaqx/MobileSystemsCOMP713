package com.example.gradeestimatorapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.gradeestimatorapplication.ui.theme.GradeEstimatorApplicationTheme
import com.example.gradeestimatorapplication.viewmodel.GradeViewModel
import android.widget.Space




class MainActivity : ComponentActivity() {

    private lateinit var gradeViewModel: GradeViewModel

    private lateinit var gradePredictor: GradePredictor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start viewModel



//        attendanceEditText = findViewById<EditText>(R.id.editTextnumber)
//        workingTimeEditText = findViewById<EditText>(R.id.editTextnumber2)
//        locationEditText = findViewById<EditText>(R.id.editTextnumber3)
//        val buttonNext: Button = findViewById<Button>(R.id.buttonNext)
//
//        buttonNext.setOnClickListener {
//            // Get the input values
//            val attendance = attendanceEditText.text.toString().toFloatOrNull() ?: 0f
//            val workingTime = workingTimeEditText.text.toString().toFloatOrNull() ?: 0f
//            val location = locationEditText.text.toString().toFloatOrNull() ?: 0f
//
//            // Create an Intent to start the next activity
//            val intent = Intent(this, GradePredictionActivity::class.java)
//            // Put the values into the Intent
//            intent.putExtra("attendance", attendance)
//            intent.putExtra("workingTime", workingTime)
//            intent.putExtra("location", location)
//
//            // Start the new activity
//            startActivity(intent)
//        }

        setContent {
            GradeEstimatorApplicationTheme {
                Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
                    setContentView(R.layout.login_activity)
                }
            }
        }
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
        } else {
            loginResultTextView.text = "Login Failed"
        }
    }


    // page routing
    fun forgotButtonClick(view: View) { setContentView(R.layout.forgot_password_activity) }
    fun myPapersButtonClick(view: View) { setContentView(R.layout.my_papers_activity) }
    fun menuButtonClick(view: View) { setContentView(R.layout.menu_activity) }
    fun signupButtonClick(view: View) { setContentView(R.layout.register_activity) }
    fun logoutButtonClick(view: View) { setContentView(R.layout.login_activity) }
    fun termButtonClick(view: View) { setContentView(R.layout.terms_activity) }
    fun privacyButtonClick(view: View) { setContentView(R.layout.privacy_activity) }
    fun languageButtonClick(view: View) { setContentView(R.layout.language_activity) }

    fun refreshButtonClick(view: View) {


        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)


        gradePredictor = GradePredictor(this)


        val attendanceEditText = findViewById<EditText>(R.id.editTextnumber)
        val workingtimeEditText = findViewById<EditText>(R.id.editTextnumber2)
        val locationEditText = findViewById<EditText>(R.id.editTextnumber3)

        val attendance = attendanceEditText.text.toString().toFloatOrNull() ?: 0f
        val workingTime = workingtimeEditText.text.toString().toFloatOrNull() ?: 0f
        val location = locationEditText.text.toString().toFloatOrNull() ?: 0f

        // predicting the grade
        val predictedGrade = gradePredictor.predict(location, workingTime, attendance)

        val updateMessage = "Grade: $predictedGrade"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.my_papers_activity)
        gradePredictor.close()
    }



    fun feedbackButtonClick(view: View) { setContentView(R.layout.feedback_activity) }
    fun feedbackSubmitButtonClick(view: View) {
        val updateMessage = "Feedback Sent!"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.feedback_activity)
    }
    fun aboutButtonClick(view: View) { setContentView(R.layout.about_activity) }
    fun settingsButtonClick(view: View) { setContentView(R.layout.settings_activity) }

    override fun onDestroy() {
        super.onDestroy()
        gradePredictor.close()
    }

}