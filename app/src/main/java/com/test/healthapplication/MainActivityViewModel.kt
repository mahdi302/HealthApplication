package com.test.healthapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.healthapplication.repositories.SuggestionRepository

class MainActivityViewModel : ViewModel() {

    private  var suggestions: MutableLiveData<List<String>>?=null

    fun init() {
        if (suggestions != null)
            return
        suggestions = SuggestionRepository.getSuggestions()

    }

    fun getSuggestions(): LiveData<List<String>>? {
        return suggestions
    }


}