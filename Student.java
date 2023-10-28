//Thread for Student
public class Student extends Thread
{
    //Variables related to Student
    private final BankAccount bankAccount;
    private final String name;
    private final int NumberOfTransactions=6;

    final int OneSecond=1000;

    //Constructor function for the Student
    //Initialises the variables declared above
    Student(ThreadGroup tg, String name, BankAccount bankAccount)
    {
        super(tg,"Student - " + name);
        System.out.println("Student Thread has been created!");
        this.name=name;
        this.bankAccount=bankAccount;
    }

    //Function that returns a withdrawal transaction
    public Transaction withdrawalTransaction()
    {
        return new Transaction(getName(),150);
    }

    //Function that returns a deposit transaction
    public Transaction depositTransaction()
    {
        return new Transaction(getName(),200);
    }

    //Run function
    //Runs 6 times while sleeping for a second in between
    //Alternates between a deposit and a withdrawal
    public void run()
    {
        for(int i=0;i<NumberOfTransactions;i++)
        {
            if(i%2==0)
            {
                bankAccount.deposit(depositTransaction());
            }
            else
            {
                bankAccount.withdrawal(withdrawalTransaction());
            }

            try
            {
                sleep(OneSecond);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Student Thread exiting!");
    }
}
