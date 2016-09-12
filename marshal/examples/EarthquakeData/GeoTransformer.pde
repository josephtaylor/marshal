/**
  This tiny class has one method that handles converting
  Longitude/Latitude locations to points on the canvas.
*/
class GeoTransformer {
  float mapGeoLeft   = -180.00;
  float mapGeoRight  =  180.00;
  float mapGeoTop    =   80.00;
  float mapGeoBottom =  -80.00;
  
  public PVector geoToPixel(PVector geo) {
    return new PVector(width * (geo.x - mapGeoLeft) /(mapGeoRight - mapGeoLeft),
                       height - height * (geo.y - mapGeoBottom) / (mapGeoTop - mapGeoBottom));
  }
  
}