package com.timkhakimov.navigator.base

/**
 * Created by Timur Khakimov on 21.07.2019
 * Операции для переключения состояний
 */
enum class SwitchStateDirection {
    BACK,       //pop stack
    BACK_TO,    //loop pop stack until target state at top
    REPLACE,    //pop stack and push
    FORWARD,    //push
    ROOT;       //clear stack and push
}