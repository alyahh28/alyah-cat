package com.example.alyah_cat.Note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alyah_cat.data.AppDatabase
import com.example.alyah_cat.data.entity.NoteEntity
import com.example.alyah_cat.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class FragmentNote : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())

        // Tombol Tambah Catatan
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(requireContext(), NoteFormActivity::class.java)
            startActivity(intent)
        }

        loadNotes()
    }

    override fun onResume() {
        super.onResume()
        loadNotes() // Refresh data saat kembali dari NoteFormActivity
    }

    fun deleteNote(note: NoteEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            AppDatabase.getInstance(requireContext()).noteDao().deleteNote(note)
            loadNotes() // Refresh data setelah hapus
        }
    }

    private fun loadNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val db = AppDatabase.getInstance(requireContext())
            val notes = db.noteDao().getAllNotes()
            binding.rvNotes.adapter = NoteAdapter(notes, this@FragmentNote)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
