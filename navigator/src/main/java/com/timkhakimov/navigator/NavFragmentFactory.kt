package com.timkhakimov.navigator

import android.os.Bundle
import com.timkhakimov.navigator.base.StateFactory
import com.timkhakimov.navigator.base.StateNavigator

/**
 * Created by Timur Khakimov on 01.08.2019
 * Базовый класс для фабрики фрагментов
 * E - константа, соответствующая какому-то состоянию/фрагменту
 * F - класс фрагмент, имплементирующий интерфейс NavFragment
 * fragmentsNavigator - интерфейс, используемый для переключения фрагментов
 *
 * Как использовать:
 * В активити создаем экземпляр NavFragmentFactory и храним ссылку на него в качестве поля класса
 * В активити создаем экземпляр класса SwitchStateNavigator, который имплементирует интерфейс StateNavigator, ссылку на него сохраняем в качестве поля класса активити и устанавливаем его (навигатор) сюда
 * Если нужно будет использовать для переключения фрагментов внутри фрагментов - то фабрику и навгиатор создаем и храним в основном фрагменте, в остальном все то же самое
 */
abstract class NavFragmentFactory<E : Enum<*>, F : NavFragment<E>> : StateFactory<E, F> {

    var fragmentsNavigator: StateNavigator<E, Bundle>? = null

    override fun newInstance(type: E): F {
        val fragment = getNewBaseFragment(type)
        fragment.setFragmentsNavigator(fragmentsNavigator)
        return fragment
    }

    abstract fun getNewBaseFragment(type: E): F
}