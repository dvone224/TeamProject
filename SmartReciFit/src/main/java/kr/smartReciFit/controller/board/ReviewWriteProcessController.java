package kr.smartReciFit.controller.board;

import com.google.gson.Gson;
import kr.smartReciFit.controller.Controller;
import kr.smartReciFit.model.board.ReviewBoard;
import kr.smartReciFit.model.board.ReviewBoardDAO;
import kr.smartReciFit.model.user.UserDAO;
import kr.smartReciFit.util.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewWriteProcessController implements Controller {

    @Override
    public String requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String user = (String) request.getSession().getAttribute("nickName");
        int recipeNum;
        if (request.getParameter("recipeNum") == null) {
            System.out.println("해당레시피가 없음");
            recipeNum = 0;
        } else {
            recipeNum = Integer.parseInt(request.getParameter("recipeNum"));
            System.out.println(recipeNum);
        }

        String[] inputNames = {"img1", "img2", "img3"}; // Define the input names
        List<String[]> fileInfoList = null;

		try {
			fileInfoList = FileUtil.uploadMultipleFiles(request, inputNames);
		} catch (ServletException | IOException e) {
			System.out.println("파일 업로드 중 예외 발생: " + e.getMessage());
			response.setContentType("text/html; charset=UTF-8");
			try { // inner try-catch for response.getWriter()
				response.getWriter().println("<script>alert('파일 업로드 중 오류가 발생했습니다.'); history.back();</script>");
			} catch (IOException ioe) {
				System.out.println("getWriter() 중 예외 발생: " + ioe.getMessage());
				ioe.printStackTrace(); // 중요: 개발 중에 디버깅을 위해 추가
				// 여기에서 추가적인 오류 처리 로직을 구현할 수 있습니다.
			}
			return null;
		}

        List<String> savedFileNames = new ArrayList<>();
        for (String[] fileInfo : fileInfoList) {
            if (fileInfo != null && fileInfo.length > 1) {
                savedFileNames.add(fileInfo[1]); // Add the saved file name
            }
        }

        Gson gson = new Gson();
        String reviewBoardImg = gson.toJson(savedFileNames);

        int userNum = UserDAO.getInstance().checkNickName(user);
        int view = 0;
        int like = 0;

        ReviewBoard vo = new ReviewBoard();
        vo.setUserNum(userNum);
        vo.setReviewBoardTitle(title);
        vo.setReviewBoardContent(content);
        vo.setReviewBoardImg(reviewBoardImg);
        vo.setReviewBoardViews(view);
        vo.setReviewBoardLikes(like);
        vo.setReviewBoardRecipeId(recipeNum);

        ReviewBoardDAO dao = ReviewBoardDAO.getInstance();
        dao.addReview(vo);

        String ctx = request.getContextPath();
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>alert('게시글이 작성되었습니다.'); location.href='" + ctx + "/reviews.do';</script>");
        return null;
    }
}