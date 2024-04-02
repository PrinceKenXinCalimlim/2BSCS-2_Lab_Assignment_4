import java.util.Scanner;

class MainUserLogin {
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final String VALID_PASSWORD = "password";
    private int loginAttempts;

    public MainUserLogin() {
        this.loginAttempts = 0;
    }

    public void logAccess(String username, String password) throws MaxLogAttemptsException, InvalidPasswordException {
        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
            throw new MaxLogAttemptsException("Maximum login attempts has been used");
        }

        if (!password.equals(VALID_PASSWORD)) {
            loginAttempts++;
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                throw new MaxLogAttemptsException("Maximum login attempts has been used");
            }
            throw new InvalidPasswordException("Invalid Password");
        }
        loginAttempts = 0;

        System.out.println(username + " You have logged in successfully ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainUserLogin authenticationManager = new MainUserLogin();

        while (true) {
            try {
                System.out.print("Please enter your credentials to log in.\nUsername: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                authenticationManager.logAccess(username, password);
                break;
            } catch (MaxLogAttemptsException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InvalidPasswordException e) {
                System.out.println(e.getMessage() + "\nPlease Try Again!\n");
            }
        }
    }
}