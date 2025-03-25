package kr.smartReciFit.util;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {

	public static List<String[]> uploadMultipleFiles(HttpServletRequest request, String[] inputNames)
			throws ServletException, IOException {
		List<String[]> fileInfoList = new ArrayList<>();
		String saveDirectory = request.getServletContext().getRealPath("img");
		Path saveDirPath = Paths.get(saveDirectory);

		if (!Files.isDirectory(saveDirPath)) {
			Files.createDirectories(saveDirPath);
		}

		for (String inputName : inputNames) {
			try {
				Part filePart = request.getPart(inputName);
				if (filePart != null && filePart.getSize() > 0) {
					String originalFileName = extractFileName(filePart);
					if (originalFileName.isEmpty()) {
						System.out.println("유효하지 않은 파일 이름 for input " + inputName);
						continue;
					}

					String savedFileName = System.currentTimeMillis() + "_" + originalFileName; // 중복 방지
					filePart.write(saveDirPath.resolve(savedFileName).toString());
					fileInfoList.add(new String[]{originalFileName, savedFileName});
					System.out.println("저장된 파일 이름 " + savedFileName + " for input " + inputName);
				} else {
					System.out.println("No file uploaded for input " + inputName);
				}
			} catch (IOException e) {
				System.err.println("File upload failed for input " + inputName + ": " + e.getMessage());
			}
		}

		return fileInfoList;
	}

	private static String extractFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				return Paths.get(fileName).getFileName().toString();
			}
		}
		return "";
	}

	public static boolean isFileDeleted(HttpServletRequest request, String delFileName) {
		String saveDirectory = request.getServletContext().getRealPath("img");
		Path filePath = Paths.get(saveDirectory, delFileName);
		try {
			Files.deleteIfExists(filePath);
			System.out.println("파일 삭제 완료: " + filePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}