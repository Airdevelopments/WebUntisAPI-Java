package de.airdevelopments.webuntisapi;

public class Test {

	public static void main(String[] args) {
		
		ApplicableWebUntisConnection connection = new ApplicableWebUntisConnection("school-sch", "poly", "username", "******");
		
		try
		{
			if(connection.login())
				System.out.println("Login Successful");

			connection.getSubstitutions("20180212", "20181230");
			
			connection.logout();
		}catch(WebUntisConnectionErrorException e)
		{
			e.printStackTrace();
			System.out.println(e.getError());
		}
	}

}
