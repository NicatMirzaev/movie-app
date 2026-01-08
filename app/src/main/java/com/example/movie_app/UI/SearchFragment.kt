package com.example.movie_app.UI

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}