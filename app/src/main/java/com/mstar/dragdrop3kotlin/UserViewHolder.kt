package com.mstar.dragdrop3kotlin

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mstar on 03-01-2022.
 */
class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var cvView: CardView = itemView.findViewById(R.id.cvView)
    var userAvatar: ImageView = itemView.findViewById(R.id.ivProfile)
    var username: TextView = itemView.findViewById(R.id.tvName)
    var reorderView: ImageView = itemView.findViewById(R.id.ivRecorder)
    var ivDefault: ImageView = itemView.findViewById(R.id.ivDefault)
}