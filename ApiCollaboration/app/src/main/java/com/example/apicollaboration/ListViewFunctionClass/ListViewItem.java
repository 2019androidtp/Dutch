package com.example.apicollaboration.ListViewFunctionClass;

public class ListViewItem {

  private String POIStr ;
  private String AddressStr ;
  private double POIlat;
  private double POIlon;
  private int icon;
  private String name;

  public void setPOIStr(String title) {
    POIStr = title ;
  }
  public void setAddressStr(String desc) {
    AddressStr = desc ;
  }
  public String getPOIStr() {
    return this.POIStr ;
  }
  public String getAddressStr() {
    return this.AddressStr ;
  }
  public  void setLat(double lat){
    POIlat = lat;
  }
  public  void setLon(double lon){
    POIlon = lon;
  }
  public double getLat(){
    return this.POIlat;
  }
  public double getLon(){
    return this.POIlon;
  }
  public int getIcon(){return icon;}
  public String getName(){return name;}

  public ListViewItem(){
    this.icon=icon;
    this.name=name;
  }

}
