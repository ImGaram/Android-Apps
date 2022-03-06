package com.example.rxjavaapplication.cheese_finder

import java.util.*

class CheeseSearchEngine(cheeses: Array<String>) {
    private var mCheese: Array<String> = cheeses
    private var mCheeseCount: Int = cheeses.size

    fun search(query: String): List<String> {
        var query = query
        query = query.toLowerCase()

        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val result: LinkedList<String> = LinkedList()

        for (i in 0 until mCheeseCount) {
            if (mCheese[i].toLowerCase().contains(query)) {
                result.add(mCheese[i])
            }
        }

        return result
    }


}