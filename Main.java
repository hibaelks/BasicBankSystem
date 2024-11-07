import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Client clients[]=new Client[0];
		Bank banks[]=new Bank[0];
		try{
			String choice="";
			Scanner inputDevice = new Scanner(System.in);
			while(choice!=null){
			System.out.println("\nHello, please select: \n1) Add bank\n2)Add client\n3)Add account\n4)Add transaction\n5)Display banks\n6)Display Clients\n7)Display all accounts\nother) Quit.");
		    choice = inputDevice.next();
			if(choice.equals("1")) banks=addBank(createBank(),banks);
			else{
				if(choice.equals("2")) clients=addClient(createClient(),clients);
			    else {
					if(choice.equals("3")) createAccount(clients,banks);
					else{
						if(choice.equals("4")) createTransaction(banks);
						else{
							if(choice.equals("5")) displayBanks(banks);
							else{
								if(choice.equals("6")) displayClients(clients);
								else{
									if(choice.equals("7"))
										displayAccounts(banks);
									else choice=null;
								}
							}
						}
					}
				}
			}
		}
		}catch(Exception e){
			System.out.println("ERROR.TRY LATER.");
		}
	}
	public static Client createClient() throws Exception{
		String numClient,fname,lname,address,email,phone;
		Scanner inputDevice = new Scanner(System.in);
		System.out.print("Please enter client's 'Id >> ");
		numClient = inputDevice.next();
		System.out.print("Please enter client's first name >> ");
		fname = inputDevice.next();
		System.out.print("Please enter client's last name >> ");
		lname = inputDevice.next();
		System.out.print("Please enter client's address >> ");
		address = inputDevice.next();
		System.out.print("Please enter client's phone number >> ");
		phone = inputDevice.next();
		System.out.print("Please enter client's email >> ");
		email = inputDevice.next();
		Client client=new Client(numClient,fname,lname,address,phone,email);
		//System.out.println(client);
		return client;
	}
	public static Client[] addClient(Client client,Client [] clients) throws Exception{
		Client[] temp=new Client[(clients.length+1)];  
		System.arraycopy(clients,0,temp,0,clients.length);
		temp[clients.length]=client;
		return temp;
	}
	public static void displayClients (Client [] clients)throws Exception{
		System.out.println("Displaying existing clients ("+ clients.length+"clients) :\n");
		for(int i=0;i<clients.length;i++){
			System.out.println("Client "+(i+1)+":\n"+clients[i]+"\n");
		}
	}
	public static Bank createBank() throws Exception{
		String id,country;
		Scanner inputDevice = new Scanner(System.in);
		System.out.print("Please enter Bank's id' >> ");
		id = inputDevice.next();
		System.out.print("Please enter Bank's country' >> ");
		country = inputDevice.next();
		Bank bank=new Bank(id,country);
		//System.out.println(client);
		return bank;
	}
	public static Bank[] addBank(Bank bank,Bank[] banks) throws Exception{
		Bank[] temp=new Bank[(banks.length+1)];  
		System.arraycopy(banks,0,temp,0,banks.length);
		temp[banks.length]=bank;
		return temp;
	}
	public static void displayBanks(Bank [] banks){
		System.out.println("Displaying existing banks ("+ banks.length+"banks) :\n");
		for(int i=0;i<banks.length;i++){
			System.out.println("Bank "+(i+1)+":\n"+banks[i]+"\n");
		}
	}
	//Searches a client by first and last name and returns it
	public static Client searchClient(String fname,String lname,Client[] clients)throws Exception{
		for(int i=0;i<clients.length;i++){
			if(clients[i].getInfo()[1].equals(fname)&&clients[i].getInfo()[2].equals(lname)) return clients[i];
		}
		return null;
	}
	public static Bank searchBank(String id,Bank[] banks)throws Exception{
		for(int i=0;i<banks.length;i++){
			if(banks[i].getInfo()[0].equals(id)) return banks[i];
		}
		return null;
	}
	public static Account createAccount(Client [] clients,Bank[] banks) throws Exception{
		String fname,lname,id,currency="",accountNbr;
		System.out.print("Creating account :");
		Scanner inputDevice = new Scanner(System.in);
		System.out.print("Please enter client's first name >> ");
		fname = inputDevice.next();
		System.out.print("Please enter client's last name >> ");
		lname = inputDevice.next();
		Client client=searchClient(fname,lname,clients);
		if(client==null)  System.out.println("Client doesn't exist yet. Create it.");
		else{
		Bank bank=null;
		while(bank==null){
		System.out.print("Please enter bank's id >> ");
		id = inputDevice.next();
		bank=searchBank(id,banks);
		if(bank==null)  System.out.println("Bank doesn't exist. TRY Another bank.");
		}
		System.out.print("Please enter Account's number >> ");
		accountNbr = inputDevice.next();
		while(!(currency.equals("MAD")||currency.equals("USD")||currency.equals("EUR"))){
			System.out.print("Please enter Account's currency (MAD,EUR,USD)>> ");
			currency = inputDevice.next();
		}
		Account account=new Account(accountNbr,currency,bank,client);
		System.out.println(account);
		client.addAccount(account);
		//System.out.println(client);
		bank.addAccount(account);
		//System.out.println(bank);
		return account;
		}
		return null;
	}
	public static Account searchAccount(Bank[] banks,String accountNbr)throws Exception{
		Account account=null;
		for(int i=0;i<banks.length;i++){
			account=banks[i].searchAccount(accountNbr);
			if(account!=null) return account;
		}
		return null;
	}
	public static void createTransaction(Bank[] banks)throws Exception{
		Transaction transaction=null;
		Account src=null,dest=null;
		String accountNbr,accountNbr2;
		System.out.print("Creating transaction :");
		Scanner inputDevice = new Scanner(System.in);
		while(src==null||dest==null){
		System.out.println("Please enter source account's number >> ");
		accountNbr = inputDevice.next();
		src=searchAccount(banks,accountNbr);
		System.out.println("Please enter destination account's number >> ");
		accountNbr2 = inputDevice.next();
		dest=searchAccount(banks,accountNbr2);
		if(src==null||dest==null) System.out.println("\nOne of the accounts is non existant.Try Again.\n");
		}
		System.out.println("Please enter Transaction's reference >> ");
		String ref = inputDevice.next();
		transaction=new Transaction(ref,src,dest);
		//System.out.println(transaction);
		src.addTransaction(transaction);
		dest.addTransaction(transaction);

	}
	public static void displayAccounts(Bank[] banks){
		for(int i=0;i<banks.length;i++){
			banks[i].displayAccounts();
		}
	}

}