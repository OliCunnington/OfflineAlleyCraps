package example.eniac.alleycraps.gui;

import example.eniac.alleycraps.crapschain.CrapsWallet;

public class GameWallet {

    CrapsWallet wallet;

    public GameWallet() {
        this.wallet = new CrapsWallet();
    }

    //TODO
    /* this will match any bet made by a player and handle the results of any roll.

    Will need to check if it is 2 or 3 dice mode

    (2)
    check if it is first roll > set point or reset game (win/loss) [7 or 11 is win, 2 3 or 12 is loss - anything else becomes point]
    if not first round [7 is loss, point is win]

     */
}
