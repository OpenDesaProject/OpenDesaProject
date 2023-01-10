package com.example.opendesa.ui.profildesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.util.Helper
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentDesaVisiMisiBinding
import com.example.opendesa.repository.Repository
import com.example.opendesa.ui.home.HomeViewModel
import com.example.opendesa.ui.home.HomeViewModelFactory

class VisiMisiFragment : Fragment(R.layout.fragment_desa_visi_misi) {
    private lateinit var visiMisiViewModel : VisiMisiViewModel
    private var _binding:FragmentDesaVisiMisiBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository()
        val viewModelFactory = VisiMisiViewModelFactory(repository)
        visiMisiViewModel = ViewModelProvider(this, viewModelFactory)[VisiMisiViewModel::class.java]
        visiMisiViewModel.getProfil()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDesaVisiMisiBinding.inflate(inflater, container, false)
        _binding!!.visiMisiViewModel = visiMisiViewModel
        _binding!!.lifecycleOwner = this
        val root = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        activity?.title = "Visi Misi"

//        view.findViewById<TextView>(R.id.visi).text = bundle!!.getString("visi")
//            ?.let { Helper.fromHTML(it) }
//        view.findViewById<TextView>(R.id.misi).text = bundle.getString("misi")
//            ?.let { Helper.fromHTML(it) }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}