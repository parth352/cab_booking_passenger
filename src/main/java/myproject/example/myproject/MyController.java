package myproject.example.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import myproject.example.myproject.entity.Position;
import myproject.example.myproject.service.MapService;

@Controller
@CrossOrigin(origins = "http://localhost")
@RequestMapping("/app")
public class MyController {
	
	@Autowired
	public MapService mapService;
	
	

    public MyController(MapService mapService) {
        this.mapService = mapService;
    }
    
    @RequestMapping(value="location" ,method=RequestMethod.GET)
	public String GoToLocation() {
		return "location.jsp";
	}
    @RequestMapping(value="address" ,method=RequestMethod.GET)
   	public String GoToAddress() {
   		return "address.html";
    	
   	}
    
    @GetMapping("/map")
    public String getPosition(@RequestParam double latitude, @RequestParam double longitude) {
         return "map.jsp";
    }
  
    
//    @GetMapping("/getCoordinates")
//    public String getCoordinates(@RequestParam String saddress, @RequestParam String daddress) {
////        mapService.getCoordinates(saddress,daddress);
////        return "route.html";
//    	System.out.print("getCoordinates api hit.");
//        return mapService.getCoordinates(saddress,daddress);
//    }
    
    @MessageMapping("/sendCoordinates")
    @SendTo("/topic/response")
    public String sendCoordinates(String message ) {
         try {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(message);
        String saddress = jsonNode.get("saddress").asText();
        String daddress = jsonNode.get("daddress").asText();

        System.out.print("sendCoordinates api hit.");
        String response = mapService.getCoordinates(saddress, daddress);
        System.out.print("function working completed.");
        
        return response;
    } catch (Exception e) {
        e.printStackTrace();
        return "{\"error\": \"Error parsing message\"}";
    }
    }
    
   /////////////////////////////////////////////////////////////////////
    @GetMapping("/getRouteUrl/{id}")
    public String getRouteUrl(@PathVariable long id) {
        return mapService.getCoordinatesUrl(id);
    }
    
    @GetMapping("/route")
    public String getRoute() {
    	return "route.html";
    }
//    @PostMapping("/confirm")
//    public String postConfirmRide() {
//    	return mapService.postConfirmRide();
//    }
// 
    
    @MessageMapping("/confirm")    // for websocket
    @SendTo("/topic/drivers")
    public String postConfirmRide() {
    	try {
    		String response = mapService.postConfirmRide();
    		System.out.println(response);
    		return response;
    	} catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Error parsing message\"}";
        }
    
//    	return"{ \\\"Error parsing response\\\"}";
    }
}
