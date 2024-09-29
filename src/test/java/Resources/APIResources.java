package Resources;

// Enum is special class is JAVA which has collection of constants and methods

public enum APIResources {

	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
    private String resource;
    
    
	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;	
	}
	
	public String getResource() {
		return resource;
		
	}
	
	
}
