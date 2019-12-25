package com.example.demoapprecycleview.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Admin on 25-12-2019 12:43
 */
@Root(name = "rss", strict = false)
class RssFeed {
    @Element
    var channel: RssChannel? = null

    override fun toString(): String {
        return "RssFeed [channel=" + channel + "]"
    }
}