package de.airdevelopments.webuntisapi;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.regex.Pattern;

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
	
	/**
	 * Transforms a given String into a byte array, also replacing a given String with a char sequence
	 * @param string The String to transform
	 * @param search The String to replace
	 * @param replace The char array with which the search String should be replaced
	 * @return The resulting byte array
	 */
	public static byte[] safeReplace(String string, String search, char[] replace)
	{
		String[] parts = string.split(Pattern.quote(search));
		
		byte[] requestData = new byte[string.length() + (parts.length - 1) * (replace.length - search.length()) ];
		
		
		CharBuffer pwBuf = CharBuffer.wrap(replace);
		CharsetEncoder enc = StandardCharsets.UTF_8.newEncoder();
		
		int offset = 0;
		for(int i = 0; i<parts.length; ++i) {
			ByteBuffer buf = ByteBuffer.wrap(requestData, offset, parts[i].length());
			
			enc.encode(CharBuffer.wrap(parts[i]), buf, true);
			offset += parts[i].length();
			
			if(i < parts.length - 1) {
				enc.encode(pwBuf, ByteBuffer.wrap(requestData, offset, replace.length), true);
			
				offset += replace.length;
			}
		}
		
		return requestData;
	}

}
