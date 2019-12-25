package com.example.demoapprecycleview.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


/**
 * Created by Admin on 25-12-2019 14:13
 */
@Root(name = "breakfast_menu")
class BreakfastMenu {
    @field:ElementList(inline = true)
    private var foodList: List<Food?>? = null

    fun BreakfastMenu() {}

    fun getFoodList(): List<Food?>? {
        return foodList
    }

    fun setFoodList(foodList: List<Food?>?) {
        this.foodList = foodList
    }
}