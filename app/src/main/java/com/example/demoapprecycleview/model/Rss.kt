package com.example.demoapprecycleview.model

import android.content.ClipData.Item
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


/**
 * Created by Admin on 25-12-2019 13:20
 */
@Root(name = "rss")
class Rss {
    fun Rss() {}
    fun Rss(
        title: String?,
        description: String?,
        link: String?,
        item: List<Item>?,
        language: String?
    ) {
        this.title = title
        this.description = description
        this.link = link
        this.item = item
        this.language = language
    }

    @Element(name = "title")
    private var title: String? = null

    @Element(name = "description")
    private var description: String? = null

    @Element(name = "link")
    private var link: String? = null

    @ElementList(entry = "item", inline = true)
    private var item: List<Item>? = null

    @Element(name = "language")
    private var language: String? = null
}