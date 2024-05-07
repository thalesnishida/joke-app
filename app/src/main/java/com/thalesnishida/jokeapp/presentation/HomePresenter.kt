package com.thalesnishida.jokeapp.presentation

import android.os.Handler
import android.os.Looper
import com.thalesnishida.jokeapp.model.Category
import com.thalesnishida.jokeapp.view.HomeFragment

class HomePresenter(private val view: HomeFragment) {

    fun showCategories(){
        view.showProgress()
        fakeRequest()
    }

    private fun onComplete() = view.hideProgress()

    private fun onSuccess(categories: List<String>){
        val response = categories.map { Category(it, 0xFFFF0000) }
        view.showCategories(response)
    }

    fun onFailure(message: String) = view.showError(message)

    private fun fakeRequest(){
        Handler(Looper.getMainLooper()).postDelayed({
          val categories = arrayListOf<String>(
              "Categoria 1",
              "Categoria 2",
              "Categoria 3"
          )

          onSuccess(categories)

          // onFailure("Algo deu errado. Tente novamenta mais tarde!")
          onComplete()
        }, 2000)
    }
}