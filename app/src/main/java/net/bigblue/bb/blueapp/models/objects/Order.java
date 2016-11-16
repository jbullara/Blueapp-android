package net.bigblue.bb.blueapp.models.objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;


public class Order implements Parcelable{

    public String category;
    public String species;
    public String grade;
    public String size;
    public String quantity;
    public String price;
//https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView

    public Order(String category, String size , String grade, String species, String quantity, String price) {



        this.category = category;
        this.species = species;
        this.grade = grade;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }


    public Order(Parcel in){
        String[] data = new String[6];

        in.readStringArray(data);


        this.category = data[0];
        this.species = data[1];
        this.grade =data[2];
        this.size = data[3];
        this.quantity = data[4];
        this.price = data[5];
    }


    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {
                this.category,
        this.species,
        this.grade,
        this.size,
        this.quantity,
        this.price,

        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public JSONObject getJSONObject(){
        JSONObject obj = new JSONObject();
        try{
            obj.put("category",category);
            obj.put("species",species);
            obj.put("grade",grade);
            obj.put("size",size);
            obj.put("quantity",quantity);
            obj.put("price",price);

        }catch(JSONException e) {

        }
        return obj;
    }
}
