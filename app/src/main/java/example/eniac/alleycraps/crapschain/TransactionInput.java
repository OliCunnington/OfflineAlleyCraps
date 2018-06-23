package example.eniac.alleycraps.crapschain;

public class TransactionInput {

    private static final String TAG = "TransactionInput";

    public String transactionOutputID;
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputID){
        this.transactionOutputID = transactionOutputID;
    }
}

