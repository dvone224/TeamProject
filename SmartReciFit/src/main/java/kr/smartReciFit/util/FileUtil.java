package kr.smartReciFit.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {

	public static String[] uploadFile(HttpServletRequest request, String fileTagName)
			throws ServletException, IOException {
		String saveDirectory = request.getServletContext().getRealPath("") + File.separator + "img";
		Path saveDirPath = Paths.get(saveDirectory);
		if (!Files.isDirectory(saveDirPath)) {
			Files.createDirectories(saveDirPath);
		}

		Part filePart = null;
		String oFileName = null;
		String sFileName = null;

		try {
			filePart = request.getPart(fileTagName);
			if (filePart == null || filePart.getSize() == 0) {
				System.out.println("파일 업로드 안됨 ");
				return null;
			}
			oFileName = extractFileName(filePart);
			if (oFileName.isEmpty()) {
				System.out.println("유효하지 않은 파일 이름");
				return null;
			}

			sFileName = System.currentTimeMillis() + "_" + oFileName; // 중복없이 파일이름 지정
			filePart.write(saveDirPath.resolve(sFileName).toString()); // 파일저장

			System.out.println("fileType = " + filePart.getContentType());
			System.out.println("저장된 파일 이름 " + sFileName);
		} catch (IOException e) {
			System.err.println("File upload failed: " + e.getMessage());
			return null;
		}

		return new String[] { oFileName, sFileName };
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
		String saveDirectory = request.getServletContext().getRealPath("") + File.separator + "img";
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