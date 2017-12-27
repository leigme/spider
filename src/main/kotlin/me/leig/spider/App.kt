package me.leig.spider

import me.leig.spider.main.Spider
import me.leig.spider.main.ThreadPool
import java.io.File

fun main(args: Array<String>) {
    println("app start...")

    val tp = ThreadPool()

    val lists = Spider().analysisUrl("http://www.jiepaiuu.com/forum-2-1.html")

    val tasks = lists.map { tp.MakeImageTask(it, StringBuffer("temps").append(File.separatorChar).append(it.split("/")[it.split("/").size - 1]).toString()) }

    tp.startTask(tasks)
}