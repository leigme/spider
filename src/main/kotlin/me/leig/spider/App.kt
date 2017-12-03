package me.leig.spider

import me.leig.spider.main.Spider

fun main(args: Array<String>) {
    println("app start...")
    val spider = Spider().analysisUrl("http://home.meishichina.com/show-top-type-recipe.html")
}