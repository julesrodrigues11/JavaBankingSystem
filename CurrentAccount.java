import java.util.Random;

//Monitor for the Concurrent System, implements the BankAccount interface
public class CurrentAccount implements BankAccount
{
    //Variables related to bank account
    private int balance;
    private int accountNumber;
    private String accountHolder;
    private Statement statement;

    //Constructor for CurrentAccount
    //Initialises the variables declared above
    CurrentAccount(String accountHolder)
    {
        System.out.println("Bank Account has been created under "+accountHolder+"'s name!");
        Random random = new Random();

        balance=0;
        this.accountHolder=accountHolder;
        this.accountNumber=random.nextInt(10000);

        statement=new Statement(accountHolder, accountNumber);
    }

    //Getter functions
    public int getBalance()
    {
        return balance;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountHolder()
    {
        return accountHolder;
    }

    //Synchronised function for depositing into bank account
    //Deposits the money into bank account
    //Notifies all other threads that are waiting after transaction has been completed
    public synchronized void deposit(Transaction t)
    {
        System.out.println(t.getCID() + " depositing £" + t.getAmount() + " into bank account");
        balance+=t.getAmount();
        statement.addTransaction(t.getCID(), t.getAmount(), getBalance());

        System.out.println(t.toString());
        System.out.println("Deposit complete");
        System.out.println("Bank balance is now £"+getBalance());
        System.out.println();
        notifyAll();
    }

    //Synchronised function for withdrawing from bank account
    //Checks if the bank account is overdrawn and waits if true
    //When false, withdraws the money from the bank account
    //Notifies all other threads that are waiting after transaction has been completed
    public synchronized void withdrawal(Transaction t)
    {
        while(!isOverdrawn(t)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(t.getCID() + " withdrawing £" + t.getAmount() + " from bank account");
        balance-=t.getAmount();
        statement.addTransaction(t.getCID(), -t.getAmount(), getBalance());

        System.out.println(t.toString());
        System.out.println("Withdrawal complete");
        System.out.println("Bank balance is now £"+getBalance());
        System.out.println();
        notifyAll();
    }

    //Function to check if account is overdrawn
    //Returns a boolean accordingly
    public boolean isOverdrawn(Transaction t)
    {
        if(t.getAmount()<getBalance())
        {
            return true;
        }
        return false;
    }

    //Function to print out the statement for the current account
    public void printStatement()
    {
        statement.print();
    }
}
