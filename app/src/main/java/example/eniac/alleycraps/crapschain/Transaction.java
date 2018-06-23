package example.eniac.alleycraps.crapschain;

import android.util.Log;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {

    private static final String TAG = "Transaction";

    public String transactionID;
    public PublicKey sender, recipient;
    public int value;
    public byte[] signature;
    public int minimumTransaction = 1;
    public ArrayList<TransactionInput> inputs;
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, int value, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }


    private String calculateHash(){
        ++sequence;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                        StringUtil.getStringFromKey(recipient) +
                        Float.toString(value) + sequence
        );
    }

    public void generateSignature(PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Integer.toString(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature(){
        String data = StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(recipient)+Integer.toString(value);
        return StringUtil.verifyECDSASig(sender,data,signature);
    }

    public boolean processTransaction(){
        if(verifySignature() == false){
            Log.d(TAG, "Transaction sig failed to verify");
            return false;
        }

        for (TransactionInput i: inputs){
            i.UTXO = ChainActivity.UTXOs.get(i.transactionOutputID);
        }

        if(getInputsValue() < minimumTransaction){
            Log.d(TAG,"Transaction inputs to small"+ getInputsValue());
            return false;
        }

        int leftOver = getInputsValue() - value;
        transactionID = calculateHash();
        outputs.add(new TransactionOutput(this.recipient,value,transactionID));
        outputs.add(new TransactionOutput(this.sender,leftOver,transactionID));

        for(TransactionOutput o: outputs){
            ChainActivity.UTXOs.put(o.id,o);
        }

        for(TransactionInput i: inputs){
            if(i.UTXO == null)continue;
            ChainActivity.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

    public int getInputsValue(){
        int total = 0;
        for (TransactionInput i: inputs){
            if(i.UTXO==null)continue;
            total += i.UTXO.value;
        }
        return total;
    }

    public int getOutputsValue(){
        int total = 0;
        for(TransactionOutput o: outputs){
            total += o.value;
        }
        return total;
    }
}
