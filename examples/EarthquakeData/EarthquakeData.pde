import io.github.josephtaylor.marshal.*;
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
  
  getRequest = new GetRequest("http://earthquake.usgs.gov/fdsnws/event/1/query"
   + "?format=geojson&starttime=2016-01-01&endtime=2016-01-02");
  getRequest.addHeader("Accept", "application/json");
  getRequest.send();
  
  Response response = marshal.unmarshal(getRequest.getContent(), Response.class, DataFormat.JSON);
  
  for (Feature feature : response.getFeatures()) {
    if (feature.getProperties().getMag() < 2.5) {
      //let's just skip the tiny earthquakes.
      continue;
    }
    GetRequest detailRequest = new GetRequest(feature.getProperties().getDetail());
    detailRequest.addHeader("Accept", "application/json");
    detailRequest.send();
    
    DetailResponse detailResponse = marshal.unmarshal(detailRequest.getContent(), DetailResponse.class, DataFormat.JSON);
    
    println(detailResponse.getGeometry());
    
    earthquakes.add(new Earthquake(
      new PVector(detailResponse.getGeometry().getCoordinates().get(0),
                  detailResponse.getGeometry().getCoordinates().get(1)
      ), feature.getProperties().getMag()));
  }
}

void draw() {
  for (Earthquake earthquake : earthquakes) {
    PVector transformed = new GeoTransformer().geoToPixel(earthquake.getLocation());
    println(transformed.x + " " + transformed.y);
    int radius = (int) (earthquake.getMagnitude() * 10.f);
    ellipse(transformed.x, transformed.y, radius, radius);
  }
  noLoop();
}
  