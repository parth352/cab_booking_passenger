package myproject.example.myproject.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import myproject.example.myproject.entity.Position;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	  private Position position;

	    public Position getPosition() {
	        return position;
	    }

	    public void setPosition(Position position) {
	        this.position = position;
	    }
}
