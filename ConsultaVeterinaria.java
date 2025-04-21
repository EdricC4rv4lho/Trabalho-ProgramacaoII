package Trabalho;

import java.time.LocalDate;

public class ConsultaVeterinaria extends Servico {
    public ConsultaVeterinaria(LocalDate data) {
        super(data); // Agora chamará corretamente o construtor da superclasse
    }
}