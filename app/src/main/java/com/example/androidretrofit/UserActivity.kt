package com.example.androidretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidretrofit.adapters.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set loading while getting data
        showLoading(true)
        // connect to user view model
        initViewModel()
        // connect to user recycler adapter
        initRecyclerView()
        //get data from retrofit
        fetchUserList()
        // click listener
        setClickListener()
    }

    private fun initViewModel() {
        // initialize view model to user view model
        viewModel = ViewModelProviders.of(this)
            .get(UserViewModel::class.java)
    }

    private fun initRecyclerView() {
        // setting user adapter for layout manager
        rvUser.layoutManager = LinearLayoutManager(this)
        // set up for user list divider
        setupListDivider()
        // initialize adapter to user adapter
        adapter = UserAdapter()
        rvUser.adapter = adapter
    }

    private fun setupListDivider() {
        // set up for item list divider
        val dividerItemDecoration = DividerItemDecoration(
            rvUser.context, DividerItemDecoration.VERTICAL )
        rvUser.addItemDecoration(dividerItemDecoration)
    }

    private fun fetchUserList() {
        // get data from api
        viewModel.getListUsers().observe(this, Observer {
            // set data to user adapter
            adapter.setMyUserList(it)

            showLoading(false)
        })
    }

    private fun setClickListener(){
        fabUser.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.fabUser -> {
                startActivity(Intent(this, UserFormActivity::class.java))
            }
        }
    }

    private fun showLoading(state: Boolean){
        if(state){
            pbUser.visibility = View.VISIBLE
        } else {
            pbUser.visibility = View.GONE
        }
    }
}
