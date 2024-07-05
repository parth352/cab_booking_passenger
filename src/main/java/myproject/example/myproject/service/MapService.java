package myproject.example.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import myproject.example.myproject.dao.PositionDao;
import myproject.example.myproject.entity.Position;
import myproject.example.myproject.response.GeocodingResponse;
import myproject.example.myproject.response.Result;
@Service
public class MapService {
	 double slat;
	 double slng;
	 double dlat;
	 double dlng;
	 String sadd;
	 String dadd;
	 
	 @Value("${tomtom.api.key}")
	 private String apiKey;
	 
   @Autowired
   private final RestTemplate restTemplate;
   public MapService(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }
   
	    @Autowired
	    public PositionDao positionDao;
	    
//	    // saving cordinates to DB
	    public Position savePosition(double slatitude, double slongitude, double dlatitude, double dlongitude, String saddress, String daddress) {
	        Position position = new Position(null, slatitude, slongitude, dlatitude, dlongitude, saddress, daddress);
	        position.setSlatitude(slatitude);
	        position.setSlongitude(slongitude);  
	        position.setDlatitude(dlatitude);
	        position.setDlongitude(dlongitude); 
	        position.setSaddress(saddress);
	        position.setDaddress(daddress);
	        return positionDao.save(position);
	    }
	    
//	    // Finding cordinates based on address
	    public String getCoordinates(String saddress, String daddress) {
	        RestTemplate restTemplate = new RestTemplate();
	        sadd =saddress;
	        dadd = daddress;
	        String url1 = "https://api.tomtom.com/search/2/geocode/" + saddress + ".json?key=" + apiKey;
	        String url2 = "https://api.tomtom.com/search/2/geocode/" + daddress + ".json?key=" + apiKey;

	        String response1 = restTemplate.getForObject(url1, String.class);
	        String response2 = restTemplate.getForObject(url2, String.class);
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode rootNode1 = mapper.readTree(response1);
	            JsonNode positionNode1 = rootNode1.path("results").path(0).path("position");
	            slat = positionNode1.path("lat").asDouble();
	            slng = positionNode1.path("lon").asDouble();
	            
	            // for Destination address
	            ObjectMapper mapper2 = new ObjectMapper();
	            JsonNode rootNode2 = mapper2.readTree(response2);
	            JsonNode positionNode2 = rootNode2.path("results").path(0).path("position");
	            dlat = positionNode2.path("lat").asDouble();
	            dlng = positionNode2.path("lon").asDouble();


	            System.out.println("Sucessfully completed");
	            
//	            return "redirect:http://localhost:8080/route?slat=" + slat + "&slng=" + slng + "&dlat=" + dlat + "&dlng=" +dlng;
	            return "{\"slat\": " + slat + ", \"slng\": " + slng + ", \"dlat\": " + dlat + ", \"dlng\": " + dlng + "}";
	            // above response is for websocket
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "{\"error\": \"Error parsing response\"}";
	        }
	    }
	    
	    public String postConfirmRide() {  // only stores data in database if user press confirm ride
	    	 savePosition(slat,slng,dlat,dlng,sadd,dadd);
	    	return "location.jsp"; 
	    }
//////////////////////////////////////////////////////////////////////////////	
	    
	    public String getCoordinatesUrl(long positionId) {
	        Position position = positionDao.findById(positionId);
	        if (position != null) {
	            double slat = position.getSlatitude();
	            double slng = position.getSlongitude();
	            double dlat = position.getDlatitude();
	            double dlng = position.getDlongitude();
	            return "redirect:http://localhost:8080/route?slat=" + slat + "&slng=" + slng + "&dlat=" + dlat + "&dlng=" +dlng;
	        } else {
	            return "Position not found";
	        }
	    }
	    
	    
	}
