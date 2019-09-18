package com.timkhakimov.navigator.base

import java.util.*

/**
 * Created by Timur Khakimov on 18.09.2019
 * custom queue with removeAll() method for getting all elements in queue
 */
class Queue<T> {

    private var deque : Deque<T>

    init {
        deque = ArrayDeque<T>()
    }

    fun isEmpty() : Boolean {
        return deque.isEmpty()
    }

    fun add(item : T) {
        deque.addLast(item)
    }

    fun peek() : T {
        return deque.peekFirst()
    }

    fun remove() : T {
        return deque.removeFirst()
    }

    fun removeAll() : Queue<T> {
        val copiedQueue = Queue<T>()
        while (!isEmpty()) {
            copiedQueue.add(remove())
        }
        return copiedQueue
    }
}