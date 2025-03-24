package kr.smartReciFit.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class JavaServer {

	private static JavaServer instance;
	ServerSocket serverSocket;
	Socket socket;
	InputStreamReader reader;
	OutputStreamWriter writer;

	private JavaServer() {
		try {
			serverSocket = new ServerSocket(8000);
			Runtime.getRuntime().exec("cmd /k start C:/KMK/python/PythonClient.bat");
			socket = serverSocket.accept();
			reader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
			writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JavaServer getInstance() {
		if (instance == null)
			instance = new JavaServer();
		return instance;
	}

	public InputStreamReader getReader() {
		return reader;
	}

	public OutputStreamWriter getWriter() {
		return writer;
	}

	// 소켓을 닫는 메서드 (필요시 사용)
	public void closeSocket() {
		try {
			if (socket != null) {
				socket.close();
			}
			if (serverSocket != null) {
				serverSocket.close();
			}

			instance = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reconnectSocket() {
		try {
			if (socket != null && socket.isClosed()) {
				Runtime.getRuntime().exec("cmd /c start C:/KMK/python/PythonClient.bat");
				socket = serverSocket.accept();
				reader = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
				writer = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}