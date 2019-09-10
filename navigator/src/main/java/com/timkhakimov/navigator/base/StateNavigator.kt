package com.timkhakimov.navigator.base

import java.util.*

/**
 * Created by Timur Khakimov on 21.07.2019
 * Интерфейс для навигации/переключения состояний/фрагментов
 * Назначение классов, которые его имплементируют:
 * - хранение очереди команд на переключение состояний,
 * - поочередное выполнение этих команд,
 * - хранение стека состояний
 * - возврат костанты, соответствующей текущему состоянию
 *
 * За непосредственное переключение состояний / фрагментов отвечает StateSwitchHandler
 *
 * E - константа, соответствующая какому-то состоянию/фрагменту
 * P - параметры (во фрагментах это будет Bundle)
 */
interface StateNavigator<E : Enum<*>, P> {
    fun addSwitchStateOperation(direction: SwitchStateDirection, state: E?, params: P?)     //создать команду из параметров и добавить в очередь
    fun addSwitchStateOperation(operation: SwitchStateOperation<E, P>)                      //добавить команду в очередь
    fun getCurrentState(): E?                                                               //текущее состояние из вершины стека
    fun copyStatesStack() : Stack<E>                                                        //получить текущий стек состояний
    fun hasOperationsInQueue(): Boolean                                                     //true, если есть команды в очереди на переключение состояний / фрагментов
    fun handleOperations()                                                                  //выполнить поочередно все команды из очереди
    fun handleNextOperation()                                                               //выполнить команду из начала очереди
    fun handleOperation(operation: SwitchStateOperation<E, P>)                              //выполнить какую-то конкретную команду
}