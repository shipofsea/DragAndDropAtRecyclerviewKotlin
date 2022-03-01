package com.mstar.dragdrop3kotlin

import android.annotation.SuppressLint
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

/**
 * Created by Mstar on 03-01-2022.
 */
class UserListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    SwipeAndDragHelper.ActionCompletionContract {

    private val USER_TYPE = 1
    private val HEADER_TYPE = 2
    private lateinit var usersList: MutableList<User>
    private var touchHelper: ItemTouchHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == HEADER_TYPE) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user_list_section_header, parent, false)
            return SectionHeaderViewHolder(view)
        }
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_user_list_item, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)

        if (itemViewType == USER_TYPE) {
            (holder as UserViewHolder).username.text = usersList[position].name
            Glide.with(holder.itemView).load(usersList[position].imageUrl).into(holder.userAvatar)
            holder.ivDefault.visibility = View.GONE

            if (usersList[position].defaultStatus == 1) holder.ivDefault.visibility =
                View.VISIBLE
            holder.reorderView.setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    touchHelper!!.startDrag(holder)
                }
                false
            }
        } else {
            val headerViewHolder = holder as SectionHeaderViewHolder
            headerViewHolder.sectionTitle.text = usersList[position].type
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (TextUtils.isEmpty(usersList[position].name)) {
            HEADER_TYPE
        } else {
            USER_TYPE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(usersList: MutableList<User>) {
        this.usersList = ArrayList()
        this.usersList = usersList
        notifyDataSetChanged()
    }

    override fun onViewMoved(oldPosition: Int, newPosition: Int) {
        val targetUser = usersList[oldPosition]
        val userDefaultStatus: Int = targetUser.defaultStatus
        val preUser: User = if (oldPosition < newPosition) {
            usersList[newPosition]
        } else {
            usersList[newPosition - 1]
        }

        if (userDefaultStatus == 1 && preUser.type != "Developers") {
            for (i in oldPosition downTo newPosition + 1) {
                Collections.swap(usersList, i, i - 1)
            }
            notifyItemMoved(oldPosition, newPosition)
            return
        }
        usersList.removeAt(oldPosition)
        targetUser.type = preUser.type
        usersList.add(newPosition, targetUser)
        notifyItemMoved(oldPosition, newPosition)
    }

    override fun onViewSwiped(position: Int) {
        usersList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setTouchHelper(touchHelper: ItemTouchHelper?) {
        this.touchHelper = touchHelper
    }

}