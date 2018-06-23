package example.eniac.alleycraps.database;

public class UserTable {

    public static final String TABLE_NAME = "transaction_table";

    public static final String _ID = "_id";
    public static final String COLUMN1 = "user";
    public static final String COLUMN2 = "public";
    public static final String COLUMN3 = "private";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+COLUMN1+", "+COLUMN2+", "+COLUMN3+");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+UserTable.TABLE_NAME;

    private static DBHelper dbInstance = null;
    private static UserTable tblInstance = null;

    private UserTable(){}

    public static UserTable getInstance(DBHelper db){
        if(UserTable.dbInstance == null){
            UserTable.dbInstance = db;
            UserTable.tblInstance = new UserTable();
        }
        return UserTable.tblInstance;
    }

    public void createTable(){}
}
