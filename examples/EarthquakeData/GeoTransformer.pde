class GeoTransformer {
  float mapGeoLeft   = -180.00;          // Longitude 125 degrees west
  float mapGeoRight  =  180.00;          // Longitude 153 degrees east
  float mapGeoTop    =   80.00;          // Latitude 72 degrees north.
  float mapGeoBottom =  -80.00;          // Latitude 56 degrees south.
  
  public PVector geoToPixel(PVector geo) {
    return new PVector(width * (geo.x - mapGeoLeft) /(mapGeoRight - mapGeoLeft),
                       height - height * (geo.y - mapGeoBottom) / (mapGeoTop - mapGeoBottom));
  }
  
}