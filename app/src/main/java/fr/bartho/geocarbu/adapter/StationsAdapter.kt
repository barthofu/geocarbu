package fr.bartho.geocarbu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import fr.bartho.geocarbu.R
import fr.bartho.geocarbu.data.Station

class StationsAdapter : BaseAdapter {

    private var context: FragmentActivity? = null
    private var list: ArrayList<Station>

    constructor(context: FragmentActivity?, list: ArrayList<Station>) {
        this.context = context
        this.list = list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(i: Int): Station {
        println("getItem")
        return list[i]
    }

    override fun getItemId(i: Int): Long {
        println("getItemId")
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        println("getView")

        lateinit var layoutItem: ConstraintLayout
        val mInflater: LayoutInflater = LayoutInflater.from(context)
        val station: Station = list[position]

        layoutItem =
            if (convertView == null) mInflater.inflate(R.layout.item_layout, parent, false) as ConstraintLayout
            else convertView as ConstraintLayout

        val name: TextView = layoutItem.findViewById(R.id.name)
        val address: TextView = layoutItem.findViewById(R.id.address)

        name.text = station.fields?.name
        address.text = station.fields?.address

        return layoutItem
    }

}