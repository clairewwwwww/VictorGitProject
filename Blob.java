import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class Blob {
    protected File file;
    protected String fileContents;
    protected String hash;

    public Blob(File file) {
        this.file = file;
        this.fileContents = getContents();
        this.hash = getHash();
    }

    private String getContents() {
        try {
            StringBuilder contents = new StringBuilder();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                contents.append(scanner.nextLine());
            }
            return contents.toString();

        } catch (Exception e) {
            System.out.println("File doesnt exist");
        }
        return null;

    }

    private String getHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(fileContents.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            System.out.println("Error in hashing");
        }
        return null;
    }

}