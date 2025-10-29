public class FuncHash2 extends HashGeral{

    public FuncHash2(int tamanho){
        super(tamanho);
    }

    protected int gerarHash(String chave){
        int hash = 7;
        for(char c : chave.toCharArray()){
            hash = (hash * 31 + c);

        }
        return Math.abs(hash) % tamanho;
    }
}
