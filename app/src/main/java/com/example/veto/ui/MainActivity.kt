/*
FEITO:
primeiro sempre vai ser BAN
quando tiver 2 ban, muda pra pick
quando tiver 2 pick, muda pra ban
2ban > 2pick > 2ban
ban -> deixa imagem preto e branco, mostra nome cor vermelha
pick -> mantem a cor da imagem, mostra nome cor verde
----------------------------------------------------------------------------------------------------
A FAZER:
ultimo mapa do mapSet tem que ser decider, ou seja, texto tem que mudar pra verde
nao permitir que a mesma imagem seja clicada mais que uma vez (se for clicada n pode acontecer nada)
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

        if (vetoStage == 0) {

        }
        if (vetoStage == 1) {
            bannedMapSet.add(mapItem)
        }
        if (vetoStage == 2) {
            pickedMapSet.add(mapItem)
        }
    }

    private fun checkMapSet() {

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
        if (bannedMapSet.count() >= 4 && pickedMapSet.count() >= 2) {
            vetoStage = VetoConstants.VETOSTAGE.IDLE
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
                        changeTextColor(R.id.text_inferno)
                    }

                    R.id.image_anubis -> {
                        changeTextColor(R.id.text_anubis)
                    }

                    R.id.image_mirage -> {
                        changeTextColor(R.id.text_mirage)
                    }

                    R.id.image_nuke -> {
                        changeTextColor(R.id.text_nuke)
                    }

                    R.id.image_ancient -> {
                        changeTextColor(R.id.text_ancient)
                    }

                    R.id.image_overpass -> {
                        changeTextColor(R.id.text_overpass)
                    }

                    R.id.image_vertigo -> {
                        changeTextColor(R.id.text_vertigo)
                    }
                }
            }

            VetoConstants.VETOSTAGE.BAN -> {
                when (id) {
                    R.id.image_inferno -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_inferno)
                    }

                    R.id.image_anubis -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_anubis)
                    }

                    R.id.image_mirage -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_mirage)
                    }

                    R.id.image_nuke -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_nuke)
                    }

                    R.id.image_ancient -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_ancient)
                    }

                    R.id.image_overpass -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_overpass)
                    }

                    R.id.image_vertigo -> {
                        changeImageColor(id)
                        changeTextColor(R.id.text_vertigo)
                    }

                }
            }
        }
    }

    private fun changeTextColor(id: Int) {

        val textView: TextView = findViewById(id)
        when (vetoStage) {
            0 -> {
                textView.setTextColor(getColor(R.color.green))
            }
            1 -> {
                textView.setTextColor(getColor(R.color.red))
            }
            2 -> {
                textView.setTextColor(getColor(R.color.green))
            }
        }
    }

    private fun changeImageColor(id: Int) {

        val imageView: ImageView = findViewById<View>(id) as ImageView
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        imageView.colorFilter = filter
    }
}