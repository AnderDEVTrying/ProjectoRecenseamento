package ProjectoRecenseamento.Executador;

import ProjectoRecenseamento.Classes.Eleitor;

import java.util.Scanner;

public class GerenciadorRecenseamento {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Eleitor eleitores = new Eleitor();
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("------Menu Inicial------");
            System.out.println("Selecione a uma opção: \n" +
                    "{1} Cadastrar Eleitor \n" +
                    "{2} Imprimir Dados dos Eleitores \n" +
                    "{3} Procurar Eleitor \n" +
                    "{4} Sair do Programa");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1 -> eleitores.inserirDados();
                case 2 -> eleitores.imprimirMunicipio();
                case 3 -> eleitores.procurarEleitor();
                case 4 -> {System.out.println("Encerrando o Programa..."); ler.close();}
                default -> System.out.println("Opção Invalida!");
            }
        }

    }
}
