package com.hyf.test.juc;

import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author baB_hyf
 * @date 2022/07/16
 */
public class ReadWriteLockTests {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock(); // block
        System.out.println(1);
    }
}
