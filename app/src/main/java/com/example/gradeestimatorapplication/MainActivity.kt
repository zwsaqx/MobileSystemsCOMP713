package com.example.gradeestimatorapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gradeestimatorapplication.ui.theme.GradeEstimatorApplicationTheme
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeEstimatorApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
                    setContentView(R.layout.login_activity)
                }
            }
        }
    }

    fun loginButtonClick(view: View) {
        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginResultTextView = findViewById<TextView>(R.id.loginResultTextView)

        val enteredUsername = usernameEditText.text.toString()
        val enteredPassword = passwordEditText.text.toString()

        val correctCredentials = mapOf(
            "" to "",  //for testing, allows you to login when the fields are empty
            "john" to "password",
            // usernames and password list goes here
        )

        if (correctCredentials.containsKey(enteredUsername) && correctCredentials[enteredUsername] == enteredPassword) {
            loginResultTextView.text = "Login Successful"
            setContentView(R.layout.menu_activity)

        } else {
            loginResultTextView.text = "Login Failed"
        }
    }

    fun forgotButtonClick(view: View) { setContentView(R.layout.forgot_password_activity)}
    fun myPapersButtonClick(view: View) { setContentView(R.layout.my_papers_activity) }
    fun menuButtonClick(view: View) { setContentView(R.layout.menu_activity) }
    fun signupButtonClick(view: View) { setContentView(R.layout.register_activity) }
    fun logoutButtonClick(view: View) { setContentView(R.layout.login_activity) }

    fun refreshButtonClick(view: View) {
        val updateMessage = "Refreshed Papers!"
        Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.my_papers_activity) }

    fun feedbackButtonClick(view: View) { setContentView(R.layout.feedback_activity) }

    fun feedbackSubmitButtonClick(view: View) {
        if (1 == 1) {   //will need to implement this later
            val updateMessage = "Feedback Sent!"
            Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
            //setContentView(R.layout.feedback_activity)

        } else {
            val updateMessage = "Feedback Failed!"
            Toast.makeText(applicationContext, updateMessage, Toast.LENGTH_SHORT).show()
        }
        setContentView(R.layout.feedback_activity) }

    fun aboutButtonClick(view: View) { setContentView(R.layout.about_activity) }

    fun settingsButtonClick(view: View) { setContentView(R.layout.settings_activity) }



}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "test $name!",
            modifier = modifier
    )

}
