package ba.unsa.etf.si.tim1.util;

public class Util {
	public static String join(String[] s, String glue) {
		int k = s.length;
		if (k == 0) {
			return null;
		}
		StringBuilder out = new StringBuilder();
		out.append(s[0]);
		for (int x=1; x < k; ++x) {
			out.append(glue).append(s[x]);
		}
		return out.toString();
	}
	
	public static String[] remove(String[] s, String element) {
		java.util.ArrayList<String> array = new java.util.ArrayList<String>();
		for (int i = 0; i < s.length; i++)
			if (!s[i].equals(element))
				array.add(s[i]);
		return array.toArray(new String[array.size()]);
	}
	
	public static String[] remove(String[] s, int index) {
		java.util.ArrayList<String> array = new java.util.ArrayList<String>();
		for (int i = 0; i < s.length; i++)
			if (i != index)
				array.add(s[i]);
		return array.toArray(new String[array.size()]);
	}
}
