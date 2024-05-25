// Main.java
import java.util.Scanner;

public class Main {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (opcao) {
                case 1:
                    criarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    deletarProduto();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Criar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Deletar Produto");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarProduto() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha

        Produto produto = new Produto(id, nome, preco);
        produtoDAO.adicionarProduto(produto);
        System.out.println("Produto criado com sucesso.");
    }

    private static void listarProdutos() {
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtoDAO.listarProdutos()) {
            System.out.println(produto);
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do produto a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        Produto produto = produtoDAO.buscarProduto(id);
        if (produto != null) {
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();  // Consumir nova linha

            Produto produtoAtualizado = new Produto(id, nome, preco);
            produtoDAO.atualizarProduto(produtoAtualizado);
            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void deletarProduto() {
        System.out.print("ID do produto a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        produtoDAO.removerProduto(id);
        System.out.println("Produto deletado com sucesso.");
    }
}
