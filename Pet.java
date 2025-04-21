package Trabalho; // Define o pacote "Trabalho", que organiza classes relacionadas ao projeto

import java.util.Scanner; // Importa a classe Scanner, usada para capturar entradas do usuário
import java.util.ArrayList; // Importa a classe ArrayList, usada para armazenar listas dinâmicas
import java.util.List; // Importa a interface List, usada para representar coleções de dados

// Classe Pet que herda a classe Cliente (relacionando pets a clientes)
public class Pet extends Cliente {

    static Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados do usuário
    private String nome; // Nome do pet
    private String especie; // Espécie do pet
    private String raca; // Raça do pet
    private int idade; // Idade do pet
    private double peso; // Peso do pet

    // Construtor que inicializa os atributos do pet
    public Pet(String nome, String especie, String raca, int idade, double peso) {
        // Chamada ao construtor da superclasse Cliente (com valores padrão)
        super("Nome ", "Telefone ", "Email "); 
        this.nome = nome; // Define o nome do pet
        this.especie = especie; // Define a espécie do pet
        this.raca = raca; // Define a raça do pet
        this.idade = idade; // Define a idade do pet
        this.peso = peso; // Define o peso do pet
    }

    @Override
    public String toString() {
        // Retorna uma representação textual do pet
        return nome + " (" + especie + ", " + raca + ", " + idade + " anos, " + peso + " kg)";
    }

    // Método para obter o nome do pet
    public String getNome() {
        return nome; // Retorna o nome do pet
    }

    // Método para obter a espécie do pet
    public String getEspecie() {
        return especie; // Retorna a espécie do pet
    }

    // Método para obter a idade do pet
    public int getIdade() {
        return idade; // Retorna a idade do pet
    }

    // Método para obter o peso do pet
    public double getPeso() {
        return peso; // Retorna o peso do pet
    }

    // Método para cadastrar um pet associado a um cliente
    private void cadastrarPet() {
        listarClientes(); // Lista todos os clientes cadastrados
        if (clientes.isEmpty()) return; // Sai do método se não houver clientes cadastrados

        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine(); // Entrada do número do cliente
        if (entrada.equalsIgnoreCase("sair")) return; // Verifica se o usuário deseja sair

        int idx = Integer.parseInt(entrada) - 1; // Obtém o índice do cliente na lista
        if (idx < 0 || idx >= clientes.size()) { // Verifica se o índice é válido
            System.out.println("Cliente inválido."); // Mensagem de erro
            return;
        }

        Cliente cliente = clientes.get(idx); // Obtém o cliente correspondente ao índice

        // Solicita e obtém as informações do pet
        System.out.print("Nome do pet (ou 'sair' para voltar): ");
        String nome = scanner.nextLine();
        if (nome.equalsIgnoreCase("sair")) return;

        System.out.print("Espécie (ou 'sair' para voltar): ");
        String especie = scanner.nextLine();
        if (especie.equalsIgnoreCase("sair")) return;

        System.out.print("Raça (ou 'sair' para voltar): ");
        String raca = scanner.nextLine();
        if (raca.equalsIgnoreCase("sair")) return;

        System.out.print("Idade (ou 'sair' para voltar): ");
        String entradaIdade = scanner.nextLine();
        if (entradaIdade.equalsIgnoreCase("sair")) return;
        int idade = Integer.parseInt(entradaIdade);

        System.out.print("Peso (ou 'sair' para voltar): ");
        String entradaPeso = scanner.nextLine();
        if (entradaPeso.equalsIgnoreCase("sair")) return;
        double peso = Double.parseDouble(entradaPeso);

        Pet pet = new Pet(nome, especie, raca, idade, peso); // Cria um novo objeto Pet
        cliente.adicionarPet(pet); // Adiciona o pet ao cliente
        System.out.println("Pet cadastrado com sucesso!"); // Mensagem de confirmação
    }

    // Método para listar os pets de um cliente
    private void listarPets() {
        listarClientes(); // Lista todos os clientes cadastrados
        if (clientes.isEmpty()) return; // Sai do método se não houver clientes cadastrados

        System.out.print("Escolha o número do cliente (ou 'sair' para voltar): ");
        String entrada = scanner.nextLine(); // Entrada do número do cliente
        if (entrada.equalsIgnoreCase("sair")) {
            System.out.println("Operação cancelada."); // Mensagem de operação cancelada
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(entrada) - 1; // Obtém o índice do cliente
        } catch (NumberFormatException e) { // Captura erros de formato da entrada
            System.out.println("Entrada inválida! Digite um número."); // Mensagem de erro
            return;
        }

        if (idx < 0 || idx >= clientes.size()) { // Verifica se o índice é válido
            System.out.println("Cliente inválido."); // Mensagem de erro
            return;
        }

        Cliente cliente = clientes.get(idx); // Obtém o cliente correspondente ao índice
        List<Pet> pets = cliente.getPets(); // Obtém a lista de pets do cliente

        // Exibe os pets do cliente ou mensagem caso não tenha pets cadastrados
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