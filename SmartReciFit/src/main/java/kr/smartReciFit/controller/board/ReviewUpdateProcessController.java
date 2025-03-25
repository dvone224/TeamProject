package kr.smartReciFit.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.util.FileUtil;

public class ReviewUpdateProcessController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 int reviewBoardNum = Integer.parseInt(request.getParameter("reviewBoardNum"));
         String title = request.getParameter("title");
         String content = request.getParameter("content");
         String ctx = request.getContextPath(); // 컨텍스트 경로
         String userNickname = request.getParameter("userNickname");

         List<String> savedFileNames = new ArrayList<>();
         Gson gson = new Gson();

         // 기존 이미지 정보 유지 및 새로운 이미지 업로드 처리
         for (int i = 1; i <= 3; i++) {
             String existingImage = request.getParameter("existingImg" + i);
             String[] fileInputName = {"img" + i};

             // 새로운 파일이 업로드되었는지 확인
             List<String[]> fileInfoList = null;
             try {
                 fileInfoList = FileUtil.uploadMultipleFiles(request, fileInputName);
             } catch (ServletException | IOException e) {
                 System.out.println("파일 업로드 중 예외 발생: " + e.getMessage());
                 response.setContentType("text/html; charset=UTF-8");
                 response.getWriter().println(
                         "<script>alert('파일 업로드 중 오류가 발생했습니다.'); history.back();</script>");
                 return null;
             }

             if (fileInfoList != null && !fileInfoList.isEmpty()) {
                 // 새로운 파일이 업로드된 경우
                 savedFileNames.add(fileInfoList.get(0)[1]);
             } else {
                 // 새로운 파일이 업로드되지 않은 경우 기존 이미지 유지
                 if (existingImage != null && !existingImage.isEmpty()) {
                     savedFileNames.add(existingImage);
                 }
             }
         }

         // ReviewBoard 객체 생성 및 데이터 설정
         String reviewBoardImg = gson.toJson(savedFileNames);
         ReviewBoard review = new ReviewBoard();
         review.setReviewBoardNum(reviewBoardNum);
         review.setReviewBoardTitle(title);
         review.setReviewBoardContent(content);
         review.setReviewBoardImg(reviewBoardImg); // JSON 형식으로 저장

         // 데이터베이스 업데이트
         ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
         int cnt = dao.updateReview(review);

         if (cnt > 0) {
             response.setContentType("text/html; charset=UTF-8");
             response.getWriter().println("<script>alert('게시글이 수정되었습니다.'); location.href='" + ctx
                     + "/reviewDetail.do?reviewBoardNum=" + reviewBoardNum + "&userNickname="
                     + userNickname + "';</script>");
         } else {
             response.setContentType("text/html; charset=UTF-8");
             response.getWriter().println("<script>alert('게시글 수정에 실패했습니다.'); history.back();</script>");
         }

         return null;
     }
 }