package com.hyf.test.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author baB_hyf
 * @date 2022/07/16
 */
public class ChannelTests {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8081));
        server.configureBlocking(false); // NONBLOCKING

// epoll_create
        Selector selector = Selector.open(); // 默认epoll

// epoll_ctl
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            Set<SelectionKey> keys = selector.keys(); // 所有注册的fd

            // epoll_wait
            while (selector.select(0/* 可指定timeout，0表示永久阻塞 */) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 有状态的fd
                Iterator<SelectionKey> it = selectionKeys.iterator();
                // 只给状态，还是得自己处理R/W
                // 需要对每个fd进行系统调用
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove(); // 不移除会重复处理
                    if (key.isAcceptable()) {
                        // 获取服务端接收新连接
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        ByteBuffer buf = ByteBuffer.allocateDirect(4096);
                        // epoll_ctl
                        client.register(selector, SelectionKey.OP_READ, buf);
                    }
                    else if (key.isReadable()) {
                        // 13. 获取当前选择器上读就绪的通道
                        SocketChannel client = (SocketChannel) key.channel();
                        // 14. 读取数据
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = client.read(buf)) > 0) {
                            buf.flip();
                            System.out.println(new String(buf.array(), 0, len));
                            buf.clear();
                        }

                        // epoll_ctl
                        client.register(selector, SelectionKey.OP_WRITE, buf);
                    }
                    else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        String content = "hello world";
                        byte[] bytes = ("HTTP/1.1 200 OK" +
                                "\r\n" +
                                "\r\n" +
                                content
                        ).getBytes(StandardCharsets.UTF_8);
                        ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
                        allocate.put(bytes);
                        allocate.flip();
                        client.write(allocate);
                        client.close();
                    }
                }
            }
        }
    }
}
