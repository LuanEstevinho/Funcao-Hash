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

            // para medir o tempo de insercao
            long inicio1 = System.nanoTime();
            while ((nome = br.readLine()) != null) {
                tabela1.inserirHash(nome);
            }
            long fim1 = System.nanoTime();

            // abrir arquivo denovo para segunda tabela
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(caminhoArquivo));

            long inicio2 = System.nanoTime();
            while ((nome = br2.readLine()) != null) {
                tabela2.inserirHash(nome);
            }
            long fim2 = System.nanoTime();

            br2.close();

            System.out.println("Tempo de inserção - Função 1: " + (fim1 - inicio1) + " ns");
            System.out.println("Tempo de inserção - Função 2: " + (fim2 - inicio2) + " ns");

            // teste de busca para tempo em ns
            String busca = "Amanda";
            long inicioBusca1 = System.nanoTime();
            boolean achou1 = tabela1.buscarHash(busca);
            long fimBusca1 = System.nanoTime();

            long inicioBusca2 = System.nanoTime();
            boolean achou2 = tabela2.buscarHash(busca);
            long fimBusca2 = System.nanoTime();

            System.out.println("\nBusca por \"" + busca + "\":");
            System.out.println("Função 1 → " + achou1 + " | Tempo: " + (fimBusca1 - inicioBusca1) + " ns");
            System.out.println("Função 2 → " + achou2 + " | Tempo: " + (fimBusca2 - inicioBusca2) + " ns");

            // teste - remover para testar tempo em ns
            long inicioRem1 = System.nanoTime();
            tabela1.removerHash(busca);
            long fimRem1 = System.nanoTime();

            long inicioRem2 = System.nanoTime();
            tabela2.removerHash(busca);
            long fimRem2 = System.nanoTime();

            System.out.println("\nRemoção de \"" + busca + "\":");
            System.out.println("Função 1 → Tempo: " + (fimRem1 - inicioRem1) + " ns");
            System.out.println("Função 2 → Tempo: " + (fimRem2 - inicioRem2) + " ns");

            // print de resultados finais
            System.out.println("\n================== RESULTADOS ==================");
            System.out.println("Colisões Função 1: " + tabela1.getColisoes());
            System.out.println("Colisões Função 2: " + tabela2.getColisoes());
            System.out.println("Tamanho final tabela 1: " + tabela1.tamanho);
            System.out.println("Tamanho final tabela 2: " + tabela2.tamanho);
            System.out.println("================================================");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
