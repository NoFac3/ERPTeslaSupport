package Query;

import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * QueryData.java - class for querying vehicle inventory and incident databases. 
 * Used by analyst profile and populating details in tickets
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class QueryData extends Query
{
    DefaultTableModel dataModel;
    String year, vmodel, vin;
    String[] customerVehicle, yearList,modelList;

    /**
     * selectVehicles method
     */
    public void selectVehicles()
    {
        setModel("SELECT * FROM APP.VEHICLEINVENTORY");
    }// end selectVehicles method
    
    /**
     * selectInventory method sets the table model for vehicle inventory by
     * for year and model
     */
    public void selectInventory()
    {
        setModel("SELECT VYEAR,MODEL FROM APP.VEHICLEINVENTORY ");
    }// end selectInventory method
    
    /**
     * selectDeaths method sets the table model for vehicle deaths
     */
    public void selectVehicleDeaths()
    {
        setModel("SELECT * FROM APP.VEHICLEDEATHS");
    }// end selectDeaths method
    
    /**
     * selectDeaths method sets the table model for vehicle deaths with columns 
     * for year, date, country, model, other vehicle, deaths, description and 
     * autopilot confirmation for vehicle incidents
     */
    public void selectDeaths()
    {        
        setModel("SELECT "+
                "AYEAR,ADATE,COUNTRY,STATELOC,MODEL,"+
                "OTHER_VEHICLE,DEATHS,AUTOPILOT_CLAIMED,DESCRIPTION "+
                "FROM VEHICLEDEATHS ");
    }// end selectDeaths method

    /**
     * selectSuddenAcceleration method sets the table model for incidents of 
     * sudden acceleration 
     */
    public void selectSuddenAcceleration() 
    {
        setModel("SELECT * FROM APP.SUDDENACCELERATION ");

    }// end selectSuddenAcceleration method
    
    /**
     * setCustomerVehicle method for setting customer vehicle information
     * @param year
     * @param vmodel
     * @param vin
     */
    public void setCustomerVehicle(String year, String vmodel, String vin)
    {
        this.year = year;
        this.vmodel = vmodel;
        this.vin = vin;
        String[] customerVehicle = {year,vmodel,vin};
        this.customerVehicle = customerVehicle;
    }// end setCustomerVehicle method
    
    /**
     * selectModelYears method sets the lists for vehicle years and models
     */
    public void selectModelYears()
    {
        // query inventory
        selectInventory();
        // get results hashmap
        Map<Integer,Map<Object,Object>> map = getMap();
        String[] yearList = new String[map.size()];
        String[] modelList = new String[map.size()];
        String year = "";
        String yearOld = "";
        String model = "";
        String modelOld = "";
        
        // append results to year and model arrays
        for (int i = 0; i < map.size(); i++)
        {
            year = map.get(i).get("VYEAR").toString();
            model = map.get(i).get("MODEL").toString();
            // check for duplicate year
            if (!year.matches(yearOld))
            {
                yearList[i] = year;
            }
            yearOld = year;
            // check for duplicate model
            if (!model.matches(modelOld))
            {
                modelList[i] = model;
            }
            modelOld = model;
        }
        // set results arrays
        this.yearList = yearList;
        this.modelList = modelList;
    }// end selectModelYears method
    
    /**
     * getCustomerVehicle method returns customer vehicles
     * @return array of vehicles
     */
    public String[] getCustomerVehicle() {
        return customerVehicle;
    }// end getCustomerVehicle method
    
    /**
     * getVehicleYears method returns vehicle years
     * @return array of years
     */
    public String[] getVehicleYears() {
        return yearList;
    }// end getVehicleYears method

    /**
     * getVehicleModels method returns vehicle models
     * @return array of models
     */
    public String[] getVehicleModels() {
        return modelList;
    }// end getVehicleModels method
    
    /**
     * getDataModel method returns data table models
     * @return data table models
     */
    public DefaultTableModel getDataModel() {
        return dataModel;
    }// end getDataModel method
   
}//end class QueryData
