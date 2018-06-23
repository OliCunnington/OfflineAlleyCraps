package example.eniac.alleycraps.crapschain;

import java.security.PublicKey;

public class TransactionOutput {

    private static final String TAG = "TransactionOutput";

    public String id;
    public PublicKey reciepient;
    public int value;
    public String parentTransactionId;


    public TransactionOutput(PublicKey reciepient, int value, String parentTransactionId){
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey){
        return (publicKey == reciepient);
    }
}
