package dataModel;

import org.json.JSONException;
import org.json.JSONObject;

import storageData.Query;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public class Field {

    private long id;
    private String name;
    private City city;
    private String type;
    private Boolean lighting;
    private String description;
    private double lat;
    private double lng;

    public Field(long id, String name, City city, String type, Boolean lighting, String description,
                 double lat, double lng){
        this.id = id;
        this.name = name;
        this.city = city;
        this.type = type;
        this.lighting = lighting;
        this.description = description;
        this.lat = lat;
        this.lng = lng;

    }

    public Field(long id, String name, City city){
        this.id = id;
        this.city = city;
        this.name = name;
    }

    public Field (JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getLong("id");
        this.name = jsonObject.getString("name");

        String cityName = jsonObject.getString("city");
        this.city = new City(Query.getCityId(cityName), jsonObject.getString("city")); //nie mam id miasta!

        this.type = jsonObject.getString("type");
        this.lighting = jsonObject.getInt("lighting")==1? true : false;
        this.description = jsonObject.getString("desciption");
        this.lat = jsonObject.getDouble("lat");
        this.lng = jsonObject.getDouble("lng");
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public City getCity(){
        return city;
    }
    public String getType(){
        return type;
    }
    public boolean getLighting(){
        return lighting;
    }
    public String getDescription(){
        return description;
    }
    public double getLat(){
        return lat;
    }
    public double getLng(){
        return lng;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCity(City city){
        this.city = city;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setLighting(boolean lighting){
        this.lighting = lighting;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setLat(double lat){
        this.lat = lat;
    }
    public void setLng(double lng){
        this.lng = lng;
    }




}
