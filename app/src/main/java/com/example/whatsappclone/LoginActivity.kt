package com.example.whatsappclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsappclone.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
            val email = input_email.text.toString()
            val password = input_password.text.toString()
            loginUser(email, password)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }

    private fun loginUser(email: String, password: String) {
        if (email.isNotBlank() && password.isNotBlank()) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intentMain = Intent(this, MainActivity::class.java)
                        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intentMain)
                        toast("User berhasil Login")
                        finish()
                    } else {
                        it.exception?.message?.let {
                            toast("Gagal Login User.\nError : $it")
                        }
                    }
                }

            return
        }
        val message =
            if (email.isBlank()) "Email" else "Password "
        toast("$message tidak boleh kosong")
    }
}