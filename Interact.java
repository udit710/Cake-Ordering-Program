import java.io.IOException;

public class Interact {
    public static void main(String[] args) throws IOException {
        char userIn;
        System.out.println("Welcome to Cakereations");
        Advancedmethods.printMenu();
        userIn = ScannerObj.cakeInput.nextLine().charAt(0);
        System.out.println();
        while(userIn != 'Q'){
            Advancedmethods.executeMenu(userIn);
                if(userIn != 'C'){
                    Advancedmethods.printMenu();
                }
            System.out.println();
            // ScannerObj.cakeInput.nextLine();
            userIn = ScannerObj.cakeInput.nextLine().charAt(0);
            System.out.println();
        }
        ScannerObj.cakeInput.close();
    }
}