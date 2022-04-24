package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/***
 * IO的进化
 * 常用指令：
 *  lsof -p 进程ID     ---查看该进程的文件描述符
 *  netstat -natp     ---查看链接建立
 *  tcpdump 查看TCP包传输
 *
 * 传统BIO (同步阻塞时期)
 *  因为socket的read(fd)操作是阻塞的，所以服务器要抛出很多线程来处理不同连接到来的数据
 *
 * NIO（同步非阻塞时期）
 *    内核socket的read(fd)操作是变成非阻塞的，只需要一个线程循环read(fd)并读取数据
 * NIO（同步非阻塞时期）select多路复用
 *    内核提供select(fds)---返回有数据的fds,只需要一个线程循环read(fd)并读取数据
 * NIO（同步非阻塞时期）epoll多路复用
 *    内核提供一个和应用共享的空间（mmap{红黑树+链表}）来存放 fds,应用和内核之间不再传输 fds，也不再使用select,而是使用epoll+read
 *
 * 总结：多路复用的好处：select（fds）减少用户态和内核态交互，最终使用共享空间可以不传输fds
 */
public class SocketIO_001 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9098,5);

            System.out.println("Server start up use port 9098");
        } catch (IOException e) {
            System.err.println("Server start error in port 9098");
        }

        while (true){
            System.out.println(Thread.currentThread().getId() + " Server ready get connection ....");
            Socket socket = serverSocket.accept();
            System.out.println("Server have get connection ....port : " + socket.getPort());
            new Thread(()->{
                    while (true){
                        try {
                            InputStream inputStream = socket.getInputStream();

                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                            char[] data = new char[1024];
                            System.out.println(Thread.currentThread().getId() + " socket ready read ....");
                            int readCharNum = bufferedReader.read(data);
                            if(readCharNum>0){
                                System.out.println("receive client " + socket.getPort() + " data : " + new String(data,0,readCharNum));
                            } else if(readCharNum==0){
                                System.out.println("receive client " + socket.getPort() + " data : " + new String(data,0,readCharNum));
                            } else {
                                socket.close();
                                System.out.println("socket close ...");
                            }
                        } catch (IOException e) {
                            try {
                                socket.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            System.err.println("Server read data error ");
                        }
                    }

            }).start();
        }

    }
}
