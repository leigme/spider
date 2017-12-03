package me.leig.spider.comm

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL


fun makeImage(url: String, fileName: String) {
    try {

        val inFile = URL(url).openStream()

//        val metadata = JpegMetadataReader.readMetadata(inFile)
//
//        val exifs = metadata.directories
//
//        for (exif in exifs) {
//            val tags = exif.tags.iterator()
//            while (tags.hasNext()) {
//                println(tags.next())
//            }
//        }

        val bis = BufferedInputStream(inFile)
        val file = File(fileName)
        val bos = BufferedOutputStream(FileOutputStream(file))
        var data = ByteArray(1024)
        var length = bis.read(data)
        while (-1 != length) {
            bos.write(data, 0, length)
            length = bis.read(data)
        }
        println("正在执行下载任务：当前正在下载图片[$fileName]")
        bis.close()
        bos.close()
    } catch (e: Exception) {
        throw e
    }
}