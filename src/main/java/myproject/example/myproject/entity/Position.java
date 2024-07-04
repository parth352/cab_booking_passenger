package myproject.example.myproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Position {
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
    private double slatitude;
    private double slongitude;
    private double dlatitude;
    private double dlongitude;
    private String saddress;
    private String daddress;
    
	public Position(Long id, double slatitude, double slongitude, double dlatitude, double dlongitude, String saddress,
			String daddress) {
		super();
		this.id = id;
		this.slatitude = slatitude;
		this.slongitude = slongitude;
		this.dlatitude = dlatitude;
		this.dlongitude = dlongitude;
		this.saddress = saddress;
		this.daddress = daddress;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getSlatitude() {
		return slatitude;
	}
	public void setSlatitude(double slatitude) {
		this.slatitude = slatitude;
	}
	public double getSlongitude() {
		return slongitude;
	}
	public void setSlongitude(double slongitude) {
		this.slongitude = slongitude;
	}
	public double getDlatitude() {
		return dlatitude;
	}
	public void setDlatitude(double dlatitude) {
		this.dlatitude = dlatitude;
	}
	public double getDlongitude() {
		return dlongitude;
	}
	public void setDlongitude(double dlongitude) {
		this.dlongitude = dlongitude;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getDaddress() {
		return daddress;
	}
	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

    // Getters and setters
   
//	@Override
//	public String toString() {
//		return "Position [latitude=" + latitude + ", longitude=" + longitude + "]";
//	}
}
