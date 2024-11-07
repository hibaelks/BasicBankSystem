class Bank{
	private String id,country;
	private Account [] accounts;
	public Bank(String id,String country){
		this.id=id;
		this.country=country;
		this.accounts=new Account[0];
	}
	public String toString(){
		return "Bank identified : "+id+" of "+country+" having "+accounts.length+" accounts.";
	}
	public void displayAccounts(){
		System.out.println("Accounts of bank "+id+" :");
		for(int i=0;i<accounts.length;i++){
			System.out.println("\n"+accounts[i]);
		}
	}
	public String[] getInfo(){
		String[] info=new String[2];
		info[0]=id;
		info[1]=country;
		return info;
	}
	public void addAccount(Account account){
		Account[] temp=new Account[(accounts.length+1)];  
		System.arraycopy(accounts,0,temp,0,accounts.length);
		temp[accounts.length]=account;
		accounts=temp;
	}
	public Account searchAccount(String accountNbr){
		for(int i=0;i<accounts.length;i++){
			if(accounts[i].getNbr().equals(accountNbr)) return accounts[i];
		}
		return null;
	}
}