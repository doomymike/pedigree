/*Pedigree Generator - Menu GUI
    MenuPanel.java
    Custom JPanel for IntroductionFrame and MenuFrame usage
    Author: Brian Zhang
    Course: ICS4UE
    Date: 12/06/18
 */

//Imports
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{

    //Base Constructor
    public MenuPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    //Paint Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setDoubleBuffered(true);

        //Main Background
        g.fillRect(0,0,1000,650);

        //Animation
        Graphics2D render = (Graphics2D)g.create();
        render.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Dispose of render
        render.dispose();
    }
}

