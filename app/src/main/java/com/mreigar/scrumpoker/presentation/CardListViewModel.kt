package com.mreigar.scrumpoker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mreigar.scrumpoker.model.Card

class CardListViewModel : ViewModel() {

    fun getCards(): LiveData<List<Card>> = cards

    fun getSelectedCard(): LiveData<Card> = selectedCard

    private val selectedCard = MutableLiveData<Card>()
    private val cards: MutableLiveData<List<Card>> = MutableLiveData()

    fun loadUsers() {
        val pokerCardList = listOf(0, "1/2", 1, 2, 3, 5, 8, 13, 20, 40, 100, "?")
        this.cards.postValue(pokerCardList.map { Card(it.toString()) })
    }

    fun onCardSelected(card: Card) {
        selectedCard.postValue(card)
    }
}