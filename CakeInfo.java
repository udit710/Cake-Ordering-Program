import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CakeInfo {
private String [][] flavoursInfo;
    private float[][] priceInfo;
    private static final String flavoursInfoFile = "data/flavours.txt";
    private static final String priceInfoFile = "data/price.txt";

    
    public CakeInfo() throws IOException {
        flavoursInfo = new String[9][10];
        priceInfo = new float[3][3];

        //load flavour information to flavoursInfo
        loadFlavours(flavoursInfoFile);
        //loading price information to priceInfo
        loadPriceInfo(priceInfoFile);
    }

    public void loadFlavours(String flavoursInfoFile) throws IOException {
        // Task 2.1: WRITE YOUR CODE HERE

            File flavours = new File(flavoursInfoFile);
            Scanner readFlavour = new Scanner(flavours);
            String t1 = "";
            String t2 = "";
            

                for (int i = 0; i < 9; i++) {
                    t1 = readFlavour.nextLine();
                    String [] arr = t1.split(":");
                    t1 = arr[0];
                    String [] arr2 = arr[1].split(",");
                    int x = arr2.length;
                    for (int j = 0; j < x; j++) {
                        t2 = arr2[j];
                        flavoursInfo[i][j] = t2;
                    }
                }
            
            readFlavour.close();
    }

    public void loadPriceInfo(String priceInfoFile) throws IOException {
        // Task 2.2: WRITE YOUR CODE HERE
        
            File price = new File(priceInfoFile);
            Scanner readPrice = new Scanner(price);

            String t1 = "";
            String t2 = "";
            

                for (int i = 0; i < 3; i++) {
                    t1 = readPrice.nextLine();
                    String [] arr = t1.split(":");
                    t1 = arr[0];
                    String [] arr2 = arr[1].split(",");
                    for (int j = 0; j < 3; j++) {
                        t2 = arr2[j];
                        priceInfo[i][j] = Float.parseFloat(t2);
                }
            }
        
            readPrice.close();
    }

    /*returns the cake price of a cake*/
    public float calCakePrice(Cake cake) { 
    // Task 2.3: WRITE YOUR CODE HERE
        int layer = cake.getNoOfLayers()-1;
        float price = 0 ;
        switch (getFlavourCategoryIndex(cake)) {
            case 1:
            price = priceInfo[layer][0];
            break;
            case 2:
            price = priceInfo[layer][0];
            break;
            case 3:
            price = priceInfo[layer][1];
            break;
            case 4:
            price = priceInfo[layer][1];
            break;
            case 5:
            price = priceInfo[layer][0];
            break;
            case 6:
            price = priceInfo[layer][2];
            break;
            case 7:
            price = priceInfo[layer][1];
            break;
            case 8:
            price = priceInfo[layer][2];
            break;
            case 9:
            price = priceInfo[layer][2];
            break;
        
            default:
            price = priceInfo[layer][2];
            break;
        }
        cake.setPrice(price);
        return price;
    }
    
    /*returns the flavour category of the specific cake flavour
    */
    public int getFlavourCategoryIndex(Cake cake){ 
                                    
        int flavourCatIndex = -1; 
	    //specific cake flavour i.e. mint chocolate, red velvet etc..
        String flavour = cake.getFlavour(); 
        for (int i = 0; i < flavoursInfo.length; ++i) {
            int j = 0;

            //loop until the end and until index is still not found
            while (j < flavoursInfo[i].length && flavourCatIndex == -1) {
                if (flavoursInfo[i][j] != null && !flavoursInfo[i][j].isEmpty()) {

		    //found the flavour within each flavour categories (1-9)
                    if (flavour.equals(flavoursInfo[i][j])) {
                        flavourCatIndex = i + 1;
                    }
                }
                j += 1;
            }
        }
        
	    //if no flavour is found, flavourCatIndex will remain as -1 when it was initialised
        return flavourCatIndex; 
    }

    


}