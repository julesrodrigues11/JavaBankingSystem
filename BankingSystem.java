import java.util.concurrent.TimeUnit;

public class BankingSystem
{
    public static void main(String[] args)
    {
        //String variable for student name
	    String name = "Carl";

        //ThreadGroup variables for main, individual and company thread groups
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup individuals = new ThreadGroup(main, "Individuals");
        ThreadGroup companies = new ThreadGroup(main, "Companies");

        //Creating BankAccount object under student's name
        BankAccount account = new CurrentAccount(name);

        //Creating Student Thread
        Student student = new Student(individuals, name, account);

        //Creating Grandmother Thread
        Grandmother grandmother = new Grandmother(individuals,"Gran", account);

        //Creating StudentLoanCompany Thread
        StudentLoanCompany SLC = new StudentLoanCompany(companies,"Student Loan Company", account);

        //Creating University Thread
        University uni = new University(companies,"Uni of Westminster", account);
        System.out.println();

        //Starting up all the Threads
        student.start();
        grandmother.start();
        SLC.start();
        uni.start();

        //Try statement that waits a certain time until printing out the final statement for the bank account
        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("All threads have finished execution. Now printing final bank statement");
        account.printStatement();
        System.out.println("Your bank balance is currently £"+account.getBalance());
    }
}
