package com.timkhakimov.navigator.base

/**
 * Created by Timur Khakimov on 06.08.2019.
 * Слушатель для отслеживания изменения состояний в SwitchStateNavigator
 */
interface OnStateChangeListener<E> {
    fun stateChanged(state : E?)
}