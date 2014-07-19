
package ClubberLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Utlis.AuctionManagementData;
import Utlis.IdWithName;
import Utlis.NewAuctionData;

public class DAL {
	private static Connection conn;
	private static Statement stmt;
	
	public DAL()
	{
	}
	
	private static void connectToDBServer()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubber_db?useUnicode=true&characterEncoding=UTF8", "root", "a");
			stmt= conn.createStatement();	
		} 
		catch (SQLException e) {
		e.printStackTrace();} catch (ClassNotFoundException e) {
			e.printStackTrace();
			disconnectFromDBServer();
		}	
	}
	
	private static void disconnectFromDBServer()
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static List<AuctionData> getAllMyAuctionsData() throws ParseException
	{
		List<AuctionData> data= new LinkedList <AuctionData>();
		
		
		connectToDBServer();
		
		try {
			
			ResultSet rs = stmt.executeQuery("select auc.*, event_type.Name as event_type_name, COALESCE(counter,0) as counter from event_type,"
					+ " Auction auc left join (Select auction_id, count(id) as counter from  offers) offers1 on offers1.auction_id = auc.id where auc.Event_Type = event_type.id order by auc.Event_Date");
			
			while (rs.next())
			{
				AuctionData auction= new AuctionData();
				auction.setEventDate(rs.getDate("Event_Date"));				
				auction.setDescription(rs.getString("Description"));
				auction.setEventType(new IdWithName(rs.getInt("auc.Event_Type"),rs.getString("event_type_name") ));
				auction.setId(rs.getInt("id"));
				auction.setOfferNumber(rs.getInt("counter"));
				data.add(auction);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			disconnectFromDBServer();
		}
		return data;	
	}
	
	public static OfferData getReviewedOfferData(Integer currOfferID, Integer currAuctionID )
	{
		OfferData offer= new OfferData();
		connectToDBServer();
		
		try {
			
			/*load offer data*/
			ResultSet rs = stmt.executeQuery("select * from offer_status, Line,Offers off, Users where off.Line_id = Line.id and Users.id= off.Pr_id and offer_status.id=off.Offer_Status and off.ID="+currOfferID);
			while (rs.next())
			{
				offer.setOfferStatusId(new IdWithName(rs.getInt("offer_status.id"),rs.getString("Offer_Status.displayName")));
				offer.setId(rs.getInt("off.id"));	
				offer.setDescription(rs.getString("off.Description"));
				offer.setExpirationDate(rs.getDate("off.Expiration_Date"));
				offer.setAuctionId(currOfferID);
				offer.setLineId(new IdWithName(rs.getInt("off.Line_id"), rs.getString("Line.Name")));
				offer.setMaxArrivalHour(rs.getTime("off.Max_Arrival_Hour"));
				offer.setPrId(new IdWithName(rs.getInt("off.Pr_id"), rs.getString("Users.First_Name") + " " + rs.getString("Users.Last_Name")));
				offer.setSubmitDate(rs.getDate("off.Created_On"));
			}
			
			/*load offer treats*/
			rs = stmt.executeQuery("select * from offer_treats ot, treats where treats.id= ot.treat_id and ot.Offer_id=" +currOfferID);
			
			while (rs.next())
			{
				offer.getOfferTreats().add(new IdWithName(null, rs.getString("treats.Name")));
			}			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			disconnectFromDBServer();
		}

		return offer;
	}
	
	public static List <UserMessagesData> getAllMassages(Integer currAuctionID)
	{
		List <UserMessagesData> messages= new LinkedList<UserMessagesData>();
		connectToDBServer();
		
		try {
			
			/*load messages*/
			ResultSet rs = stmt.executeQuery("select * from Messages where Messages.Auction_id="+currAuctionID);
			while (rs.next())
			{
				UserMessagesData message= new UserMessagesData();
				message.setAuctionId(currAuctionID);
				message.setCreatedOn(rs.getTimestamp("Created_On"));
				message.setDescription(rs.getString("Description"));
				message.setFromUserId(rs.getInt("From_User_id"));
				message.setId(rs.getInt("id"));
				message.setToUserId(rs.getInt("To_User_id"));
				
				messages.add(message);
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			disconnectFromDBServer();
		}
		
		return messages;
		
	}
	
	public static boolean addNewMessage(String description) throws Exception
	{
		connectToDBServer();
		
		try {
			//create new message in db:
			String sqlMessageInsertion= String.format("insert into Messages values(%d,%d,%d,%d,'%s','%s')",
					null,null,null,null,null,description);
			
			stmt.executeUpdate(sqlMessageInsertion);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally
		{
			disconnectFromDBServer();
		}
	}
	
	public static AuctionManagementData getAllAuctionOffersData(Integer currAuctionID) throws ParseException
	{
		List<OfferData> offers= new LinkedList <OfferData>();
		AuctionData auction = new AuctionData();
		AuctionManagementData data;
		connectToDBServer();
		
		try {
			
			ResultSet rs = stmt.executeQuery("select * from Line,Offers off, Users where off.Line_id = Line.id and Users.id= off.Pr_id and off.Auction_ID="+currAuctionID + " order by off.id");
			while (rs.next())
			{
				OfferData offer= new OfferData();
				offer.setId(rs.getInt("off.id"));
				offer.setAuctionId(currAuctionID);	
				offer.setDescription(rs.getString("off.Description"));
				offer.setExpirationDate(rs.getDate("off.Expiration_Date"));
				offer.setId(rs.getInt("off.id"));
				offer.setLineId(new IdWithName(rs.getInt("off.Line_id"), rs.getString("Line.Name")));
				offer.setMaxArrivalHour(rs.getTime("off.Max_Arrival_Hour"));
				offer.setPrId(new IdWithName(rs.getInt("off.Pr_id"), rs.getString("Users.First_Name") + " " + rs.getString("Users.Last_Name")));
				offer.setSubmitDate(rs.getDate("off.Created_On"));
				offers.add(offer);
			}
			

			rs = stmt.executeQuery("select * from Auction auc ,Areas, event_type where auc.id=" +currAuctionID+ " and Areas.id= auc.area  and auc.Event_Type = event_type.id");
			while (rs.next())
			{
				auction.setId(currAuctionID);
				auction.setArea(new IdWithName(rs.getInt("Areas.id"),rs.getString("Areas.Name")));
				auction.setAuctionStatus(new IdWithName (rs.getInt("Auction_Status"),null));
				auction.setBusinessType(new IdWithName (rs.getInt("Business_Type"),null));
				auction.setDateFlexible(rs.getBoolean("Is_Date_Flexible"));
				auction.setDescription(rs.getString("auc.Description"));
				auction.setEventDate(rs.getDate("Event_Date"));
				auction.setEventType(new IdWithName (rs.getInt("auc.Event_Type"),rs.getString("event_type.Name")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			disconnectFromDBServer();
		}
		
		data= new AuctionManagementData(offers,auction);
		return data;	
	}
	
	
	public static NewAuctionData getAllNewAuctionData ()
	{
		connectToDBServer();
		NewAuctionData data= new NewAuctionData();
		String query;
			
		query = "Select * from Event_Type;";
		data.setEventTypes(GetIdAndNameData(query));
		
		query = "Select * from Music_Style;";
		data.setMusicStyles(GetIdAndNameData(query));
		
		query = "Select * from Areas;";
		data.setArea(GetIdAndNameData(query));
		
		query = "Select * from Business_Type;";
		data.setBusinessType(GetIdAndNameData(query));
		
		query = "Select * from Businesses;";
		data.setCertainBusiness(GetIdAndNameData(query));
		
		query = "Select * from Sitts_Type;";
		data.setSittsType(GetIdAndNameData(query));
		
		disconnectFromDBServer();
	
		
		return data;
	}
	
	public static LinkedList<IdWithName> GetIdAndNameData(String query)
	{
		
		LinkedList<IdWithName> data= new LinkedList<IdWithName> ();  
		try 
		{
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				data.add(new IdWithName(rs.getInt("Id"), rs.getString("Name")));
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 

		return data;
	}
	
	public static void addNewAuction(AuctionData auction) throws Exception
	{
		connectToDBServer();
		
		DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm:ss a");
		String date= df.format(auction.getEventDate());
		Date date2= df.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(date2.getTime());
		
		try {
			//create new auction in db:
			String sqlAuctionInsertion= String.format("insert into Auction values(%d,%d,'%s','%s',%d,'%s','%s',%d,%d,%d,'%s',%d,%d,'%s',%d,%d, %s)",
					null,auction.getMinAge(),auction.getExceptionsDescription(),auction.getGuestesQuantiny(),getValidID(auction.getEventType()), 
					sqlDate,auction.getIsDateFlexible(),getValidID( auction.getArea()),getValidID(auction.getBusinessType()),
					getValidID(auction.getCertainBusiness()),auction.getDescription(),getValidID(auction.getDetailsToDisplay()),
					getValidID(auction.getSittsType()),auction.isSmoking(), getValidID(auction.getAuctionStatus()),	auction.getCreatedBy(), null);
			
			stmt.executeUpdate(sqlAuctionInsertion, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs= stmt.getGeneratedKeys();
			rs.next();
			Integer auctionId= rs.getInt(1);
			//add relevant records to auction music style table:
			for(IdWithName item: auction.getMusicStyle())
			{
				String sqlMusicStyles= String.format("insert into Auction_Music_Style values(%d,%d,%d)", null,auctionId , item.getId());
				stmt.executeUpdate(sqlMusicStyles);
			}	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			disconnectFromDBServer();
		}
	}


	private static Integer getValidID(IdWithName fieldToCheck)
	{
		Integer res=null;
		
		if (fieldToCheck != null)
		{
			res = fieldToCheck.getId();
		}
		
		return res;
	}
	
	/*
	 * Maayan: 	24/4/2014
	 * 			* add function - insertNewUser
	 */
	public static boolean insertNewUser(UserData userData, UserType userType) throws ParseException {
		
		boolean isSucceed = true;

		connectToDBServer();

		Date birthDate = StringToDate(userData.getBirthDate());
				
		String sql= "INSERT INTO USERS(User_Type, First_Name, Last_Name, Gender, Phone_Number, Email, Password, Birth_Date)"
				+ " VALUES('"+userType+"','"+userData.getFirstName()+"','"+userData.getLastName()+"','"+userData.getGender()+"','"+userData.getPhoneNumber()+"','"+userData.getEmail()+"','"+userData.getPassword()+"','"+birthDate+"')";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isSucceed = false;
			e.printStackTrace();
		}
		finally
		{
			disconnectFromDBServer();
		}		
		
		return isSucceed;
	}

	private static Date StringToDate(Date userBirthDate) {
		
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date= df.format(userBirthDate);
		Date date2 = new Date();
		
		try {
			date2 = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date sqlDate = new java.sql.Date(date2.getTime());

		return sqlDate;
	}

	/*
	 * Maayan: 	24/4/2014
	 * 			* add function - getUserLoginAttempts
	 */	
	public static int getUserLoginAttempts(String email) {
	
		int loginAttempts =0;
		
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT Login_Attempts FROM clubber_db.users;");
			while (rs.next())
			{
				loginAttempts = rs.getInt("Login_Attempts");
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
				
		return loginAttempts;
		
	}
	
	/*
	 * Orel & Maayan: 	25/4/2014
	 * 			* add function - increaseLoginAttemptsDB
	 */		
	public static void increaseLoginAttemptsDB(String email) {
		
		connectToDBServer();
		
		try {
			stmt.executeQuery("UPDATE clubber_db.users "
							   + "SET Login_attempts = Login_attempts + 1 "
							   + "WHERE Email ='" + email + "'");	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
	}

	/*
	 * Maayan: 	24/4/2014
	 * 			* add function - isEmailUnique
	 */			
	public static boolean isEmailUnique(String userEmail) {

		boolean isEmailUnique = true;
		
		connectToDBServer();
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT Email "
										   + "FROM clubber_db.users "
										   + "WHERE Email ='" + userEmail + "'");
			if (rs.next())
			{
				isEmailUnique = false;
			}		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			isEmailUnique = false;
			
		}
		finally{
			disconnectFromDBServer();
		}
				
		return isEmailUnique;		
		
	}	
	
	/*
	 * Orel & Maayan: 25/4/2014
	 * 				  * add function - isEmailExists
	 */
	public static boolean isEmailExists(String email) {
		
		boolean isExists = false;
		
		connectToDBServer();
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT Email "
										   + "FROM clubber_db.users "
										   + "WHERE Email ='" + email + "'");
			if (rs.next())
			{
				isExists = true;
			}		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		return isExists;
	}
	
	/*
	 * Orel & Maayan: 25/4/2014
	 * 				  * add function - isPasswordMatchesEmail
	 */
	public static boolean isPasswordMatcheEmail(String email, String password) {
		
		boolean isMatch = true;
		
		connectToDBServer();
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT Password "
										   + "FROM clubber_db.users "
										   + "WHERE Email ='" + email + "'");
			if (rs.next())
			{
				String userPassword = rs.getString("Password");
				
				if(userPassword.equals(password) == false)
				{
					isMatch = false;
				}
			}		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			isMatch = false;
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		return isMatch;
	}

	public static void updateUserPassword(String email, String password) {
		// TODO Auto-generated method stub
		
		connectToDBServer();
		
		try {
			stmt.executeQuery("UPDATE clubber_db.users "
						   + "SET Password =" + password
						   + "WHERE Email ='" + email + "'");	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
	}
	
	public static PR getUserProfileData(String email)
	{
		
		PR pr = new PR();
		connectToDBServer();
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT * "
					   + "FROM clubber_db.users "
					   + "WHERE Email ='" + email + "'");
			
			while (rs.next())
			{
				pr.setFirstName(rs.getString("First_Name"));
				pr.setLastName(rs.getString("Last_Name"));
				pr.setGender(rs.getString("Gender"));
				pr.setPhoneNumber(rs.getString("Phone_Number"));
				pr.setEmail(rs.getString("Email"));
				pr.setPassword(rs.getString("Password"));
				pr.setBirthDate(rs.getDate("Birth_Date"));
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		return pr;
		
	}
	
	public static boolean updateUserDetails(UserData userData) {
		// TODO Auto-generated method stub
		
		boolean isSucceed = true;
		
		connectToDBServer();

		String sql = "UPDATE clubber_db.users "
				   + "SET First_Name = '" + userData.getFirstName() + "'"
				   + ", Last_Name = '" + userData.getLastName() + "'"
				   + ", Gender = '" + userData.getGender() + "'"
				   + ", Phone_Number = '" + userData.getPhoneNumber() + "'"
				   + ", Password = '" + userData.getPassword() + "'"
				   + ", Birth_Date = '" + userData.getBirthDate() + "'"
				   + " WHERE Email ='" + userData.getEmail() + "'";
		
		try {
			stmt.executeUpdate(sql);	
		} 
		catch (SQLException e) {
			isSucceed = false;
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		return isSucceed;
	}
	
	public static UserReviews getPrProfileReview(String email) {
		// TODO Auto-generated method stub
		
		UserReviews reviews = null;
		
		connectToDBServer();

		try {

			ResultSet rs = stmt.executeQuery("SELECT Availability, Realiability, Treats "
					   + "FROM clubber_db.pr_review "
					   + "WHERE PR_id IN "
					   + "(SELECT id form clubber_db.users "
					   + "WHERE Email ='" + email + "')");
			
			
			while (rs.next())
			{
				reviews = new UserReviews();
				reviews.setAvailability(rs.getInt("Availability"));
				reviews.setRealiability(rs.getInt("Realiability"));
				reviews.setTreats(rs.getInt("Treats"));
			}
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		
		return reviews;
	}

	public static UserReviews getClientProfileReview(String email) {
		// TODO Auto-generated method stub
		UserReviews reviews = null;
		
		connectToDBServer();

		try {

			ResultSet rs = stmt.executeQuery("SELECT Punctuality, Realiability "
					   + "FROM clubber_db.customer_review "
					   + "WHERE PR_id IN "
					   + "(SELECT id form clubber_db.users "
					   + "WHERE Email ='" + email + "')");
			
			
			while (rs.next())
			{
				reviews = new UserReviews();
				reviews.setPunctuality(rs.getInt("Punctuality"));
				reviews.setRealiability(rs.getInt("Realiability"));
			}
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		
		return reviews;

	}

	public static List<LineData> getCustomerRecomendedLines(String email) {

		List<LineData> lines = null;
		
		UserReviews reviews = null;
		
		connectToDBServer();

		try {

			ResultSet rs = stmt.executeQuery("select L.name, L.line_photo "
					+ "from users as U, auction as A, line as L, businesses as B"
					+ " where email = '" + email + "' and"
					+ " U.id = A.Created_By and"
					+ " A.area = B.Area and"
					+ " B.id = L.Business_id");
			
			while (rs.next())
			{
				lines = new ArrayList<>();
				LineData line = new LineData();
				line.setM_LineName(rs.getString("name"));
				//line.setPhoto(rs.getString("line_photo"));
				
				lines.add(line);
			}
		
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally{
			disconnectFromDBServer();
		}
		
		return lines;
	}

	
	public static ArrayList<BusinessData> getWelcomeScreenEvents(String i_Date) throws ParseException {
		String dateStr;
		DateFormat formatter ; 
		Date date ; 
		   formatter = new SimpleDateFormat("yyyy-mm-dd");
		   
		   ArrayList<BusinessData> data = new ArrayList<BusinessData>();
		// access date fields
			
			connectToDBServer();
			
			try 
			{
				ResultSet rs = stmt.executeQuery("select * from line L, Businesses B where L.Business_id = B.id AND L.Line_Start_Date >= '"+i_Date+"'");
				while (rs.next())
				{
					dateStr = (rs.getString("L.Line_Start_Date"));
					BusinessData bData = new BusinessData();
					//set business data
					bData.setId(rs.getInt("b.id"));
					bData.setM_Name(rs.getString("b.name"));
					bData.setM_StreetId(rs.getInt("b.street"));
					bData.setM_HouseNumber(rs.getInt("b.structure_number"));
					bData.setM_BusinessPhoneNumber(rs.getString("b.Business_Phone_Number"));
					bData.setM_Description(rs.getString("b.Description"));
					bData.setM_BusinessTypeId(rs.getInt("b.Business_Type"));
					bData.setM_CityId(rs.getInt("b.city"));
					bData.setM_AreaId(rs.getInt("b.area"));
					
					LineData lData= new LineData();
					date = formatter.parse(dateStr);
					lData.setM_LineName(rs.getString("L.name"));
					lData.setDescription(rs.getString("L.Description"));
					lData.setDj(rs.getString("L.DJ"));
					lData.setMinAge(rs.getInt("L.Min_Age"));
					lData.setStartDate(date);
					
					bData.getM_Lines().add(lData);
					
					data.add(bData);
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				disconnectFromDBServer();
			}
			return data;
		}
		
	
	
}