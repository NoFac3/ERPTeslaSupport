package Account;

import java.awt.Rectangle;

/**
 * LayoutPosition.java - class used for setting the dimensions and position of 
 * the internal frames
 * CSIS 643 - D01
 * @author Kore Woody
 */
public class LayoutPosition 
{
    public int x,y,w,h;
    public Rectangle full,full1,full2;
    public Rectangle left;
    public Rectangle ul,ml,ll,ulBox,llBoxFull,mlBox,llBox;
    public Rectangle middle;
    public Rectangle um,mm,lm,umBox,lmBoxFull,mmBox,lmBox;
    public Rectangle lllmBox,lllmBox1,lllmBox2,lllmBox3;
    public Rectangle right,rightSmall,rightFull;
    public Rectangle ur,mr,lr,urBox,lrBoxFull,mrBox,lrBox;
    public int a = 1;
    
    /**
     * setRectangleDefault sets the default position for the internal frames
     * @param width desktop pane width
     * @param height desktop pane height
     */
    public void setRectangleDefault(int width, int height)
    {
        full = new Rectangle(0,0,width,height);
        full1 = new Rectangle(0,0,1600,1060);
        full2 = new Rectangle(0,0,1300,1060);

        // set LEFT positions
        left = new Rectangle(0,0,(int)(width*0.1319261214),full.height);
        ul = new Rectangle(left.x,left.y,left.width,(int)(left.height*0.2455795678));
        ml = new Rectangle(left.x, ul.height + 5,left.width,(left.height - ul.height)/2);
        ll = new Rectangle(left.x, ml.y + ml.height + 5,left.width,(left.height - ul.height)/2);
        ulBox = new Rectangle(ul.x,ul.y,ul.width,ul.height);
        llBoxFull = new Rectangle(ml.x,ml.y,ml.width,(ml.height * 2)+5);
        mlBox = new Rectangle(ml.x,ml.y,ml.width,ml.height);
        llBox = new Rectangle(ll.x,ll.y,ll.width,ll.height);

        // set MIDDLE positions
        middle = new Rectangle(left.width + 5,0,(int)(width*0.4340369393),full.height);
        um = new Rectangle(middle.x,middle.y,middle.width,(int)(middle.height*0.2455795678));
        mm = new Rectangle(middle.x, um.height + 5,middle.width,(middle.height - um.height)/2);
        lm = new Rectangle(middle.x, mm.y + mm.height + 5,middle.width,(middle.height - um.height)/2);
        umBox = new Rectangle(um.x,um.y,um.width,um.height);
        lmBoxFull = new Rectangle(mm.x,mm.y,mm.width,(mm.height*2)+5);
        mmBox = new Rectangle(mm.x,mm.y,mm.width,mm.height);
        lmBox = new Rectangle(lm.x,lm.y,lm.width,lm.height);
        lllmBox = new Rectangle(ml.x,ml.y,ml.width + 5 + mm.width,(mm.height*2)+5);
        lllmBox1 = new Rectangle(ml.x,ml.y,ml.width + 5 + mm.width,mm.height);
        lllmBox2 = new Rectangle(ml.x,ll.y,ml.width + 5 + mm.width,lm.height);
        lllmBox3 = new Rectangle(ml.x,ml.y,ml.width + 5 + mm.width,(mm.height*2)+5);

        //set RIGHT positions
        right = new Rectangle(middle.x + middle.width + 5,0,middle.width,full.height);
        rightSmall = new Rectangle(middle.x + middle.width + 5,0,left.width,full.height);
        rightFull = new Rectangle(right.x,right.y,right.width, right.height+10);
        ur = new Rectangle(right.x,right.y,right.width,(int)(right.height*0.2455795678));
        mr = new Rectangle(right.x, ur.height + 5,right.width,(right.height - ur.height)/2);
        lr = new Rectangle(right.x, mr.y + mr.height + 5,right.width,(right.height - ur.height)/2);
        urBox = new Rectangle(ur.x,ur.y,ur.width,ur.height);
        lrBoxFull = new Rectangle(mr.x,mr.y,mr.width,(mr.height*2)+5);
        mrBox = new Rectangle(mr.x,mr.y,mr.width,mr.height);
        lrBox = new Rectangle(lr.x,lr.y,lr.width,lr.height);
    }// end setRectangleDefault method

    /**
     * setSizePosition method sets the size and position of the given frame
     * @param frame     internal frame name
     * @param account   user type
     * @param a         number of frame
     * @return Rectangle dimensions and location of frame
     */
    public Rectangle setSizePosition(String frame, String account, int a)
     {
         // check for frame name then set by default rectangle
         if (frame.matches("Calendar"))
         {
             return ulBox;
         }
         if (frame.matches("Messages"))
         {
             return umBox;
         }
         if (frame.matches("Admin"))
         {
             return mmBox;
         }
         if (frame.matches("Manager"))
         {
             return llBoxFull;
         }
         if (frame.matches("Engineer"))
         {
             return rightFull;
         }
         if (frame.matches("Analyst"))
         {
             // check for analyst frame type
             if (account.matches("Analyst"))
             {
                 // check for frame number
                 if (a==1)
                 {
                     this.a = 2;
                     return lllmBox1;
                 }
                 if (a==2)
                 {
                     this.a = 3;
                     return lllmBox2;
                 }
                 if (a==3)
                 {
                     return rightFull;
                 }
                 //a = a + 1;
                 //this.a = a;
             }
             else // default if user is not analyst
             {
                return umBox;
             }
         }
         if (frame.matches("TechSupport"))
         {
             // check for tech account type
             if (account.matches("Tech"))
             {
                 return lllmBox;
             }
             if (account.matches("Engineer"))
             {
                 return umBox;
             }
             if (account.matches("Manager"))
             {
                 return lmBoxFull;
             }
             else // tech default
             {
                return lmBox;
             }
         }
         else
         {
             return null;
         }
         
     }// end setSizePosition method   
    
}// end class Layout position
