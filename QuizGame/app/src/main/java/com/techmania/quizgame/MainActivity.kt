package com.techmania.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.techmania.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.buttonStartQuiz.setOnClickListener {

            val intent = Intent(this,QuizActivity::class.java)
            startActivity(intent)
            finish()

        }

        mainBinding.buttonSignOut.setOnClickListener {

            //email and password
            FirebaseAuth.getInstance().signOut()

            //google account
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build()
            val googleSignInClient = GoogleSignIn.getClient(this,gso)
            googleSignInClient.signOut().addOnCompleteListener { task ->

                if (task.isSuccessful){
                    Toast.makeText(applicationContext,"Sign out is successful",Toast.LENGTH_LONG).show()
                }

            }


            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}