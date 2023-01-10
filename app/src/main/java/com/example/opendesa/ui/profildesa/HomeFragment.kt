package com.example.opendesa.ui.profildesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentDesaHomeBinding
import com.example.opendesa.repository.Repository

class HomeFragment : Fragment(R.layout.fragment_desa_home) {
    private lateinit var visiMisiViewModel : VisiMisiViewModel
    private var _binding: FragmentDesaHomeBinding?=null
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
        _binding = FragmentDesaHomeBinding.inflate(inflater, container, false)
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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        activity?.title = "Sejarah"
//        val bundle = this.arguments
//        view.findViewById<TextView>(R.id.welcome).text = bundle?.getString("sambutan_camat")
//            ?.let { Helper.fromHTML(it) }
//         val image = bundle!!.getString("logo")?.let { Helper.getImageFromURL(it) }
//         if (image != null)
//         view.findViewById<ImageView>(R.id.logoHomeView).setImageDrawable(image)
//    }
}