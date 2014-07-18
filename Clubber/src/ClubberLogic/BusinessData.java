package ClubberLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BusinessData {
	private Integer m_Id;
	private String m_Name;
	private Integer m_AreaId;
	private Integer m_CityId;
	private Integer m_StreetId;
	private Integer m_HouseNumber;
	private String m_BusinessPhoneNumber;
	private String m_Description;
	private File m_Photo;
	private Integer m_BusinessTypeId;
	private ArrayList<LineData> m_Lines = new ArrayList<LineData>();
		
	
	public Integer getM_Id() {
		return m_Id;
	}
	public void setM_Id(Integer m_Id) {
		this.m_Id = m_Id;
	}
	public Integer getM_AreaId() {
		return m_AreaId;
	}
	public void setM_AreaId(Integer m_AreaId) {
		this.m_AreaId = m_AreaId;
	}
	public Integer getM_CityId() {
		return m_CityId;
	}
	public void setM_CityId(Integer m_CityId) {
		this.m_CityId = m_CityId;
	}
	public Integer getM_StreetId() {
		return m_StreetId;
	}
	public void setM_StreetId(Integer m_StreetId) {
		this.m_StreetId = m_StreetId;
	}
	public Integer getM_HouseNumber() {
		return m_HouseNumber;
	}
	public void setM_HouseNumber(Integer m_HouseNumber) {
		this.m_HouseNumber = m_HouseNumber;
	}
	public String getM_BusinessPhoneNumber() {
		return m_BusinessPhoneNumber;
	}
	public void setM_BusinessPhoneNumber(String m_BusinessPhoneNumber) {
		this.m_BusinessPhoneNumber = m_BusinessPhoneNumber;
	}
	public String getM_Description() {
		return m_Description;
	}
	public void setM_Description(String m_Description) {
		this.m_Description = m_Description;
	}
	public File getM_Photo() {
		return m_Photo;
	}
	public void setM_Photo(File m_Photo) {
		this.m_Photo = m_Photo;
	}
	public Integer getM_BusinessTypeId() {
		return m_BusinessTypeId;
	}
	public void setM_BusinessTypeId(Integer m_BusinessTypeId) {
		this.m_BusinessTypeId = m_BusinessTypeId;
	}
	public void setM_Lines(ArrayList<LineData> m_Lines) {
		this.m_Lines = m_Lines;
	}
	public Integer getId() {
		return m_Id;
	}
	public void setId(Integer id) {
		this.m_Id = id;
	}
	
	public List<LineData> getM_Lines() {
		return m_Lines;
	}
	public void Add_Line(LineData i_Line) {
		m_Lines.add(i_Line);
	}
	public String getM_Name() {
		return m_Name;
	}
	public void setM_Name(String m_Name) {
		this.m_Name = m_Name;
	}
}
