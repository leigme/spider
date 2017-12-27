package me.leig.spider.main

import org.apache.log4j.Logger
import org.jsoup.Jsoup

class Spider {

    private val log = Logger.getLogger(Spider::class.java.name)

    fun analysisUrl(url: String): MutableList<String> {

        val lists: MutableList<String> = mutableListOf()

        try {
            var doc = Jsoup.connect(url).get()
            var elements = doc.select("[class=ml mlt mtw cl]").select("[class=c cl]").select("a").select("img")
            val urls = mutableListOf<String>()
            elements
                    .map { it.attr("src") }
                    .mapTo(urls) { "http://www.jiepaiuu.com/thread-${it.split("/")[it.split("/").size - 1].split(".")[0]}-1-1.html" }

            urls.mapTo(lists) { analysisUrlToBigImage(it) }

            val strNum = doc.select("[class=last]").text().split(" ")

            val pageNum = strNum[strNum.size - 1].trim()

            for (i in 1 until pageNum.toInt()) {

                val newUrl = url.split("1.")

                doc = Jsoup.connect(newUrl[0] + "$i." + newUrl[1]).get()
                elements = doc.select("[class=ml mlt mtw cl]").select("[class=c cl]").select("a").select("img")

                val urls = mutableListOf<String>()

                elements
                        .map { it.attr("src") }
                        .mapTo(urls) { "http://www.jiepaiuu.com/thread-${it.split("/")[it.split("/").size - 1].split(".")[0]}-1-1.html" }

                urls.mapTo(lists) { analysisUrlToBigImage(it) }
            }

            return lists

        } catch (e: Exception) {
            log.error(e.message)
            throw e
        }
    }

    private fun analysisUrlToBigImage(url: String): String {
        try {
            val doc = Jsoup.connect(url).get()
            val elements = doc.select("[class=t_f]").select("img")
            return elements[0].attr("file")

        } catch (e: Exception) {
            log.error(e.message)
            throw e
        }
    }
}