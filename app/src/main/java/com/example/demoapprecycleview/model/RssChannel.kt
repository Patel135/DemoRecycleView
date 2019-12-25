package com.example.demoapprecycleview.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Admin on 25-12-2019 12:44
 */
@Root(name = "channel", strict = false)
class RssChannel {
    @Element
    private val title: String? = null

    @Element
    private val image: RssImage? = null

    @ElementList(inline = true, required = false)
    var item: List<RssItem>? = null

    override fun toString(): String {
        return "Channel [image=" + image + ", item=" + item + "]"
    }
}