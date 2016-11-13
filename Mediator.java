package CurrencyReport;

import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Mediator {
	ArrayList<String> countries = new ArrayList<String>();
	ArrayList<InternetAddress> receivers = new ArrayList<InternetAddress>();
	boolean swi = true;
	String data = null;
	
	public void init() {
		try {
			receivers.add(new InternetAddress("zhangwenfancgm@gmail.com"));
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1);
					synchronized (this) {
						while (swi) {
							StringBuffer sb = new StringBuffer();
							data = ConnectionUtil.getData();
							for (String s : countries) {
								sb.append(ConnectionUtil.searcher(data, s) + "\n");
							}
							System.out.println(sb);
							for (InternetAddress ia : receivers) {
								CurrencyReportor.sendCurrncyReport(sb.toString(), ia);
							}
							Thread.sleep(43200000);
						}
					}
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				
			}
		});
		t1.start();
	}
	
	public void addCountry(String countryName) {
		synchronized (this) {
			countries.add(countryName);
		}
	}
	
	public void addReceivers(String receiverMail) {
		synchronized (this) {
			try {
				receivers.add(new InternetAddress(receiverMail));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void close() {
		swi = false;
	}
	
	public ArrayList<String> getCountries() {
		return this.countries;
	}
	
	public ArrayList<InternetAddress> getReceivers() {
		return this.receivers;
	}

	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		Mediator m = new Mediator();
		m.addCountry("欧元");
		System.out.println(m.getReceivers());
		m.addCountry("加拿大元");
		
		m.init();
		System.out.println(m.getReceivers());
		m.addCountry("阿根廷比索");
		m.addCountry("阿尔及利亚第纳尔");
		m.addReceivers("449348260@qq.com");
m.addReceivers("heqiyunvancouver@gmail.com");
		
		
		
	}

}
