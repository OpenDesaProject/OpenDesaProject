package com.example.opendesa.ui.profildesa


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.opendesa.util.Helper
import com.example.opendesa.R

class StrukturFragment : Fragment(R.layout.fragment_desa_struktur) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        activity?.title = "Struktur Pemerintahan"
//        val image = bundle!!.getString("bagan_struktur")?.let { Helper.getImageFromURL(it) }
//        if (image != null)
//            view.findViewById<ImageView>(R.id.imgStruktur).setImageDrawable(image)

    }
}