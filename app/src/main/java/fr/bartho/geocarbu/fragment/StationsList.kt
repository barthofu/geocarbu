package fr.bartho.geocarbu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ListView
import androidx.fragment.app.ListFragment
import fr.bartho.geocarbu.R
import fr.bartho.geocarbu.utils.StationsManager
import fr.bartho.geocarbu.adapter.StationsAdapter
import fr.bartho.geocarbu.databinding.FragmentStationsListBinding

/**
 * A simple [Fragment] subclass.
 */
class StationsList : Fragment() {

    private lateinit var binding: FragmentStationsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentStationsListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val listView = binding.list

        val stationsAdapter = StationsAdapter(activity, StationsManager.stations)
        listView.adapter = stationsAdapter

        return binding.root
    }

}