class Client{
	private String numClient,fname,lname,address,phone,email;
	private Account[] accounts;
	public Client(String numClient,String fname,String lname,String address,String phone,String email){
		this.numClient=numClient;
		this.fname=fname;
		this.lname=lname;
		this.address=address;
		this.phone=phone;
		this.email=email;
		this.accounts=new Account[0];
	}
	public String toString(){
		return fname+" "+lname+"\n"+address+"\n"+phone+"\n"+email+"\n"+accounts.length+" accounts.";	
	}
	public String[] getInfo(){
		String[] info=new String[6];
		info[0]=numClient;
		info[1]=fname;
		info[2]=lname;
		info[3]=address;
		info[4]=phone;
		info[5]=email;
		return info;
	}
	public void addAccount(Account account){
		Account[] temp=new Account[(accounts.length+1)];  
		System.arraycopy(accounts,0,temp,0,accounts.length);
		temp[accounts.length]=account;
		accounts=temp;
	}
}