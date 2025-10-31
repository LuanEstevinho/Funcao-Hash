public class FuncHash2 extends HashGeral{

    // puxa o atributo tamanho da classe pai
    public FuncHash2(int tamanho){
        super(tamanho);
    }

    // usa a função hash polinomial
    protected int gerarHash(String chave){
        // inicia o valor do hash como 7, que vai ser multiplicado durante a execução
        int hash = 7; // pq 7? numero primo pequeno
        // itera na string recebida
        for(char c : chave.toCharArray()){
            // faz a operação, exemplo:
            // 'Ana'= (7*31) + 65 = 282 (Letra A), (282*31) + 110 = 8852 (Letra n), (8852*31) + 97 = 274509 (Letra a)
            hash = (hash * 31 + c); // 31 é usado pq é um numero primo e ajuda a distribuir melhor os valores

        }
        // usa math.abs para sempre retornar o valor absoluto do hash (tirar sinal negativo, letra...)
        return Math.abs(hash) % tamanho; // ex: 274509 % 32 = 13 -> então 'Ana' vai para o indice 13 se o tamanho for 32, muda de acordo com o tamanho
    }
}
