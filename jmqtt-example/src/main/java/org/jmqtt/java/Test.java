package org.jmqtt.java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Test {

	public static Selector selector = null;// Selector.open();

	public static void main(String[] args) {

		SocketChannel sc;
		try {
			sc = SocketChannel.open();
			selector = Selector.open();
			sc.configureBlocking(false);
			SocketAddress remoteAddress = new InetSocketAddress("192.168.100.126", 31883);
			boolean connect = sc.connect(remoteAddress);
/*
			while (!sc.finishConnect()) {

				// wait, or do something else...
				// System.out.println(connect);
				System.out.println(connect);
			}
			System.out.println(connect);*/

			sc.register(selector, SelectionKey.OP_CONNECT);

			// 循环处理
			while (true) {
				/*
				 * 选择一组可以进行I/O操作的事件，放在selector中，客户端的该方法不会阻塞，
				 * selector的wakeup方法被调用，方法返回，而对于客户端来说，通道一直是被选中的
				 * 这里和服务端的方法不一样，查看api注释可以知道，当至少一个通道被选中时。
				 */
				selector.select();

				// 获取监听事件
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();

				// 迭代处理
				while (iterator.hasNext()) {
					// 获取事件
					SelectionKey key = iterator.next();

					// 移除事件，避免重复处理
					iterator.remove();

					// 连接事件发生
					if (key.isConnectable()) {
						handleConnect(key);
					} else if (key.isReadable()) {// 监听到读事件，对读事件进行处理
						handleRead(key);
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 处理客户端连接服务端成功事件
	 */
	private static void handleConnect(SelectionKey key) throws IOException {
		// 获取与服务端建立连接的通道
		SocketChannel channel = (SocketChannel) key.channel();
		if (channel.isConnectionPending()) {
			// channel.finishConnect()才能完成连接
			channel.finishConnect();
		}

		channel.configureBlocking(false);

		// 数据写入通道
		channel.write(ByteBuffer.wrap(new String("Hello Server!").getBytes()));

		
		System.out.println("Connected!");
		
		// 通道注册到选择器，并且这个通道只对读事件感兴趣
		channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * 监听到读事件，读取客户端发送过来的消息
	 */
	private static void handleRead(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();

		// 从通道读取数据到缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(128);
		channel.read(buffer);

		// 输出服务端响应发送过来的消息
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		if(msg!=null&&!msg.equals(""))
		System.out.println("client receive msg from server：" + msg);

	}
}
