import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Campeonato extends Partida{
    private ArrayList<Equipe> participantes;
    private boolean isStarted;

    public  Campeonato(){
        participantes = new ArrayList<>();
        isStarted=false;
    }


    public void addParticipantes(Equipe participante) {
        this.participantes.add(participante);
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void setStarted() {
        this.isStarted = true;
    }

    /*metodo utilizado na busca de um time, retorna Equipe se encontrar a
    * String que é passada como parametro dentre os nomes dos objetos do
    * tipo Equipe, caso contrário retorna null
    * */
    private Equipe buscaTime(String nome){
        Equipe aux = null;
        boolean found=false;
        for(Equipe it: this.participantes){
            if(nome.equalsIgnoreCase(it.getNome())){
                found=true;
                aux=it;
            }
        }
        if(found){
            return aux;
        }else{
            return null;
        }
    }

    /*metodo preliminar ao novaPartida, valida os nomes dos times antes de
    * invocar o metodo novaPartida()
    */
    private void criaPartida(){
        Scanner scanner = new Scanner(System.in);
        String mandante, desafiante;
        do{
            System.out.print("Time mandante: ");
            mandante=scanner.nextLine();
            System.out.print("Time desafiante: ");
            desafiante=scanner.nextLine();
            if(desafiante.equalsIgnoreCase(mandante)){
                System.out.println("Um time pode até ser seu maior inimigo, mas não pode jogar contra si mesmo");
            }

        }while(mandante.equalsIgnoreCase(desafiante));
        if(buscaTime(mandante)!=null && buscaTime(desafiante)!=null){
            novaPartida(buscaTime(mandante),buscaTime(desafiante));
            setStarted();
        }else{
            System.out.println("Times nao encontrados!");
        }
    }

    //metodo utilizado para trocar as posicoes das Equipes no ArrayList
    private void trocaPosicao(int indexI, int indexJ){
        Equipe aux;
        aux = this.participantes.get(indexI);
        this.participantes.set(indexI, this.participantes.get(indexJ));
        this.participantes.set(indexJ, aux);
    }

    /*Metodo ordena a tabela dos times de acordo com os criterios do campeonato
    * sendo estes: pontos--> saldo de gols--> gols pro--> menor numero de vermelhos
    * --> menor numero de amarelos
    */
    private void sortCampeonato(){
        for(int i=0; i<this.participantes.size();i++){
            for(int j=i+1; j<this.participantes.size();j++){
                //checa pontos
                if(this.participantes.get(j).getPontos()>this.participantes.get(i).getPontos()){
                    trocaPosicao(i,j);
                } else if (this.participantes.get(j).getPontos()==this.participantes.get(i).getPontos()) {
                    //se pontos sao iguais, checa saldo de gols
                    if(this.participantes.get(j).getSaldoGols()>this.participantes.get(i).getSaldoGols()){
                        trocaPosicao(i,j);
                    } else if (this.participantes.get(j).getSaldoGols()==this.participantes.get(i).getSaldoGols()) {
                        //se saldo de gols for igual, checa gols pro
                        if(this.participantes.get(j).getGolsPro()>this.participantes.get(i).getGolsPro()){
                            trocaPosicao(i,j);
                        }else if(this.participantes.get(j).getGolsPro()==this.participantes.get(i).getGolsPro()){
                            //se quantia de gols pro for igual checa vermelhos
                            if(this.participantes.get(j).getVermelhos()<this.participantes.get(i).getVermelhos()){
                                trocaPosicao(i, j);
                            }else if(this.participantes.get(j).getVermelhos()==this.participantes.get(i).getVermelhos()){
                                //se quantia de vermelhos for igual, checa-se amarelos
                                if(this.participantes.get(j).getAmarelos()<this.participantes.get(i).getAmarelos()){
                                    trocaPosicao(i, j);
                                } else if (this.participantes.get(j).getAmarelos()==this.participantes.get(i).getAmarelos()) {
                                    Random random = new Random();
                                    //se empatar em todos os critérios, colocação é decidida via sorteiO
                                    if(random.nextInt(2)==0){
                                        trocaPosicao(i,j);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void novoParticipante(){
        Scanner scanner = new Scanner(System.in);
        if (this.participantes.size()<20){
            String entrada=null;
            do{
                System.out.print("Nome: ");
                entrada = scanner.nextLine();
                if(buscaTime(entrada)!=null){
                    System.out.println("Time ja existe!");
                }
            }while(buscaTime(entrada)!=null);

            addParticipantes(new Equipe(entrada));
        }else{
            System.out.println("Limite de participantes atingido!");
        }
    }

    private void exibeTabela(){
        int i=1;
        System.out.println("-----------------------------------------------------------------");
        for(Equipe it: this.participantes){
            System.out.println(
                    "|"+ i + " - " + it.getNome() + " | Pontos: " + it.getPontos()
                    + " | SG: " + it.getSaldoGols() +" | GP: "+ it.getGolsPro() + " | CA: " +
                    it.getAmarelos() + " | CV: " + it.getVermelhos()
            );
            i++;
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private void exibeLiberta(){
        int i=1;
        System.out.println("-----------------------------------------------------------------");
        for(Equipe it: this.participantes){
            System.out.println(
                    "|"+ i + " - " + it.getNome() + " | Pontos: " + it.getPontos()
                    + " | SG: " + it.getSaldoGols() +" | GP: "+ it.getGolsPro() + " | CA: " +
                    it.getAmarelos() + " | CV: " + it.getVermelhos()
            );
            i++;
            if(i==7) break;
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private void exibeRebaixados(){

        System.out.println("-----------------------------------------------------------------");
        for(int i = this.participantes.size()-4; i<=this.participantes.size(); i++){
            System.out.println(
                    "|"+ i + " - " + this.participantes.get(i-1).getNome() +
                    " | Pontos: " + this.participantes.get(i-1).getPontos()
                    + " | SG: " + this.participantes.get(i-1).getSaldoGols()
                    + " | GP: "+ this.participantes.get(i-1).getGolsPro()
                    + " | CA: " +this.participantes.get(i-1).getAmarelos()
                    + " | CV: " + this.participantes.get(i-1).getVermelhos()
            );
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private void estatisticasLider() {
        System.out.println(
                "Lider: " + this.participantes.get(0).getNome()+ " | Pontos: " +
                this.participantes.get(0).getPontos()+ " | SG: " +
                this.participantes.get(0).getSaldoGols() + " | GP: "+
                this.participantes.get(0).getGolsPro() + " | CA: " +
                this.participantes.get(0).getAmarelos() + " | CV: " +
                this.participantes.get(0).getVermelhos()
        );

    }

    public void exibeMenu(){
        int op = 1;
        Scanner scanner = new Scanner(System.in);
        do{
            /*Quando ainda não houve nenhuma partida iniciada
            * exibe o seguinte menu
            */
            if(!this.isStarted()){
                System.out.println("1 - Novo time");
                System.out.println("2 - Nova partida");
                System.out.println("3 - Ver participantes");
                System.out.println("0 - Sair");
                op=scanner.nextInt();
                scanner.nextLine();
                switch (op){
                    case 1:
                        //verifica se é possivel adicionar um novo participante, se sim, adiciona
                        novoParticipante();
                        break;
                    case 2:
                        if(participantes.size()<6){
                            System.out.println("Cadastre ao menos 6 participantes!");
                        }else{
                            criaPartida();
                            sortCampeonato();
                        }
                        break;
                    case 3:
                        exibeTabela();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }else{
                System.out.println("1 - Nova partida");
                System.out.println("2 - Ver tabela");
                System.out.println("3 - Estatisticas do lider");
                System.out.println("4 - Classificados para a libertadores");
                System.out.println("5 - Rebaixamento");
                System.out.println("0 - Sair");
                op=scanner.nextInt();
                scanner.nextLine();
                switch (op){
                    case 1:
                        criaPartida();
                        sortCampeonato();
                        break;
                    case 2:
                        exibeTabela();
                        break;
                    case 3:
                        estatisticasLider();
                        break;
                    case 4:
                        exibeLiberta();
                        break;
                    case 5:
                        exibeRebaixados();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }
        }while(op!=0);
    }

}
