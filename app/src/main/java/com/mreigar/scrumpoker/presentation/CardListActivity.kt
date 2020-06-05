package com.mreigar.scrumpoker.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mreigar.scrumpoker.R
import com.mreigar.scrumpoker.model.Card
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class CardListActivity : AppCompatActivity() {

    private val viewModel: CardListViewModel by viewModels()

    private var cardsAdapter by Delegates.notNull<CardAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getCards().observe(this, Observer {
            cardsAdapter.submitList(it)
        })

        viewModel.getSelectedCard().observe(this, Observer {
            selectedCardTitle.text = it.title
            motion.setTransition(R.id.select_card)
            motion.transitionToEnd()
        })

        cardsAdapter = CardAdapter(object : CardAdapter.CardClickListener {
            override fun onCardClick(card: Card) {
                viewModel.onCardSelected(card)
            }
        }).also {
            cards.adapter = it
        }

        viewModel.loadUsers()
    }
}