package me.leig.spider.main

import me.leig.spider.comm.makeImage
import org.jsoup.Jsoup

class Spider {

    fun analysisUrl(url: String): MutableList<String> {
        try {
            val doc = Jsoup.connect(url).get()
            val elements = doc.select("[class=ml mlt mtw cl]").select("[class=c cl]").select("a").select("img")
            val urls = mutableListOf<String>()
            elements
                    .map { it.attr("src") }
                    .mapTo(urls) { "http://www.jiepaiuu.com/thread-${it.split("/")[it.split("/").size - 1].split(".")[0]}-1-1.html" }

            return urls

        } catch (e: Exception) {
            throw e
        }
    }

    fun analysisUrlToBigImage(url: String): String {
        try {
            val doc = Jsoup.connect(url).get()
            val elements = doc.select("[class=t_f]").select("img")
            return elements[0].attr("file")

        } catch (e: Exception) {
            throw e
        }
    }
}