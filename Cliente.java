package Trabalho; // Define o pacote "Trabalho" que agrupa classes relacionadas ao projeto

import java.util.ArrayList; // Importa a classe ArrayList, usada para armazenar listas dinâmicas
import java.util.List; // Importa a interface List, usada como tipo de lista
import java.util.Scanner; // Importa a classe Scanner, usada para capturar entradas do usuário

// Classe principal que representa um cliente no sistema
public class Cliente {
    // Declaração de variáveis estáticas compartilhadas por todas as instâncias da classe
    static Scanner scanner = new Scanner(System.in); // Scanner usado para entrada de dados do usuário
    static List<Cliente> clientes = new ArrayList<>(); // Lista de clientes cadastrados

    // Lista de pets que pertencem a cada cliente
    private List<Pet> pets = new ArrayList<>();

    // Atributos privados do cliente: nome, telefone e email
    private String nome;
    private String telefone;
    private String email;

    // Construtor da classe Cliente que inicializa os atributos nome, telefone e email
    public Cliente(String nome, String telefone, String email) {
        this.nome = nome; // Define o nome do cliente
        this.telefone = telefone; // Define o telefone do cliente
        this.email = email; // Define o email do cliente
    }

    // Método privado para cadastrar um novo cliente
    private void cadastrarCliente() {
        // Solicita o nome do cliente
        System.out.print("Nome (ou 'sair' para voltar): ");
        String nome = scanner.nextLine(); // Lê a entrada do usuário
        if (nome.equalsIgnoreCase("sair")) return; // Verifica se o usuário deseja sair

        // Solicita o telefone do cliente
        System.out.print("Telefone (ou 'sair' para voltar): ");
        String telefone = scanner.nextLine(); // Lê a entrada do usuário
        if (telefone.equalsIgnoreCase("sair")) return; // Verifica se o usuário deseja sair

        // Solicita o email do cliente
        System.out.print("Email (ou 'sair' para voltar): ");
        String email = scanner.nextLine(); // Lê a entrada do usuário
        if (email.equalsIgnoreCase("sair")) return; // Verifica se o usuário deseja sair

        // Adiciona o cliente à lista de clientes
        clientes.add(new Cliente(nome, telefone, email));
        System.out.println("Cliente cadastrado com sucesso!"); // Mensagem de confirmação
    }

    // Método público para obter o nome do cliente
    public String getNome() {
        return nome; // Retorna o nome do cliente
    }

    // Método estático para listar todos os clientes cadastrados
    public static void listarClientes() {
        // Verifica se a lista de clientes está vazia
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado."); // Exibe mensagem de aviso
            return; // Sai do método
        }
        // Itera sobre a lista de clientes e imprime cada um com seu índice
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i)); // Exibe o índice e o cliente
        }
    }

    // Métodos para gerenciar pets do cliente
    public void adicionarPet(Pet pet) {
        pets.add(pet); // Adiciona um pet à lista de pets do cliente
    }

    public List<Pet> getPets() {
        return pets; // Retorna a lista de pets do cliente
    }
}