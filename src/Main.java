import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConexaoDB.conectar();
        if (conn != null) {
            SistemaClientes sistema = new SistemaClientes(conn);
            sistema.menu();
        }
    }
}