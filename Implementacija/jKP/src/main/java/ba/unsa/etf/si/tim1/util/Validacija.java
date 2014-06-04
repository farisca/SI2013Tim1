package ba.unsa.etf.si.tim1.util;

public class Validacija {
	public static void validirajPassword(String password) throws Exception {
		if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*") || password.length() < 6)
			throw new Exception("Šifra mora imati barem 6 znakova i mora sadržavati i slova i brojeve!");
	}
	
	public static void validirajImeIPrezime(String imePrezime) throws Exception {
		if(!imePrezime.contains(" ") || imePrezime.split("[^a-zA-ZČčĆćĐđŠšŽž\\s]", 2).length != 1)
			throw new Exception("Niste ispravno unijeli ime i prezime (dozvoljena su samo slova)!");
	}
	
	public static void validirajUsername(String username) throws Exception {
		if (username.isEmpty() || username.split("[^a-zA-Z0-9_]", 2).length != 1)
			throw new Exception("Niste pravilno upisali korisničko ime (dozvoljena su samo slova, brojevi i znak _)!");
	}

}
