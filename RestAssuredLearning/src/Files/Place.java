package Files;

public class Place {
	public location location;
	public int accuracy;
	public String name;
	public String phone_number;
	public String address;
	public types types;
	public String website;
	public String language;
	public Place(){
	}
	public Place(location loc, int acc, String name, String phone, String add, types types, String website, String language){
		this.location = loc;
		this.accuracy = acc;
		this.name = name;
		this.phone_number = phone;
		this.address = add;
		this.types = types;
		this.website = website;
		this.language = language;
	}
	@Override
    public String toString() { 
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": "+this.location.lat+",\r\n"
				+ "    \"lng\": "+this.location.lng+"\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": "+this.accuracy+",\r\n"
				+ "  \"name\": "+this.name+",\r\n"
				+ "  \"phone_number\": "+this.phone_number+",\r\n"
				+ "  \"address\": "+this.address+",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": "+this.website+",\r\n"
				+ "  \"language\": "+this.language+"\r\n"
				+ "}\r\n"
				+ " \r\n"
				+ "";
    }
	
}
 
 


