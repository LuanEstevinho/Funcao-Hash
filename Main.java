import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int tamanho = 32;
        HashGeral tabela1 = new FuncHash1(tamanho);
        HashGeral tabela2 = new FuncHash2(tamanho);

        String caminhoArquivo = "female_names.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String nome;


            while ((nome = br.readLine()) != null) {
                tabela1.inserirHash(nome);
                tabela2.inserirHash(nome);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("\n================== RESULTADOS ==================");
        System.out.println("\nColisões Função 1: "+ tabela1.getColisoes());
        System.out.println("Colisões Função 2: "+ tabela2.getColisoes());
        System.out.println("================================================");

        tabela1.colisoesPorIndex();
        tabela2.colisoesPorIndex();

    }
}
