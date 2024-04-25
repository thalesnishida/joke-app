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

    fun onComplete(){
        view.hideProgress()
    }
    fun onSuccess(categoreis: List<String>){
        val response = categoreis.map { Category(it, 0xFFFF0000) }
        view.showCategories(response)
    }

    fun onFailure(message: String){
        view.showError(message)
    }
    private fun fakeRequest(){
        Handler(Looper.getMainLooper()).postDelayed({
          val categories = arrayListOf<String>(
              "Categoria 1",
              "Categoria 2",
              "Categoria 3"
          )

          // onSuccess(categories)

          onFailure("Algo deu errado. Tente novamenta mais tarde!")
          onComplete()
        }, 2000)
    }
}