package com.opendesa.demodk

import android.content.Intent
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private var desaRawJson = "[{\"id\":1,\"kecamatan_id\":\"1\",\"nama\":\"desa 1\"}]"
    private var desaJsonArr = JSONArray(desaRawJson)

    fun toggle(toggler:Boolean, drawer: LinearLayout ){
        if (toggler) expand(drawer)
        else collapse(drawer)
    }

    private var toggleNavbar = false
    fun navbarToggle(v: View){
        toggleNavbar = !toggleNavbar
        toggle(toggleNavbar, findViewById<LinearLayout>(R.id.drawerLayout))
    }

    private var toggleProfile = false
    fun toggleProfile(v: View){
        toggleProfile = !toggleProfile
        toggle(toggleProfile, findViewById<LinearLayout>(R.id.profilLayout))
    }
    private var toggleDesa = false
    fun toggleDesa(v: View){
        toggleDesa = !toggleDesa
        toggle(toggleDesa, findViewById<LinearLayout>(R.id.desaLayout))
    }


    private var togglePotensi = false
    fun togglePotensi(v: View){
        togglePotensi = !togglePotensi
        toggle(togglePotensi, findViewById<LinearLayout>(R.id.potensiLayout))
    }


    private var toggleStatistik = false
    fun toggleStatistik(v: View){
        toggleStatistik = !toggleStatistik
        toggle(toggleStatistik, findViewById<LinearLayout>(R.id.statistikLayout))
    }


    private var toggleUnduhan = false
    fun toggleUnduhan(v: View){
        toggleUnduhan = !toggleUnduhan
        toggle(toggleUnduhan, findViewById<LinearLayout>(R.id.unduhanLayout))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();

        for (layout in arrayOf(R.id.drawerLayout, R.id.desaLayout, R.id.profilLayout, R.id.potensiLayout, R.id.statistikLayout, R.id.unduhanLayout))
            collapse(findViewById<LinearLayout>(layout))

        val profileFragmentNames = listOf("Sejarah", "Letak Geografis", "Struktur Pemerintahan", "Visi dan Misi",)
        val profileFragmentIds = listOf(R.id.home,R.id.geografis,R.id.struktur,R.id.visimisi,)
        findViewById<RecyclerView>(R.id.profilRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(profileFragmentNames.map {item -> Helper.fromHTML(item)},
                R.layout.simple_list_item_blue)
                .apply {
                    setOnClick { position ->
                        startActivity(Intent(this@MainActivity, KecamatanActivity::class.java).apply {
                            putExtra("fragment_id", profileFragmentIds[position])
                        }) }
                }
        }


        findViewById<RecyclerView>(R.id.potensiRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(
                listOf<String>("Pariwisata", "Perdagangan", "Wisata",).map {item -> Helper.fromHTML(item)},
                R.layout.simple_list_item_blue)
        }

        findViewById<RecyclerView>(R.id.statistikRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(
                listOf<String>("Penduduk", "Pendidikan", "Kesehatan", "Program dan Bantuan", "Anggaran dan Realisasi", "Anggaran Desa").map {item -> Helper.fromHTML(item)},
                R.layout.simple_list_item_blue)
        }

        findViewById<RecyclerView>(R.id.unduhanRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(
                listOf<String>("Prosedur", "Regulasi", "Dokumen",).map {item -> Helper.fromHTML(item)},
                R.layout.simple_list_item_blue)
        }

        Thread {
            try {
                desaRawJson = Helper.getRespFromAPI("/desa")
                desaJsonArr = JSONArray(desaRawJson)
                println(desaRawJson)
            } catch (e: java.lang.Exception) {
                runOnUiThread {
                    Toast.makeText(this, "Failed getting data from the internet", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
            runOnUiThread {
                updateView()
            }
        }.start()
    }
    fun justifyListViewHeightBasedOnChildren(listView: ListView) {
        val adapter = listView.adapter ?: return
        val vg: ViewGroup = listView
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, vg)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val par = listView.layoutParams
        par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = par
        listView.requestLayout()
    }
    private fun updateView(){
        val desaListArr = ArrayList<Spanned>(desaJsonArr.length())
        for ( i in 0 until desaJsonArr.length()){
            val desaObj = desaJsonArr.getJSONObject(i)
            desaListArr.add(Helper.fromHTML(desaObj.getString("nama")))
        }

        var desaListLeft: List<Spanned> = ArrayList<Spanned>()
        var desaListRight = desaListLeft
        val partitionSize: Int = desaListArr.size/2 + 1

        if (desaListArr.size > 0) {
            desaListLeft = desaListArr.subList(0, partitionSize)
            if (partitionSize < desaListArr.size)
                desaListRight = desaListArr.subList(partitionSize, desaListArr.size)
        }

        findViewById<RecyclerView>(R.id.desaRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(desaListArr, R.layout.simple_list_item_blue)
//                .apply {
//                setOnClick { position -> runAtIndex( position ) }
//            }
        }

        findViewById<RecyclerView>(R.id.leftListDesa).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(desaListLeft, R.layout.simple_list_item_white)
//                .apply {
//                setOnClick { position -> runAtIndex( position ) }
//            }
        }
        findViewById<RecyclerView>(R.id.rightListDesa).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CustomAdapter(desaListRight, R.layout.simple_list_item_white)
//                .apply {
//                    setOnClick { position -> runAtIndex( partitionSize + position ) }
//                }
        }
    }

    private fun runAtIndex(index:Int){
        startActivity(Intent(this, KecamatanActivity::class.java).apply {
            putExtra("kecamatan_id", desaJsonArr.getJSONObject(index).getString("kecamatan_id"))
        })
    }


    fun expand(v: View){
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight: Int = v.measuredHeight

        v.layoutParams.height = 1
        v.visibility = View.VISIBLE

        val a: Animation = object : Animation(){
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    LinearLayout.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()

            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight : Int = v.measuredHeight
        val a : Animation = object : Animation(){
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f){
                    v.visibility = View.GONE
                }else{
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }
}

class CustomAdapter(private val dataSet: List<Spanned>, private val resource: Int) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var onClick: (position:Int) -> Unit = { position ->
        println("The $position Clicked")
    }
    fun setOnClick(onClick: (position:Int) -> Unit) {
        this.onClick = onClick
    }



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.itemTextView)
        val parent: View = view
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(resource, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
        viewHolder.parent.setOnClickListener {
            this.onClick(position)
        }
        println(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}