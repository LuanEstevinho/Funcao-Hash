public class FuncHash1 extends HashGeral {

    // puxa o atributo tamanho da classe pai
    public FuncHash1(int tamanho) {
        super(tamanho);
    }

    // usa a soma simples como fução hash
    protected int gerarHash(String chave) {
        // inicia o valor da variavel que vai armazenar o hash como 0
        int valorHash = 0;

        // itera na chave com toCharArray() para arrecadar cada elemento
        for (char c : chave.toCharArray()) {
            // soma os elementos
            valorHash += c;
        }
        // retorna ele já em forma de indice
        return valorHash % tamanho;
    }
}
