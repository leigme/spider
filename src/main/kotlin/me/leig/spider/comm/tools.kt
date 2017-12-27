package me.leig.spider.comm

import org.apache.log4j.Logger
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class Tools

private val log = Logger.getLogger(Tools::class.java)

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
        val data = ByteArray(1024)
        var length = bis.read(data)
        while (-1 != length) {
            bos.write(data, 0, length)
            length = bis.read(data)
        }

        bis.close()
        bos.close()
    } catch (e: Exception) {
        log.error(e.message)
    }
}