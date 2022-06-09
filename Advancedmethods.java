import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Advancedmethods {
    //Created an ArrayList to make and store orders as we do not know how many cakes will be ordered
    static ArrayList <Cake> cakeList = new ArrayList<Cake>();
    static ArrayList <String> cakeMessage = new ArrayList<String>();
    private static String yesOrNo="";
    private static String pastry="";
    public static float writePrice=0;
    private static String express="";
    //Menu for users to navigate through the program
    public static void printMenu() throws IOException{
        System.out.println("Click 'A' to start a new cake order.");
        System.out.println("Click 'V' to view shopping cart.");
        System.out.println("Click 'E' to edit cake info.");
        System.out.println("Click 'R' to remove a cake.");
        System.out.println("Click 'C' to checkout.");
        System.out.println("Click 'Q' to quit.");
    }
    //Method to execute all the options available to the users in printMenu()
    public static void executeMenu(char userIn) throws IOException {
        switch(userIn){
            case 'A' :
                System.out.println("Chocolate 1:plain chocolate,double chocolate,jaffa,mint chocolate,red velvet\nVanilla 2:plain vanilla,vanilla fondant,vanilla buttercream,vanilla mascarpone,vanilla citrus\nCaramel 3:dulce de leche,salted caramel,caramel mango\nBanana 4:plain banana,banana foster,banana cream cheese\nLemon 5:plain lemon,lemon yoghurt,lemon coconut\nTea 6:matcha green tea,hojicha\nCoffee 7:espresso,mocha,coffee walnut\nExotic 8:plain durian,durian extra,jackfruit kiwano\nSpecial 9:opera,pavlova,mille crepe,strawberry shortcake,mixed berry cheesecake\n");
                addCake();
                break;
            case 'V' :
                viewCart();
                break;
            case 'E' :
                viewCart();
                //Condition to check number of cakes
                if (cakeList.size()<1) {
                    System.out.println("There is no cake to be edited");
                }else{
                    System.out.println("Select cake number to edit: ");
                    String _cakeToEdit = ScannerObj.cakeInput.nextLine();
                    checkInt(_cakeToEdit);
                    while ((checkInt(_cakeToEdit) == false)||(_cakeToEdit.length()!=1)) {
                        System.out.println("Enter valid number!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToEdit = ScannerObj.cakeInput.nextLine();
                    }
                    int cakeToEdit=Integer.parseInt(_cakeToEdit);
                    while (cakeList.size()<(cakeToEdit)) {
                        System.out.println("Cake not found!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToEdit = ScannerObj.cakeInput.nextLine();
                    checkInt(_cakeToEdit);
                    while ((checkInt(_cakeToEdit) == false)||(_cakeToEdit.length()!=1)) {
                        System.out.println("Enter valid number!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToEdit = ScannerObj.cakeInput.nextLine();
                    }
                    cakeToEdit=Integer.parseInt(_cakeToEdit);
                    }
                    editCake(cakeToEdit-1);
                }
            break;
            case 'R' :
                viewCart();
                //Condition to check number of cakes
                if (cakeList.size()<1) {
                    System.out.println("There is no cake to be removed");
                }else{
                    System.out.println("Select cake number to edit: ");
                    String _cakeToRemove = ScannerObj.cakeInput.nextLine();
                    checkInt(_cakeToRemove);
                    while ((checkInt(_cakeToRemove) == false)||(_cakeToRemove.length()!=1)) {
                        System.out.println("Enter valid number!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToRemove = ScannerObj.cakeInput.nextLine();
                    }
                    int cakeToRemove=Integer.parseInt(_cakeToRemove);
                    while (cakeList.size()<(cakeToRemove)) {
                        System.out.println("Cake not found!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToRemove = ScannerObj.cakeInput.nextLine();
                    checkInt(_cakeToRemove);
                    while ((checkInt(_cakeToRemove) == false)||(_cakeToRemove.length()!=1)) {
                        System.out.println("Enter valid number!");
                        System.out.println("Select cake number to edit: ");
                        _cakeToRemove = ScannerObj.cakeInput.nextLine();
                    }
                    cakeToRemove=Integer.parseInt(_cakeToRemove);
                    }
                    removeCake(cakeToRemove-1);
                }
            break;
            case 'C' :
                checkoutCake();
            break;
            default :
            System.out.println("Invalid input!");
            System.out.println("Please choose the above given options!");
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
        flavour=flavour.toLowerCase();
        System.out.println();
        //Prompt user to enter number of layers
        System.out.println("Enter number of layers(1-3): ");
        //Converted the String input for number of layers to Int
        //Did not take Int input directly as that may cause IOException
        String _noOfLayers = ScannerObj.cakeInput.nextLine();
        checkInt(_noOfLayers);
        while ((checkInt(_noOfLayers) == false)||(_noOfLayers.length()!=1)) {
            System.out.println("Enter valid number!");
            System.out.println("Enter number of layers(1-3): ");
            _noOfLayers = ScannerObj.cakeInput.nextLine();
        }
        int noOfLayers = Integer.parseInt(_noOfLayers);
        //Loop used to check if the number of layers entered is appropriate according to the given data
        while((noOfLayers>3)||(noOfLayers<1)){
            if (noOfLayers>3) {
                System.out.println("You cannot have more than 3 layers");
            } else if(noOfLayers<0){
                System.out.println("You must have atleast 1 layer");
            }
            System.out.println("Enter number of layers(1-3): ");
            _noOfLayers = ScannerObj.cakeInput.nextLine();
            checkInt(_noOfLayers);
            while ((checkInt(_noOfLayers) == false)||(_noOfLayers.length()!=1)) {
                System.out.println("Enter valid number!");
                System.out.println("Enter number of layers(1-3): ");
                _noOfLayers = ScannerObj.cakeInput.nextLine();
            }
            noOfLayers = Integer.parseInt(_noOfLayers);
        }
        System.out.println();
        //Prompt user to enter any dietary requirements
        System.out.println("Enter any dietary requirements(if none, enter 'none'):");
        String dietaryReq = ScannerObj.cakeInput.nextLine();
        System.out.println("Would you like to have a custom message written on this cake?(Additional cost $5.00)");
        String writeOn=ScannerObj.cakeInput.nextLine();
        writeOn=writeOn.toLowerCase();
        String writing="";
        if(writeOn.equals("yes")){
            System.out.println("Enter message(20 characters max): ");
            writing=ScannerObj.cakeInput.nextLine();
            while (writing.length()>20) {
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
        } else {}
    }
    //Method to view a list of added cakes
    public static void viewCart() throws IOException {
        float totalPrice=0;
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
        //Removed the cake to be edited
        cakeList.remove(cakeToEdit);
        cakeMessage.remove(cakeToEdit);
        //Added a new cake
        addCake();
        // //Set the newly added cake to the cake that that the user wishes to edit
        // cakeList.set(cakeToEdit,cakeList.get(cakeList.size()-1));
        // cakeMessage.set(cakeToEdit, element)
        // int toRemove = cakeList.size()-1;
        // //Removed the newly added cake
        // cakeList.remove(toRemove);
        // cakeMessage.remove(toRemove);
    }
    //Method to remove any added cakes
    public static void removeCake(int cakeToRemove) throws IOException {
        //Asking user for re-assurance before removing cake
        System.out.println("Are you sure you want to remove " + cakeList.get(cakeToRemove).toString() + "?");
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
        //prompt user for candle
        System.out.println("If you wish to add a candle for $10.50, please enter yes,else, enter no");
        yesOrNo = ScannerObj.cakeInput.nextLine();
        yesOrNo=yesOrNo.toLowerCase();
        if(yesOrNo.equals("yes")){
            finalPrice=totalPrice+10.50f;
            System.out.println("Candle added!");
        }else{
            yesOrNo="no";
            finalPrice=totalPrice;
        }
        System.out.println();
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
        System.out.println("Would you like an express service for your order for $50?(Delivery within 3 days)");
        System.out.println("Note: Standard delivery times are 7-14 days.");
        express = ScannerObj.cakeInput.nextLine();
        express = express.toLowerCase();
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
        System.out.println("In order to confirm order, enter confirm ");
        String confirmation = ScannerObj.cakeInput.nextLine();
        confirmation=confirmation.toLowerCase();
        if (confirmation.equals("confirm")) {
            generateInvoice();
            cakeList.clear();
            cakeMessage.clear();
        }else{
            if(yesOrNo.equals("yes")){
                finalPrice-=10.50f;
            }else{}
            printMenu();
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
        //Created object to get current time
        LocalDateTime currentTime = LocalDateTime.now();
        //Formatted the time as per requirements
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss");
        DateTimeFormatter timeFormat2 = DateTimeFormatter.ofPattern("yyyy");
        //Stored the formatted time to relative strings
        _fileName = currentTime.format(timeFormat);
        _fileName2 = currentTime.format(timeFormat2);
        //Replaced ":" with "." as required
        _fileName = _fileName.replaceAll(":", ".");

        //Created file object
        File file = new File(_fileName+" AEST "+_fileName2+".txt");
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
        for (int i = 0; i < cakeList.size(); i++) {
            pw.println();
            pw.println();
            pw.println();
            pw.println("--------------------Cake Order--------------------");
            pw.println();
            pw.println(cakeList.get(i).toString());
            String tempM=cakeMessage.get((i));
            if (!tempM.equals("")) {
                pw.println("Message on cake: "+ tempM);
            }else{}
            pw.println();
            pw.println("------------------------------------------------------");
        }
        pw.close();
        //Invoice end
    }
    public static String getPastry() {
        String[] pastryFlav={"chocolate","vanilla","banana","mango","red velvet","brownie"};
        System.out.println("Congratulations! You are now eligible for a free pastry!");
        System.out.println("Please choose a flavour for the pastry: ");
        for (int i = 0; i < pastryFlav.length; i++) {
            System.out.println((i+1)+") "+pastryFlav[i]);
        }
        String pastry= ScannerObj.cakeInput.nextLine();
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
    public static boolean checkString(String[] arr, String toCheckValue){
        // check if the specified element
        // is present in the array or not
        // using Linear Search method
        boolean test = false;
        for (String element : arr) {
            if (element.equals(toCheckValue)) {
                test = true;
                break;
            }
        }
        return test;
    }
    public static boolean checkInt(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Invalid input!");
        }
        return false;
    }
}
