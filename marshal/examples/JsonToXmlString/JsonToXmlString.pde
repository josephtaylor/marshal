/*
  In this example, we load a list of points from a JSON file.
  The list is then transformed to XML and printed to the console.
*/

// Note: this is the ONLY import required for using marshal.
// Processing will also import the underlying dependencies but this all you need.
import io.github.josephtaylor.marshal.*;


import java.util.List;

/*
  This is a tiny class to hold a list of PVectors.
  This is needed so that marshal knows what kind of objects are in the list.
  It's essentially just a wrapper.
*/
class PointList {
  private List<PVector> points = new ArrayList<PVector>();
  
  public List<PVector> getPoints() {
    return points;
  }
  
  public void setPoints(List<PVector> points) {
    this.points = points;
  }
}

Marshal marshal;
PointList pointList;

void setup() {
  //creating our marshal object.
  marshal = new Marshal(this);
  
  //loading our PointList from the json file and converting it to XML
  String xml = marshal.transformFile("points.json")
                        .ofType(PointList.class)
                        .from(DataFormat.JSON)
                        .to(DataFormat.XML)
                        .getString();
  
  //printing out the XML to the console.
  println(xml);
  
  noLoop();
}

void draw() {
}