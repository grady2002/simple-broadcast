import java.util.Scanner;
import java.sql.*;

class Sender {
    void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("cmd", "/c",
                        "clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void sendBroadcast(String from, String message) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            var conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/broadcast",
                    "root",
                    "");
            Statement s = conn.createStatement();
            s.executeUpdate("truncate table main");
            String sql = "insert into main (sender, broadcast) values ('" +
                    from + "', '" + message + "');";
            s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    void exec() {
        try {
            clear();
            Scanner s = new Scanner(System.in);
            System.out.print("Broadcast message system\n\n");
            System.out.print("Sender ? ");
            String from = s.nextLine();
            System.out.print("Short 1 line Message ? ");
            String message = s.nextLine();
            sendBroadcast(from, message);
            s.close();
            System.out.println("\nBroadcast sent restart Sender client to send another broadcast");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Sender b = new Sender();
        b.exec();
    }
}