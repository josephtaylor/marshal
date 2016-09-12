/*
  This is a more complex example that pulls Earthquake data
  from the USGS API and plots the locations of earthquake activity
  on the canvas.
  
  This is just earthquake data from 1 Jan, 2016, and there's a ton ! who knew?!
  
  Anyway, this example makes use of the HTTP Requests for Processing library
*/

// This is the only import you need for Marshal
import io.github.josephtaylor.marshal.*;

// the import for the HTTP Requests for Processing Library
import http.requests.*;

import java.util.List;
import java.util.Map;

Marshal marshal;
GetRequest getRequest;

List<Earthquake> earthquakes;

public void setup() {
  size(1000, 500);
  background(255);
  fill(0,0,0,50);
  smooth();
  
  earthquakes = new ArrayList<Earthquake>();
  
  marshal = new Marshal(this);
  
  // Sending our request to the public USGS API to get data back.
  getRequest = new GetRequest("http://earthquake.usgs.gov/fdsnws/event/1/query"
   + "?format=geojson&starttime=2016-01-01&endtime=2016-01-02");
  getRequest.addHeader("Accept", "application/json");
  getRequest.send();
   
  /*
    This is the big line right here.
    
    Here we are taking the JSON string returned from the HTTP request and unmarshaling it into
    a Response object, which we can use below.
  */
  Response response = marshal.unmarshal(getRequest.getContent(), Response.class, DataFormat.JSON);
  
  // Looping through each "feature", which is some kind of earthquake activity
  for (Feature feature : response.getFeatures()) {
    if (feature.getProperties().getMag() < 2.5) {
      //let's just skip the tiny earthquakes.
      continue;
    }
    
    // Here we do another request to the USGS using the "detail" property from our initial request.
    // This returns more detailed information about the Feature
    GetRequest detailRequest = new GetRequest(feature.getProperties().getDetail());
    detailRequest.addHeader("Accept", "application/json");
    detailRequest.send();
    
    // Here, like we did before, we take the response from the GetRequest 
    // and unmarshal or transform it into a DetailResponse object that we can use below.
    DetailResponse detailResponse = marshal.unmarshal(detailRequest.getContent(), DetailResponse.class, DataFormat.JSON);
    
    // dumping the lat/long data so we can see them coming in.
    println(detailResponse.getGeometry());
    
    //From each DetailResponse we can create an earthquake with the lat/long location and the magnitude.
    earthquakes.add(new Earthquake(
      new PVector(detailResponse.getGeometry().getCoordinates().get(0),
                  detailResponse.getGeometry().getCoordinates().get(1)
      ), feature.getProperties().getMag()));
  }
}

/**
  In our draw method, we plot each earthquake on the canvas.
  The ellipse that is drawn is scaled up/down based on the magnitude of the earthquake.
**/
void draw() {
  for (Earthquake earthquake : earthquakes) {
    PVector transformed = new GeoTransformer().geoToPixel(earthquake.getLocation());
    println(transformed.x + " " + transformed.y);
    int radius = (int) (earthquake.getMagnitude() * 10.f);
    ellipse(transformed.x, transformed.y, radius, radius);
  }
  noLoop();
}
  