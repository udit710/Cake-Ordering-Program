import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Advancedmethods {
    static ArrayList <Cake> cakeList = new ArrayList<Cake>(100);
    private static String yesOrNo="";
    public static void printMenu() {
        System.out.println("Click 'A' to start a new cake order.");
        System.out.println("Click 'V' to view shooping cart.");
        System.out.println("Click 'E' to edit cake info.");
        System.out.println("Click 'R' to remove a cake.");
        System.out.println("Click 'C' to checkout.");
        System.out.println("Click 'Q' to quit.");
    }
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
            System.out.println("Select cake number to edit: ");
            int cakeToEdit = ScannerObj.cakeInput.nextInt();
            ScannerObj.cakeInput.nextLine();
            editCake(cakeToEdit-1);
        }
        if(userIn == 'R'){
            viewCart();
            System.out.println("Select cake number to remove: ");
            int cakeToRemove = ScannerObj.cakeInput.nextInt();
            ScannerObj.cakeInput.nextLine();
            removeCake(cakeToRemove-1);
        }
        if(userIn == 'C'){
            checkoutCake();
        }
    }
    public static void addCake() throws IOException {
        Cake cake;
        CakeInfo cakeInfo = new CakeInfo();
        System.out.println("Enter your choice of flavour: ");
        String flavour = ScannerObj.cakeInput.nextLine();
        System.out.println();
        System.out.println("Enter number of layers(1-3): ");
        String _noOfLayers = ScannerObj.cakeInput.nextLine();
        int noOfLayers = Integer.parseInt(_noOfLayers);
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
        System.out.println("Enter any dietary requirements(if none, enter 'none'):");
        String dietaryReq = ScannerObj.cakeInput.nextLine();
        cake = new Cake(flavour, noOfLayers, dietaryReq);
        float price;
        price=cakeInfo.calCakePrice(cake);
        cake.setPrice(price);
        System.out.println(cake.toString());
        System.out.println("In order to continue, please enter 'yes', else, enter 'no'");
        yesOrNo = ScannerObj.cakeInput.nextLine();
        if (yesOrNo.equals("yes")) {
            cakeList.add(cake);   
        } else {}
    }
    public static void viewCart() throws IOException {
        float totalPrice=0;
        for(int i=0; i<cakeList.size();++i){
            System.out.println((i+1)+") "+ cakeList.get((i)).toString());
            totalPrice+=cakeList.get((i)).getPrice();
        }
        System.out.printf("Total cost: $%.2f\n", totalPrice);
    }
    public static void editCake(int cakeToEdit)throws IOException {
        addCake();
        cakeList.set(cakeToEdit,cakeList.get(cakeList.size()-1));
        int toRemove = cakeList.size()-1;
        cakeList.remove(toRemove);
    }
    public static void removeCake(int cakeToRemove) throws IOException {
        viewCart();
        cakeList.remove(cakeToRemove);
        viewCart();
    }
    public static void checkoutCake() throws IOException {
        float finalPrice=0;
        float totalPrice=0;
        for(int i=0; i<cakeList.size();++i){
            totalPrice+=cakeList.get((i)).getPrice();
        }
        System.out.println("Your orders are: ");
        viewCart();
        System.out.println("If you wish to add a candle for $10.50, please enter yes,else, enter no");
        String yesOrNo = ScannerObj.cakeInput.nextLine();
        if(yesOrNo.equals("yes")){
            finalPrice=totalPrice+10.50f;
            System.out.println("Candle added!");
        }else{}
        System.out.println();
        System.out.println("Enter delivery address: ");
        String userAddress = ScannerObj.cakeInput.nextLine();
        while (userAddress.length()<10){
            System.out.println("Please enter valid address: ");
            userAddress = ScannerObj.cakeInput.nextLine();
        }
        System.out.println();
        System.out.println("Your final order details are: ");
        viewCart();
        if (yesOrNo.equals("yes")) {
            System.out.println("Additional candle: "+yesOrNo);
        }else{
            System.out.println("Additional candle: "+yesOrNo);
        }
        System.out.println("Final price: $"+finalPrice);
        System.out.println("---------");
        System.out.println("Delivery address: " +userAddress );
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
        String _fileName;
        String _fileName2;
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss");
        DateTimeFormatter timeFormat2 = DateTimeFormatter.ofPattern("yyyy");
        _fileName = currentTime.format(timeFormat);
        _fileName2 = currentTime.format(timeFormat2);
        _fileName = _fileName.replaceAll(":", ".");

        File file = new File(_fileName+" AEST "+_fileName2+".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println("--------------------Invoice--------------------");
        for (int i = 0; i < cakeList.size(); i++) {
            pw.println("Cake "+(i+1)+": " + cakeList.get(i).toString());
        }
        pw.println("Total Cost: $"+totalPrice+" Additional candle: "+yesOrNo);
        pw.println("Final Price: $"+ finalPrice);
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
    }
}
