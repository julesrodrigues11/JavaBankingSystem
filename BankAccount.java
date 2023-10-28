public interface BankAccount
{
    int getBalance(); //Return the current balance
    int getAccountNumber(); //Return the account number
    String getAccountHolder(); //Return the account holder

    void deposit(Transaction t); //Perform a deposit transaction
    void withdrawal(Transaction t); //Perform a withdrawal transaction

    boolean isOverdrawn(Transaction t); //Returns true if overdrawn; false otherwise
    void printStatement(); //Prints out the transactions performed so far
}
