package pruebaWeb;

public class InfoPais {
	private String _id;
	
	private String iso2;
	private String iso3;
	private String lat;
	private String longi;
	private String flag;
	public InfoPais(String _id, String iso2, String iso3, String lat, String longi, String flag) {
		super();
		this._id = _id;
		this.iso2 = iso2;
		this.iso3 = iso3;
		this.lat = lat;
		this.longi = longi;
		this.flag = flag;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getIso2() {
		return iso2;
	}
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	} 
	
	public String toString() {
		return "Codigo ISO-3 del pais: "+this.getIso3()+"\n";
		
	}
	


}
