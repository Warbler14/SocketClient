package com.study.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientSocketMain {
	public static void main(String[] args) {
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket socket = null;
		
		try {
			
			socket = new Socket("localhost", 7000);

			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("connected " + socket.isConnected() );

			for (int idx0 = 0, end0 = 20; idx0 < end0; idx0++) {
				
				String sendMsg = "client send data";
				byte[] sendMsgByte = sendMsg.getBytes();
				dos.writeInt(sendMsgByte.length);
				dos.write(sendMsgByte);
				dos.flush();
				
				
				byte[] receivedMsgByte = new byte[dis.readInt()];
				dis.readFully(receivedMsgByte);
				String receivedMsg = new String(receivedMsgByte);
				System.out.println("From Server : " + receivedMsg);

				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}//end for
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
				dos.close();
				socket.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
	}
}
