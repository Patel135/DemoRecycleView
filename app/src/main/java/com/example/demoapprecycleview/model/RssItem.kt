package com.example.demoapprecycleview.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Admin on 25-12-2019 12:46
 */
@Root(name = "item", strict = false)
class RssItem {
    @Element
    private val title: String? = null

    @Element
    private val link: String? = null

    @Element
    private val pubDate: String? = null

    @Element
    private val description: String? = null

    override fun toString(): String {
        return ("RssItem [title=" + title + ", link=" + link + ", pubDate=" + pubDate
                + ", description=" + description + "]")
    }
}