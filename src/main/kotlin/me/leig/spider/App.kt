package me.leig.spider

import me.leig.spider.comm.makeImage
import me.leig.spider.main.Spider

fun main(args: Array<String>) {
    println("app start...")
    val spider = Spider().analysisUrl("")
    makeImage("http://i3.meishichina.com/attachment/recipe/2017/11/29/20171129151194979042613.jpg@!c320", "222.jpg")
}