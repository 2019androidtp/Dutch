package com.example.tmapapi;

public class ListViewItem {

  private String POIStr ;
  private String AddressStr ;

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
}
