import java.util.Date;
import java.text.SimpleDateFormat;
class Account{
	private String accountNbr;
	public enum Currency {MAD,EUR,USD}
	private Date cdate,mdate;
	private Currency currency; 
	private Bank bank;
	private Client client;
	private Transaction[] transactions;
	public Account(String nbr,String curr,Bank bank,Client client){
		this.accountNbr=nbr;
		switch (curr){
			case "MAD": this.currency=Currency.MAD; break;
			case "EUR": this.currency=Currency.EUR; break;
			case "USD": this.currency=Currency.USD; break;
	    }
		this.bank=bank;
		this.client=client;
		this.cdate=new Date();
		this.mdate=new Date();
		this.transactions=new Transaction[0];
	}
	public String getNbr(){
		return accountNbr;
	}
	public String toString(){
		String temp="";
		switch (currency){
			case Currency.MAD: temp="MAD"; break;
			case Currency.USD: temp="USD"; break;
			case Currency.EUR: temp="EUR"; break;
	    }
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String fcdate=format.format(cdate);
		String str="Account number : "+accountNbr+"\n\tCreated: "+fcdate+"\n\tCurrency: "+
		temp+"\n\tBank's id: "+bank.getInfo()[0]+"\n\tClient: "+client.getInfo()[1]+" "+client.getInfo()[2]+".\n";
		str+="\n\tTransactions: ";
		for(int i=0;i<transactions.length;i++){
			str+="\n\t\t"+transactions[i].toString();
		}
		return str;
	}
	public Bank getBank(){
		return bank;
	}
	public void addTransaction(Transaction transaction){
		Transaction[] temp=new Transaction[(transactions.length+1)];  
		System.arraycopy(transactions,0,temp,0,transactions.length);
		temp[transactions.length]=transaction;
		transactions=temp;
	}
}