package de.airdevelopments.webuntisapi;

import java.util.ArrayList;

import de.airdevelopments.webuntisapi.objects.Substitution;

public class Test {
	
	public static void main(String[] args)
	{
		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test() throws Exception
	{
		WebUntisConnection connection = new WebUntisConnection("school-sch", "poly", "username", "password");
		
		if(connection.login())
		{
			System.out.println("Connection opened!");
		}
		else
		{
			System.out.println("Connection broken!");
		}
		
		
		ArrayList<Substitution> subs =  connection.getSubstitutions("20160314", "20160314");
		for(Substitution sub : subs)
		{
			System.out.println("Type: " + sub.type.toString() + " Hour: " + sub.schoolhour + " Classes: " + sub.classname + " Teacher: " + sub.teacher + " Subject: " + sub.subject + " Rooms: " + sub.room + " Text: " + sub.text);
		}
		
		if(connection.logout())
		{
			System.out.println("Connection completed!");
		}
		else
		{
			System.out.println("Connection broken!");
		}
	}
	
}
