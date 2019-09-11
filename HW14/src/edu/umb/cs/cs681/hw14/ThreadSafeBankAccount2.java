package edu.umb.cs.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.ArrayList;

public class ThreadSafeBankAccount2 {
    private double balance = 0;
    private ReentrantLock lock;
    private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
    private ThreadSafeBankAccount2 account;
    private boolean done = true;

    public ThreadSafeBankAccount2(){
        lock = new ReentrantLock();
        sufficientFundsCondition = lock.newCondition();
        belowUpperLimitFundsCondition = lock.newCondition();
        account = this;
    }

    public void deposit(double amount){
        lock.lock();
        System.out.println("Lock obtained");
        System.out.println("User " + Thread.currentThread().getId() + " Deposit..sending Request... " + amount);
        while(balance >= 300){
            System.out.println(Thread.currentThread().getId() +
                    " (d): await(): Balance exceeds the upper limit.");
            try {
                belowUpperLimitFundsCondition.await();
            } catch (InterruptedException e) {
                done=false;
                System.out.println(Thread.currentThread().getId() +
                        " (d): Terminated ");
                System.out.println("Balance : " + balance);
                break;
            }
        }
        if (done) {
            System.out.print("Current balance (d): " + balance);
            balance += amount;
            System.out.println(Thread.currentThread().getId() +
                    " (d): New balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        else if (!done) {
            System.out.println("Cancelled.");
        }
        lock.unlock();
        System.out.println("Unlocked");
    }

    public void withdraw(double amount){
        lock.lock();
        System.out.println("Locked");
        System.out.println("User " + Thread.currentThread().getId() + " Withdrawing .. Sending Request..." + amount);
        while(balance <= 0){
            System.out.println(Thread.currentThread().getId() +
                    " (w): await(): Insufficient funds");
            try {
                sufficientFundsCondition.await();
            } catch (InterruptedException e) {
                done=false;
                System.out.println(Thread.currentThread().getId() +
                        " (w): Terminated ");
                System.out.println("Balance : " + balance);
                break;
            }
        }
        if (done) {
            System.out.print("Current balance (w): " + balance);
            balance -= amount;
            System.out.println(Thread.currentThread().getId() +
                    " (w): New balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        else if (!done) {
            System.out.println("Transaction ...Cancelled...");
        }
        lock.unlock();
        System.out.println("Unlocked");
    }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        ArrayList<Thread> Deposits = new ArrayList<Thread>();
        Thread Withdraw = new Thread( bankAccount.new WithdrawRunnable());

        for(int i = 0; i < 3; i++){
            Deposits.add(new Thread( bankAccount.new DepositRunnable()));
        }

        for(int j = 0; j <= Deposits.size() - 1; j++) {
            Deposits.get(j).start();
        }

        Withdraw.start();
        for(int k = 0; k <= Deposits.size() - 1; k++) {
            Deposits.get(k).interrupt();
        }
        Withdraw.interrupt();
    }

    private class DepositRunnable implements Runnable{
        public void run(){
            while(done) {
                account.deposit(100);
            }
        }
    }

    private class WithdrawRunnable implements Runnable{
        public void run(){
            while(done) {
                account.withdraw(100);
            }
        }
    }
}
