package dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Agnieszka on 2015-11-08.
 */
public class Event {

    public String id;
    String id_user;
    public String city;
    String id_field;
    public String field;
    public int free_slots;
    int slots;
    public String date;
    String login;
    String name;
    int reputation;
    String telephone;
    String avatar;
    public int price;
    public String hour;

    public Event(String id, String id_user, String city, String id_field, String field, int free_slots,
                 int slots, String date, String login, String name, int reputation, String telephone, String avatar, int price, String hour){
        this.id = id;
        this.id_user = id_user;
        this.city = city;
        this.id_field = id_field;
        this.field = field;
        this.free_slots = free_slots;
        this.slots = slots;
        this.date = date;
        this.login = login;
        this.name = name;
        this.reputation = reputation;
        this.telephone = telephone;
        this.avatar = avatar;
        this.price = price;
        this.hour = hour;

    }

    public Event(JSONObject jsonObject1){

        try {
            JSONObject jsonField = jsonObject1.getJSONObject("field");
            this.city = jsonField.getString("city");
            //this.city = jsonArrayField.getJSONObject(0).toString();
            this.field = jsonField.getString("field");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
        //    JSONArray jsonArrayEvent = jsonObject1.getJSONArray("id");
            this.id = jsonObject1.getString("id");
        //    this.id_user = jsonObject1.getString("id_user");

        //    this.id_field = jsonObject1.getString("id_field");
        //    this.field = jsonObject1.getString("field");
        //    this.free_slots = 5;
            this.free_slots = Integer.parseInt(jsonObject1.getString("freeSlots"));
        //    this.slots = Integer.parseInt(jsonObject1.getString("slots"));
            this.date = jsonObject1.getString("date").substring(0,10);
        //    this.login = jsonObject1.getString("login");
        //    this.name = jsonObject1.getString("name");
        //    this.reputation = Integer.parseInt(jsonObject1.getString("reputation"));
        //    this.telephone = jsonObject1.getString("telephone");
        //    this.avatar = jsonObject1.getString("avatar");
            this.price = Integer.parseInt(jsonObject1.getString("price"));
            this.hour = jsonObject1.getString("date").substring(11, 16);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
