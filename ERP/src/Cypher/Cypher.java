package Cypher;

import java.io.File;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

/**
 * Cypher.java - class for encrypting and decrypting values
 * CSIS 643 - d01
 * @author Kore Woody
 */
public class Cypher 
{
    protected String encID;
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String secret = "Tesslassshhhhhhhhhhh";
    
    /**
     * fileKey method gets encryption key file
     */
    public static void fileKey()
    {
        String fileName = "tsecky.key";
        String appPath = "src\\Cypher\\";
        String key = "";
        try{ // try to access database key file
            Path path = Paths.get(fileName);// set path
            // reconstruct file path
            String filePath = path.toAbsolutePath().toString().replace(fileName, appPath + fileName);
            // get file
            File file = new File(filePath);
            // read file
            Scanner sc = new Scanner(file);
            key = sc.next();
        }catch (Exception e)
        {
            try{// try to access root directory key file
            Path path = Paths.get(fileName); // set path
            /// get absolue path
            String filePath = path.toAbsolutePath().toString();
            // get file
            File file = new File(filePath);
            // read file
            Scanner sc = new Scanner(file);
            key = sc.next();
            } catch (Exception ex){}// catch exceptions
        }
        // set secret and set key
        secret = key;
        setKey(key);
    }// end fileKey method

    /**
     * setCypher method for encrypting user login
     * @param userID    
     * @param passID 
     */
    public void setCypher(String userID, String passID)
    {
        String ID = userID + " " + passID;
        try{
            // set digest algorithm
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // set hash
            final byte[] hash = digest.digest(ID.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();// build hex
            for (int i = 0; i < hash.length; i++) 
            {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) 
                {
                    hexString.append('0');
                }
                hexString.append(hex);
                this.encID = hexString.toString();// set encrypted ID
            }
        } //catch exception 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {}
        
    }// end setCypher method
    
    /**
     * setKey method for setting the encryption key
     * @param newKey new encryption key
     */
    public static void setKey(final String newKey) 
    {
        //Instantiate new message digest object
        MessageDigest sha = null;
        try {
            key = newKey.getBytes("UTF-8");// set charset
            sha = MessageDigest.getInstance("SHA-1");// set algorithm
            //set key
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            // set secret key
            secretKey = new SecretKeySpec(key, "AES");
        } 
        // catch exception
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {}
    }// end setKey method
    
    /**
     * get Key method returns encryption key string
     * @return 
     */
    private static String getKey()
    {
        return secret;
    }// end getKey method

    /**
     * encrypt method for encrypting strings
     * @param text any string value
     * @return return encrypted string
     */
    public static String encrypt(final String text) 
    {
        try {
            // Instantiate new Cipher objest with AES encryption
            Cipher cipher = Cipher.getInstance("AES");
            // set encryption mode and key
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
        } 
        catch (Exception e) {// throws exception
            return text;// return unencrypted string
        }
    }// end setCypher method

    /**
     * decrypt method for decrypting encrypted string
     * @param text  encrypted string
     * @return decrypted string
     */
    public static String decrypt(final String text) 
    {
        //if(text.length()>16)
        try {
            // Instantiate new Cipher objest with AES encryption
            Cipher cipher = Cipher.getInstance("AES");
            // set decryption mode and key
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // return decrypted string
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(text)));
        } 
        catch (Exception e) {// catch exception
            return text;// return encrypted string
        }
    }// setDecypher method    
}// end Cypher class
