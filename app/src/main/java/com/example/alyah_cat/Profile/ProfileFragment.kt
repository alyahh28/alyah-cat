package com.example.alyah_cat.Profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_cat.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Profil Mahasiswa"

        // Logika Klik Button Sosial Media
        binding.btnInstagram.setOnClickListener {
            openUrl("https://www.instagram.com/username_kamu")
        }

        binding.btnLinkedIn.setOnClickListener {
            openUrl("https://www.linkedin.com/in/username_kamu")
        }

        binding.btnGithub.setOnClickListener {
            openUrl("https://github.com/username_kamu")
        }
    }

    // Fungsi helper untuk membuka URL
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}