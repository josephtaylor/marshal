/**
  This reprsents the data for an Earthquake.
  Each earthquake has two pieces of info that we care about.
    - location - where it happened
    - magnitude - how big it was
*/
class Earthquake {
  private final PVector location;
  private final float magnitude;
  
  public Earthquake(final PVector location, final float magnitude) {
    this.location = location;
    this.magnitude = magnitude;
  }
  
  public PVector getLocation() {
    return location;
  }
  
  public float getMagnitude() {
    return magnitude;
  }
}