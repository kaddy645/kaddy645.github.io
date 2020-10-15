import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Wallet {
   /**
    * The RandomAccessFile of the wallet file
    */  
   private RandomAccessFile file;

   /**
    * Creates a Wallet object
    *
    * A Wallet object interfaces with the wallet RandomAccessFile
    */
    public Wallet () throws Exception {
	this.file = new RandomAccessFile(new File("wallet.txt"), "rw");
    }

   /**
    * Gets the wallet balance. 
    *
    * @return                   The content of the wallet file as an integer
    */
    public int getBalance() throws IOException {
	this.file.seek(0);
	return Integer.parseInt(this.file.readLine());
    }

   /**
    * Sets a new balance in the wallet
    *
    * @param  newBalance          new balance to write in the wallet
    */
    public void setBalance(int newBalance) throws Exception {
	this.file.setLength(0);
	String str = new Integer(newBalance).toString()+'\n'; 
	this.file.writeBytes(str); 
    }

public synchronized int safeWithdraw(int valueToWithdraw) throws Exception {
	
		FileLock lock = file.getChannel().lock();
		int updatedBalance =0;
		// Current Thread has access to wallet.txt
		Thread.sleep(1000);
		try {

			int balance = getBalance();

			// verify the amount
			if (balance < valueToWithdraw) {
				// return the balance in wallet if less than valuToWithdraw
			    updatedBalance = balance;
				setBalance(balance - balance);
				

			} else {
				// update balance otherwise
			updatedBalance = (balance - valueToWithdraw);
			setBalance(balance - valueToWithdraw);
			
				
			}


	} finally {
			// unlock the file once done with writing
			lock.close();
		}

           return updatedBalance;

	}


public synchronized void safeDeposit(int valueToDeposit) throws Exception {
	
		
		
		// Current Thread has access to wallet.txt
		
		try {
			 	if (valueToDeposit<0){
        	System.out.println("!**Invalid Transaction Can't be zero**!");
    	}else{

			int balance = getBalance();

			setBalance(balance+valueToDeposit);
			System.out.println("!" + valueToDeposit);
		}
          

	}catch(Exception ex){

	} 
    

	}


   /**
    * Closes the RandomAccessFile in this.file
    */
    public void close() throws Exception {
	this.file.close();
    }
}
