package ProjectoRecenseamento.Classes;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Pessoa {
    public enum Sexo{Masculino, Feminino}
    protected String nome;
    protected String bi;
    protected LocalDate dataNascimento;
    protected Sexo sexo;

    public Pessoa() {
    }

    public Pessoa(String nome, String bi, String sexo, int dia, int mes, int ano) {
        this.nome = nome;
        this.bi = bi;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        if(sexo.equalsIgnoreCase("M")){
            this.sexo = Sexo.Masculino;
        } else if (sexo.equalsIgnoreCase("F")) {
            this.sexo = Sexo.Feminino;
        }else {
            System.out.println("Sexo Invalido");
        }
    }
    //Tratamento de Erro para caso de inserção de Data Invalida
    public boolean datavalida(int ano, int mes, int dia) {
        try {
            LocalDate.of(ano, mes, dia);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
