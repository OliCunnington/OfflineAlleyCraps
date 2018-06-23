package example.eniac.alleycraps.crapschain;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class Block {

    private static final String TAG = "Block";

    public String hash;
    public String previousHash;
    private String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    private long timeStamp;
    private int nonce;

    public Block(String previousHash){
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedHash = StringUtil.applySha256(
                    previousHash+
                            Long.toString(timeStamp)+
                            Integer.toString(nonce)+
                            merkleRoot);
        return calculatedHash;
    }

    public void mineBlock(int difficulty){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDifficultyString(difficulty);
        while(!hash.substring(0,difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        Log.d("Block Mined!", Integer.toString(nonce)+"::"+hash);
    }

    public boolean addTransaction(Transaction transaction){
        if(transaction == null) return false;
        if(previousHash!="0"){
            if(transaction.processTransaction() != true){
                Log.d("Transaction Failed","failed to process transaction");
                return false;
            }
        }

        transactions.add(transaction);
        Log.d("Transaction Successfull","Successfully added Transaction to Block");
        return true;
    }

}
