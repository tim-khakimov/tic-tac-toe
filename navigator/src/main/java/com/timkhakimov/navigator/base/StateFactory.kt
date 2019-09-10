package com.timkhakimov.navigator.base

/**
 * Created by Timur Khakimov on 21.07.2019
 * Интерфейс - фабрика состояний / фрагментов
 * E - константа, соответствующая какому-то состоянию/фрагменту
 * F - состояние / экземпляр / фрагмент - соответствующий этой константе
 */
interface StateFactory<E : Enum<*>, F> {
    fun newInstance(state: E): F
}