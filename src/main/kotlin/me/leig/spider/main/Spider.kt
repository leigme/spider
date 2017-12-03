package me.leig.spider.main

import org.jsoup.Jsoup

class Spider {

    fun analysisUrl(url: String) {
        try {
            val doc = Jsoup.connect(url).get()
            val titleAndPic = doc.select("div.pic")
            println("title:" + titleAndPic[1].select("a").attr("title") + "pic:" + titleAndPic[1].select("a").select("img").attr("data-src"))
        } catch (e: Exception) {
            throw e
        }
    }
}