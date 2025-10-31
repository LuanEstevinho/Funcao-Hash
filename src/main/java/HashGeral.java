abstract class HashGeral {
    protected int colisoes;
    protected String[] TabelaHash;
    protected int tamanho;
    protected int[] colisaoPorPosicao;

    public HashGeral(int tamanho) {
        this.tamanho = tamanho;
        this.colisoes = 0;
        //define que a TabelaHash é um vetor do tamanho 'tamanho'
        this.TabelaHash = new String[tamanho];
        this.colisaoPorPosicao = new int[tamanho];
    }

    // metodo abstrato para gerar um hash com base nas funções hash escolhidas
    protected abstract int gerarHash(String chave);

    // metodo para inserção da chave hash na tabela
    public void inserirHash(String chave) {
        // se a chave for nula, sai
        if (chave == null) return;

        // usa fator de carga (quantidade de elementos / tamanho da tabela) para redimensionar
        double fatorCarga = (double) contarElementos() / tamanho;
        // quando o faftor de carga chega a 0,75 ou mais (75% da tabela preenchida) ele redimensiona
        if (fatorCarga >= 0.75) {
            // chama a função de redimensionamento
            redimensionarTabela();
            // insere novamente as chaves hash
            inserirHash(chave);
            return;
        }

        // joga o valor gerado pela função hash na variavel posicao
        int posicao = gerarHash(chave);
        // guarda a variavel posicao em outra variavel para não perder senso do inicio
        int guardarValorInicial = posicao;

        // enquanto a posição que esta sendo validada não for nula ou "vazio"
        while (TabelaHash[posicao] != null && !TabelaHash[posicao].equals("vazio")) {
            // significa que está gerando colisoes, portanto icrementa aqui
            colisoes++;
            // mesma coisa porém conta colisoes por cada posição
            colisaoPorPosicao[posicao]++;

            // aqui faz a sondagem linear, quando existe colisão, ele vai para o proximo indice sequencialmente até achar algum que esteja vazio ou null
            posicao = (posicao + 1) % tamanho;
            // se a posição for = ao valor que entrou no loop, ele sai
            if (posicao == guardarValorInicial) return;
        }

        // define na posição 'posicao' o valor da chave
        TabelaHash[posicao] = chave;
    }

    // metodo para buscar chave hash
    public boolean buscarHash(String chave) {
        // faz o mesmo que no metodo de inserir
        int posicao = gerarHash(chave);
        int guardarValorInicial = posicao;

        // enquanto não for nulo, verifica se o valor da posicao é = ao valor da chave
        while (TabelaHash[posicao] != null) {
            if (TabelaHash[posicao].equals(chave)) {
                return true;
            }
            // sondagem linear
            posicao = (posicao + 1) % tamanho;
            // msm coisa da func de cima
            if (posicao == guardarValorInicial) break;
        }
        return false;
    }


    // metodo para remover hash
    public boolean removerHash(String chave) {
        // usa a mesma logica das funcoes de cima
        int index = gerarHash(chave);
        int guardarValorInicial = index;


        while (TabelaHash[index] != null) {
            if (TabelaHash[index].equals(chave)) {

                // atribui a posicao como String "vazio" para que ela se diferencie de null
                TabelaHash[index] = "vazio";
                return true;
            }

            // sondagem linear
            index = (index + 1) % tamanho;
            if (index == guardarValorInicial) break;
        }
        return false;
    }

    // metodo para redimensionar a tabela
    protected void redimensionarTabela() {
        int novoTamanho = tamanho * 2;
        // cria uma outra tabela com o dobro do tamanho da anterior
        String[] novaTabela = new String[novoTamanho];

        System.out.println("Redimensionando tabela de " + tamanho + " para " + novoTamanho);

        // não perde o valor do tamanho antigo, e atribui tamanho ao dobro do valor anterior
        int tamanhoAntigo = tamanho;
        tamanho = novoTamanho;

        // for each para fazer rehashing nos elementos
        for (String chave : TabelaHash) {
            if (chave != null && !chave.equals("vazio")) {
                // gera um novo hash da chave e armazena na variavel novaPos
                int novaPos = gerarHash(chave);
                // enquanto houver colisoes, faz sondagem linear
                while (novaTabela[novaPos] != null) {
                    novaPos = (novaPos + 1) % novoTamanho;
                }
                // define a posicao da tabela com o valor da chave
                novaTabela[novaPos] = chave;
            }
        }
        // apenas define as tabelas antigas para as redimensionadas
        TabelaHash = novaTabela;
        tamanho = novoTamanho;
        colisaoPorPosicao = new int[novoTamanho];
    }

    // metodo para contar elementos (usado na operação do fator de garga la em inserir)
    protected int contarElementos() {
        int count = 0;
        for (String s : TabelaHash) {
            if (s != null && !s.equals("vazio")) count++;
        }
        return count;
    }

    // conta colisoes por indice da tabela
    public void colisoesPorIndex() {
        System.out.println("\nColisões por índice da tabela:");
        for (int i = 0; i < tamanho; i++) {
            // print formatado para colisoes na tabela, no formato [00]: ...
            System.out.printf("[%02d]: %d colisões%n", i, colisaoPorPosicao[i]);
        }
    }

    // retorna colisoes
    public int getColisoes() {
        return colisoes;
    }
}
