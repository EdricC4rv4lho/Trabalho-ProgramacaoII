package Trabalho; // Define o pacote "Trabalho", organizando classes relacionadas ao projeto

import java.util.Scanner; // Importa a classe Scanner para capturar entradas do usuário
import java.util.ArrayList; // Importa a classe ArrayList para criar listas dinâmicas
import java.util.List; // Importa a interface List para manipular coleções de dados
import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas

// Classe Servicos que gerencia os serviços contratados pelos clientes e seus pets
public class Servicos {

    static Scanner scanner = new Scanner(System.in); // Scanner estático para entrada de dados
    private List<Cliente> clientes; // Lista de clientes cadastrados
    private List<ServicoContratado> servicosContratados = new ArrayList<>(); // Lista de serviços contratados

    // Construtor que recebe a lista de clientes como parâmetro
    public Servicos(List<Cliente> clientes) {
        this.clientes = clientes; // Associa a lista de clientes ao objeto Servicos
    }

    // Método para listar os clientes cadastrados
    public void listarClientes() {
        if (clientes.isEmpty()) { // Verifica se há clientes cadastrados
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Clientes cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            // Exibe os clientes com números de índice
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }
    }

    // Método para contratar um serviço para um cliente e seu pet
    public void contratarServico() {
        listarClientes(); // Lista todos os clientes
        if (clientes.isEmpty()) return; // Sai se não houver clientes cadastrados

        // Solicita o número do cliente
        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) return;

        int idx; // Índice do cliente
        try {
            idx = Integer.parseInt(entrada) - 1; // Converte entrada para índice
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (idx < 0 || idx >= clientes.size()) { // Verifica se o índice é válido
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente cliente = clientes.get(idx); // Obtém o cliente correspondente
        List<Pet> pets = cliente.getPets(); // Obtém os pets do cliente
        if (pets.isEmpty()) { // Verifica se o cliente possui pets cadastrados
            System.out.println("Este cliente não possui pets cadastrados.");
            return;
        }

        // Exibe os pets do cliente
        System.out.println("Escolha o pet:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getNome() + " (do cliente " + cliente.getNome() + ")");
        }

        // Solicita o número do pet
        System.out.print("Escolha o número do pet (ou 'sair' para voltar): ");
        String entradaPet = scanner.nextLine();
        if (entradaPet.equalsIgnoreCase("sair")) return;

        int petIdx;
        try {
            petIdx = Integer.parseInt(entradaPet) - 1; // Converte entrada para índice
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (petIdx < 0 || petIdx >= pets.size()) { // Verifica se o índice é válido
            System.out.println("Pet inválido.");
            return;
        }

        Pet pet = pets.get(petIdx); // Obtém o pet correspondente

        // Exibe os serviços disponíveis
        System.out.println("Serviços disponíveis:");
        System.out.println("1. Banho e Tosa");
        System.out.println("2. Consulta Veterinária");
        System.out.println("3. Hospedagem");
        System.out.println("4. Adestramento");
        System.out.print("Escolha o serviço (ou 'sair' para voltar): ");
        String entradaServico = scanner.nextLine();
        if (entradaServico.equalsIgnoreCase("sair")) return;

        int servico; // Identificador do serviço
        try {
            servico = Integer.parseInt(entradaServico); // Converte entrada para identificador de serviço
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        // Solicita a data do serviço
        System.out.print("Data do serviço (AAAA-MM-DD) (ou 'sair' para voltar): ");
        String dataStr = scanner.nextLine();
        if (dataStr.equalsIgnoreCase("sair")) return;

        LocalDate data; // Data do serviço
        try {
            data = LocalDate.parse(dataStr); // Converte a string para uma data válida
        } catch (Exception e) {
            System.out.println("Data inválida! Insira no formato AAAA-MM-DD.");
            return;
        }

        // Cria o objeto serviço com base na escolha do usuário
        Servico s = null;
        switch (servico) {
            case 1 -> s = new BanhoETosa(data);
            case 2 -> s = new ConsultaVeterinaria(data);
            case 3 -> s = new Hospedagem(data);
            case 4 -> s = new Adestramento(data);
            default -> {
                System.out.println("Serviço inválido.");
                return;
            }
        }

        // Cria e registra o serviço contratado
        ServicoContratado servicoContratado = new ServicoContratado(cliente, pet, s);
        servicosContratados.add(servicoContratado);

        System.out.println("Serviço contratado com sucesso para " + pet.getNome() + " do cliente " + cliente.getNome());
    }

    // Método para listar os serviços contratados
    public void listarServicosContratados() {
        if (servicosContratados.isEmpty()) { // Verifica se há serviços contratados
            System.out.println("Nenhum serviço contratado.");
            return;
        }

        // Exibe os serviços contratados com detalhes
        System.out.println("Serviços contratados:");
        for (int i = 0; i < servicosContratados.size(); i++) {
            ServicoContratado servico = servicosContratados.get(i);
            System.out.println((i + 1) + ". Cliente: " + servico.getCliente().getNome() +
                               ", Pet: " + servico.getPet().getNome() +
                               ", Serviço: " + servico.getServico().getClass().getSimpleName() +
                               ", Data: " + servico.getServico().getData()); // Data do serviço
        }
    }

    // Método para cancelar um serviço contratado
    public void cancelarServico() {
        listarServicosContratados(); // Lista todos os serviços contratados
        if (servicosContratados.isEmpty()) return; // Sai se não houver serviços contratados

        // Solicita o número do serviço para cancelar
        System.out.print("Escolha o número do serviço para cancelar (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) return;

        int idx; // Índice do serviço na lista
        try {
            idx = Integer.parseInt(entrada) - 1; // Converte entrada para índice
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (idx < 0 || idx >= servicosContratados.size()) { // Verifica se o índice é válido
            System.out.println("Serviço inválido.");
            return;
        }

        // Remove o serviço da lista de contratados
        servicosContratados.remove(idx);
        System.out.println("Serviço cancelado com sucesso!");
    }
}