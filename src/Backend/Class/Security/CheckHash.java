package Backend.Class.Security;


public class CheckHash {
    public static void main(String[] args) {
        Security security = new Security();
        String password = "ijkl"; // Cambia esto por la contraseña que deseas hashear
        String hashedPassword = security.hashPassword(password);
        System.out.println("Hash generado para la contraseña '" + password + "': " + hashedPassword);
    }
    
}
