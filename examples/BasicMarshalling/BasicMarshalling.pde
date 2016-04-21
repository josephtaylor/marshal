/**
  This is a basic example of how to use the Marshal
  library to marshal objects to a String.
  
  In this sketch we have a list of PVectors that you can
  add to by left-clicking on the sketch window.
  
  Pressing "j", "x", or "y" will dump the marshalled String
  version of the list to the console in either JSON, XML, or Yaml
  based on which key is pressed.
**/

// Note: this is the ONLY import required for using marshal.
// Processing will also import the underlying dependencies but this all you need.
import io.github.josephtaylor.marshal.*;

import java.util.List;

List<PVector> points;

Marshal marshal;

public void setup() {
  size(500, 500);
  background(255);
  
  points = new ArrayList<PVector>();
  
  //pass this PApplet to marshal's constructor.
  marshal = new Marshal(this);
}

/**
  Our draw method rendors our list of
  points to the screen.
**/
public void draw() {
  background(255);
  for (PVector p : points ) {
    ellipse(p.x, p.y, 15, 15);
  };
}

/**
  In our key press handling method
  we use marshal's marshal() method to
  convert the points list into a String.
  
  We pass in one of the DataFormat enums to
  specify which format to marshal the list into.
  
  j -> json
  x -> xml
  y -> yaml
**/
public void keyPressed(KeyEvent event) {
  switch(event.getKey()) {
    case 'j':
      println("\nPOINTS IN JSON: " + marshal.marshal(points, DataFormat.JSON));
      break;
    case 'x':
      println("\nPOINTS IN XML: " + marshal.marshal(points, DataFormat.XML));
      break;
    case 'y':
      println("\nPOINTS IN YAML: " + marshal.marshal(points, DataFormat.YAML));
      break;
    default:
  }
}

/**
  Our mouse press handler adds a new PVector to the list
  at the mouse location if the left mouse button is pressed.
  
  A right mouse button press will clear the list.
**/
public void mousePressed(MouseEvent event) {
  if (event.getButton() == LEFT) {
      points.add(new PVector(mouseX, mouseY));
  }
  if (event.getButton() == RIGHT) {
      points.clear();
  }
}