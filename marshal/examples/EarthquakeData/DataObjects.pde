/*
  This file contains several small objects.
  
  These objects represent a subset of the JSON that is
  returned from the USGS Endpoints.
  
*/
class Response {
  private List<Feature> features = new ArrayList<Feature>();
  
  public List<Feature> getFeatures() {
    return features;
  }
  
  public void setFeatures(final List<Feature> features) {
    this.features = features;
  }
}

class Geometry {
  private String type;
  
  private List<Float> coordinates;
  
  public List<Float> getCoordinates() {
    return coordinates;
  }
  
  public String getType() {
    return type;
  }
  
  public void setCoordinates(final List<Float> coordinates) {
    this.coordinates = coordinates;
  }
  
  public void setType(final String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return super.toString() + " [ " + coordinates.get(0) + ", " + coordinates.get(1) + " ]";
  }
}

class DetailResponse {
  private Geometry geometry;
  
  public Geometry getGeometry() {
    return geometry;
  }
  
  public void setGeometry(final Geometry geometry) {
    this.geometry = geometry;
  }
}

class Properties {
  private String detail;
  private float mag;
  
  public String getDetail() {
    return detail;
  }
  
  public float getMag() {
    return mag;
  }
  
  public void setDetail(final String detail) {
    this.detail = detail;
  }
  
  public void setMag(final float mag) {
    this.mag = mag;
  }
}

class Feature {
  private Properties properties;
  
  public Properties getProperties() {
    return properties;
  }
  
  public void setProperties(final Properties properties) {
    this.properties = properties;
  }
}