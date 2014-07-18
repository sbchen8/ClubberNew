package ClubberServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClubberLogic.DAL;
import Utlis.Constants;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
        String emailParam = request.getParameter(Constants.EMAIL);
        String passwordParam = request.getParameter(Constants.PASSWORD);
        
        boolean isSucceed = true; 
        String message = "";
        
        if(DAL.isEmailExists(emailParam) == false)
        {
        	isSucceed = false;
        	message = "דואר אלקטרוני לא קיים במערכת";
        }
        
        if(isSucceed == true)
        {
        	if(DAL.isPasswordMatchEmail(emailParam, passwordParam) == false)
        	{
            	isSucceed = false;
            	message = "סיסמה אינה נכונה";
            	
            	request.setAttribute(Constants.LOGIN_FAILED, message);
            	getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);            	
        	}
        }

        if(isSucceed == true)
        {
        	request.getSession(true).setAttribute(Constants.EMAIL, emailParam);
        	getServletContext().getRequestDispatcher("/welcomePage.jsp").forward(request, response);
        }
	}

}
