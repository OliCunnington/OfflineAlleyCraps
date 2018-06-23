package example.eniac.alleycraps.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "data.db";

    private static Context appContext;
    private static DBHelper dbInstance;

    public UserTable users;
    public TXTable transactions;

    public static DBHelper getInstance(Context context){
        if(DBHelper.dbInstance == null){
            DBHelper.appContext = context.getApplicationContext();
            DBHelper.dbInstance = new DBHelper();
        }

        return DBHelper.dbInstance;
    }

    private DBHelper(){
        super (DBHelper.appContext, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
        users = UserTable.getInstance(this);
        transactions = TXTable.getInstance(this);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        users.createTable();
        transactions.createTable();

        //TODO populate from file???
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO upgrade?
    }

    public void closeDB(){
        DBHelper.appContext = null;
        DBHelper.dbInstance.close();
        DBHelper.dbInstance = null;
        users = null;
        transactions = null;
    }
}
