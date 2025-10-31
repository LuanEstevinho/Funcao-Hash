public class FuncHash2 extends HashGeral{

    // puxa o atributo tamanho da classe pai
    public FuncHash2(int tamanho){
        super(tamanho);
    }

    // usa a função hash polinomial
    protected int gerarHash(String chave){
        // inicia o valor do hash como 7, que vai ser multiplicado durante a execução
        int hash = 7;
        // itera na string recebida
        for(char c : chave.toCharArray()){
            // faz a operação, exemplo:
            // 'ANA' = (((7 * 31) + 65) * 31 + 78) * 31 + 65 ---- isso é sempre (valor do hash atual * 31 + valor da letra)
            hash = (hash * 31 + c);

        }
        // usa math.asb para sempre retornar o valor absoluto do hash (tirar sinal negativo, letra...)
        return Math.abs(hash) % tamanho;
    }
}
