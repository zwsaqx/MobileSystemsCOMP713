package com.example.gradeestimatorapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.view.View
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
        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)


        gradePredictor = GradePredictor(this)
        val location = 80f
        val workingTime = 70f
        val attendance = 90f
        val predictedGrade = gradePredictor.predict(location, workingTime, attendance)
        Toast.makeText(applicationContext,  "Estimated Grade: $predictedGrade", Toast.LENGTH_SHORT).show()

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


        val updateMessage = "Refreshed Papers!"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.my_papers_activity)
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