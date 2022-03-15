package fr.bartho.geocarbu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import fr.bartho.geocarbu.R

class StationsAdapter(private var context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return 10
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        lateinit var layoutItem: ConstraintLayout
        val mInflater: LayoutInflater = LayoutInflater.from(context)

        layoutItem =
            if (convertView == null) mInflater.inflate(R.layout.item_layout, parent, false) as ConstraintLayout
            else convertView as ConstraintLayout

        val textView: TextView = layoutItem.findViewById(R.id.textView)

        textView.text = "Station n°${position}"

        return layoutItem
    }

}