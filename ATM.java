import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposited: ₹" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawn: ₹" + amount);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance or invalid withdrawal amount.");
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }
}

public class ATM extends JFrame implements ActionListener {
    private BankAccount account;
    private JLabel balanceLabel;
    private JButton checkBalanceButton, depositButton, withdrawButton, exitButton;

    public ATM(BankAccount account) {
        this.account = account;

        setTitle("ATM");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        balanceLabel = new JLabel("Balance: ₹0.00", SwingConstants.CENTER);
        checkBalanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(balanceLabel);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBalanceButton) {
            balanceLabel.setText("Balance: ₹" + account.checkBalance());
        } else if (e.getSource() == depositButton) {
            String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
            if (amountStr != null) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(amount);
                    balanceLabel.setText("Balance: ₹" + account.checkBalance());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == withdrawButton) {
            String amountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
            if (amountStr != null) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.withdraw(amount);
                    balanceLabel.setText("Balance: ₹" + account.checkBalance());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
                }
            }
        } else if (e.getSource() == exitButton) {
            JOptionPane.showMessageDialog(this, "Thank you for using the ATM.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount userAccount = new BankAccount(0.00); 
                new ATM(userAccount);
            }
        });
    }
}
