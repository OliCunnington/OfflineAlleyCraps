package example.eniac.alleycraps.crapschain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class ChainActivity extends AppCompatActivity {

    private static final String TAG = "ChainActivity";

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static HashMap<String,TransactionOutput> UTXOs = new HashMap<>();

    public static int difficulty = 3;
    public static int minimumTransaction = 1;
    public static CrapsWallet walletA;
    public static CrapsWallet walletB;
    public static Transaction genesisTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_chain);
        /*------------------------
        Walletless Blockchain Test
        ------------------------*/
        /*
        blockchain.add(new Block("Genesis Block","0"));
        Log.d("Mining gen block", blockchain.get(0).hash);
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block",blockchain.get(blockchain.size()-1).hash));
        Log.d("Mining 2nd block", blockchain.get(1).hash);
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block",blockchain.get(blockchain.size()-1).hash));
        Log.d("Mining 3rd block", blockchain.get(2).hash);
        blockchain.get(2).mineBlock(difficulty);

        Log.d("Chain valid: ", ""+isChainValid());


        String blockchainJSON = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        Log.d("Blockcahin: ",blockchainJSON);
        */

        /*--------------
        Test With Wallet
        --------------*/


        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);


        walletA = new CrapsWallet();
        walletB = new CrapsWallet();
        CrapsWallet coinBase = new CrapsWallet();

        //prints private and public keys for all wallets
        Log.d("Wallet A private:",StringUtil.getStringFromKey(walletA.privateKey));
        Log.d("Wallet A public:",StringUtil.getStringFromKey(walletA.publicKey));
        Log.d("Wallet B private:",StringUtil.getStringFromKey(walletB.privateKey));
        Log.d("Wallet B public:",StringUtil.getStringFromKey(walletB.publicKey));
        Log.d("Wallet CoinBase pri:",StringUtil.getStringFromKey(coinBase.privateKey));
        Log.d("Wallet CoinBase pub:",StringUtil.getStringFromKey(coinBase.publicKey));

        //create Genesis Transaction
        genesisTransaction = new Transaction(coinBase.publicKey, walletA.publicKey,100,null);
        genesisTransaction.generateSignature(coinBase.privateKey);
        genesisTransaction.transactionID = "0";
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.recipient,genesisTransaction.value,genesisTransaction.transactionID));
        UTXOs.put(genesisTransaction.outputs.get(0).id,genesisTransaction.outputs.get(0));

        Log.d("Creating & Mining gen", "");

        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        /*-----------------
            TESTING
        -----------------*/
        Block block1 = new Block(genesis.hash);
        Log.d(TAG, "wallet a ballance: "+walletA.getBalance());
        Log.d(TAG, "Wallet A attempting to transfer 40 to wallet B");
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40));
        addBlock(block1);
        Log.d(TAG,"wallet a Ballance is: "+walletA.getBalance());
        Log.d(TAG,"wallet b Ballance is: "+walletB.getBalance());

        Block block2 = new Block(block1.hash);
        Log.d("Wallet A", "attempting to transfer 1000 to wallet B (more than avail.)");
        block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000));
        addBlock(block2);
        Log.d(TAG,"Wallet A Ballance is: "+walletA.getBalance());
        Log.d(TAG,"Wallet B Ballance is: "+walletB.getBalance());

        Block block3 = new Block(block1.hash);
        Log.d(TAG, "Wallet B attempting to transfer 20 to wallet A");
        block2.addTransaction(walletB.sendFunds(walletA.publicKey, 20));
        //addBlock(block3);
        Log.d(TAG,"Wallet A Ballance is: "+walletA.getBalance());
        Log.d(TAG,"Wallet B Ballance is: "+walletB.getBalance());

        Log.d(TAG,"isChainValid():"+isChainValid());

        String blockchainJSON = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        Log.d(TAG,"Blockchain: "+blockchainJSON);
    }

    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');
        HashMap<String,TransactionOutput> tempUTXOs = new HashMap<>();
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        for(int i=1;i<blockchain.size();++i){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                Log.d(TAG, "Cur. Hashes not equal : "+currentBlock.toString());
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                Log.d(TAG, "Prev. Hashes not equal : "+currentBlock.toString());
                return false;
            }

            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                Log.d(TAG,"Block has not been mined");
                return false;
            }

            TransactionOutput tempOutput;
            for(int t=0;t<currentBlock.transactions.size();t++){
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if(!currentTransaction.verifySignature()){
                    Log.d(TAG, t+" transaction id");
                    return false;
                }

                if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()){
                    Log.d(TAG, t+" transaction id");
                    return false;
                }

                for (TransactionInput input: currentTransaction.inputs){
                    tempOutput = tempUTXOs.get(input.transactionOutputID);

                    if(tempOutput == null){
                        Log.d(TAG, t+" transaction id, referenced input missing");
                        return false;
                    }

                    if(input.UTXO.value != tempOutput.value){
                        Log.d(TAG, t+" transaction id, referenced input transaction is invalid");
                        return false;
                    }

                    tempUTXOs.remove(input.transactionOutputID);
                }

                for(TransactionOutput output: currentTransaction.outputs){
                    tempUTXOs.put(output.id, output);
                }

                if(currentTransaction.outputs.get(0).reciepient != currentTransaction.recipient){
                    Log.d(TAG, t+" transaction id, recipients don't match");
                    return false;
                }

                if(currentTransaction.outputs.get(1).reciepient != currentTransaction.sender){
                    Log.d(TAG, t+" transaction id, reciepient of change is not sender");
                    return false;
                }
            }
        }
        return true;
    }

    public static void addBlock(Block newBlock){
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }
}
