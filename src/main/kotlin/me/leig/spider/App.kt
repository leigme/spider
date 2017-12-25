package me.leig.spider

import me.leig.spider.comm.makeImage
import me.leig.spider.main.Spider

fun main(args: Array<String>) {
    println("app start...")
    val urls = Spider().analysisUrl("http://www.jiepaiuu.com/forum-2-1.html")
    for (url in urls) {
        makeImage(Spider().analysisUrlToBigImage(url), "temps\\${Spider().analysisUrlToBigImage(url).split("/")[Spider().analysisUrlToBigImage(url).split("/").size - 1]}")
    }


}