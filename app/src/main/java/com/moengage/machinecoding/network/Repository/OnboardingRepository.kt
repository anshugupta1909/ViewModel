package com.moengage.machinecoding.network.Repository

import com.moengage.machinecoding.network.model.Question

class OnboardingRepository {

    fun fetchQuestions(): List<Question> {
        // Mocked API response
        return listOf(
            Question( "What is your name?", "text"),
            Question( "What is your age?", "number"),
            Question( "What is your birthdate?", "date")
        )
    }
}