
public class Checking extends ATM {
    int amountChecking;

    double addCustom;
    double checkingBalance;

    // withdrawing
    double takeMyCustom;
    double takeCustom;

    // withdrawing

    public Checking() {

        this.addCustom = 0;
        this.checkingBalance = 0;
        // withdrawing

        this.takeCustom = 0;
    }

    public Checking(double addCustom) {
        addCustom = addCustom;

        takeMyCustom = takeCustom;
        // add methods for withdrawing
    }

    // checkingbalance

    public double getaddCustom() {
        checkingBalance += addCustom;
        return checkingBalance;
    }

    public void setaddCustom(double addCustom) {
        addCustom = checkingBalance;
    }

}
