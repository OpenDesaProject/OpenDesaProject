package com.opendesa.demodk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.opendesa.demodk.ui.desa.*
import org.json.JSONObject

class KecamatanActivity : AppCompatActivity() {
    private var kecamatanRawJson = "{\"id\":1,\"kabupaten_id\":\"1\",\"nama\":\"Kecamatan 1\",\"desa_count\":\"4\",\"profil\":{\"id\":1,\"kecamatan_id\":\"1\",\"alamat\":null,\"visi\":\"<h1>Visiay<\\/h1><p><b>Lorem ipsum<\\/b> dolor sit amet consectetur adipisicing elit. Ipsum, ex nihil eos voluptate officiis debitis maiores omnis earum quam ratione ea. <i>Illo nam facilis quae<\\/i> asperiores provident odio ducimus amet sapiente iure nihil <b>officiis<\\/b>, minima labore hic atque nostrum cumque enim deserunt? Veniam, culpa odit! Pariatur laudantium quod eveniet quas?<\\/p>\",\"misi\":\"<h1>Misiay<\\/h1><p><b>Lorem ipsum<\\/b> dolor sit amet consectetur adipisicing elit. Ipsum, ex nihil eos voluptate officiis debitis maiores omnis earum quam ratione ea. <i>Illo nam facilis quae<\\/i> asperiores provident odio ducimus amet sapiente iure nihil <b>officiis<\\/b>, minima labore hic atque nostrum cumque enim deserunt? Veniam, culpa odit! Pariatur laudantium quod eveniet quas?<\\/p>\",\"logo\":\"http:\\/\\/127.0.0.1:8000\\/image.png\",\"bagan_struktur\":\"http:\\/\\/127.0.0.1:8000\\/image.png\",\"photo_camat\":\"http:\\/\\/127.0.0.1:8000\\/user.png\",\"camat\":\"Nama Camat\",\"sekretaris\":null,\"kepsek_pemerintahan_umum\":null,\"kepsek_kesejahteraan_masyarakat\":null,\"kepsek_pemberdayaan_masyarakat\":null,\"kepsek_pelayanan_umum\":null,\"kepsek_trantib\":null,\"sambutan_camat\":\"<p><b>Assalamualaikum Wr. Wb.<\\/b> <br>dolor sit amet consectetur adipisicing elit. Ipsum, ex nihil eos voluptate officiis debitis maiores omnis earum quam ratione ea. <i>Illo nam facilis quae<\\/i> asperiores provident odio ducimus amet sapiente iure nihil <b>officiis<\\/b>, minima labore hic atque nostrum cumque enim deserunt? Veniam, culpa odit! Pariatur laudantium quod eveniet quas?<\\/p>\"},\"data_umum\":{\"id\":1,\"kecamatan_id\":\"1\",\"luas_wilayah\":\"22.0\",\"batas_utara\":\"12\",\"batas_timur\":\"12\",\"batas_selatan\":\"12\",\"batas_barat\":\"12\"},\"desa\":[{\"id\":1,\"kecamatan_id\":\"1\",\"nama\":\"desa 1\"},{\"id\":2,\"kecamatan_id\":\"1\",\"nama\":\"desa 2\"},{\"id\":3,\"kecamatan_id\":\"1\",\"nama\":\"desa 3\"},{\"id\":4,\"kecamatan_id\":\"1\",\"nama\":\"desa 4\"}]}"
    private var kecamatanJsonObj = JSONObject(kecamatanRawJson)
    private val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kecamatan)
        val kecamatanId = 1 // intent.getStringExtra("kecamatan_id")

        val loadingFragment= LoadingFragment()
        val homeFragment= HomeFragment().apply { arguments = bundle }
        val geografisFragment= GeografisFragment().apply { arguments = bundle }
        val strukturFragment= StrukturFragment().apply { arguments = bundle }
        val visiMisiFragment = VisiMisiFragment().apply { arguments = bundle }

        setCurrentFragment(loadingFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFragment)
                R.id.geografis->setCurrentFragment(geografisFragment)
                R.id.struktur->setCurrentFragment(strukturFragment)
                R.id.visimisi->setCurrentFragment(visiMisiFragment)
            }
            true
        }
        bottomNavigationView.visibility = View.INVISIBLE


        Thread {
            try {
                kecamatanRawJson = Helper.getRespFromAPI("/kecamatan/$kecamatanId")
                kecamatanJsonObj = JSONObject(kecamatanRawJson)
                println(kecamatanRawJson)
            } catch (e: java.lang.Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Failed getting data from the internet", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
            runOnUiThread {
                onDataLoaded()
                when(intent.getIntExtra("fragment_id",R.id.home)){
                    R.id.home->setCurrentFragment(homeFragment)
                    R.id.geografis->setCurrentFragment(geografisFragment)
                    R.id.struktur->setCurrentFragment(strukturFragment)
                    R.id.visimisi->setCurrentFragment(visiMisiFragment)
                    else -> setCurrentFragment(homeFragment)
                }
                bottomNavigationView.visibility = View.VISIBLE
            }
        }.start()


    }

    private fun onDataLoaded(){
        val profil = kecamatanJsonObj.optJSONObject("profil")
        if (profil == null){
            Toast.makeText(this, "Kecamatan ini tidak memiliki profil", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        bundle.putString("nama", kecamatanJsonObj.getString("nama"))
        bundle.putString("visi", profil.getString("visi"))
        bundle.putString("misi", profil.getString("misi"))
        bundle.putString("logo", profil.getString("logo"))
        bundle.putString("sambutan_camat", profil.getString("sambutan_camat"))
        bundle.putString("bagan_struktur", profil.getString("bagan_struktur"))
        bundle.putString("photo_camat", profil.getString("photo_camat"))
        bundle.putString("sambutan_camat", profil.getString("sambutan_camat"))
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}