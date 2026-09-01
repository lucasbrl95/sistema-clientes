import java.sql.*;
import java.util.Scanner;

public class SistemaClientes {
    private Connection conn;
    private Scanner scanner = new Scanner(System.in);

    public SistemaClientes(Connection conn) {
        this.conn = conn;
    }

    public void menu() {
    int opcao;
    do {
        System.out.println("\n=== Sistema de Gestão de Clientes ===");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Buscar por nome");
        System.out.println("4 - Excluir cliente");
        System.out.println("5 - Atualizar cliente");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> buscar();
            case 4 -> excluir();
            case 5 -> atualizar();
            case 0 -> System.out.println("Encerrando...");
            default -> System.out.println("Opção inválida.");
        }
    } while (opcao != 0);
}

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        String sql = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void listar() {
        String sql = "SELECT * FROM clientes";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Clientes ---");
            boolean vazio = true;
            while (rs.next()) {
                vazio = false;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("----------------------------");
            }
            if (vazio) System.out.println("Nenhum cliente cadastrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    private void buscar() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("----------------------------");
            }
            if (!encontrou) System.out.println("Nenhum cliente encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
    }

    private void excluir() {
        System.out.print("ID do cliente a excluir: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            if (linhas > 0) System.out.println("Cliente excluído!");
            else System.out.println("ID não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }

    private void atualizar() {
    System.out.print("ID do cliente a atualizar: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Novo nome: ");
    String nome = scanner.nextLine();
    System.out.print("Novo email: ");
    String email = scanner.nextLine();
    System.out.print("Novo telefone: ");
    String telefone = scanner.nextLine();

    String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, telefone);
        ps.setInt(4, id);
        int linhas = ps.executeUpdate();
        if (linhas > 0) System.out.println("Cliente atualizado com sucesso!");
        else System.out.println("ID não encontrado.");
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar: " + e.getMessage());
    }
}
}