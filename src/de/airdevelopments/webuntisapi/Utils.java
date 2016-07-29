package de.airdevelopments.webuntisapi;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Utils {
	
	public static String getRandomId()
	{
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}
