package com.mstar.dragdrop3kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Mstar on 03-01-2022.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialView()
    }

    private fun initialView() {
        val userRecyclerView = findViewById<RecyclerView>(R.id.lstUser)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        val usersData = UsersData()
        val usersList = usersData.getUsersList()

        val adapter = UserListAdapter()
        val swipeAndDragHelper = SwipeAndDragHelper(adapter)
        val touchHelper = ItemTouchHelper(swipeAndDragHelper)
        adapter.setTouchHelper(touchHelper)
        userRecyclerView.adapter = adapter
        touchHelper.attachToRecyclerView(userRecyclerView)
        adapter.setUserList(usersList)
    }
}