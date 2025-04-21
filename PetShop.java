package Trabalho;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do sistema Pet Shop.
 * Gera menu de operações e gerencia a execução.
 */
public class PetShop {
    static Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados do usuário.
    private List<Cliente> clientes = new ArrayList<>(); // Lista de clientes cadastrados.
    private Servicos servicos; // Instância para gerenciar serviços.

    public static void main(String[] args) {
        PetShop petShop = new PetShop();
        petShop.executarSistema(); // Inicia o sistema.
    }

    public PetShop() {
        this.servicos = new Servicos(clientes); // Conectar clientes ao sistema de serviços.
    }

    public void executarSistema() {
        int opcao = -1; // Variável para armazenar escolha do menu.

        do {
            System.out.println("\n===== Sistema Pet Shop =====");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Cadastrar pet para cliente");
            System.out.println("4. Listar pets de cliente");
            System.out.println("5. Contratar serviço");
            System.out.println("6. Listar serviços contratados");
            System.out.println("7. Cancelar serviço contratado");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) { // Verifica se a entrada é um número.
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine(); // Limpa entrada incorreta.
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer.

            processarOpcao(opcao); // Processa a escolha do usuário.

        } while (opcao != 8); // Repete enquanto o usuário não escolher "Sair".
    }

    private void processarOpcao(int opcao) {
        // Escolhe qual método executar de acordo com a opção selecionada.
        switch (opcao) {
            case 1 -> cadastrarCliente(); // Cadastrar novo cliente.
            case 2 -> listarClientes(); // Listar todos os clientes cadastrados.
            case 3 -> cadastrarPet(); // Cadastrar um pet para cliente existente.
            case 4 -> listarPets(); // Listar os pets de determinado cliente.
            case 5 -> servicos.contratarServico(); // Contratar um serviço.
            case 6 -> servicos.listarServicosContratados(); // Listar serviços contratados.
            case 7 -> servicos.cancelarServico(); // Cancelar serviço contratado.
            case 8 -> System.out.println("Saindo... Até logo!"); // Finalizar programa.
            default -> System.out.println("Opção inválida! Escolha uma opção entre 1 e 8."); // Trata opção fora do menu.
        }
    }

    private void cadastrarCliente() {
        // Coleta dados do cliente e adiciona à lista de clientes.
        System.out.print("Nome (ou 'sair' para voltar): ");
        String nome = scanner.nextLine();
        if (nome.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return; // Interrompe operação.
        }

        System.out.print("Telefone (ou 'sair' para voltar): ");
        String telefone = scanner.nextLine();
        if (telefone.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Email (ou 'sair' para voltar): ");
        String email = scanner.nextLine();
        if (email.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        clientes.add(new Cliente(nome, telefone, email)); // Adiciona cliente na lista.
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        // Exibe clientes cadastrados ou mensagem caso não existam.
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("Lista de clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }
    }

    private void cadastrarPet() {
        listarClientes(); // Exibe clientes para escolha.
        if (clientes.isEmpty()) return; // Não permite cadastrar pet sem cliente.

        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(entrada) - 1; // Obtém índice do cliente.
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (idx < 0 || idx >= clientes.size()) {
            System.out.println("Cliente inválido."); // Verifica se o índice é válido.
            return;
        }

        Cliente cliente = clientes.get(idx); // Obtém cliente selecionado.

        System.out.print("Nome do pet (ou 'sair' para voltar): ");
        String nome = scanner.nextLine();
        if (nome.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Espécie (ou 'sair' para voltar): ");
        String especie = scanner.nextLine();
        if (especie.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Raça (ou 'sair' para voltar): ");
        String raca = scanner.nextLine();
        if (raca.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.print("Idade (ou 'sair' para voltar): ");
        String entradaIdade = scanner.nextLine();
        if (entradaIdade.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(entradaIdade); // Converte idade para número inteiro.
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        System.out.print("Peso (ou 'sair' para voltar): ");
        String entradaPeso = scanner.nextLine();
        if (entradaPeso.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(entradaPeso); // Converte peso para número decimal.
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número válido.");
            return;
        }

        Pet pet = new Pet(nome, especie, raca, idade, peso); // Cria novo pet.
        cliente.adicionarPet(pet); // Adiciona pet ao cliente.
        System.out.println("Pet cadastrado com sucesso!");
    }

    private void listarPets() {
        listarClientes(); // Exibe lista de clientes.
        if (clientes.isEmpty()) return;

        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada.");
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(entrada) - 1; // Obtém índice do cliente.
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número.");
            return;
        }

        if (idx < 0 || idx >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente cliente = clientes.get(idx);
        List<Pet> pets = cliente.getPets(); // Obtém lista de pets do cliente.

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado para este cliente.");
        } else {
            System.out.println("Pets do cliente " + cliente.getNome() + ":");
            for (Pet pet : pets) {
                System.out.println("- " + pet.getNome() + " (Espécie: " + pet.getEspecie() + ", Idade: " + pet.getIdade() + " anos, Peso: " + pet.getPeso() + " kg)");
            }
        }
    }
}