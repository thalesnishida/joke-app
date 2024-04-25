package com.thalesnishida.jokeapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thalesnishida.jokeapp.R
import com.thalesnishida.jokeapp.model.Category
import com.thalesnishida.jokeapp.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter
import java.net.CacheResponse

class HomeFragment: Fragment() {

    private lateinit var progress: ProgressBar
    private lateinit var presenter: HomePresenter
    private val adapter = GroupieAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = view.findViewById(R.id.progress_bar)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = adapter

        presenter.showCategories()
    }

    fun showProgress(){
        progress.visibility = View.VISIBLE
    }

    fun showError(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun hideProgress(){
        progress.visibility = View.GONE
    }

    fun showCategories(response: List<Category>){
        val categories = response.map{ CategoryItem(it)}
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }
}