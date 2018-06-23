package example.eniac.alleycraps.elements;

import android.util.Log;

import com.google.gson.Gson;

import example.eniac.alleycraps.crapschain.CrapsWallet;

public class User {

    private static final String TAG = "User";

    public CrapsWallet userWallet;
    private String username;

    public User(String user){
        username = user;
        userWallet = new CrapsWallet();
    }

    public String getName(){
        return username;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String userJSON = gson.toJson(this);
        Log.d(TAG,userJSON);
        return userJSON;
    }

}
