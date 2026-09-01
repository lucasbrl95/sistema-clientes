import java.util.ArrayList;
import java.util.Scanner;

public class SistemaClientes {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int proximoId = 1;

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Sistema de Gestão de Clientes ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar por nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 0 -> System.out.println("Encerrando sistema...");
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

        clientes.add(new Cliente(proximoId++, nome, email, telefone));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listar() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n--- Clientes ---");
        for (Cliente c : clientes) {
            c.exibir();
        }
    }

    private void buscar() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(nome)) {
                c.exibir();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum cliente encontrado.");
    }
}