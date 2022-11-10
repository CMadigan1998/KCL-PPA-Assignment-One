import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * 
 * @author Michael Kölling and Josh Murphy
 * @version 1.0
 * 
 * Modified by Charlie Madigan K19019003
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private Portfolio portfolio;
    private Property currentProperty;
    private int currentPropertyIndex, numberOfPropertiesViewed, totalViewedPrice;
    
    /**
     * Create a PropertyViewer and display its GUI on screen.
     * Also 
     */
    public PropertyViewer()
    {
        currentPropertyIndex = 0;
        numberOfPropertiesViewed = 1;
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        currentProperty = portfolio.getProperty(currentPropertyIndex);
        gui.showProperty(currentProperty);
        gui.showFavourite(currentProperty);
        gui.showID(currentProperty);
        totalViewedPrice = currentProperty.getPrice();
    }

    /**
     *This function displays the details of the next property in the window
     *This is done but first increasing the property stored in a local variable
     *then checking to see if the index is in range of thre array list
     *if it is, then the information is displayed, 
     *if not, an error message is written to the console and the user is returned to the previous property  viewed.
     */
    public void nextProperty() {
        currentPropertyIndex ++;
        int totalProperties = portfolio.numberOfProperties();
        if (currentPropertyIndex >= totalProperties) {
            System.out.println("!!ERROR!! - Property index is out of range. Returning to the previously viewed property.");
            currentPropertyIndex--;
        } else {
            currentProperty = portfolio.getProperty(currentPropertyIndex);
            gui.showProperty(currentProperty);
            gui.showFavourite(currentProperty);
            gui.showID(currentProperty);
            numberOfPropertiesViewed++;
            totalViewedPrice += currentProperty.getPrice();
        }
        
    }

    /**
     *This function displays the details of the previously property in the window
     *This is done but first increasing the property stored in a local variable
     *then checking to see if the index is in range of thre array list
     *if it is, then the information is displayed, 
     *if not, an error message is written to the console and the user is returned to the previous property  viewed.
     */
    public void previousProperty()
    {
        currentPropertyIndex --;
        if (currentPropertyIndex < 0) {
            System.out.println("!!ERROR!! - Property index is out of range. Returning to the previously viewed property.");
            currentPropertyIndex++;
        } else {
            currentProperty = portfolio.getProperty(currentPropertyIndex);
            gui.showProperty(currentProperty);
            gui.showFavourite(currentProperty);
            gui.showID(currentProperty);
            numberOfPropertiesViewed++;
            totalViewedPrice += currentProperty.getPrice();
        }
    }

    /**
     * This function marks the property as either a favourite or not
     * After this has been done, a tag is shown at the bottom of the window if the property is marked as a favourite
     */
    public void toggleFavourite()
    {
        currentProperty.toggleFavourite();
        gui.showFavourite(currentProperty);
    }
    

    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = currentProperty.getLatitude();
       double longitude = currentProperty.getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * This method acts as a getter for the number of properties viewed field
     */
    public int getNumberOfPropertiesViewed()
    {
        return numberOfPropertiesViewed;
    }
    
    /**
     * This method calculates the average price of all viewed properties and then returns that value
     * to the method that called it
     */
    public int averagePropertyPrice()
    {
        int averagePrice = totalViewedPrice / numberOfPropertiesViewed; 
        return averagePrice;
    }
}
