package model;

public class Viagem {
    private int id;
    private String nome;
    private float km;
    private String data;
    private int idVeiculo;
    private float valor;
    
    public Viagem(){
        
    }
   
    public Viagem(int id, String nome, float km, String data, float valor, int idVeiculo) {
        this.id = id;
        this.nome = nome;
        this.km = km;
        this.data = data;
        this.valor = valor;
        this.idVeiculo = idVeiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
