public class Equipe {
    private String nome;
    private int pontos, golsContra, golsPro, saldoGols, vermelhos, amarelos;

    /* o construtor inicia o objeto com uma String para o nome
    *  e com todos os outros atributos em 0, já que neste momento
    * ainda não houveram partidas
    */
    public Equipe(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.golsContra = 0;
        this.golsPro = 0;
        this.saldoGols = 0;
        this.vermelhos = 0;
        this.amarelos = 0;
    }

    //getters e setters
    public String getNome() {
        return nome;
    }
    /*Nao criei um setter para o nome pois creio que a decisão do nome
    * deva ser final quando realizada ao criar o time
    */
    public int getPontos() {
        return pontos;
    }

    /*Ao invés de usar os setters convencionais, utilizei um
    * setter incremental, já que cada partida só irá somar
    * pontos ao time, o mesmo vale para gols feitos, recebidos
    * e para os cartões
    */
    public void addPontos(int pontos) {
        this.pontos += pontos;
    }

    public void addGolsContra(int golsContra) {
        this.golsContra += golsContra;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public void addGolsPro(int golsPro) {
        this.golsPro += golsPro;
    }

    public int getSaldoGols() {
        return saldoGols;
    }

    /*O setter para o saldo de gols é um caso especial que não
    * recebe parametro, ele apenas realiza o calculo do saldo
    * de gols com as informações contidas no objeto
    */
    public void calcSaldoGol() {
        this.saldoGols = this.golsPro-this.golsContra;
    }

    public int getVermelhos() {
        return vermelhos;
    }

    public void addVermelhos(int vermelhos) {
        this.vermelhos += vermelhos;
    }

    public int getAmarelos() {
        return amarelos;
    }

    public void addAmarelos(int amarelos) {
        this.amarelos += amarelos;
    }
}
