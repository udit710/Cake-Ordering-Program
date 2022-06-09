import java.io.IOException;

public class Interact {
    public static void main(String[] args) throws IOException {
        String _userIn;
        char userIn;
        //Start of user interactive program
        System.out.println("Welcome to Cakereations");
        //print options
        Advancedmethods.printMenu();
        _userIn = ScannerObj.cakeInput.nextLine();
        _userIn = _userIn.toUpperCase();
        userIn = _userIn.charAt(0);
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
            _userIn = ScannerObj.cakeInput.nextLine();
            _userIn = _userIn.toUpperCase();
            userIn = _userIn.charAt(0);
            System.out.println();
        }
    }
}

//157,express,clear