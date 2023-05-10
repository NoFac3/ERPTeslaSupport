/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Query;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Chronology.Chronology;
import Cypher.Cypher;
import static Cypher.Cypher.setKey;
import DB.Database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author consp
 */
public class Cypher2 extends Query
{

    /**
     *
     */
    protected String encID;
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    /**
     * setCypher method for encrypting user login
     * @param userID    
     * @param passID 
     */
    public void setCypher(String userID, String passID)
    {
        String ID = userID + " " + passID;
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(ID.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++)
            {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length()==1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
                this.encID = hexString.toString();
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }// end setCypher method
    
    /**
     * setCypher method for encrypting strings
     * @param value any string value
     */
    public void setCypher(String value)
    {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(value.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++)
            {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length()==1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
                this.encID = hexString.toString();
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }// end setCypher method
    
    /**
     * getCypher method returns the encrypted String
     * @return encrypted String
     */
    public String getCypher()
    {
        return encID;
    }// end getCypher method

    /**
     *
     * @param myKey
     */
    public static void setKey2(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String encrypt2(final String strToEncrypt, final String secret) {
        try {
            setKey2(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     *
     * @param strToDecrypt
     * @param secret
     * @return
     */
    public static String decrypt2(final String strToDecrypt, final String secret) {
        try {
            setKey2(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(
                            strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    /**
     *
     * @param message
     */
    public static void print(String message)
    {
        System.out.println(message);
    }

    /**
     *
     * @param format
     * @param message
     */
    public static void printf(String format, String message)
    {
        System.out.printf(format,message);
    }
    
    /**
     *
     */
    public static void fileKey()
    {
        
        String fileName = "tsecky.key";
        String appPath = "src\\Cypher\\";
        print("fileName: "+fileName);
        
        try{
            Path path = Paths.get(fileName);
            print("Path: "+path);
            String filePath = path.toAbsolutePath().toString().replace(fileName, appPath + fileName);
            print("filePath: "+filePath);
            File file = new File(filePath);
            BufferedReader read = new BufferedReader(new FileReader(file));
            String key = read.readLine();
            print("Key: "+key);
        }catch (Exception e)
        {
            try{
            Path path = Paths.get(fileName);
            print("Path: "+path);
            String filePath = path.toAbsolutePath().toString();
            print("filePath: "+filePath);
            File file = new File(filePath);
            BufferedReader read = new BufferedReader(new FileReader(file));
            String key = read.readLine();
            print("Key: "+key);
            } catch (Exception ex)
            {
                System.err.print(ex);
            }
        }
        /*
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader read = new BufferedReader(new InputStreamReader(in)))   {
            String text;

            while ((text = read.readLine()) != null) {
                setKey(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    
    /**
     *
     * @param key
     */
    public static void encryptDatabase(String key)
    {
        String[] tables = {"LOGIN","TICKET","TICKETLOG","ACCOUNTLOG","MESSAGES", "CUSTOMERS","CUSTOMERVEHICLES", "VEHICLEINVENTORY","ERRORCODES", "VEHICLEDEATHS","SUDDENACCELERATION"};
        /*
        for (String table: tables)
        {        
            ResultSet rs = resultSet("SELECT * FROM " + table);
            List<String> headers = new ArrayList<String>();
            int columns = 0;
            int row = 0;
            String columnType = "";
            String columnName = "";

            try {
                while (rs.next()) 
                {
                    List<String> results = new ArrayList<String>();
                    ResultSetMetaData md = rs.getMetaData();
                    columns = md.getColumnCount();
                    row = rs.getRow();
                    for (int i = 1; i <= columns; i++)
                    {
                        columnType = md.getColumnTypeName(i);
                        columnName = md.getColumnName(i);

                        if (row==1)
                        {
                            headers.add(columnName);
                        }

                        if (columnType.matches("VARCHAR"))
                        {

                            if (rs.getString(columnName)==null)
                            {
                                results.add("'" + rs.getString(columnName) + "'");
                            }
                            else
                            {
                                results.add("'" + encrypt(rs.getString(columnName)) + "'");
                            }
                            //results.add(encrypt(rs.getString(columnName)));
                        }
                        else if (columnType.matches("INT") || columnType.matches("DOUBLE"))
                        {
                            results.add(rs.getString(columnName));
                        }
                        else
                        {
                            results.add(rs.getString(columnName));
                        }
                    }
                    String query = "\nUPDATE " + table + " SET ";
                    for (int h = 1; h <= headers.size()-1; h++) 
                    {
                        if (h == headers.size()-1)
                        {
                            query = query + headers.get(h) + " = " + results.get(h) + 
                                    " WHERE " + headers.get(0) + " = " + results.get(0) + " ";
                        }
                        else
                        {
                            query = query + headers.get(h) + " = " + results.get(h) + ", ";
                        }
                    }
                    execute(query);
                }
            } 
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        } */
    }
    
    /**
     *
     */
    public static void setNewCypher()
    {
        final String secretKey = "Tesslassshhhhhhhhhhh";
        Query q = new Query();
        q.setKey(secretKey);
        //fileKey();
        encryptDatabase(secretKey);
        q.changeEncryptionKey(secretKey);
        
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        setNewCypher();
        
        /*
        Calendar cal = Calendar.getInstance();
        
        
        
        int month = cal.get(Calendar.MONTH);
        int week = cal.get(Calendar.WEEK_OF_MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        //int day1 = cal.get(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        
        print("Month: " + month);
        print("Week of Month: " + week);
        print("Day of Week: " + day);
        
        
        //first day

        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        int firstColumn = cal.get(Calendar.DAY_OF_WEEK);
        print("First Column: " + firstColumn);
        //int firstColumn = cal.getTime().getDay();
        
        print("\n");

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        //print("Last Day of Month: " + cal);
        print("Last Day of Month: " + cal.get(Calendar.DAY_OF_MONTH));
        print("Last Day of Month*: " + cal.getTime().getDay());
        //print("Last Day of Month: " + );
        int lastColumn = cal.getTime().getDay();
        
                
        
        
        //Days in month
        cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int days = cal.get(Calendar.DAY_OF_MONTH);
        print("Total Days: " + days);
        
        
        cal = Calendar.getInstance();
        cal.setTime(new Date());
        //int i = cal.get(Calendar.DAY_OF_WEEK, cal.getActualMaximum());
        
        print(" : " + month);
        print(" : " + month);
        print(" : " + month);
        
        
        
        
        
        
        //System.out.println("\n\n----------------BREAK: PART 2--------------------\n\n");
        
        //
        String firstName = "John";
        String lastName = "Smith";
        String customerName = firstName + " " + lastName;
        String customerID = firstName.toLowerCase() + lastName.toLowerCase();
        String phone = "333-333-3333";//"123-456-7890";
        String email = "js2@gmail.com";//"jm3@gmail.com";
        String vin = "";
        String tickets = "";
        
        String accuracy = "AND";//exact
        // exact for creating customer ids and updating db
        // general for  
        // GENERAL
        
        q.setModel("SELECT CUSTOMERID FROM CUSTOMERS WHERE "+
                "FIRSTNAME = '" + q.encrypt(firstName) + 
                "' "+accuracy+" LASTNAME =  '" + q.encrypt(lastName) + 
                "' "+accuracy+" PHONE = '" + q.encrypt(phone) + 
                "' "+accuracy+" EMAIL = '" + q.encrypt(email) + "' ");
        
        
        ResultSet rs = q.getResultSet();
        Map<Integer,Map<String,String>> map = q.getMap();
        
        if(map!=null)
        {
            customerID = map.get(0).get("CUSTOMERID");
            System.out.println("\nExisting Customer ID: " + customerID);
        }
        else
        {
            q.setModel("SELECT * FROM CUSTOMERS WHERE "+
                "FIRSTNAME = '" + q.encrypt(firstName) + 
                "' "+accuracy+" LASTNAME =  '" + q.encrypt(lastName) + "' ");
            map = q.getMap();
            if(map!=null)
            {
                int[] ids = new int[map.size()];
                String[] cUsers = new String[map.size()];
                int temp = 0;

                for (int i : map.keySet())
                {
                    System.out.println("\n" + i + ": " + map.get(i));
                    cUsers[i] = map.get(i).get("CUSTOMERID");
                    ids[i] = (int) Integer.parseInt(map.get(i).get("CUSTOMERID").replaceAll("[^0-9]",""));
                    if (ids[i]>temp)
                    {
                        temp = ids[i];
                    }
                    System.out.println("\n---> CUSTOMER ID: " + cUsers[i]);
                    System.out.println("---> CUSTOMER ID: " + ids[i]);
                }
                customerID = customerID + (temp+1);
                System.out.println("\nNew ID: " + customerID);
            }
            else
            {
                System.out.println("?? CustomerID: "+customerID+1);
            }
            
        }*/
        /*
        Map<Integer,Map<String,String>> map = new HashMap<>();
        
        String openBy = "kaarons1";
        String editBy = "kaarons1";
        String closeBy = "";
        
        String[] departments = new String[3];
        q.setModel("SELECT Department FROM LOGIN WHERE USERID = '"+q.encrypt(openBy)+"'");
        map = q.getMap();
        for (int i : map.keySet())
        {
            departments[0] = map.get(i).get("DEPARTMENT");
            System.out.println("\n" + i + ": " + departments[0]);
        }
        if (!editBy.matches(""))
        {
            q.setModel("SELECT DEPARTMENT FROM LOGIN WHERE USERID = '"+q.encrypt(editBy)+"'");
            map = q.getMap();
            for (int i : map.keySet())
            {
                departments[1] = map.get(i).get("DEPARTMENT");
                System.out.println("\n" + i + ": " + departments[1]);
            }
        }
        
            q.setModel("SELECT DEPARTMENT FROM LOGIN WHERE USERID = '"+q.encrypt(closeBy)+"'");
            map = q.getMap();
            for (int i : map.keySet())
            {
                String dept = map.get(i).get("DEPARTMENT");
                if (dept.matches(""))
                {
                    dept = "";
                }
                departments[2] = q.encrypt(dept);
                System.out.println("\n" + i + ": " + departments[2]);
            }
        */
        /*
        String originalString = "password";
        String encryptedString = encrypt(originalString, secretKey) ;
        String decryptedString = decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        
        Map<Integer,String[]> map = new HashMap<Integer,String[]>();
        String value = "";
        int columns = 6;
        
        String startValue = "";
        
        String[] x1 = {"default", "password","default", "default", "default", "default"
        ,"manager", "password","Manager", "Manager", "Mary", "Hart"
        ,"engineer","password","Engineer","Engineering", "Jane", "Dawn"
        ,"analyst", "password","Analyst", "Analyst", "John", "Dane"
        ,"tech", "password","Tech", "Tech Support", "Jane", "Kyle"
        ,"jsmith1", "password","Tech", "Tech Support", "John", "Smith"
        ,"jsmith2", "password","Tech", "Tech Support", "John", "Smith"
        ,"jesquire1","password","Admin", "Jane Esquire", "Jane", "Esquire"
        ,"kesquire1","password","Admin", "Kevin Esquire","Kevin","Esquire"
                ,"sreed1", "password","Manager", "Scott Reed", "Scott","Reed","mlow1", "password","Manager", "Mark Low", "Mark", "Low","lhouse1", "password","Engineer","Leah House", "Leah", "House","sjones1", "password","Analyst", "Scout Jones", "Scout","Jones","kaarons1", "password","Tech", "Kyle Aarons", "Kyle", "Aarons"};

        map.put(1, x1);
         
        
        for (int j = 0; j<x1.length;j++);
        {
            String[] x2 = {};
            for (int k = 0; k<columns; k++)
            {
                
            }
            for (int i= 0; i<columns; i++)
            {
                System.out.printf("\n('2023-04-01',");
                for (String d : map.get(i))
                {
                    encryptedString = encrypt(d, secretKey) ;
                    value = value + "\'"+encryptedString+"',";
                }
                value = value + "   ),";
                System.out.printf("%s",value.replace(",   ),","),"));
            }
        }

        
        String[] defa = {"default", "password","default", "default", "default", "default"};
        */
    }
}
