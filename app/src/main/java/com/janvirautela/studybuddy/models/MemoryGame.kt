package com.janvirautela.studybuddy.models

import com.janvirautela.studybuddy.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize: BoardSize, private val customImages: List<String>?){

    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var numCardFlips = 0
    private var indexOfSingleSelectedCard: Int? = null

    init {
        if (customImages == null) {
            val chosenImages: List<Int> = DEFAULT_ICONS.shuffled().take(boardSize.getNumFound())
            val randomizedImages: List<Int> = (chosenImages + chosenImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it) }
        } else {
            val randomizedImages = (customImages + customImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it.hashCode(), it) }
        }
    }

    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card = cards[position]
        //0 cards previously flipped over
        //1 card previously flipped over
        //2 cards previously flipped over
        var foundMatch = false
        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        }else{
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifier !=cards[position2].identifier){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for (card in cards){
            if (!card.isMatched){
            card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumFound()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumMoves(): Int {
        return numCardFlips / 2
    }
}