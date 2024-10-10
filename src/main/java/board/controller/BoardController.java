package board.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.action.BoardDeleteFormAction;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardReplyFormAction;
import board.action.BoardReplyProAction;
import board.action.BoardWriteFormAction;
import board.action.BoardWriteProAction;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private Map<String, Object> map = new HashMap<String, Object>();
   
   public BoardController() {
      super();
   }

   @Override
   public void init(ServletConfig config) throws ServletException {
      // properties 파일의 경로 구하기
      String realFolder = config.getServletContext().getRealPath("property");
      String realPath = realFolder + "/" + "command.properties";
      System.out.println("realPath = " + realPath);

      // properties 파일 읽기
      FileInputStream fis = null;
      Properties properties = new Properties();
      try {
         fis = new FileInputStream(realPath);
         // 파일을 읽어옴
         properties.load(fis);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (fis != null)
               fis.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      // command.properties 파일의 내용대로 객체 생성해서 Map에 저장하기
      Iterator<?> iterator = properties.keySet().iterator();

      while (iterator.hasNext()) {
         // 키 값 읽어오기(= 기준 왼쪽값)
         String command = (String) iterator.next();
         // 키 값에 해당하는 클래스명 읽어오기(= 기준 오른쪽값)
         String className = properties.getProperty(command);

         // 클래스 명으로 객체 생성
         try {
            Class<?> commandClass = Class.forName(className);
            // 객체 생성
            Object instance = commandClass.newInstance();
            // Map 객체에 저장
            map.put(command, instance);
         } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      // map 객체에 저장된 내용 확인
      System.out.println(map);
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doProcess(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      doProcess(request, response);
   }

   protected void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // 한글 인코딩 설정
      request.setCharacterEncoding("UTF-8");
      // 1. 요청 정보 확인
      // http://localhost:8080/19-board/boardWriteForm.do
      // url에서 "19-board" 뒤의 "/boardWriteForm.do" 만 추출
      String command = request.getServletPath();
      System.out.println("command = " + command);

      // 2. 요청 작업 처리 (= Model 선택)
      Action action = (Action) map.get(command);

      // 3. 데이터 처리 + view 처리 파일 선택
      String view = null;
      if (action != null) {
         try {
            view = action.execute(request, response);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      // 4. 페이지 이동
      if (view != null) {
         RequestDispatcher dispatcher = request.getRequestDispatcher(view);
         dispatcher.forward(request, response);
      }
   }
}
