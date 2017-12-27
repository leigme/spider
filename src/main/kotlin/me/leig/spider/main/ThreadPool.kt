package me.leig.spider.main

import me.leig.spider.comm.makeImage
import org.apache.log4j.Logger
import java.util.concurrent.ArrayBlockingQueue

/**
 *
 *
 * @author leig
 *
 */

class ThreadPool {

    val log = Logger.getLogger(ThreadPool::class.java)!!

    // 线程池大小
    private val threadPoolSize: Int

    // 执行线程池
    private val mArrayBlockingQueue: ArrayBlockingQueue<Thread>

    // 初始化线程池大小,默认10
    constructor(threadPoolSize: Int = 10) {

        this.threadPoolSize = threadPoolSize

        this.mArrayBlockingQueue = ArrayBlockingQueue(threadPoolSize)
    }

    fun startTask(tasks: List<BaseTask>) {

        for (task in tasks) {
            val t = Thread(task)
            t.start()
            mArrayBlockingQueue.put(t)
        }
    }

    inner class MakeImageTask(private val url: String = "", private val fileName: String = ""): BaseTask(MakeImageTask::class.java.name) {

        override fun run() {

            log.info("当前执行正在从:[$url]下载图片:[$fileName]")

            try {
                makeImage(url, fileName)
//                println("=========>${Thread.currentThread()}")
            } catch (e: Exception) {

                log.error("执行:[$url]下载任务出错: ${e.message}")

                e.printStackTrace()
            }
            mArrayBlockingQueue.remove()
        }

    }
}