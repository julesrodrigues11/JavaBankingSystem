//Thread for University
public class University extends Thread
{
    //Variables related to University
    private final BankAccount bankAccount;
    private final String name;
    private final int NumberOfTransactions=3;

    final int ThreeSeconds=3000;

    //Constructor for University
    //Initialises the variables declared above
    University(ThreadGroup tg, String name, BankAccount bankAccount)
    {
        super(tg, name);
        System.out.println("University Thread has been created!");
        this.name=name;
        this.bankAccount=bankAccount;
    }

    //Run function
    //Runs three times while sleeping for 3 seconds in between
    //Performs three withdrawal transactions
    public void run()
    {
        for(int i=0;i<NumberOfTransactions;i++)
        {
            Transaction feeWithdrawal=new Transaction((getName()),1000);
            bankAccount.withdrawal(feeWithdrawal);

            try
            {
                sleep(ThreeSeconds);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("University thread exiting!");
    }
}
