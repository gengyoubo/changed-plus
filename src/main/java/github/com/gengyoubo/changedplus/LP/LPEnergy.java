package github.com.gengyoubo.changedplus.LP;
/**
 * LP(Latex Power)
 */
public class LPEnergy {
    public static final int LP_PER_RF = 100;
    /**
     *RF(Redstone Flux)
     */
    public static int convertLPtoRF(int lp) {
        return lp * LP_PER_RF;
    }

    /**
     *LP(Latex Power)
     */
    public static int convertRFtoLP(int rf) {
        return rf / LP_PER_RF;
    }
}

interface ILPStorage {
    int receiveLP(int maxReceive, boolean simulate);
    int extractLP(int maxExtract, boolean simulate);
    int getLPStored();
    int getLPCapacity();
}
