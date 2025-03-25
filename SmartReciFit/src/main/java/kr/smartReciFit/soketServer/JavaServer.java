package kr.smartReciFit.soketServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServer {

	private static JavaServer instance;
	ServerSocket serverSocket;
	Socket socket;
	InputStreamReader reader;
	OutputStreamWriter writer;
	Process pythonProcess;

	private JavaServer() {
		try {
			serverSocket = new ServerSocket(8000);
			String path = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = java.net.URLDecoder.decode(path, "UTF-8") + "kr/smartReciFit/soketServer";
			System.out.println("fullPath = " + fullPath);

			File currentDir = new File(fullPath);

			// Python 파일 실행
			ProcessBuilder processBuilder = new ProcessBuilder("python", "PythonClient.py");
			processBuilder.directory(currentDir); // 작업 디렉토리 설정
			processBuilder.redirectErrorStream(true); // 오류 스트림을 표준 출력과 함께 출력
			processBuilder.start();
			
			// 소켓 연결 대기
			socket = serverSocket.accept();
			reader = new InputStreamReader(socket.getInputStream(), java.nio.charset.StandardCharsets.UTF_8);
			writer = new OutputStreamWriter(socket.getOutputStream(), java.nio.charset.StandardCharsets.UTF_8);
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
			if (pythonProcess != null) {
				pythonProcess.destroy(); // Python 프로세스 종료
			}

			instance = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reconnectSocket() {
		try {
			if (socket != null && socket.isClosed()) {
				String path = this.getClass().getClassLoader().getResource("").getPath();
				String fullPath = java.net.URLDecoder.decode(path, "UTF-8") + "kr/smartReciFit/soketServer";
				System.out.println("fullPath = " + fullPath);

				File currentDir = new File(fullPath);

				// Python 파일 실행
				ProcessBuilder processBuilder = new ProcessBuilder("python", "PythonClient.py");
				processBuilder.directory(currentDir); // 작업 디렉토리 설정
				processBuilder.redirectErrorStream(true); // 오류 스트림을 표준 출력과 함께 출력
				processBuilder.start();
				// 소켓 연결 대기
				socket = serverSocket.accept();
				reader = new InputStreamReader(socket.getInputStream(), java.nio.charset.StandardCharsets.UTF_8);
				writer = new OutputStreamWriter(socket.getOutputStream(), java.nio.charset.StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
