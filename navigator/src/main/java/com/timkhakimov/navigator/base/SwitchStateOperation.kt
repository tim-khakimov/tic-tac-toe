package com.timkhakimov.navigator.base

/**
 * Created by Timur Khakimov on 21.07.2019
 * Команда на переключение состояния / фрагмента
 * E - константа, соответствующая какому-то состоянию/фрагменту
 * P - параметры (во фрагментах это будет Bundle)
 */
class SwitchStateOperation<E : Enum<*>, P>(
        var direction: SwitchStateDirection,
        var state: E?,
        var params: P?)