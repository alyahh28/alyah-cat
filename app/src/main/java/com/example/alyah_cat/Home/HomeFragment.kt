package com.example.alyah_cat.Home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.alyah_cat.Home.Message.MessageFragment
import com.example.alyah_cat.Home.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.Home.pertemuan_4.Custom1Activity
import com.example.alyah_cat.Home.pertemuan_4.WebViewActivity
import com.example.alyah_cat.Home.pertemuan_8.DelpanActivity
import com.example.alyah_cat.data.api.CatFactApiClient
import com.example.alyah_cat.data.api.NewsApiClient
import com.example.alyah_cat.R
import com.example.alyah_cat.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""

        // 2. Load Data API
        loadNewsSlider()    // Slider Atas (CNN)
        loadVerticalNews()  // List Bawah (Antara)
        loadCatFact()       // Info Harian

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
            loadNewsSlider()
            loadVerticalNews()
        }

        // 3. Navigasi Menu
        setupNavigation()

        binding.btnLogout.setOnClickListener { showLogoutDialog() }
    }

    private fun loadNewsSlider() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getCnnNews()
                // Ambil 5 berita yang memiliki gambar
                val sliderData = response.data?.filter { it.image != null || it.thumbnail != null }?.take(5) ?: emptyList()

                if (sliderData.isNotEmpty()) {
                    val adapter = NewsAdapter(sliderData)
                    binding.viewPagerNews.adapter = adapter
                    binding.newsDotsIndicator.attachTo(binding.viewPagerNews)

                    // FIX: Mencegah konflik swipe antara Slider dan BaseActivity (ViewPager2 di dalam ViewPager2)
                    binding.viewPagerNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageScrollStateChanged(state: Int) {
                            super.onPageScrollStateChanged(state)
                            binding.viewPagerNews.parent.requestDisallowInterceptTouchEvent(state != ViewPager2.SCROLL_STATE_IDLE)
                        }
                    })

                    // Efek Visual "Peek" (Mengintip item samping)
                    val transformer = CompositePageTransformer().apply {
                        addTransformer(MarginPageTransformer(40))
                        addTransformer { page, position ->
                            val r = 1 - Math.abs(position)
                            page.scaleY = 0.85f + r * 0.15f
                        }
                    }
                    binding.viewPagerNews.setPageTransformer(transformer)
                    binding.viewPagerNews.offscreenPageLimit = 3

                    binding.viewPagerNews.visibility = View.VISIBLE
                    binding.newsDotsIndicator.visibility = View.VISIBLE
                } else {
                    binding.viewPagerNews.visibility = View.GONE
                    binding.newsDotsIndicator.visibility = View.GONE
                }
            } catch (e: Exception) {
                binding.viewPagerNews.visibility = View.GONE
                binding.newsDotsIndicator.visibility = View.GONE
            }
        }
    }

    private fun loadVerticalNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getAntaraNews()
                val listData = response.data?.take(10) ?: emptyList()

                if (listData.isNotEmpty()) {
                    val adapter = NewsListAdapter(listData)
                    binding.rvNewsList.apply {
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(requireContext())
                        isNestedScrollingEnabled = false
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal muat berita list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupNavigation() {
        binding.btnkalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), KalkulatorActivity::class.java))
        }

        binding.btncustom1.setOnClickListener {
            startActivity(Intent(requireContext(), Custom1Activity::class.java))
        }

        binding.btnMessage.setOnClickListener {
            startActivity(Intent(requireContext(), MessageFragment::class.java))
        }

        binding.btnwebview.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnLayanan.setOnClickListener {
            startActivity(Intent(requireContext(), DelpanActivity::class.java))
        }

        binding.btnPerangkat.setOnClickListener {
            val intent = Intent(requireContext(), com.example.alyah_cat.Home.pertemuan_10.TenthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil info harian."
            }
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}