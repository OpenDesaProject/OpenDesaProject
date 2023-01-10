package com.example.opendesa.ui.keluhan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentComplaintBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComplaintFragment : Fragment() {
  private var _binding: FragmentComplaintBinding? = null
  private val binding get() = _binding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentComplaintBinding.inflate(inflater, container, false)
    return binding?.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}