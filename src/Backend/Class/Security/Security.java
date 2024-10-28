package Backend.Class.Security;

import Main.Java.DataBase.DataBaseconnector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Security {
    
    // Método para validar el usuario
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean validateUser(String user, char[] password) {
        String query = "SELECT contraseña_hash FROM Cuentas WHERE nombre_usuario = ?";
        try (Connection conn = DataBaseconnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedHash = rs.getString("contraseña_hash").trim();
                String inputHash = hashPassword(String.valueOf(password));
                return inputHash.equals(storedHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para hashear la contraseña usando SHA-512
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para obtener el número de cuenta
    @SuppressWarnings("CallToPrintStackTrace")
    public String getAccountNumber(String user) {
        String accountNumber = null;
        String query = "SELECT numero_cuenta FROM Cuentas WHERE nombre_usuario = ?";
        
        try (Connection conn = DataBaseconnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                accountNumber = rs.getString("numero_cuenta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return accountNumber; // Devuelve el número de cuenta
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public int getAccountId(String user) {
        int accountId = -1; // Valor por defecto si no se encuentra la cuenta
        String query = "SELECT id FROM Cuentas WHERE nombre_usuario = ?";
    
        try (Connection conn = DataBaseconnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                accountId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return accountId; // Devuelve el ID de la cuenta
    }
    
}
