package Account;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * TreeBuilder.java - 
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class TreeBuilder 
{
    DefaultMutableTreeNode treeNode1, treeNode2, treeNode3, treeNode4, treeNode;
    String root = "Guides";
    String[] mainNodes = {"Connection Texts","Emergency","Log File", "Model S", "Model X","Model 3"};
    
    /**
     *
     * @param k
     */
    public TreeBuilder(String k){}
    
    /**
     *
     */
    public TreeBuilder()
    {
        treeNode1 = new DefaultMutableTreeNode("Guides!!");
        treeNode2 = new DefaultMutableTreeNode("Connection Tests");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("910 Connection Test");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Emergency");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("emergency response guide");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("V2 Supercharger First Responder Guide en");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Roadside Assistance");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roaside Assistance   US   EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("roadside assistance v2 5");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("owners manual rhd");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Log File");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Log file instructions");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Model S");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Model S Owners Manual Touchscreen");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Model S Quick Guide");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("wall connector install guide japan 0");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Model S New Vehicle Limited Warranty");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2016 Models S Emergency Responders Guide en");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("model s 2014 emergency response guide");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("model s owners manual eu english 5.9");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2015 Dual Motor Model S Emergency Response Guide");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Model X");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2016 Model X Emergency Response Guide");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Model 3");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2017 Model 3 Emergency Response Guide en");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Model Y");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Model Y Emergency Response Guide en");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Roadster");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 Addendum   CA EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2008 Owner s Manual   US   EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2010 Warranty   US   EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Owner s Manual   DE");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Owner s Manual   ES");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Owner s Manual   FR");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Owner s Manual   UK   EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Owner s Manual   US   EN");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 addendum   Can En");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("roadster owners handbook");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster Emergency Responder Guide");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("20130130 Tesla Roadster2010 2013 QRG");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("20130130 Tesla Roadster20082009 QRG");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Roadster Roadside Assistance 2.5 (web ready)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 1 2 S2 Recovery Guide July 09");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Japanese Roadster Owner s Manual   English");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("eng jp 2.5owners manual   web version   ja en 0");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("1.0");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("2008 WarrantyOnlineFinal");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("24701291 Tesla Owners Manual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("2008 ChargingYourVehicleOnlineFinal");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("2008 RoadsideAssistanceOnlineFinal");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("2008 Roadster CompleteManual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("2008 Roadster TouchScreenOnlineFinal");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2.0");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 2 2S Quick Reference");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 2 2S Roadside Assistance Guide");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 2 2S Touch Screen Users Manual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 2 2S Warranty Guide");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster Mobile Connector Users Manual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("44270217 Tesla Roadster 2 2S Owners Manual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Tesla Roadster 2 2S Mobile Connector Users Manual");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2.5");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 Warranty");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 Owners Manual");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 Quick Reference");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Roadster 2.5 Touchscreen Users Manual");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Firmware");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Firmware Updates");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("firmware release notes   2012 01 12");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Other");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("warranty v2 5");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Warranty European Pre Owned Vehicles 19.7.2015");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Warranty July 2018");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("tesla new vehicle limited warranty en gb");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("tesla new vehicle limited warranty en us");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("29 1 20 tesla new vehicle limited warranty en us");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("JVC NX KD5000 Radio");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1475 003A");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1475 005A");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1627 001A");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1627 002A");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1640 001A");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("LVT1714 001A");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        
        this.treeNode = treeNode1;

    }

    /**
     *
     * @return
     */
    public String[] getMainNodes() {return mainNodes;}

    /**
     *
     * @return
     */
    public DefaultMutableTreeNode getModel() {return treeNode;}
    
    /**
     *
     */
    public void setTree()
    {
        
    }
}
