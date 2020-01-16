package com.test.healthapplication.repositories

import androidx.lifecycle.MutableLiveData

object SuggestionRepository {
    private var suggestions = ArrayList<String>()


     fun getSuggestions(): MutableLiveData<List<String>> {
        setSuggestions()
        var data = MutableLiveData<List<String>>()
        data.value = suggestions
        return data
    }

    private fun setSuggestions() {
        suggestions.add("وعده های غذایی خود را حذف نکنید")
        suggestions.add("پروتئین مورد نیاز خود را هر روز مصرف نمایید")
        suggestions.add("به میزان لازم در طول روز آب بنوشید")


    }

}