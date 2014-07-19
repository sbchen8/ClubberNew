package ClubberServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utlis.Constants;
import ClubberLogic.DAL;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		
		String oldPassword = request.getParameter(Constants.OLD_PASSWORD);
		String newPassword = request.getParameter(Constants.NEW_PASSWORD);
		String email = request.getParameter(Constants.EMAIL);
		
		boolean isValid = DAL.isPasswordMatcheEmail(email, oldPassword);
		
		if(isValid == true)
		{
			DAL.updateUserPassword(email, newPassword);
		}
		else
		{
			String message = "סיסמה אינה נכונה";
			request.setAttribute(Constants.MESSAGE_TEXT, message);
		}
	}

}
