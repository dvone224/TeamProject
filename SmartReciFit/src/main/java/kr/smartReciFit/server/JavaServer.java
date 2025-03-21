package kr.smartReciFit.server;

import java.net.*;

import kr.smartReciFit.model.recipe.RecipeDAO;

import java.io.*;

public class JavaServer {
	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(8000);
		System.out.println("Waiting for a connection...");
		try {
            Runtime.getRuntime().exec("cmd /c start C:/KMK/python/PythonClient.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
		Socket socket = serverSocket.accept();
	
		System.out.println("Connected by " + socket.getInetAddress());

		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());

		try {
			out.writeUTF("Zt1kQ71z4Vs");
			String message = in.readUTF();
			System.out.println("Received: " + message);

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		socket.close();
		serverSocket.close();
	}
}