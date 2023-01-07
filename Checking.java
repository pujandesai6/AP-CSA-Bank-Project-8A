
public class Checking extends ATM
{
    int amountChecking;
    double add10;
    double add20;
    double add50;
    double add100;
    double addCustom;
    double checkingBalance;

    // withdrawing
    // double take10;
    // double take20;
    // double take50;
    // double take100;
    // double takeCustom;

    private double my10;
    private double my20;
    private double my50;    
    private double my100;
    private double myCustom;
    private double myCheckingBalance;
    // withdrawing
    // private double takeMy10;
    // private double takeMy20;
    // private double takeMy50;
    // private double takeMy100;
    // private double takeMyCustom;

    
    public Checking()
    {
        this.add10 = 0;
        this.add20 = 0;
        this.add50 = 0;
        this.add100 = 0;
        this.addCustom = 0;
        this.checkingBalance = 0;
        //withdrawing
        // this.take10 = 0;
        // this.take20 = 0;
        // this.take50 = 0;
        // this.take100 = 0;
        // this.takeCustom = 0;
    }
    public Checking(double add10 ,double add20, double add50, double add100, double checkingBalance, double take10, double take20, double take50, double take100)
    {
        myCheckingBalance = checkingBalance;
        my10 = add10;
        my20 = add20;
        my50 = add50; 
        my100 = add100;
        myCustom = addCustom;

        // takeMy10 = take10;
        // takeMy20 = take20;
        // takeMy50 = take50;
        // takeMy100 = take100;
        // takeMyCustom = takeCustom;
        // add methods for withdrawing
    }
    //add10
    // public double gettake10()
    // {
    //     return takeMy10;
    // }
    // // public void double
        
    
    // public double gettake20()
    // {
    //     return takeMy20;
    // }
    // public double gettake50()
    // {
    //     return takeMy50;
    // }
    // public double gettake100()
    // {
    //     return takeMy100;
    // }
    // public double gettakeCustom()
    // {
    //     return takeMyCustom;
    // }

    public double getadd10()
    {
    return my10;
    }
    public void setadd10(double add20)
    {
        my20 = add20;
    }
    //add20
    public double getadd20()
    {
    return my20;
    }
    public void setadd20(double add20)
    {
        my20 = add20;
    }
    //add50
    public double getadd50()
    {
    return my50;
    }
    public void setadd50(double add50)
    {
        my50 = add50;
    }
    //add100
    public double getadd100()
    {
    return my100;
    }
    public void setadd100(double add100)
    {
        my100 = add100;
    }
    //checkingbalance
    public double getcheckingBalance()
    {
        return myCheckingBalance;
    }
    public void setcheckingBalance(double checkingBalance)
    {
        myCheckingBalance = checkingBalance;
    }
    public double getaddCustom()
    {
        return myCustom;
    }
    public void setaddCustom(double addCustom)
    {
        myCustom = addCustom;
    }
    
   //interesting methods
   public double balance10()
   {
    double newBalance = checkingBalance + 10;
    return newBalance;
   }
   public double balance20()
   {
    double newBalance = checkingBalance + 20;
    return newBalance;
   }
   public double balance50()
   {
    double newBalance = checkingBalance + 50;
    return newBalance;
   }
   public double balance100()
   {
    double newBalance = checkingBalance + 100;
    return newBalance;
   }
   public double balanceCustom()
   {
    double newBalance = checkingBalance + addCustom;
    return newBalance;
   }
  
}

