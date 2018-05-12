package de.airdevelopments.webuntisapi;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.json.JSONArray;
public class Utils {
	
	/**
	 * Obtain a random id String of length 32
	 * @return A random id for a method request (32 chars)
	 */
	public static String getRandomId()
	{
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	/**
	 * Transforms a JSONArray consisting of integers into a java integer array
	 * @param array The JSONArray to copy
	 * @return The resulting integer array
	 */
	public static int[] toIntArray(JSONArray array)
	{
		int[] result = new int[array.length()];
		for(int i = 0; i < array.length(); i++)
		{
			result[i] = array.getInt(i);
		}
		return result;
	}

}
