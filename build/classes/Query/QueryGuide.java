package Query;

import java.util.Map;

/**
 * QueryGuide.java - class that queries database for Error code guides
 * CSIS 643 - D01
 * @author Kore Woody
 */

public class QueryGuide extends Query
{
    String codeName,meaning,solution,code,todo,manual;
    
    /**
     * selectGuide method queries database for entered error code and formats the 
     * HTML page
     * @param code 
     */
    public void selectGuide(String code)
    {
        //clean and format code entry
        code = code.trim();
        code = code.replace(" ","_");
        String start = code.split("_")[0].toUpperCase();
        String end = code.split("_")[1].toLowerCase();
        code = start + "_" + end;
        code = code.replace("__","_");
        
        // create and execute query
        setModel("SELECT * FROM ERRORCODES WHERE CODE = '" + encrypt(code) + "'");
        
        Map<Integer,Map<Object,Object>> map = getMap();
        // check for null results
        if (map!=null)
        {
            // get code attributes
            String codeName = map.get(0).get("CODENAME").toString();
            String meaning = map.get(0).get("MEANING").toString();
            String todo = map.get(0).get("TODO").toString();
            this.code = code;
            this.codeName = codeName;
            this.meaning = meaning;
            this.todo = todo;
            // set guide solution with format
            this.solution = "<html><body>"+
                    "<h1><b>" + code + "</b></h1>\n\n" +
                    "<hr><h1>Name: <b>" + codeName + "</b></h1>\n\n" + 
                    "<p>" +
                    "<i>" + meaning + "</i>\n\n\n" +
                    "</p>" +
                    "<hr><h2><b><i>Trouble Shooting</i></b></h2>\n\n" + 
                    "<p>" +
                    "" + todo + ""+ 
                    "</p><hr></body><html>";
         }
        else// null query results
        {
            this.solution = "<h1>Code not found</h1>"
                    +"<h2>There were no results for <em>" + code + "</em> at this time.</h2>"+
                    "Try Searching for something else..";
        }
    }// end selectGuide method
    
    /**
     * selectGuides method queries database for all error codes and formats them
     * into a single page
     */
    public void selectGuides()
    {        
        //create and execute query
        String query = "SELECT * FROM ERRORCODES ";
        setModel(query);
        String solution = "";
        Map<Integer,Map<Object,Object>> map = getMap();
        //check for null map
        if (map!=null)
        {
            for (int i = 0; i< map.size();i++)
            {
                // get error code values
                String code = map.get(i).get("CODE").toString();
                String codeName = map.get(i).get("CODENAME").toString();
                String meaning = map.get(i).get("MEANING").toString();
                String todo = map.get(i).get("TODO").toString();
                this.code = code;
                this.codeName = codeName;
                this.meaning = meaning;
                this.todo = todo;
                // format solution text
                solution = solution + "<hr>"+
                        "<h1><b>" + code + "</b></h1>\n\n" +
                        "<hr><h1>Name: <b>" + codeName + "</b></h1>\n\n" + 
                        "<p>" +
                        "<i>" + meaning + "</i>\n\n\n" +
                        "</p>" +
                        "<h2><b><i>Trouble Shooting</i></b></h2>\n\n" + 
                        "<p>" +
                        "" + todo + ""+ 
                        "</p><br>";
            }
            String x = "<html><body>";
            String y = "</body><html>";
            this.solution = x + solution + y;
         }
        else // null results
        {
            this.solution = "<h1>Code not found</h1>"
                    +"<h2>There were no results for <em>" + code + "</em> at this time.</h2>"+
                    "Try Searching for something else..";
        }
    }// end selecGuide method
    
    /**
     * insertGuide method creates insert query to add new error code guide to
     * database
     * @param code  error code
     * @param name  name of error code
     * @param meaning   meaning of error code
     * @param todo      how to troubleshoot error code
     */
    public void insertGuide(String code, String name, String meaning,String todo)
    {
        insert("INSERT INTO ERRORCODES(CODE,CODENAME,MEANINGM,TODO) VALUES " + 
                "('" + encrypt(code)  + "','" + encrypt(name) +"','"+
                encrypt(meaning)+"','" +encrypt(todo) +"')");
    }//end insertGuide method
    
    /**
     * updateGuide method creates update query then calls Query to execute
     * @param code  error code
     * @param name  name of error code
     * @param meaning   meaning of error code
     * @param todo      how to troubleshoot error code
     */
    public void updateGuide(String code, String name, String meaning,String todo)
    {
        execute("UPDATE ERRORCODES SET "+
                "CODE = '" + encrypt(code) + "'" + 
                "CODENAME = '" + encrypt(name) + "'" + 
                "MEANING = '" + encrypt(meaning) + "'" + 
                "TODO = '" + encrypt(todo) + "'" );
    }//end updateGuide method
    
    /**
     * deleteGuide created delete query and calls query to execute 
     * @param code error code
     */
    public void deleteGuide(String code)
    {
        execute("DELETE * FROM ERRORCODES WHERE CODE = '" + encrypt(code) + "'");
    }//end deleteGuide  method
    
    /**
     * getSolution method returns the entire solution page
     * @return solution String
     */
    public String getSolution() {
        return solution;
    }//end getSolution method

    /**
     * getGuideName method returns the guide name
     * @return guideName
     */
    public String getGuideName() 
    {
        return codeName;
    }//end getGuideName method

    /**
     * getMeaning method returns the meaning of the error code
     * @return code meaning
     */
    public String getMeaning() 
    {
        return meaning;
    }//end getMeaning method

    /**
     * getToDo method returns the troubleshooting portion of the guide
     * @return String Troubleshooting
     */
    public String getToDo() 
    {
        return todo;
    }//end getToDo method
    
}// end QueryQuide class
