package com.example.alyah_cat.Settings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.alyah_cat.R

class SettingsAdapter(context: Context, data: List<SettingModel>) :
    ArrayAdapter<SettingModel>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false)
        val item = getItem(position)

        // Hubungkan data ke widget di item_setting.xml
        view.findViewById<TextView>(R.id.tvSettingTitle).text = item?.title
        view.findViewById<TextView>(R.id.tvSettingDesc).text = item?.desc

        val imgIcon = view.findViewById<ImageView>(R.id.imgIcon)
        imgIcon.setImageResource(item?.iconResId ?: R.drawable.ic_settings)

        return view
    }
}