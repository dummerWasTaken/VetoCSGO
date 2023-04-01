/*

primeiro sempre vai ser BAN

	quando tiver 2 ban, muda pra pick

	quando tiver 2 pick, muda pra ban

	2ban > 2pick > 2ban

	o mapa que sobrar vai ser o "decider"

ban -> deixa imagem preto e branco, mostra nome cor vermelha

pick -> mantem a cor da imagem, mostra nome cor verde

 */

package com.example.veto.ui

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.veto.R
import com.example.veto.databinding.ActivityMainBinding
import com.example.veto.infra.VetoConstants
import com.example.veto.models.MapItem

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayOfMaps: ArrayList<MapItem>

    private var bannedMapSet: MutableSet<MapItem> = mutableSetOf()
    private var pickedMapSet: MutableSet<MapItem> = mutableSetOf()

    private var vetoStage = VetoConstants.VETOSTAGE.BAN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpArrayOfMaps()
        setListeners()
    }

    override fun onClick(view: View) {

        val id: Int = view.id

        pickOrBan(id)

    }

    private fun setUpArrayOfMaps() {
        arrayOfMaps = arrayListOf(
            MapItem(R.id.image_inferno, "inferno.jpg", "INFERNO", 0),
            MapItem(R.id.image_mirage, "mirage.jpg", "MIRAGE", 0),
            MapItem(R.id.image_overpass, "overpass.jpg", "OVERPASS", 0),
            MapItem(R.id.image_vertigo, "vertigo.jpg", "VERTIGO", 0),
            MapItem(R.id.image_nuke, "nuke.jpg", "NUKE", 0),
            MapItem(R.id.image_ancient, "ancient.jpg", "ANCIENT", 0),
            MapItem(R.id.image_anubis, "anubis.jpg", "ANUBIS", 0)

        )
    }


    private fun setListeners() {
        binding.imageAncient.setOnClickListener(this)
        binding.imageAnubis.setOnClickListener(this)
        binding.imageInferno.setOnClickListener(this)
        binding.imageMirage.setOnClickListener(this)
        binding.imageNuke.setOnClickListener(this)
        binding.imageOverpass.setOnClickListener(this)
        binding.imageVertigo.setOnClickListener(this)
    }

    private fun updateMapItemStage(id: Int) {

        val mapItemIndex = arrayOfMaps.indexOfFirst { it.id == id }
        val mapItem = arrayOfMaps[mapItemIndex]

        mapItem.stage = vetoStage

        arrayOfMaps[mapItemIndex] = mapItem

    }

    private fun updateMapSet(id: Int) {

        val mapItem = arrayOfMaps.first { it.id == id }

        if (vetoStage == 1) {
            bannedMapSet.add(mapItem)
        }

        if (vetoStage == 2) {
            pickedMapSet.add(mapItem)
        }

    }

    private fun checkMapSet() {

        if (bannedMapSet.count() >= 4 && pickedMapSet.count() >= 2) {

        }

        if (bannedMapSet.count() >= 2 && pickedMapSet.count() >= 2) {
            vetoStage = VetoConstants.VETOSTAGE.BAN
            return
        }

        if (bannedMapSet.count() > 2) {
            vetoStage = VetoConstants.VETOSTAGE.PICK
            return
        }

        if (pickedMapSet.count() > 2) {
            vetoStage = VetoConstants.VETOSTAGE.BAN
            return
        }

    }

    private fun pickOrBan(id: Int) {

        updateMapItemStage(id)
        updateMapSet(id)
        checkMapSet()

        when (vetoStage) {

            VetoConstants.VETOSTAGE.PICK -> {

                when (id) {
                    R.id.image_inferno -> {

                        val textView: TextView = findViewById(R.id.text_inferno)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_anubis -> {

                        val textView = findViewById<TextView>(R.id.text_anubis)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_mirage -> {

                        val textView: TextView = findViewById(R.id.text_mirage)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_nuke -> {

                        val textView: TextView = findViewById(R.id.text_nuke)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_ancient -> {

                        val textView: TextView = findViewById(R.id.text_ancient)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_overpass -> {

                        val textView: TextView = findViewById(R.id.text_overpass)
                        textView.setTextColor(getColor(R.color.green))
                    }

                    R.id.image_vertigo -> {

                        val textView: TextView = findViewById(R.id.text_vertigo)
                        textView.setTextColor(getColor(R.color.green))
                    }

                }
            }

            VetoConstants.VETOSTAGE.BAN -> {
                when (id) {
                    R.id.image_inferno -> {

                        val imageView: ImageView =
                            findViewById<View>(R.id.image_inferno) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageView.colorFilter = filter


                        val textView: TextView = findViewById(R.id.text_inferno)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_anubis -> {

                        val imageview: ImageView =
                            findViewById<View>(R.id.image_anubis) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_anubis)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_mirage -> {

                        val imageview: ImageView =
                            findViewById<View>(R.id.image_mirage) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_mirage)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_nuke -> {

                        val imageview: ImageView = findViewById<View>(R.id.image_nuke) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_nuke)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_ancient -> {

                        val imageview: ImageView =
                            findViewById<View>(R.id.image_ancient) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_ancient)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_overpass -> {

                        val imageview: ImageView =
                            findViewById<View>(R.id.image_overpass) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_overpass)
                        textView.setTextColor(getColor(R.color.red))
                    }

                    R.id.image_vertigo -> {

                        val imageview: ImageView =
                            findViewById<View>(R.id.image_vertigo) as ImageView
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0f)

                        val filter = ColorMatrixColorFilter(matrix)
                        imageview.colorFilter = filter

                        val textView: TextView = findViewById(R.id.text_vertigo)
                        textView.setTextColor(getColor(R.color.red))
                    }

                }
            }
        }
    }

}