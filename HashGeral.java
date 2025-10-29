abstract class HashGeral {
    protected int colisoes;
    protected String[] TabelaHash;
    protected int tamanho;
    protected int[] colisaoPorPosicao;

    public HashGeral(int tamanho) {
        this.tamanho = tamanho;
        this.colisoes = 0;
        this.TabelaHash = new String[tamanho];
        this.colisaoPorPosicao = new int[tamanho];
    }

    protected abstract int gerarHash(String chave);

    public void inserirHash(String chave){
        if (chave == null){
            return;
        }
        int posicao = gerarHash(chave);
        int guardarValorInicial = posicao;

        while (TabelaHash[posicao] != null && !TabelaHash[posicao].equals("vazio")){
            colisoes++;
            colisaoPorPosicao[posicao]++;
            posicao = (posicao + 1) % tamanho;
            if (posicao == guardarValorInicial){
                return;
            }
        }
        TabelaHash[posicao] = chave;
    }

    public boolean buscarHash(String chave){
        int posicao = gerarHash(chave);
        int guardarValorInicial = posicao;

        while (TabelaHash[posicao] != null){
            if (TabelaHash[posicao].equals(chave)){
                return true;
            }
            posicao = (posicao + 1) % tamanho;
            if (posicao == guardarValorInicial){
                break;
            }
        }
        return false;
    }

    public boolean removerHash(String chave){
        int index = gerarHash(chave);
        int guardarValorInicial = index;

        while (TabelaHash[index] != null){
            if (TabelaHash[index].equals(chave)){
                TabelaHash[index] = "vazio";
                return true;
            }

            index = (index + 1) % tamanho;

            if (index == guardarValorInicial){
                break;
            }
        }
        return false;
    }

    public void colisoesPorIndex() {
        System.out.println("\nColisões por índice da tabela:");
        for (int i = 0; i < tamanho; i++){
            System.out.printf("[%02d]: %d colisões%n ", i, colisaoPorPosicao[i]);
        }
    }

    public int getColisoes(){
        return colisoes;
    }
}
