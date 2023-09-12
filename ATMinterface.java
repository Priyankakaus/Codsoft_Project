import java.util.Scanner;
import java.lang.Thread;

interface ATMmachine{
    void withdraw(int decrement);
    void deposit(int increment);
    void checkBalance();
}


class PriyankaUser{
    //ACCOUNT DETAILS OF USER**********
        int userid = 9890;
        int passcode = 1085; 
        int balance = 1050000;
    }

class ATM extends PriyankaUser implements ATMmachine{

    public void withdraw(int decrement) {
        System.out.println("Withdrawal processing..........");
      try {
        Thread.sleep(1500);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }  
        if(decrement>balance){
            System.out.println("sufficient balance for this withdrawal");
        }else{
            balance -= decrement;
            System.out.println("Amount succesfully withdrawl");
        }

    }

    public void deposit(int increment) {
        System.out.println("deposit processing..........");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            if(increment>0){
                balance += increment;
                System.out.println("Sucessfully desposit your amount");
            }else{
                System.out.println("Enter a valid number of amount");
            }
        
    }

    public void checkBalance() {
        System.out.println("checking Balance..........");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(balance);
    }

}

public class ATMinterface {
    public static void main(String[] args) {
        ATM  acc = new ATM();
        Scanner sc = new Scanner(System.in);
        System.out.println("[W][E][L][C][O][M][E] [T][O] [P][U][N][J][A][B] [N][A][T][I][O][N][A][L] [B][A][N][K]");
        System.out.println("===============================");
        System.out.println("What action do you wana perform !!");
        System.out.println("===============================");
        boolean b = false;
        while(!b){
            System.out.println("press 1 for Checkbalance");
            System.out.println("press 2 for withdraw");
            System.out.println("press 3 for deposit");
            System.out.println("press 0 for Exit from ATM");
            System.out.println("===============================");
            
            int val = sc.nextInt();
            switch (val) {
                case 1:
                    System.out.println("===============================");
                    acc.checkBalance();
                    System.out.println("===============================");
                    break;
                case 2:
                    System.out.println("===============================");
                    System.out.println("what amount you want to be withdraw");
                    int rupee = sc.nextInt();
                    acc.withdraw(rupee);
                    System.out.println("===============================");
                    break;
                case 3:
                    System.out.println("===============================");
                    System.out.println("what amount you want to be deposit");
                    int rupee1 = sc.nextInt();
                    acc.deposit(rupee1);
                    System.out.println("===============================");
                    break;
                case 0:
                    b =true;
                    break;
                
                default:
                    System.out.println("Your Enter wrong enquiry");
                    break;
            }

        }    
    System.out.println("==========================");    
    System.out.println("********************__THANK YOU FOR USING__********************");
    System.out.println("==========================");  
    }
}