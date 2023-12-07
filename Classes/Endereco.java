package ProjectoRecenseamento.Classes;

import java.util.Scanner;

public class Endereco {
    private String cidade;
    private String bairro;
    private int quarteirao;
    Scanner ler = new Scanner(System.in);
    public Endereco(String cidade, String bairro, int quarteirao) {
        this.bairro = bairro;
        this.quarteirao = quarteirao;
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getQuarteirao() {
        return quarteirao;
    }

    public void setQuarteirao(int quarteirao) {
        this.quarteirao = quarteirao;
    }
}
