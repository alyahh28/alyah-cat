package com.example.alyah_cat.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.databinding.ActivityLoginBinding // Pastikan import binding ini muncul

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString()

            if (username.isNotEmpty()) {
                Toast.makeText(this, "Halo, $username", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Silahkan isi username", Toast.LENGTH_SHORT).show()
            }
        }
    }
}