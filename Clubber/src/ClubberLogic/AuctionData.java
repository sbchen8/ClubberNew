package ClubberLogic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Utlis.IdWithName;

public class AuctionData {
	private Integer id;
	private List<IdWithName> musicStyle;
	private Integer minAge;
	private String exceptionsDescription;
	private Integer guestesQuantiny;
	private IdWithName eventType;
	private Date eventDate;
	private boolean isDateFlexible;
	private IdWithName area;
	private List<IdWithName> businessType;
	private IdWithName certainBusiness;
	private String description;
	private IdWithName detailsToDisplay;
	private List<IdWithName> sittsType;
	private boolean smoking;
	private IdWithName auctionStatus;
	private IdWithName createdBy;
	private Integer offerNumber;
	
	public AuctionData()
	{
		auctionStatus=new IdWithName(1,null);
		musicStyle= new LinkedList<IdWithName>();	
		sittsType= new LinkedList<IdWithName>();	
		businessType= new LinkedList<IdWithName>();
		createdBy =null;
		setOfferNumber(0);
	}
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public IdWithName getEventType() {
		return eventType;
	}
	
	public void setEventType(IdWithName eventType) {
		this.eventType = eventType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public String getExceptionsDescription() {
		return exceptionsDescription;
	}

	public void setExceptionsDescription(String exceptionsDescription) {
		this.exceptionsDescription = exceptionsDescription;
	}

	public Integer getGuestesQuantiny() {
		return guestesQuantiny;
	}

	public void setGuestesQuantiny(Integer guestesQuantiny) {
		this.guestesQuantiny = guestesQuantiny;
	}

	public String getIsDateFlexible() {
		if (isDateFlexible)
		{
			return "True";
		}
		else
		{
			return "False";
		}
	}

	public void setDateFlexible(boolean isDateFlexible) {
		this.isDateFlexible = isDateFlexible;
	}

	public IdWithName getArea() {
		return area;
	}

	public void setArea(IdWithName area) {
		this.area = area;
	}

	public IdWithName getCertainBusiness() {
		return certainBusiness;
	}

	public void setCertainBusiness(IdWithName certainBusiness) {
		this.certainBusiness = certainBusiness;
	}

	public IdWithName getDetailsToDisplay() {
		return detailsToDisplay;
	}

	public void setDetailsToDisplay(IdWithName detailsToDisplay) {
		this.detailsToDisplay = detailsToDisplay;
	}

	public String isSmoking() {
		if (smoking)
		{
			return "True";
		}
		else
		{
			return "False";
		}
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public IdWithName getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(IdWithName auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public IdWithName getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(IdWithName createdBy) {
		this.createdBy = createdBy;
	}

	public List<IdWithName> getMusicStyle() {
		return musicStyle;
	}

	public void setMusicStyle(List<IdWithName> musicStyle) {
		this.musicStyle = musicStyle;
	}
	public Integer getOfferNumber() {
		return offerNumber;
	}
	public void setOfferNumber(Integer offerNumber) {
		this.offerNumber = offerNumber;
	}
	public List<IdWithName> getBusinessType() {
		return businessType;
	}
	public void setBusinessType(List<IdWithName> businessType) {
		this.businessType = businessType;
	}
	public List<IdWithName> getSittsType() {
		return sittsType;
	}
	public void setSittsType(List<IdWithName> sittsType) {
		this.sittsType = sittsType;
	}
	 

}
