package com.mstar.dragdrop3kotlin

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mstar on 03-01-2022.
 */
class SectionHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var sectionTitle: TextView = itemView.findViewById(R.id.tvSectionHeader)
}