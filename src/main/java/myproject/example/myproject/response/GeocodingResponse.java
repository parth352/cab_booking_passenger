package myproject.example.myproject.response;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResponse {
	 private List<Result> results;

	    public List<Result> getResults() {
	        return results;
	    }

	    public void setResults(List<Result> results) {
	        this.results = results;
	    }
}
