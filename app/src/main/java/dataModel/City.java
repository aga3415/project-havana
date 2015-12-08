package dataModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

/**
 * Created by Agnieszka on 2015-11-28.
 */
public class City {
    private long id;
    private String name;

    public City(long id, String name){
        this.id = id;
        this.name = name;
    }

    public City(JSONObject jsonObject) throws JSONException{
        this.id = jsonObject.getLong("id");
        this.name = jsonObject.getString("name");
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }


}
