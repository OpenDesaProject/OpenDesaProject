package com.opendesa.demodk.ui.desa


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.opendesa.util.Helper
import com.example.opendesa.R

class GeografisFragment : Fragment(R.layout.fragment_desa_geografis) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Geografis"
        val bundle = this.arguments
        val image = bundle!!.getString("photo_camat")?.let { Helper.getImageFromURL(it) }
        if (image != null)
            view.findViewById<ImageView>(R.id.imgGeografis).setImageDrawable(image)

    }
}