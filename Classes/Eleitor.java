package ProjectoRecenseamento.Classes;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Eleitor extends Pessoa {
    Scanner ler = new Scanner(System.in);

    public enum Municipio { //Enumeração dos Municipios
        Matola, Maputo_Cidade

    }

    private int proximoID = 1;
    private int id_eleitor;
    private Endereco endereco;
    private LocalDate dataemissao;
    private Municipio municipio;
    private Queue<Eleitor> eleitores = new LinkedList<>();

    //Bloco de Inicialização para exibir mensagem do sistema
    static {
        System.out.println("-------------------COMISSÃO NACIONAL DE ELEIÇÕES-------------------");
        System.out.println("--------SISTEMA DE RECENSEAMENTO ELEITORAL--------");
    }

    public Eleitor() {
    }

    public Eleitor(String nome, String bi, String sexo, int dia, int mes, int ano, Endereco endereco) {
        super(nome, bi, sexo, dia, mes, ano);
        this.id_eleitor = proximoID++;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        this.endereco = endereco;
        this.municipio = null;
        this.dataemissao = LocalDate.now();
    }

    //Metodo para cadastrar o eleitor
    public void inserirDados() {
        try { //Tratamento de Erros
            selecionarMunicipio();
            ler.nextLine();

            System.out.println("Inserir Nome do Eleitor: ");
            String nome = ler.nextLine();
            System.out.println("Inserir B.I do Eleitor: ");
            String bi = ler.nextLine();
            System.out.println("Inserir Sexo do Eleitor(M/F): ");
            String sexoTipo = ler.nextLine();

            System.out.println("Inserir Data de Nascimento do Eleitor(DD/MM/YY): ");
            int dia = ler.nextInt();
            int mes = ler.nextInt();
            int ano = ler.nextInt();

            //Exibir Mensagem de Erro caso a data inserida não siga o padrão requisitado
            if (!super.datavalida(ano, mes, dia)) {
                System.err.println("Data de Nascimento Inválida! Verifique se os valores inseridos seguem o padrão (DD/MM/YY).");
            }

            ler.nextLine();

            //Inserir Endereço do Eleitor
            System.out.println("Inserir a Cidade do Eleitor: ");
            String cidade = ler.nextLine();
            System.out.println("Inserir Bairro do Eleitor:");
            String bairro = ler.nextLine();
            System.out.println("Inserir Quarteirão do Eleitor:");
            int quarteirao = ler.nextInt();

            //Inserir os dados no constructor e relacionar ao eleitor
            Endereco endereco = new Endereco(cidade, bairro, quarteirao);
            Eleitor eleitor = new Eleitor(nome, bi, sexoTipo, dia, mes, ano, endereco);
            eleitor.municipio = this.municipio;
            eleitores.add(eleitor);
            System.out.println("Eleitor Cadastrado!");
            this.id_eleitor = proximoID++;

        } catch (InputMismatchException e) { //Para capturar erros relacionados com os dados não inteiros
            System.err.println("Inserção de dado invalida! Verifique se o dado que inseriu é um valor inteiro.");
        } catch (DateTimeException e) { //Para capturar o erro relacionado a data mal inserida
            System.err.println(e.getMessage());
        }
        ler.nextLine();
    }

    //Metodo para pedir ao usuário para inserir o municipio ao qual deseja inserir os dados
    public void selecionarMunicipio() {
        int opcao;
        while (true) {
            System.out.println("Selecione o Municipio: \n" + "{1} Maputo Cidade \n" + "{2} Matola");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1 -> {
                    municipio = Municipio.Maputo_Cidade;
                    System.out.println("Municipio selecionado: " + municipio);
                    return;
                }
                case 2 -> {
                    municipio = Municipio.Matola;
                    System.out.println("Municipio selecionado: " + municipio);
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    //Metodo para imprimir o municipio de acordo com o municipio requisitado pelo usuário
    public void imprimirMunicipio() {
        System.out.println("Selecione o Municipio: \n" + "{1} Maputo Cidade \n" + "{2} Matola \n" + "{3} Todos");
        int opcao = ler.nextInt();
        Municipio municipio = null; //Variavel para receber o municipio selecionado

        switch (opcao) {
            case 1 -> municipio = Municipio.Maputo_Cidade;
            case 2 -> municipio = Municipio.Matola;
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }
        System.out.println(" Município Selecionado: " + municipio);

        //Percorrer os Eleitores e Imprimir os dados com base no municipio selecionado
        for (Eleitor eleitor : eleitores) {
            if (eleitor.municipio != null && eleitor.municipio.equals(municipio)) {
                System.out.println("ID do Eleitor: " + eleitor.getId_eleitor());
                System.out.println("Nome: " + eleitor.getNome());
                System.out.println("Sexo: " + eleitor.getSexo());
                System.out.println("Data de Nascimento: " + eleitor.getDataNascimento());

                //Veriificar se o endereco foi preenchido
                if (eleitor.endereco != null) {
                    System.out.println("Cidade: " + eleitor.endereco.getCidade());
                    System.out.println("Data de Emissão: " + eleitor.getDataemissao());
                    System.out.println("Bairro: " + eleitor.endereco.getBairro());
                    System.out.println("Quarteirão: " + eleitor.endereco.getQuarteirao());
                }
                System.out.println("B.I.: " + eleitor.getBi());
                System.out.println("---------------------------------");
            } else {
                System.out.println("Nenhum Dado foi Encontrado!");
            }
        }
    }

    //Metodo para procurar o eleitor dentro da fila
    public void procurarEleitor() {
        System.out.println("Digite o B.I do Eleitor: ");
        String procurarB_I = ler.nextLine();

        // Variável para verificar se o eleitor foi encontrado
        boolean encontrado = false;

        for (Eleitor eleitor : eleitores) {
            if (eleitor.getBi().equalsIgnoreCase(procurarB_I)) {
                // Exibir os dados do eleitor encontrado
                System.out.println("Eleitor encontrado:");
                System.out.println("ID do Eleitor: " + eleitor.getId_eleitor());
                System.out.println("Nome: " + eleitor.getNome());
                System.out.println("Sexo: " + eleitor.getSexo());
                System.out.println("Data de Nascimento: " + eleitor.getDataNascimento());
                System.out.println("Município: " + eleitor.municipio);

                // Verificar se o endereço foi preenchido
                if (eleitor.endereco != null) {
                    System.out.println("Cidade: " + eleitor.endereco.getCidade());
                    System.out.println("Data de Emissão: " + eleitor.getDataemissao());
                    System.out.println("Bairro: " + eleitor.endereco.getBairro());
                    System.out.println("Quarteirão: " + eleitor.endereco.getQuarteirao());
                }
                System.out.println("B.I.: " + eleitor.getBi());
                System.out.println("---------------------------------");

                encontrado = true;
                break;
            }
        }

        if (encontrado){
            eliminarEleitor(procurarB_I);
        }else {
            System.out.println("Eleitor não encontrado!");
        }
    }

    public void eliminarEleitor(String procura_BI) {
        //Requisitar autorização do usuário para eliminar o eleitor procurado
        System.out.println("Deseja Eliminar o Eleitor?S/N");
        String opcao = ler.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            Queue<Eleitor> armazenaFila = new LinkedList<>(); //Fila temporária para armazenar os eleitores não removidos
            boolean eliminado = false;

            while (!eleitores.isEmpty()) {
                Eleitor eleitor = eleitores.poll();

                if (eleitor.getBi().equalsIgnoreCase(procura_BI)) {
                    eliminado = true;
                    System.out.println("Eleitor eliminado com sucesso!");
                } else {
                    armazenaFila.add(eleitor);
                }
            }
            eleitores = armazenaFila; //Retornar os eleitores não eliminados para a Fila original

            if (!eliminado) {
                System.out.println("Eleitor não encontrado!");
            }
        } else if (opcao.equalsIgnoreCase("N")) {
            System.out.println("Operação Cancelada. Retornando ao Menu Principal.");
        }

    }


    public Queue<Eleitor> getEleitores() {
        return eleitores;
    }

    public void setEleitores(Queue<Eleitor> eleitores) {
        this.eleitores = eleitores;
    }

    public int getId_eleitor() {
        return id_eleitor;
    }

    public void setId_eleitor(int id_eleitor) {
        this.id_eleitor = id_eleitor;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataemissao() {
        return dataemissao;
    }

}
