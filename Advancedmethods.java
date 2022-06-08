import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Advancedmethods {
    //Created an ArrayList to make and store orders as we do not know how many cakes will be ordered
    static ArrayList <Cake> cakeList = new ArrayList<Cake>();
    private static String yesOrNo="";
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

        if (userIn == 'A') {
            System.out.println("Chocolate 1:plain chocolate,double chocolate,jaffa,mint chocolate,red velvet\nVanilla 2:plain vanilla,vanilla fondant,vanilla buttercream,vanilla mascarpone,vanilla citrus\nCaramel 3:dulce de leche,salted caramel,caramel mango\nBanana 4:plain banana,banana foster,banana cream cheese\nLemon 5:plain lemon,lemon yoghurt,lemon coconut\nTea 6:matcha green tea,hojicha\nCoffee 7:espresso,mocha,coffee walnut\nExotic 8:plain durian,durian extra,jackfruit kiwano\nSpecial 9:opera,pavlova,mille crepe,strawberry shortcake,mixed berry cheesecake\n");
            addCake();
        }
        if(userIn == 'V'){
            viewCart();
        }
        if(userIn == 'E'){
            viewCart();
            //Condition to check number of cakes
            if (cakeList.size()<1) {
                System.out.println("There is no cake to be edited");
            }else{
                System.out.println("Select cake number to edit: ");
                int cakeToEdit = ScannerObj.cakeInput.nextInt();
                ScannerObj.cakeInput.nextLine();
                editCake(cakeToEdit-1);
            }
        }
        if(userIn == 'R'){
            viewCart();
            //Condition to check number of cakes
            if (cakeList.size()<1) {
                System.out.println("There is no cake to be removed");
            }else{
                System.out.println("Select cake number to remove: ");
                int cakeToRemove = ScannerObj.cakeInput.nextInt();
                ScannerObj.cakeInput.nextLine();
                removeCake(cakeToRemove-1);
            }
        }
        if(userIn == 'C'){
            checkoutCake();
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
        System.out.println();
        //Prompt user to enter number of layers
        System.out.println("Enter number of layers(1-3): ");
        //Converted the String input for number of layers to Int
        //Did not take Int input directly as that may cause IOException
        String _noOfLayers = ScannerObj.cakeInput.nextLine();
        int noOfLayers = Integer.parseInt(_noOfLayers);
        //Loop used to check if the number of layers entered is appropriate according to the given data
        while((noOfLayers>3)||(noOfLayers<0)){
            if (noOfLayers>3) {
                System.out.println("You cannot have more than 3 layers");
            } else if(noOfLayers<0){
                System.out.println("You must have atleast 1 layer");
            }
            System.out.println("Enter number of layers(1-3): ");
            _noOfLayers = ScannerObj.cakeInput.nextLine();
            noOfLayers = Integer.parseInt(_noOfLayers);
        }
        System.out.println();
        //Prompt user to enter any dietary requirements
        System.out.println("Enter any dietary requirements(if none, enter 'none'):");
        String dietaryReq = ScannerObj.cakeInput.nextLine();
        //Assigned the user input to the cake
        cake = new Cake(flavour, noOfLayers, dietaryReq);
        float price;
        //Calculated the cake price
        price=cakeInfo.calCakePrice(cake);
        cake.setPrice(price);
        System.out.println(cake.toString());
        //Ask user for assurance before continuing
        System.out.println("In order to continue, please enter 'yes', else, enter 'no'");
        yesOrNo = ScannerObj.cakeInput.nextLine();
        if (yesOrNo.equals("yes")) {
            cakeList.add(cake);   
        } else {}
    }
    //Method to view a list of added cakes
    public static void viewCart() throws IOException {
        float totalPrice=0;
        for(int i=0; i<cakeList.size();++i){
            System.out.println((i+1)+") "+ cakeList.get((i)).toString());
            totalPrice+=cakeList.get((i)).getPrice();
        }
        System.out.printf("Total cost: $%.2f\n", totalPrice);
    }
    //Method to edit added cakes
    public static void editCake(int cakeToEdit)throws IOException {
        //Added a new cake
        addCake();
        //Set the newly added cake to the cake that that the user wishes to edit
        cakeList.set(cakeToEdit,cakeList.get(cakeList.size()-1));
        int toRemove = cakeList.size()-1;
        //Removed the newly added cake
        cakeList.remove(toRemove);
    }
    //Method to remove any added cakes
    public static void removeCake(int cakeToRemove) throws IOException {
        //Asking user for re-assurance before removing cake
        System.out.println("Are you sure you want to remove " + cakeList.get(cakeToRemove).toString() + "?");
        String yesOrNo = ScannerObj.cakeInput.nextLine();
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
        String yesOrNo = ScannerObj.cakeInput.nextLine();
        if(yesOrNo.equals("yes")){
            finalPrice=totalPrice+10.50f;
            System.out.println("Candle added!");
        }else{}
        System.out.println();
        //Prompt user for delivery address
        System.out.println("Enter delivery address: ");
        String userAddress = ScannerObj.cakeInput.nextLine();
        while (userAddress.length()<10){
            System.out.println("Please enter valid address: ");
            userAddress = ScannerObj.cakeInput.nextLine();
        }
        System.out.println();
        System.out.println("Your final order details are: ");
        viewCart();
        System.out.println("Additional candle: "+yesOrNo);
        System.out.printf("Final price: $%.2f\n",finalPrice);
        System.out.println("---------");
        System.out.println("Delivery address: " +userAddress );
        System.out.println("---------");

        //Prompt user for confirmation to checkout
        System.out.println("In order to confirm order, enter confirm ");
        String confirmation = ScannerObj.cakeInput.nextLine();
        if (confirmation.equals("confirm")) {
            generateInvoice();
            cakeList.clear();
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
        }
        pw.printf("Total Cost: $%.2f Additional candle: ",totalPrice);
        pw.println(yesOrNo);
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
            pw.println();
            pw.println("------------------------------------------------------");
        }
        pw.close();
        //Invoice end
    }
}
