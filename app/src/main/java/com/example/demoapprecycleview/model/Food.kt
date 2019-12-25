package com.example.demoapprecycleview.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Admin on 25-12-2019 14:14
 */
@Root(name = "food")
class Food {

    @field:Element(name = "name")
    private var name: String? = null

    @field:Element(name = "price")
    private var price: String? = null

    @field:Element(name = "description")
    private var description: String? = null

    @field:Element(name = "calories")
    private var calories: String? = null

    fun Food() {}

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getCalories(): String? {
        return calories
    }

    fun setCalories(calories: String?) {
        this.calories = calories
    }
}