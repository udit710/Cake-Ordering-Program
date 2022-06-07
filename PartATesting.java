import java.io.IOException;
import java.io.File;

public class PartATesting {
    public static void main(String args[]) throws IOException {

        float expectedP,actualP;
        boolean pass;
        CakeInfo cakeInfo = new CakeInfo();
        Cake cakeA;
	    Cake cakeB;
        Cake cakeC;
        Cake cakeD;
        Cake cakeE;
        Cake cakeF;
        Cake cakeG;
        Cake cakeH;
        Cake cakeI;

        System.out.println(new File(".").getAbsolutePath());
        cakeA = new Cake("hojicha", 3, "gluten-free");
        System.out.println("Test 1 - 3-layer hojicha gluten-free for $160.00");
        // the expected output value
        expectedP = 160.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeA);
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeB = new Cake("banana foster", 1, "none");
        System.out.println("Test 2 - 1-layer banana foster no dietary requirements for $40.00");
        // the expected output value
        expectedP = 40.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeB); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }
        cakeC = new Cake("opera", 2, "none");
        System.out.println("Test 3 - 2-layer opera no dietary requirements for $105.00");
        // the expected output value
        expectedP = 105.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeC); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class"+ actualP);
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeD = new Cake("espresso", 2, "vegan");
        System.out.println("Test 4 - 2-layer espresso vegan for $85.00");
        // the expected output value
        expectedP = 85.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeD); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeE = new Cake("plain chocolate", 1, "vegan");
        System.out.println("Test 5 - 1-layer plain chocolate vegan for $30.00");
        // the expected output value
        expectedP = 30.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeE); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        //test for getPrice() method
        System.out.println("Test 6 - getprice() method for cakeC");
        // the expected output value
        expectedP = 105.00f;
        // calling the getPrice() method
        actualP = cakeC.getPrice(); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for getPrice() in Cake class");
        }else{
            System.out.println("Failed this test for getPrice() in Cake class. Please check your code!");
        }
        //test for toString() method
        System.out.println("Test 7 - toString() method for cakeD");
        // the expected output value
        String expectedS = "Order: 2-layer espresso cake   Dietary requirements: vegan   Cake Price: $85.00";
        // calling the toString() method
        String actualS;
        actualS = cakeD.toString(); 
        pass = actualS.equals(expectedS);
        if (pass){
            System.out.println("Passed this test for toString() in Cake class");
        }else{
            System.out.println("Failed this test for toString() in Cake class. Please check your code!");
        }

        //Test for getFlavour() method
        System.out.println("Test 8 - toString() method for cakeD");
        // the expected output value
        expectedS = "opera";
        // calling the getFlavour() method
        actualS = cakeC.getFlavour(); 
        pass = actualS.equals(expectedS);
        if (pass){
            System.out.println("Passed this test for getFlavour() in Cake class");
        }else{
            System.out.println("Failed this test for getFlavout() in Cake class. Please check your code!");
        }

        cakeF = new Cake("plain chocolate", 2, "vegan");
        System.out.println("Test 9 - 2-layer plain chocolate vegan for $65.00");
        // the expected output value
        expectedP = 65.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeF); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeG = new Cake("jaffa", 3, "vegan");
        System.out.println("Test 10 - 3-layer jaffa vegan for $100.00");
        // the expected output value
        expectedP = 100.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeG); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeH = new Cake("caramel mango", 3, "gluten-free");
        System.out.println("Test 11 - 3-layer caramel mango gluten-free for $130.00");
        // the expected output value
        expectedP = 130.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeH); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

        cakeI = new Cake("plain durian", 1, "vegan");
        System.out.println("Test 12 - 1-layer plain chocolate vegan for $50.00");
        // the expected output value
        expectedP = 50.00f;
        // calling the calCakeInfo method
        actualP = cakeInfo.calCakePrice(cakeI); 
        pass = actualP == expectedP;
        if (pass){
            System.out.println("Passed this test for calCakePrice in CakeInfo class");
        }else{
            System.out.println("Failed this test for calCakePrice in CakeInfo class, the expected value is " + Float.toString(expectedP) + " but the actual value is "+Float.toString(actualP)+". Please check your code!");
        }

    }
}