package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import jdbc.user.dao.UserDAO;
import jdbc.user.vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.do")
public class UserServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private UserDAO dao;
   private RequestDispatcher rd;
   
   public UserServlet() {
      System.out.println("UserServlet 생성자 호출됨");
   }
   
   @Override
   public void init() throws ServletException {
      System.out.println("UserServlet init() method called!!");
      dao = new UserDAO();
   }
   
    @Override
    public void destroy() {
       System.out.println(">>>UserServlet destroy() method called!!");
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("UserServlet doGet() method called!!");
      String cmd = request.getParameter("cmd");
      System.out.println(">>>> command : " + cmd);
      if(cmd.equals("userList")) {
         userList(request, response);
      }else if(cmd.equals("userDetail")) {
         userDetail(request, response);
      } else if(cmd.equals("userJson")) {
         userJson(request, response);
      }else if(cmd.equals("userJsonDelete")) {
    	  userJsonDelete(request,response);
      }
   }//doGet
   
   private void userJsonDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	  String userid = request.getParameter("userid");
	  int cnt=dao.deleteUSer(userid);
	  //정상적으로 삭제가 잘 되었으면 userJson 메소드를 호출해서 Json문자열을 클라이언트에게 다시 전달한다.
	  if(cnt==1) {
		  userJson(request, response);
	  }
}

private void userJson(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException{
      //DAO에서 받은 List<UserVO>를 JSON 데이터로 변환
      List<UserVO> users = dao.getUsers();
      Gson gson = new Gson();
      String jsonUsers = gson.toJson(users);
      //Json 데이터를 CLient에게 전달한다
      //response(응답) 데이터 인코딩
      response.setCharacterEncoding("UTF-8");
      //response(응답) mime type 설정
      response.setContentType("application/json");
      
      response.addHeader("Access-Control-Allow-Origin", "*");
      response.addHeader("Access-Control-Allow-Credential", "ture");
      
      //client에게 data를 전송하기 위한 Stream 생성
      PrintWriter out = response.getWriter();
      //변환된 JSON String 스트림에 쓰기
      out.write(jsonUsers);
      out.flush();
      out.close();
   }

   private void userDetail(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String userid = request.getParameter("id");
      System.out.println(">>> userid " + userid);
      
//      //1. DAO 호출
//      List<UserVO> users = dao.getUsers();
//      System.out.println(users);
//      //2. DAO로 받아 온  List 객체를  JSP에서 사용할 수 있도록  request 객체 저장합니다.
//      request.setAttribute("userList", users);
//      //3. 결과를 출력해줄 JSP - userList.jsp 를 포워딩 
//      rd = request.getRequestDispatcher("userList.jsp");
//      rd.forward(request, response);
   }

   private void userList(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      //1. DAO 호출
      List<UserVO> users = dao.getUsers();
      System.out.println(users);
      //2. DAO로 받아 온  List 객체를  JSP에서 사용할 수 있도록  request 객체 저장합니다.
      request.setAttribute("userList", users);
      //3. 결과를 출력해줄 JSP - userList.jsp 를 포워딩 
      rd = request.getRequestDispatcher("userList.jsp");
      rd.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}