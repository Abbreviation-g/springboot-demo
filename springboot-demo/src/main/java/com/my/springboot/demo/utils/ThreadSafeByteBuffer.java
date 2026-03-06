package com.my.springboot.demo.utils;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeByteBuffer {
    private final ReentrantLock lock = new ReentrantLock();
    private final ByteBuffer byteBuffer;

    public ThreadSafeByteBuffer(int capacity) {
        this.byteBuffer = ByteBuffer.allocate(capacity);
    }

    public void put(byte[] bytes) {
        lock.lock();
        try {
            if (byteBuffer.remaining() >= bytes.length) {
                byteBuffer.put(bytes);
            }
        } finally {
            lock.unlock();
        }
    }

    public byte[] get(int size) {
        lock.lock();
        try {
            return getDataFromBuffer(size);
        } finally {
            lock.unlock();
        }
    }

    public int position() {
        return byteBuffer.position();
    }

    private byte[] getDataFromBuffer(int size) {
        int length = byteBuffer.position();
        byteBuffer.flip();

        byte[] sendData = new byte[size];
        if (length < size) {
            byte[] data = new byte[length];
            byteBuffer.get(data);
            System.arraycopy(data, 0, sendData, 0, data.length);
        } else {
            byteBuffer.get(sendData);
        }
        byteBuffer.compact();

        return sendData;
    }
}
