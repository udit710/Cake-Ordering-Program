import java.io.IOException;

public class PlaceOrder {
    public static void main(String[] args) throws IOException {
        String _userIn;
        char userIn;
        //Start of user interactive program
        System.out.println("Welcome to Cakereations");
        //print options
        ProcessOrder.printMenu();
        //Take user input
        _userIn = ScannerObj.cakeInput.nextLine();
        while (_userIn.equals("")) {
            System.out.println("Invalid input!");
            _userIn = ScannerObj.cakeInput.nextLine();
        }
        //Convert input to char
        _userIn = _userIn.toUpperCase();
        userIn = _userIn.charAt(0);
        System.out.println();
        //Loop for program to keep on running
        while(userIn != 'Q'){
            ProcessOrder.executeMenu(userIn);
            ProcessOrder.printMenu();
            System.out.println();
            _userIn = ScannerObj.cakeInput.nextLine();
            while (_userIn.equals("")) {
                System.out.println("Invalid input!");
                _userIn = ScannerObj.cakeInput.nextLine();
            }
            _userIn = _userIn.toUpperCase();
            userIn = _userIn.charAt(0);
            System.out.println();
        }   
    }
}