//Thread for Grandmother
public class Grandmother extends Thread
{
    //Variables related to Grandmother
    private final BankAccount bankAccount;
    private final String name;
    private final int NumberOfTransactions=2;

    final int FiveSeconds=3000;

    //Constructor Function for the Grandmother
    //Initialises the variables declared above
    Grandmother(ThreadGroup tg, String name, BankAccount bankAccount)
    {
        super(tg, "Grandmother - " + name);
        System.out.println("Grandmother Thread has been created!");
        this.name=name;
        this.bankAccount=bankAccount;
    }

    //Run function
    //Runs two times while sleeping for 5 seconds in between
    //Performs two deposit transactions
    public void run()
    {
        for(int i=0;i<NumberOfTransactions;i++)
        {
            Transaction topUpTransaction=new Transaction(getName(),300);
            if(i%2==0)
            {
                System.out.println("Wish you a Happy Birthday! - From Gran");
            }
            else
            {
                System.out.println("Wish you a Merry Christmas! - From Gran");
            }
            bankAccount.deposit(topUpTransaction);

            try
            {
                sleep(FiveSeconds);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Grandmother Thread exiting!");
    }
}
