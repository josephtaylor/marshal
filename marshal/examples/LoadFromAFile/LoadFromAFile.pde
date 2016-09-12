/*
  In this example, we load a list of points from a JSON file.
  The list of points is represented by the PointList object.
  
  Right clicking on the canvas will add new points to the list.
  
  Pressing spacebar will save the list to "points.json" in the data folder.
  
  Pressing 'c' will clear the list.
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
  size(500, 500);
  background(255);
  
  //creating our marshal object.
  marshal = new Marshal(this);
  
  //loading our PointList from the json file
  pointList = marshal.load("points.json", PointList.class, DataFormat.JSON);
}

// here we are just drawing ellipses at each point in the list.
void draw() {
  background(255);
  for (PVector p : pointList.getPoints()) {
    ellipse(p.x, p.y, 10, 10);
  }
}

// When the mouse is pressed we add a point
// to the list at the mouse location.
void mousePressed() {
  pointList.getPoints().add(new PVector(mouseX, mouseY));
}

// when the spacebar is pressed we save the current state of the list
// to the file points.json in the data folder.
//
// pressing 'c' will clear the list.
//
void keyPressed(KeyEvent event) {
  if (event.getKey() == ' ') {
    marshal.save(pointList, "points.json", DataFormat.JSON);  
  } else if (event.getKey() == 'c') {
    pointList.getPoints().clear();
  }
}