import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProcessOrder {
    //Created an ArrayList to make and store orders as we do not know how many cakes will be ordered
    static ArrayList <Cake> cakeList = new ArrayList<Cake>();
    static ArrayList <String> cakeMessage = new ArrayList<String>();
    private static String yesOrNo="";
    private static String pastry="";
    public static float writePrice=0;
    private static String express="";
    private static final String flavoursInfoFile = "data/flavours.txt";
    //Menu for users to navigate through the program
    public static void printMenu() throws IOException{
        System.out.println("Click 'A' to start a new cake order.");
        System.out.println("Click 'V' to view shopping cart.");
        System.out.println("Click 'E' to edit cake info.");
        System.out.println("Click 'R' to remove a cake.");
        System.out.println("Click 'C' to checkout.");
        System.out.println("Click 'Q' to quit.");
    }

    /**
     * Method to execute all the options available to the users in printMenu().
     * 
     * Used switch statement instead of if-else as we need a default statement as well
     * 
     * @param userIn: options:
     *                  - A: Adds a new cake
     *                  - V: View the cart of added cakes
     *                  - E: Edit any cakes from the cart
     *                  - R: Remove any cakes from the cart
     *                  - C: Checkout
     *                  - Q: Quit program
     * @throws IOException
     */
    public static void executeMenu(char userIn) throws IOException {
        switch(userIn){
            case 'A' :
            /**
             * Created new file object in order to print the contents of "data/flavours.txt"
             */
            File flavours = new File(flavoursInfoFile);
            Scanner readFlavour = new Scanner(flavours);
            String t1 = "";
            //Loop to print each line of the file.
            for (int i = 0; i < 9; i++) {
                t1 = readFlavour.nextLine();
                System.out.println(t1);
            }
            System.out.println();
                addCake();
                System.out.println("Cake added!");
                System.out.println();
                break;
            case 'V' :
                viewCart();
                System.out.println();
                break;
            case 'E' :
                viewCart();
                //Condition to check number of cakes
                if (cakeList.size()<1) {
                    System.out.println("There is no cake to be edited");
                }else{
                    System.out.println("Select cake number to edit: ");
                    String _cakeToEdit = ScannerObj.cakeInput.nextLine();
                    //Used checkInt() method to validate the user input
                    boolean checker=checkInt(_cakeToEdit,0,cakeList.size()+1,"Select cake number to edit: ");
                    //If input is invalid, check input again till it is valid
                    while (checker==false) {
                        _cakeToEdit = ScannerObj.cakeInput.nextLine();
                        checker=checkInt(_cakeToEdit,0,cakeList.size()+1,"Select cake number to edit: ");
                    }
                    int cakeToEdit=Integer.parseInt(_cakeToEdit);                   
                    editCake(cakeToEdit-1);
                }
            break;
            case 'R' :
                viewCart();
                //Condition to check number of cakes
                if (cakeList.size()<1) {
                    System.out.println("There is no cake to be removed");
                }else{
                    System.out.println("Select cake number to remove: ");
                    String _cakeToRemove = ScannerObj.cakeInput.nextLine();
                    //Used checkInt() method to validate the user input
                    boolean checker=checkInt(_cakeToRemove,0,cakeList.size()+1,"Select cake number to remove: ");
                    //If input is invalid, check input again till it is valid
                    while (checker==false) {
                        _cakeToRemove = ScannerObj.cakeInput.nextLine();
                        checker=checkInt(_cakeToRemove,0,cakeList.size()+1,"Select cake number to remove: ");
                    }
                    int cakeToRemove=Integer.parseInt(_cakeToRemove);
                    removeCake(cakeToRemove-1);
                }
            break;
            case 'C' :
                if (cakeList.size()<1) {
                    System.out.println("There is no cake to checkout!");
                }else{
                    checkoutCake();
                    System.out.println();
                }
            break;
            default :
            /**
             * Default set for any input apart from available options
             */
            System.out.println("Invalid input!");
            System.out.println("Please choose from the given options!");
        }
    }
    //Method to add cake to ArrayList
    public static void addCake() throws IOException {
        //Created new cake object
        Cake cake;
        CakeInfo cakeInfo = new CakeInfo();
        //Prompt user to enter flavour of choice
        System.out.println("Enter your choice of flavour: ");
        String flavour = ScannerObj.cakeInput.nextLine();
        while (flavour.equals("")) {
            System.out.println("Invalid input!");
            System.out.println("Enter your choice of flavour: ");
            flavour = ScannerObj.cakeInput.nextLine();
        }
        flavour=flavour.toLowerCase();
        System.out.println();
        //Prompt user to enter number of layers
        System.out.println("Enter number of layers(1-3): ");
        //Converted the String input for number of layers to Int
        //Did not take Int input directly as that may cause IOException
        String _noOfLayers = ScannerObj.cakeInput.nextLine();
        //Used checkInt() method to validate the user input
        boolean checker=checkInt(_noOfLayers,0,4,"Enter number of layers(1-3): ");
        //If input is invalid, check input again till it is valid
        while (checker==false) {
            _noOfLayers = ScannerObj.cakeInput.nextLine();
            checker=checkInt(_noOfLayers,0,4,"Enter number of layers(1-3): ");
        }
        int noOfLayers=Integer.parseInt(_noOfLayers);
        System.out.println();
        //Prompt user to enter any dietary requirements
        System.out.println("Enter any dietary requirements(if none, enter 'none'):");
        String dietaryReq = ScannerObj.cakeInput.nextLine();
        while (dietaryReq.equals("")) {
            System.out.println("Invalid input!");
            System.out.println("Enter any dietary requirements(if none, enter 'none'):");
            dietaryReq = ScannerObj.cakeInput.nextLine();
        }
        /*
         * Extra feature 1:
         * User can add a custom message of upto 20 characters to each cake for an
         * additional $5
         */
        System.out.println("Would you like to have a custom message written on this cake?(Additional cost $5.00)");
        System.out.println("Enter 'yes' or 'no': ");
        String writeOn=ScannerObj.cakeInput.nextLine();
        writeOn=writeOn.toLowerCase();
        String writing="";
        //Check if user wants custom message or not
        if(writeOn.equals("yes")){
            //Check if custom message has maximum 20 characters
            System.out.println("Enter message(20 characters max): ");
            writing=ScannerObj.cakeInput.nextLine();
            while ((writing.length()>20)||(writing.length()<1)) {
                System.out.println("20 characters max!");
                System.out.println("Enter again: ");
                writing=ScannerObj.cakeInput.nextLine();
            }
            writePrice+=5;
        }else{
            writeOn="no";
        }
        //Assigned the user input to the cake
        cake = new Cake(flavour, noOfLayers, dietaryReq);
        float price;
        //Calculated the cake price
        price=cakeInfo.calCakePrice(cake);
        if (writeOn.equals("yes")) {
            price+=5;
        }else{}
        cake.setPrice(price);
        System.out.println(cake.toString());
        if (writeOn.equals("yes")) {
            System.out.println("Message on cake: "+writing);
        }else{}
        //Ask user for assurance before continuing
        System.out.println("In order to continue, please enter 'yes', else, enter 'no'");
        yesOrNo = ScannerObj.cakeInput.nextLine();
        yesOrNo=yesOrNo.toLowerCase();
        if (yesOrNo.equals("yes")) {
            cakeList.add(cake);
            cakeMessage.add(writing);
            System.out.println("Cake added!");
        } else {}
    }
    //Method to view a list of added cakes
    public static void viewCart() throws IOException {
        float totalPrice=0;
        //Loop to print added cakes and their respective custom messages(if any)
        for(int i=0; i<cakeList.size();++i){
            System.out.println((i+1)+") "+ cakeList.get((i)).toString());
            totalPrice+=cakeList.get((i)).getPrice();
            String tempM=cakeMessage.get((i));
            if (!tempM.equals("")) {
                System.out.println("Message on cake: "+ tempM);
            }else{}
        }
        System.out.printf("Total cost: $%.2f\n", totalPrice);
    }
    //Method to edit added cakes
    public static void editCake(int cakeToEdit)throws IOException {
        /**
         * Created 2 new Cake objects
         * First one is assigned to the cake that is to be edited
         */
        Cake cake;
        Cake cake2;
        cake=cakeList.get(cakeToEdit);
        addCake();
        //Second one is assigned to the newly added cake
        cake2=cakeList.get(cakeList.size()-1);
        /**
         * Created separate Strings for each attribute of the cake
         * Used the getter methods from Cake class to get the attributes of
         * the new cake
         */
        String newFlavour=cake2.getFlavour();
        int newLayers=cake2.getNoOfLayers();
        String newDietaryReq=cake2.getDietaryReq();
        float newPrice=cake2.getPrice();
        String newMessage=cakeMessage.get(cakeList.size()-1);
        //Used setter methods of the Cake class to assign these attributes to the first cake
        cake.setFlavour(newFlavour);
        cake.setNoOfLayers(newLayers);
        cake.setDietaryReq(newDietaryReq);
        cake.setPrice(newPrice);
        cakeMessage.set(cakeToEdit, newMessage);
        //Removed the new cake
        cakeList.remove(cakeList.size()-1);
    }
    //Method to remove any added cakes
    public static void removeCake(int cakeToRemove) throws IOException {
        //Asking user for re-assurance before removing cake
        System.out.println("Are you sure you want to remove " + cakeList.get(cakeToRemove).toString() + "?");
        System.out.println("Enter 'yes' or 'no': ");
        String yesOrNo = ScannerObj.cakeInput.nextLine();
        yesOrNo=yesOrNo.toLowerCase();
        if (yesOrNo.equals("yes")) {
            cakeList.remove(cakeToRemove);
            System.out.println("Cake deleted!");            
        }else{}
        viewCart();
    }
    //Method to checkout
    public static void checkoutCake() throws IOException {
        float finalPrice=0;
        float totalPrice=0;
        for(int i=0; i<cakeList.size();++i){
            totalPrice+=cakeList.get((i)).getPrice();
        }
        //Showing the user a list of added cakes
        System.out.println("Your orders are: ");
        viewCart();
        //Ask user if they want additional candle
        System.out.println("If you wish to add a candle for $10.50, please enter yes,else, enter no");
        yesOrNo = ScannerObj.cakeInput.nextLine();
        yesOrNo=yesOrNo.toLowerCase();
        //Used if-else conditional statements to change the total price as per user input for adding candle
        if(yesOrNo.equals("yes")){
            finalPrice=totalPrice+10.50f;
            System.out.println("Candle added!");
        }else{
            yesOrNo="no";
            finalPrice=totalPrice;
        }
        System.out.println();
        //Check if price is above $199 for implementing getPastry feature
        if (totalPrice > 199) {
            pastry=getPastry();
        }else{}
        //Prompt user for delivery address
        System.out.println("Enter delivery address: ");
        String userAddress = ScannerObj.cakeInput.nextLine();
        while (userAddress.length()<10){
            System.out.println("Please enter valid address: ");
            userAddress = ScannerObj.cakeInput.nextLine();
        }
        System.out.println();
        /*
         * Extra feature 2:
         * Users can get express delivery(within 3 days) for their cake at an additional cost of $50
         */
        System.out.println("Would you like an express service for your order for $50?(Delivery within 3 days)");
        System.out.println("Note: Standard delivery times are 7-14 days.");
        System.out.println("Enter 'yes' or 'no': ");
        express = ScannerObj.cakeInput.nextLine();
        express = express.toLowerCase();
        //Used if-else conditional statements to change the total price as per user input for express delivery
        if (express.equals("yes")) {
            System.out.println("Awesome, you skipped the long queues with Cakereations express!");
            finalPrice+=50;
        }else{
            express = "no";
        }
        System.out.println();
        System.out.println("Your final order details are: ");
        viewCart();
        if (totalPrice > 199) {
            System.out.println("Complimentary pastry: "+ pastry);
        }else{}
        System.out.println("Additional candle: "+yesOrNo);
        System.out.printf("Final price: $%.2f\n",finalPrice);
        System.out.println("---------");
        System.out.println("Delivery address: " +userAddress );
        System.out.println("Express service: "+ express);
        System.out.println("---------");

        //Prompt user for confirmation to checkout
        System.out.println("In order to confirm order, enter 'confirm' ");
        String confirmation = ScannerObj.cakeInput.nextLine();
        confirmation=confirmation.toLowerCase();
        if (confirmation.equals("confirm")) {
            generateInvoice();
            cakeList.clear();
            cakeMessage.clear();
            System.out.println();
            System.out.println("Welcome to Cakereations!");
            System.out.println();

        }else{
            if(yesOrNo.equals("yes")){
                finalPrice-=10.50f;
            }else{}
        }
    }
    //Method used to generate a new invoice for each order on checkout
    public static void generateInvoice() throws IOException {
        float totalPrice=0;
        float finalPrice=0;
        for(int i=0; i<cakeList.size();++i){
            System.out.println((i+1)+") "+ cakeList.get((i)).toString());
            totalPrice+=cakeList.get((i)).getPrice();
        }
        if(yesOrNo.equals("yes")){
            finalPrice=totalPrice+10.50f;
        }else{
            finalPrice=totalPrice;
        }
        if (express.equals("yes")) {
            finalPrice+=50;
        }else{}
        //Created two separate strings in order to format the name of the file
        String _fileName;
        String _fileName2;
        String z;
        //Created object to get current time
        LocalDateTime currentTime = LocalDateTime.now();
        //Formatted the time as per requirements
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss");
        DateTimeFormatter timeFormat2 = DateTimeFormatter.ofPattern("yyyy");
        //Stored the formatted time to relative strings
        _fileName = currentTime.format(timeFormat);
        _fileName2 = currentTime.format(timeFormat2);
        //Getting the timezone and storing as short abbreviation
        z = TimeZone.getTimeZone("Australia/Melbourne").getDisplayName(false,TimeZone.SHORT);
        //Replaced ":" with "." as required
        _fileName = _fileName.replaceAll(":", ".");

        //Created file object
        File file = new File(_fileName+" "+z+" "+_fileName2+".txt");
        //Created object to type into the file
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        //Invoice start
        pw.println("--------------------Invoice--------------------");
        for (int i = 0; i < cakeList.size(); i++) {
            pw.println("Cake "+(i+1)+": " + cakeList.get(i).toString());
            String tempM=cakeMessage.get((i));
            if (!tempM.equals("")) {
                pw.println("Message on cake: "+ tempM);
            }else{}
        }
        if (totalPrice > 199) {
            pw.println("Complimentary pastry: "+ pastry);
        }else{}
        pw.printf("Total Cost: $%.2f Additional candle: %s\n",totalPrice,yesOrNo);
        pw.println("Express delivery: "+ express);
        pw.printf("Final Price: $%.2f", finalPrice);
        pw.println();
        pw.println("--------------------End Invoice--------------------");
        //Used loop to print specific details about each cake
        for (int i = 0; i < cakeList.size(); i++) {
            pw.println();
            pw.println();
            pw.println();
            pw.println("--------------------Cake Order--------------------");
            pw.println();
            pw.println(cakeList.get(i).toString());
            String tempM=cakeMessage.get((i));
            //Checked if there is any message entered for that specific cakes
            if (!tempM.equals("")) {
                pw.println("Message on cake: "+ tempM);
            }else{}
            pw.println();
            pw.println("------------------------------------------------------");
        }
        pw.close();
        //Invoice end
    }
    /**
     * Extra feature 3:
     * Users get  a free pastry if their total cake price 
     * (excluding the price of the candle) is atleast $200
     */
    public static String getPastry() {
        //List of available flavours for pastry
        String[] pastryFlav={"chocolate","vanilla","banana","mango","red velvet","brownie"};
        System.out.println("Congratulations! You are now eligible for a free pastry!");
        System.out.println("Please choose a flavour for the pastry: ");
        for (int i = 0; i < pastryFlav.length; i++) {
            System.out.println((i+1)+") "+pastryFlav[i]);
        }
        String pastry= ScannerObj.cakeInput.nextLine();
        while (pastry.equals("")) {
            System.out.println("Invalid input!");
            System.out.println("Please choose a flavour for the pastry: ");
            pastry = ScannerObj.cakeInput.nextLine();
        }
        pastry=pastry.toLowerCase();
        boolean flavCheck=checkString(pastryFlav, pastry);
        while (flavCheck == false) {
            System.out.println("Enter valid flavour: ");
            pastry= ScannerObj.cakeInput.nextLine();
            pastry=pastry.toLowerCase();
            flavCheck=checkString(pastryFlav, pastry);
        }
        return pastry;
    }

    /* Method to check if the specified value
     * is present in the array or not
     * using Linear Search method
    */
    public static boolean checkString(String[] arr, String toCheckValue){
        boolean test = false;
        for (String element : arr) {
            if (element.equals(toCheckValue)) {
                test = true;
                break;
            }
        }
        return test;
    }

    /* 
     * Method to check if a String input can be parsed into an int
     * and check if the parsed int is in the required range
    */
    public static boolean checkInt(String string, int lower,int upper,String error){
        int num=0;
        //Check if the string is empty
        if (string.equals("")) {
            System.out.println("Invalid input!");
            System.out.println(error);
            return false;
        }

        //Check if string can be parsed
        try{
            num=Integer.parseInt(string);
        }catch(Exception e){
            System.out.println("Invalid input!");
            System.out.println(error);
            return false;
        }
    
        //Check if parsed int is in range
        if((num>=upper)||(num<=lower)){
            System.out.println("Invalid input!");
            System.out.println(error);
            return false;
        }
        //return true if all checks cleared
        return true;
    }
}
