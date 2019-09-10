package com.timkhakimov.navigator.base

import java.util.*

/**
 * Created by Timur Khakimov on 05.08.2019.
 * Навигатор состояний / фрагментов
 * Класс работает с абстракциями в виде дженериков и интерфейса StateSwitchHandler, так что можно использовать не только для переключения фрагментов
 * E - константа, соответствующая какому-то состоянию/фрагменту
 * P - параметры (во фрагментах это будет Bundle)
 * @param stateSwitchHandler - интерфейс для обработки переключения состояний / фрагментов
 */
class SwitchStateNavigator<E, P>(var stateSwitchHandler: StateSwitchHandler<E, P>)
    : StateNavigator<E, P>, StateSwitchHandler<E, P> {

    private var commandsQueue: Deque<SwitchStateOperation<E, P>> = ArrayDeque<SwitchStateOperation<E, P>>()
    private var statesStack: Stack<E> = Stack()
    private var stateChangeListeners = mutableListOf<OnStateChangeListener<E>>()

    override fun addSwitchStateOperation(direction: SwitchStateDirection, state: E?, params: P?) {
        addSwitchStateOperation(SwitchStateOperation(direction, state, params))
    }

    override fun addSwitchStateOperation(operation: SwitchStateOperation<E, P>) {
        commandsQueue.addLast(operation)
    }

    override fun getCurrentState(): E? {
        if (statesStack.isEmpty()) {
            return null
        }
        return statesStack.peek()
    }

    override fun copyStatesStack(): Stack<E> {

        var tempStack = Stack<E>()
        while (!statesStack.isEmpty()) {
            tempStack.push(statesStack.pop())
        }
        var copiedStack = Stack<E>()
        while (!tempStack.isEmpty()) {
            var state = tempStack.pop()
            statesStack.push(state)
            copiedStack.push(state)
        }
        return copiedStack
    }

    override fun hasOperationsInQueue(): Boolean {
        return !commandsQueue.isEmpty()
    }

    override fun handleOperations() {
        while (hasOperationsInQueue()) {
            handleNextOperation()
        }
    }

    override fun handleNextOperation() {
        handleOperation(commandsQueue.removeFirst())
    }

    override fun handleOperation(operation: SwitchStateOperation<E, P>) {
        when (operation.direction) {
            SwitchStateDirection.BACK -> back(operation.params)
            SwitchStateDirection.BACK_TO -> operation.state?.let { backTo(it, operation.params) }
            SwitchStateDirection.REPLACE -> operation.state?.let { replace(it, operation.params) }
            SwitchStateDirection.FORWARD -> operation.state?.let { forward(it, operation.params) }
            SwitchStateDirection.ROOT -> operation.state?.let { root(it, operation.params) }
        }
    }

    override fun back(params: P?) {
        if (!statesStack.isEmpty()) {
            statesStack.pop()
        }
        stateSwitchHandler.back(params)
        if(statesStack.isEmpty()) {
            callStateChanged(null)
        } else {
            callStateChanged(statesStack.peek())
        }
    }

    override fun backTo(fragmentState: E, params: P?) {
        while (!statesStack.isEmpty() || statesStack.peek() == fragmentState) {
            statesStack.pop()
        }
        if (statesStack.isEmpty()) {
            statesStack.push(fragmentState)
        }
        stateSwitchHandler.backTo(fragmentState, params)
        callStateChanged(fragmentState)
    }

    override fun replace(fragmentState: E, params: P?) {
        if (!statesStack.isEmpty()) {
            statesStack.pop()
        }
        statesStack.push(fragmentState)
        stateSwitchHandler.replace(fragmentState, params)
        callStateChanged(fragmentState)
    }

    override fun forward(fragmentState: E, params: P?) {
        statesStack.push(fragmentState)
        stateSwitchHandler.forward(fragmentState, params)
        callStateChanged(fragmentState)
    }

    override fun root(fragmentState: E, params: P?) {
        while (!statesStack.isEmpty()) {
            statesStack.pop()
        }
        statesStack.push(fragmentState)
        stateSwitchHandler.root(fragmentState, params)
        callStateChanged(fragmentState)
    }

    private fun callStateChanged(state : E?) {
        for (listener in stateChangeListeners) {
            listener.stateChanged(state)
        }
    }

    fun addStateChangeListener(stateChangeListener: OnStateChangeListener<E>) {
        stateChangeListeners.add(stateChangeListener)
    }

    fun removeStateChangeListener(stateChangeListener: OnStateChangeListener<E>) {
        stateChangeListeners.remove(stateChangeListener)
    }
}