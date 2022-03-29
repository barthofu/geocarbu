package fr.bartho.geocarbu.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import fr.bartho.geocarbu.activity.DetailsActivity
import fr.bartho.geocarbu.adapter.StationsAdapter
import fr.bartho.geocarbu.databinding.FragmentStationsListBinding
import fr.bartho.geocarbu.utils.StationsManager

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
    ): View {

        val listView = binding.list

        val stationsAdapter = StationsAdapter(activity, StationsManager.stations)
        listView.adapter = stationsAdapter

        listView.setOnScrollListener(object : AbsListView.OnScrollListener {

            override fun onScrollStateChanged(absListView: AbsListView, scrollState: Int) {
                if (listView.lastVisiblePosition == listView.adapter
                        .count - 1 && listView.getChildAt(listView.childCount - 1)
                        .bottom <= listView.height
                ) {
                    StationsManager.next()
                    stationsAdapter.notifyDataSetChanged()
                }
            }

            override fun onScroll(absListView: AbsListView, first: Int, count: Int, total: Int) {}
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("station", stationsAdapter.getItem(position))
            startActivity(intent)
        }

        return binding.root
    }

}