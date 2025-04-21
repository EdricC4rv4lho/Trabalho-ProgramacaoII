package Trabalho;

public class ServicoContratado {
    private Cliente cliente;
    private Pet pet;
    private Servico servico;

    public ServicoContratado(Cliente cliente, Pet pet, Servico servico) {
        this.cliente = cliente;
        this.pet = pet;
        this.servico = servico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public Servico getServico() {
        return servico;
    }
}