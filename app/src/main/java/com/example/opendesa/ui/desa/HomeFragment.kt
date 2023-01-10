package com.opendesa.demodk.ui.desa

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.opendesa.util.Helper
import com.example.opendesa.R


class HomeFragment : Fragment(R.layout.fragment_desa_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Sejarah"
        val bundle = this.arguments
        view.findViewById<TextView>(R.id.welcome).text = bundle?.getString("sambutan_camat")
            ?.let { Helper.fromHTML(it) }
        val image = bundle!!.getString("logo")?.let { Helper.getImageFromURL(it) }
        if (image != null)
            view.findViewById<ImageView>(R.id.logoHomeView).setImageDrawable(image)
    }
}