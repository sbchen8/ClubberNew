package ClubberServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ClubberLogic.AuctionData;
import ClubberLogic.DAL;
import Utlis.Constants;
import Utlis.SessionUtils;

/**
 * Servlet implementation class SearchAuction
 */
@WebServlet("/SearchAuction")
public class SearchAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAuction() {
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

        ArrayList<AuctionData> auctionsList = new ArrayList<>();
        
        // Get User parameter
        String agesRangeParam = request.getParameter(Constants.AGES_RANGE);
        String musicStyleParam = request.getParameter(Constants.MUSIC_STYLE);
        String dayInWeekParam = request.getParameter(Constants.DAY_IN_WEEK);
        String searchByMyLinesParam = request.getParameter(Constants.SEARCH_BY_MY_LINES);
  
        String prEmail = request.getParameter(Constants.EMAIL);
        
        if(searchByMyLinesParam != null && searchByMyLinesParam.equals("on"))
        {
        	auctionsList =  DAL.searchAuctionsByPrLines(prEmail);
        }
        
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();        
        
        try 
        {
            out.print(gson.toJson(auctionsList));
        }
        catch(Exception e)
        {
        	out.print(gson.toJson(auctionsList));
        }
        finally 
        {
            out.close();
        }        
        
        getServletContext().getRequestDispatcher("/SearchAuctions.jsp").forward(request, response);
	}

}
