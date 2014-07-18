package ClubberServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClubberLogic.DAL;
import ClubberLogic.PR;
import Utlis.Constants;


/**
 * Servlet implementation class SignUp
 */
@WebServlet("/UpdatePrDetails")
public class UpdatePrDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePrDetails() {
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
        String message = "";
        PR pr = new PR();
        
        pr.setFirstName(request.getParameter(Constants.FIRST_NAME));
        pr.setLastName(request.getParameter(Constants.LAST_NAME));
        pr.setGender(request.getParameter(Constants.GENDER));
        pr.setBirthDate(request.getParameter(Constants.BIRTHDATE)); // Date.valueOf(request.getParameter(Constants.BIRTHDATE));
        pr.setPhoneNumber(request.getParameter(Constants.PHONE_NUMBER));
        pr.setEmail(request.getParameter(Constants.EMAIL));
        pr.setPassword(request.getParameter(Constants.PASSWORD));
                
        boolean isSucceed = DAL.updateUserDetails(pr);
        
        if(isSucceed == true)
        {
        	message = "העדכון בוצע";
        }
        else
        {
        	message = "העדכון נכשל";
        }
        
        request.getSession(true).setAttribute(Constants.MESSAGE_TEXT, message);
        getServletContext().getRequestDispatcher("/PrProfile.jsp").forward(request, response);
	}

}


 