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
import com.example.alyah_cat.Home.pertemuan_2.KalkulatorActivity
import com.example.alyah_cat.Home.pertemuan_3.LoginActivity
import com.example.alyah_cat.Home.pertemuan_4.Custom1Activity
import com.example.alyah_cat.Home.pertemuan_4.WebViewActivity
import com.example.alyah_cat.Home.pertemuan_8.DelpanActivity
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

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""

        loadNewsSlider()
        loadVerticalNews()
        setupNavigation()

        binding.btnLogout.setOnClickListener { showLogoutDialog() }
    }

    private fun loadNewsSlider() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getCnnNews()
                // PERBAIKAN: Filter lebih fleksibel (bisa image atau thumbnail)
                val sliderData = response.data?.filter { it.image != null || it.thumbnail != null }?.take(5) ?: emptyList()

                if (sliderData.isNotEmpty()) {
                    val adapter = NewsAdapter(sliderData)
                    binding.viewPagerNews.adapter = adapter
                    binding.newsDotsIndicator.attachTo(binding.viewPagerNews)

                    binding.viewPagerNews.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
                    
                    // FIX: Agar slide slider tidak pindah fragment (Konflik ViewPager2)
                    val recyclerView = binding.viewPagerNews.getChildAt(0) as? androidx.recyclerview.widget.RecyclerView
                    recyclerView?.apply {
                        // Memastikan slider menangani swipe horizontal sendiri
                        addOnItemTouchListener(object : androidx.recyclerview.widget.RecyclerView.OnItemTouchListener {
                            override fun onInterceptTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: android.view.MotionEvent): Boolean {
                                when (e.action) {
                                    android.view.MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                                }
                                return false
                            }
                            override fun onTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: android.view.MotionEvent) {}
                            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
                        })
                    }

                    binding.viewPagerNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageScrollStateChanged(state: Int) {
                            super.onPageScrollStateChanged(state)
                            binding.viewPagerNews.requestDisallowInterceptTouchEvent(state != ViewPager2.SCROLL_STATE_IDLE)
                        }
                    })

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
                    hideSlider()
                }
            } catch (e: Exception) {
                hideSlider()
            }
        }
    }

    private fun hideSlider() {
        binding.viewPagerNews.visibility = View.GONE
        binding.newsDotsIndicator.visibility = View.GONE
    }

    private fun loadVerticalNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = NewsApiClient.apiService.getAntaraNews()
                val listData = response.data ?: emptyList()

                if (listData.isNotEmpty()) {
                    val adapter = NewsListAdapter(listData.take(15))
                    binding.rvNewsList.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvNewsList.adapter = adapter
                    // PENTING: Matikan nested scroll agar RecyclerView memanjang di dalam NestedScrollView
                    binding.rvNewsList.isNestedScrollingEnabled = false
                    binding.rvNewsList.visibility = View.VISIBLE
                } else {
                    binding.rvNewsList.visibility = View.GONE
                    if (isAdded) {
                        Toast.makeText(requireContext(), "Daftar berita kosong", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "Gagal memuat berita bawah: ${e.message}", Toast.LENGTH_SHORT).show()
                }
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
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            bottomNav?.selectedItemId = R.id.about
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

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
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
