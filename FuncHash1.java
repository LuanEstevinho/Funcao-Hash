public class FuncHash1 extends HashGeral {

    public FuncHash1(int tamanho) {
        super(tamanho);
    }

    protected int gerarHash(String chave) {
        int valorHash = 0;

        for (char c : chave.toCharArray()) {
            valorHash += c;
        }
        return valorHash % tamanho;
    }
}
