package com.example.alyah_cat.Home.pertemuan_3

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.R
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Inisialisasi View
        val etNama = findViewById<EditText>(R.id.et_reg_nama)
        val etEmail = findViewById<EditText>(R.id.et_reg_email)
        val etTgl = findViewById<EditText>(R.id.et_reg_tgl)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val etUser = findViewById<EditText>(R.id.et_reg_username)
        val etPass = findViewById<EditText>(R.id.et_reg_pass)
        val etConfirm = findViewById<EditText>(R.id.et_reg_confirm_pass)
        val btnSelanjutnya = findViewById<Button>(R.id.btn_selanjutnya)

        // DatePicker Logic
        etTgl.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                etTgl.setText("$day/${month + 1}/$year")
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnSelanjutnya.setOnClickListener {
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            // Simpan ke SharedPreferences
            val editor = sharedPref.edit()
            editor.putString("reg_nama", etNama.text.toString())
            editor.putString("reg_email", etEmail.text.toString())
            editor.putString("reg_tgl", etTgl.text.toString())
            editor.putString("reg_gender", gender)
            editor.putString("reg_user", etUser.text.toString())
            editor.putString("reg_pass", etPass.text.toString())
            editor.putString("reg_confirm", etConfirm.text.toString())
            editor.apply()

            // Pindah ke Halaman Validasi
            startActivity(Intent(this, ValidationActivity::class.java))
        }
    }
}