package atmProject;

public class ATM {
	private double balance = 0.0;
	

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}
public void withdrawal(double amount) {
	if(amount< balance)
	balance = balance - amount ;
	}
	public void deposit(double amount) { 
		balance = balance + amount ;
		
		}
 
}
