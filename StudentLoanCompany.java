//Thread for Student Loan Company
public class StudentLoanCompany extends Thread
{
    //Variables related to Student Loan Company
    private final BankAccount bankAccount;
    private final String name;
    private final int NumberOfTransactions=3;

    final int ThreeSeconds=3000;

    //Constructor for the Student Loan Company
    //Initialises the variables declared above
    StudentLoanCompany(ThreadGroup tg, String name, BankAccount bankAccount)
    {
        super(tg, name);
        System.out.println("Student Loan Company has been created!");
        this.name=name;
        this.bankAccount=bankAccount;
    }

    //Run function
    //Runs three times while sleeping for 3 seconds in between
    //Performs three deposit transactions
    public void run()
    {
        for(int i=0;i<NumberOfTransactions;i++)
        {
            Transaction loanDeposit=new Transaction(getName(),1000);
            bankAccount.deposit(loanDeposit);

            try
            {
                sleep(ThreeSeconds);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Student Loan Company Thread exiting!");
    }
}
