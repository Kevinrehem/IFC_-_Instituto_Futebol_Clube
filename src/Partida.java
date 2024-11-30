import java.util.Scanner;

//crio uma classe que vai fazer o gerenciamento das partidas individuais
public class Partida {
    public void novaPartida(Equipe mandante, Equipe desafiante){
        int golsDes, golsMan, amarelosMan, vermelhosMan, amarelosDes,  vermelhosDes;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gols do " + mandante.getNome() + ": ");
        golsMan=scanner.nextInt();
        System.out.print("Amarelos do " + mandante.getNome() + ": ");
        amarelosMan = scanner.nextInt();
        System.out.print("Vermelhos do " + mandante.getNome() + ": ");
        vermelhosMan = scanner.nextInt();

        System.out.print("Gols do " + desafiante.getNome() + ": ");
        golsDes=scanner.nextInt();
        System.out.print("Amarelos do " + desafiante.getNome() + ": ");
        amarelosDes = scanner.nextInt();
        System.out.print("Vermelhos do " + desafiante.getNome() + ": ");
        vermelhosDes = scanner.nextInt();

        mandante.addAmarelos(amarelosMan);
        mandante.addVermelhos(vermelhosMan);
        mandante.addGolsPro(golsMan);
        mandante.addGolsContra(golsDes);
        mandante.calcSaldoGol();

        desafiante.addGolsPro(golsDes);
        desafiante.addGolsContra(golsMan);
        desafiante.addAmarelos(amarelosMan);
        desafiante.addVermelhos(vermelhosMan);
        desafiante.calcSaldoGol();
        if(golsDes>golsMan){
            desafiante.addPontos(3);
        }else if (golsMan>golsDes){
            mandante.addPontos(3);
        }else{
            desafiante.addPontos(1);
            mandante.addPontos(1);
        }

    }
}
