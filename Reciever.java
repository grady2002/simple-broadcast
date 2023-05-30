import java.sql.*;

class Reciever {
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

    void getMessages() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/broadcast",
                    "root", "");
            Statement s = conn.createStatement();
            String sql = "select * from main;";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                System.out.println("From : " + r.getString(2) + "\nMessage: " + r.getString(3));
                conn.close();
            }
        } catch (Exception e) {
            // unhandled
        }
    }

    void recieveBroadcast() {
        getMessages();
    }

    void exec() {
        while (true) {
            clear();
            recieveBroadcast();
            System.out.print("\n\nClose window to stop");
        }
    }

    public static void main(String[] args) {
        Reciever reciever = new Reciever();
        reciever.exec();
    }
}