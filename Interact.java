import java.io.IOException;

public class Interact {
    public static void main(String[] args) throws IOException {
        char userIn;
        //Start of user interactive program
        System.out.println("Welcome to Cakereations");
        //print options
        Advancedmethods.printMenu();
        userIn = ScannerObj.cakeInput.nextLine().charAt(0);
        System.out.println();
        while(userIn != 'Q'){
            Advancedmethods.executeMenu(userIn);
                if(userIn != 'C'){
                    Advancedmethods.printMenu();
                }else{
                    //If user chose to checkout, reprint the "Welcome" message for new user and wait for their input
                    System.out.println();
                    System.out.println("Welcome to Cakereations");
                    Advancedmethods.printMenu();
                }
            System.out.println();
            userIn = ScannerObj.cakeInput.nextLine().charAt(0);
            System.out.println();
        }
        ScannerObj.cakeInput.close();
    }
}