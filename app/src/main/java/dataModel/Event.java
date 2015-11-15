package dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Agnieszka on 2015-11-08.
 */
public class Event {

    public String id;
    public String id_user;
    public String user_name;
    public String city;
    String id_field;
    public String field;
    public String free_slots;
    String slots;
    public String date;
    public String price;
    public String hour;
    public String fieldAddress;
    public String userStatus;

    public Event(){

    }

    public Event(String id, String id_user, String city, String id_field, String field, String free_slots,
                 String slots, String date, String user_name, String price, String hour, String lat, String lng, String userStatus){
        this.id = id;
        this.id_user = id_user;
        this.city = city;
        this.id_field = id_field;
        this.field = field;
        this.free_slots = free_slots;
        this.slots = slots;
        this.date = date;
        this.price = price;
        this.hour = hour;
        this.user_name = user_name;
        this.userStatus = userStatus;

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
            this.free_slots = jsonObject1.getString("freeSlots");
        //    this.slots = Integer.parseInt(jsonObject1.getString("slots"));
            this.date = jsonObject1.getString("date").substring(0,10);
        //    this.login = jsonObject1.getString("login");
        //    this.name = jsonObject1.getString("name");
        //    this.reputation = Integer.parseInt(jsonObject1.getString("reputation"));
        //    this.telephone = jsonObject1.getString("telephone");
        //    this.avatar = jsonObject1.getString("avatar");
            this.price = jsonObject1.getString("price");
            this.hour = jsonObject1.getString("date").substring(11, 16);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Event createDetailsAboutEvent(JSONObject jsonObject){
        String id, city, id_field, field, free_slots, date, hour, price, author_id, author_name, slots;
        String lat, lng;
        String userStatus;
        //Event event = new Event();

        try {
            JSONObject jSONField = jsonObject.getJSONObject("field");
            id_field = jSONField.getString("id");
            city = jSONField.getString("city");
            field = jSONField.getString("field");
            //field = jsonObject.toString();
            lat = jSONField.getString("lat");
            lng = jSONField.getString("lng");

            id = jsonObject.getString("id");
            free_slots = jsonObject.getString("freeSlots");
            date = jsonObject.getString("date").substring(0,10);
            hour = jsonObject.getString("date").substring(11,16);
            price = jsonObject.getString("price");
            slots = jsonObject.getString("slots");
            userStatus = jsonObject.getString("userStatus");

            JSONObject jSONUser = jsonObject.getJSONObject("user");
            author_id = jSONUser.getString("id");
            author_name = jSONUser.getString("login");

            /*event.id = id;
            event.id_field = id_field;
            event.city = city;
            event.field = field;
            event.free_slots = free_slots;
            event.date = date;
            event.hour = hour;
            event.price = price;*/

            return new Event(id, author_id, city, id_field, field, free_slots,
                    slots, date, author_name, price, hour, lat, lng, userStatus);

        } catch (JSONException e) {
            e.printStackTrace();
            //return event;
            return null;
        }

    }
}
