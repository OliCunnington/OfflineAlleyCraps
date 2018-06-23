package example.eniac.alleycraps.database;

public class TXTable {

    public static final String TABLE_NAME = "transaction_table";

    public static final String _ID = "_id";
    public static final String COLUMN1 = "from";
    public static final String COLUMN2 = "to";
    public static final String COLUMN3 = "value";
    public static final String COLUMN4 = "data";

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY, "+COLUMN1+", "+COLUMN2+", "+COLUMN3+", "+COLUMN4+");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TXTable.TABLE_NAME;

    private static DBHelper dbInstance = null;
    private static TXTable tblInstance = null;

    private TXTable(){}

    public static TXTable getInstance(DBHelper db){
        if(TXTable.dbInstance == null){
            TXTable.dbInstance = db;
            TXTable.tblInstance = new TXTable();
        }
        return TXTable.tblInstance;
    }

    public void createTable(){}
}
