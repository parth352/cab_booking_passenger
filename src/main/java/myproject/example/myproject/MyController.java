package myproject.example.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import myproject.example.myproject.entity.Position;
import myproject.example.myproject.service.MapService;

@Controller
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
//        mapService.getCoordinates(saddress,daddress);
//        return "location.jsp";
//    }
    
    @GetMapping("/getCoordinates")
    public String getCoordinates(@RequestParam String saddress, @RequestParam String daddress) {
//        mapService.getCoordinates(saddress,daddress);
//        return "route.html";
        return mapService.getCoordinates(saddress,daddress);
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
    @PostMapping("/confirm")
    public String postConfirmRide() {
    	return mapService.postConfirmRide();
    }
 
}
