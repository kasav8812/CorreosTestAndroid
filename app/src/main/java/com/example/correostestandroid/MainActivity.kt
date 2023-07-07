package com.example.correostestandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.correostestandroid.Adapters.CallBackSelected
import com.example.correostestandroid.Adapters.ListMailAdapter
import com.example.correostestandroid.Module.MailViewModel
import com.example.correostestandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, CallBackSelected {
    private lateinit var mailViewModel: MailViewModel
    private lateinit var mAdapter : ListMailAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mailViewModel = ViewModelProvider(this).get(MailViewModel::class.java)
        createTable()
        addListener()
        mailViewModel.callApi(this@MainActivity)
        getData()
    }

    fun addListener(){
        binding.mSearch.setOnQueryTextListener(this)
    }

    private fun createTable(){
        binding.mListMailsRecycler.layoutManager = LinearLayoutManager(this)
        mAdapter = ListMailAdapter(arrayListOf())
        binding.mListMailsRecycler.adapter = mAdapter
    }

    private fun getData(){
        mailViewModel.getMails(this@MainActivity)!!.observe(this, Observer {
            mAdapter.updateList(it,this)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mailViewModel.filterMails(newText!!)!!.observe(this, Observer {
            mAdapter.updateList(it,this)
        })
        return false
    }

    override fun refreshData() {
        mailViewModel.getMails()!!.observe(this, Observer {
            mAdapter.updateList(it,this)
        })
    }
}