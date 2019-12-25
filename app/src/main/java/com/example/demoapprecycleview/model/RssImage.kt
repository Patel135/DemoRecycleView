package com.example.demoapprecycleview.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Admin on 25-12-2019 12:45
 */
@Root(name = "image", strict = false)
class RssImage {
    @Element
    private val url: String? = null

    @Element
    private val width: String? = null

    @Element
    private val height: String? = null

    override fun toString(): String {
        return "RssImage [url=" + url + ", width=" + width + ", height=" + height + "]"
    }
}