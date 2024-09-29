package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.GoogleMapsAdd;


public class TestDataBuild {
	
	public GoogleMapsAdd add_place_payload(String name,String language , String address) 
	{
		POJO.GoogleMapsAdd gm=new POJO.GoogleMapsAdd();
		gm.setAccuracy(50);
		gm.setAddress(address);
		gm.setLanguage(language);
		gm.setWebsite("http://google.com");
		gm.setName(name);
		gm.setPhone_number("(+91) 983 893 3937");
		
		
		POJO.Location lc=new POJO.Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		 List<String> t1=new ArrayList<String>();
		 t1.add("shoe park");
		 t1.add("shop");
		 
		 gm.setTypes(t1);
		 gm.setLocation(lc);
	
		 
		 return gm;
		
	}
	
	public String deletePlacepayload(String placeId) {
		
		return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";
		
	}

}
