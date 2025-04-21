package Trabalho;

import java.time.LocalDate;

public abstract class Servico {
    private LocalDate data; // Atributo para armazenar a data do serviço

    // Construtor que recebe a data
    public Servico(LocalDate data) {
        this.data = data;
    }

    // Método para obter a data
    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " - Data: " + data;
    }
}