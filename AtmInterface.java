import java.util.Scanner;

class Bank {
    String name;
    String username;
    String password;
    String accountNumber;
    int balance = 100000000;
    int transactions = 0;
    String transactionsHistory = "";

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Name : ");
        this.name = scanner.nextLine();
        System.out.print("Enter Your Username : ");
        this.username = scanner.nextLine();
        System.out.print("Enter Your Password : ");
        this.password = scanner.nextLine();
        System.out.print("Enter Your Account Number : ");
        this.accountNumber = scanner.nextLine();
        System.out.print("Account Registered Successfully! Please Login to continue \n");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner scanner = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("Enter Your Username : ");
            String username = scanner.nextLine();
            if (username.equals(this.username)) {
                while (!isLogin) {
                    System.out.print("Enter Your Password : ");
                    String password = scanner.nextLine();
                    if (password.equals(this.password)) {
                        System.out.print("Login Successful!\n");
                        isLogin = true;
                    } else {
                        System.out.print("Incorrect Password\n");
                    }
                }
            } else {
                System.out.print("Username not found");
            }
        }
        return true;
    }

    public void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw : ");
        int amount = scanner.nextInt();
        try {
            if (balance >= amount) {
                balance = balance - amount;
                System.out.println("Withdraw Successful!");
                String message = amount + " Rs Withdrawn Successfully \n";
                transactionsHistory = transactionsHistory.concat(message);
            } else {
                System.out.println("Insufficient Balance");
            }
        } catch (Exception e) {
            System.out.println("Error Occurred! try again");
        }
    }

    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit : ");
        int amount = scanner.nextInt();
        transactions++;
        balance = balance + amount;
        System.out.println(amount + " deposited successfully!");
        String message = amount + " Rs deposited \n";
        transactionsHistory = transactionsHistory.concat(message);
    }

    public void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name : ");
        String recipient = scanner.nextLine();
        System.out.print("\nEnter amount to transfer : ");
        int amount = scanner.nextInt();

        try {
            if (balance >= amount) {
                transactions++;
                balance = balance - amount;
                System.out.println("\nSuccessfully Transferred to " + recipient);
                String message = amount + " Rs transferred to " + recipient + " \n";
                transactionsHistory = transactionsHistory.concat(message);
            } else {
                System.out.println("Insufficient Balance");
            }
        } catch (Exception e) {
            System.out.println("Error! try again");
        }
    }

    public void checkBalance() {
        System.out.println("\nyour account balance is " + balance + " Rs\n");
    }

    //4
    public void transactionHistory() {
        if (transactions == 0) {
            System.out.println("No transactions yet\n");
        } else {
            System.out.println("\n" + transactionsHistory);
        }
    }
}

public class AtmInterface {
    public static int takeIntegerInput(int limit) {

        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                flag = true;

                if (input > limit || input < 1) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.print("\nEnter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n************ Welcome to ATM System ************");
        System.out.println("\n1.Register\n2.Exit");
        System.out.print("Enter your choice : ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            Bank bank = new Bank();
            bank.register();
            while (true) {
                System.out.println("\n1.Login\n2.Exit");
                System.out.print("Enter your choice : ");
                int option = takeIntegerInput(2);
                if (option == 1) {
                    if (bank.login()) {
                        System.out.println("************ WELCOME BACK " + bank.name + " ************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Withdraw\n2.Deposit\n3.Transfer\n4.Check Balance\n5.Transaction History\n6.Exit");
                            System.out.print("Enter your choice : ");
                            int temp = takeIntegerInput(6);
                            switch (temp) {
                                case 1 -> bank.withdraw();
                                case 2 -> bank.deposit();
                                case 3 -> bank.transfer();
                                case 4 -> bank.checkBalance();
                                case 5 -> bank.transactionHistory();
                                case 6 -> isFinished = true;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }

}