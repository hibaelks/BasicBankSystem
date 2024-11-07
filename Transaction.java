import java.util.*;
import java.text.SimpleDateFormat;

class Transaction{
	enum Type {VIRINI,VIREST,VIRMULTA,VIRCHAC};
	private Type type;
	private String ref;
	private double amount;
	private Account src,dest;
	private final long timeStamp; 
	public Transaction(String ref,Account src,Account dest) { 
		this.src=src;
		this.dest=dest;
		timeStamp = new Date().getTime(); 
		setType(src,dest);
		this.ref=ref;
		amount=0;
	} 
	public String getDate() { 
		SimpleDateFormat date =new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
		return date.format(new Date(timeStamp));
	} 
	public String getTime() { 
		SimpleDateFormat time =new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
		return time.format(new Date(timeStamp));
	} 
	public String getType() {
		switch (type){
			case VIRINI: return "VIRINI";
			case VIREST: return "VIREST";
			case VIRMULTA: return "VIRMULTA";
			case VIRCHAC: return "VIRCHAC";
		}
		return "";
	}
	public String toString(){
		return "Transaction de type "+getType()+" effectuee le "+getDate()+" a "+getTime();
	}
	private void setType(Account src,Account dest){
		String id1=src.getBank().getInfo()[0];		
		String id2=dest.getBank().getInfo()[0];
		String country1=src.getBank().getInfo()[1];
		String country2=dest.getBank().getInfo()[1];
		if(id1.equals(id2)&&country1.equals(country2)) type=Type.VIRINI;
		if(id1.equals(id2)&&!country1.equals(country2)) type=Type.VIRMULTA;
		if(!id1.equals(id2)&&country1.equals(country2)) type=Type.VIREST;
		if(!id1.equals(id2)&&!country1.equals(country2)) type=Type.VIRCHAC;
	}
	//test the class
    /*public static void main(String [] ags){
		Transaction t = new Transaction();
		//System.out.println(t.getStamp()+'\n');
		System.out.println(t);
	}*/
}