package ClubberServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClubberLogic.BusinessData;
import ClubberLogic.DAL;
import ClubberLogic.LineData;
import ClubberLogic.PR;
import ClubberLogic.UserReviews;
import Utlis.Constants;
import Utlis.IdWithName;
import Utlis.SessionUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetDBData
 */
@WebServlet("/GetDBData")
public class GetDBData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public GetDBData() throws SQLException {
        super();
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
		
		response.setContentType("text/html; charset=UTF-8");
		//get auction,offer ids:
		Integer auctionId= SessionUtils.getCurrentAuctionToDisplay(request.getSession());
		Integer offerID= SessionUtils.getCurrentOfferToDisplay(request.getSession());
				
		
		//all returns data types:
		Object data;
		
		String prEmail= SessionUtils.getUserEmail(request);
		String requestType= request.getParameter("RequestType");
		PrintWriter out = response.getWriter();
		String json=null;
        try 
        {
            
        	Gson gson = new Gson();

            if (requestType.equals(Constants.DB_DATA_NEW_AUCTION))
            {
            	data= DAL.getAllNewAuctionData();
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_MY_AUCTIONS))
            {
            	data= DAL.getAllMyAuctionsData();
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_AUCTION_MANAGEMENT))
            {
            	data= DAL.getAllAuctionOffersData(auctionId);
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_OFFER_REVIEW))
            {
            	data=DAL.getReviewedOfferData(offerID, auctionId);
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_MESSAGES))
            {
            	data=DAL.getAllMassages(auctionId);
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_ADD_MESSAGE))
            {
            	String description= request.getParameter(Constants.OUTGOING_MESSAGE_DESCRIPITON);
            	data= DAL.addNewMessage(description, auctionId);
            	json = gson.toJson(data);
            }
            else if (requestType.equals(Constants.DB_DATA_MAIN_LINES))
            {
            	data=DAL.getWelcomeScreenEvents(request.getParameter("InDate"));
            	json = gson.toJson(data);
       		}
            else if(requestType.equals(Constants.DB_DATA_USER_PROFILE))
            {
            	PR pr= DAL.getUserProfileData(prEmail);
            	json = gson.toJson(pr);
            }
            else if(requestType.equals(Constants.DB_DATA_PR_PROFILE_REVIEW)){
            	UserReviews reviews = DAL.getPrProfileReview(prEmail);
            	json = gson.toJson(reviews);
            }
            else if(requestType.equals(Constants.DB_DATA_CLIENT_PROFILE_REVIEW)){
            	UserReviews reviews = DAL.getClientProfileReview(prEmail);
            	json = gson.toJson(reviews);
            	
            }
            else if(requestType.equals(Constants.DB_DATA_RECOMENDED_LINES)){
            	List<LineData> recomendedLines = DAL.getCustomerRecomendedLines(prEmail);
            	json = gson.toJson(recomendedLines);
            }
            else if(requestType.equals(Constants.DB_DATA_ALL_BUSINESSES)){
            	ArrayList<BusinessData> businesses = DAL.getAllBusinesses();
            	json = gson.toJson(businesses);
            }
            else if(requestType.equals(Constants.DB_DATA_GET_BUSINESS_DATA)){
            	int businessId = Integer.parseInt(request.getParameter("businessId"));
            	BusinessData businessData = DAL.getBusinessData(businessId);
            	json = gson.toJson(businessData);
            }
            else if(requestType.equals(Constants.DB_DATA_GET_BUSINESS_AREAS_DATA))
            {
            	ArrayList<IdWithName> areasList = DAL.getBusinessAreasData();
            	json = gson.toJson(areasList);            	
            }
            else if(requestType.equals(Constants.DB_DATA_GET_BUSINESS_CITIES_DATA)){
            	int areaId = Integer.parseInt(request.getParameter("areaId"));
            	ArrayList<IdWithName> citiesList = DAL.getBusinessCitiesData(areaId);
            	json = gson.toJson(citiesList);            	
            }
            else if(requestType.equals(Constants.DB_DATA_GET_BUSINESSES_TYPE_DATA))
            {
            	ArrayList<IdWithName> typesList = DAL.getBusinessesTypeData();
            	json = gson.toJson(typesList);            	
            }            
            
            System.out.println(json);
            out.print(json);
            out.flush();    
        } 
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally 
        {
            out.close();
        }
	}
	


}
