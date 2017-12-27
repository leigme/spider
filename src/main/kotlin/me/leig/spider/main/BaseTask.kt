package me.leig.spider.main

import org.apache.log4j.Logger

/**
 *
 *
 * @author leig
 *
 */
 
abstract class BaseTask constructor(className: String): Runnable {

    open val log: Logger = Logger.getLogger(className)

}