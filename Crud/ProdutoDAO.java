// ProdutoDAO.java
import java.io.*;
import java.util.*;

public class ProdutoDAO {
    private static final String FILE_NAME = "produtos.txt";
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoDAO() {
        carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        salvarProdutos();
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProduto(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public void atualizarProduto(Produto produtoAtualizado) {
        Produto produto = buscarProduto(produtoAtualizado.getId());
        if (produto != null) {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            salvarProdutos();
        }
    }

    public void removerProduto(int id) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produtos.remove(produto);
            salvarProdutos();
        }
    }

    private void carregarProdutos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                produtos.add(Produto.fromString(linha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void salvarProdutos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Produto produto : produtos) {
                writer.write(produto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }
}
