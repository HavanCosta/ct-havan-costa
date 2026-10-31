import java.util.List;
import java.util.Scanner;

void main() {
    Scanner entrada = new Scanner(System.in);
    GerenciadorAlunos gerenciador = new GerenciadorAlunos();

    Aluno aluno = new Aluno("João", 25, 200, true);
    Aluno aluno2 = new Aluno("Carlos", 30, 180, false);
    Aluno aluno3 = new Aluno("Pedro", 22, 200, true);
    Aluno aluno4 = new Aluno("Ana", 28, 220, true);
    Aluno aluno6 = new Aluno("Marcos", 24, 0, false);

    gerenciador.cadastrarAluno(aluno);
    gerenciador.cadastrarAluno(aluno2);
    gerenciador.cadastrarAluno(aluno3);
    gerenciador.cadastrarAluno(aluno4);
    gerenciador.cadastrarAluno(aluno6);

    while (true) {
        IO.println("\n===== CT HAVAN COSTA =====");
        IO.println("1 - Cadastrar aluno");
        IO.println("2 - Listar alunos");
        IO.println("3 - Consultar faturamento");
        IO.println("4 - Buscar aluno por nome");
        IO.println("5 - Buscar aluno por mensalidade");
        IO.println("6 - Encontrar a maior mensalidade");
        IO.println("7 - Listar alunos que pagam a maior mensalidade");
        IO.println("8 - Sair");
        IO.println("Escolha uma opção:");

        int opcao;
        if (entrada.hasNextInt()) {
            opcao = entrada.nextInt();
            entrada.nextLine();
        } else {
            IO.println("Opção inválida! Digite um número de 1 a 8.");
            entrada.nextLine();
            continue;
        }

        switch (opcao) {
            case 1:
                IO.println("Digite o nome do aluno:");
                String nomeDigitado = entrada.nextLine().trim();
                if (nomeDigitado.isBlank()) {
                    IO.println("Nome inválido! Digite um nome.");
                    break;
                }

                if (gerenciador.buscarAlunoPorNome(nomeDigitado) != null) {
                    IO.println("Aluno já cadastrado!");
                    break;
                }
                IO.println("Nome disponível para cadastro!");

                IO.println("Digite a idade do aluno:");
                int idade = 0;

                while (true) {
                    if (entrada.hasNextInt()) {
                        idade = entrada.nextInt();

                        if (idade > 0) {
                            break;
                        }
                        IO.println("Idade inválida! Digite uma idade maior que zero:");
                    } else {
                        IO.println("Entrada inválida! Digite a idade usando números inteiros:");
                        entrada.next();
                    }
                }

                IO.println("Digite a mensalidade do aluno:");
                double mensalidade = 0;

                while (true) {
                    if (entrada.hasNextDouble()) {
                        mensalidade = entrada.nextDouble();

                        if (mensalidade >= 0) {
                            break;
                        }

                        IO.println("Mensalidade inválida! Digite um valor igual ou maior que zero:");
                    } else {
                        IO.println("Entrada inválida! Digite a mensalidade usando números:");
                        entrada.next();
                    }
                }

                entrada.nextLine();

                boolean ativo = mensalidade > 0;
                Aluno novoAluno = new Aluno(nomeDigitado, idade, mensalidade, ativo);
                gerenciador.cadastrarAluno(novoAluno);

                IO.println("Aluno cadastrado com sucesso!");
                break;

            case 2:
                IO.println("\n===== ALUNOS CADASTRADOS =====");

                for (Aluno a : gerenciador.listarAlunos()) {
                    IO.println(a.getNome() + " - " + a.getIdade() + " anos");
                }
                break;

            case 3:
                double total = gerenciador.calcularFaturamento();
                int quantidadeAtivos = gerenciador.contarAlunosAtivos();

                IO.println("\n===== FATURAMENTO =====");
                IO.println("Alunos ativos: " + quantidadeAtivos);
                IO.println("Faturamento mensal: " + formatarMoeda(total));
                break;

            case 4:
                IO.println("Digite o nome do aluno que deseja buscar:");
                String nomeBusca = entrada.nextLine().trim();

                Aluno alunoEncontrado = gerenciador.buscarAlunoPorNome(nomeBusca);

                if (alunoEncontrado != null) {
                    IO.println(alunoEncontrado.getNome() + " - " + alunoEncontrado.getIdade() + " anos");
                } else {
                    IO.println("Aluno não encontrado!");
                }
                break;

                case 5:
                IO.println("Digite a mensalidade que deseja buscar:");

                double valorMensalidade;

                while (true) {
                    if (entrada.hasNextDouble()) {
                        valorMensalidade = entrada.nextDouble();

                        if (valorMensalidade >= 0) {
                            break;
                        }

                        IO.println("Mensalidade inválida! Digite um valor igual ou maior que zero:");
                    } else {
                        IO.println("Entrada inválida! Digite um valor numérico:");
                        entrada.next();
                    }
                }
                entrada.nextLine();
                List<Aluno> encontrados = gerenciador.buscarAlunosPorMensalidade(valorMensalidade);

                if (encontrados.isEmpty()) {
                    IO.println("Valor não encontrado!");
                } else {
                    for (Aluno a : encontrados) {
                        IO.println(a.getNome() + " - " + formatarMoeda(a.getMensalidade()));
                    }
                }
                break;

            case 6:
                double maiorMensalidade = gerenciador.encontrarMaiorMensalidade();

                IO.println("Maior mensalidade: " + formatarMoeda(maiorMensalidade));
                break;

            case 7:
                IO.println("===== ALUNOS COM MAIOR MENSALIDADE =====");
                List<Aluno> alunosMaiorMensalidade = gerenciador.listarAlunosComMaiorMensalidade();

                if (alunosMaiorMensalidade.isEmpty()) {

                    IO.println("Nenhum aluno ativo encontrado.");
                } else {
                    for (Aluno a : alunosMaiorMensalidade) {
                        IO.println(a.getNome() + " - Mensalidade: " + formatarMoeda(a.getMensalidade()));
                    }
                }
                break;

            case 8:
                IO.println("Encerrando o sistema. Até mais!");
                return;
            default:
                IO.println("Essa opção ainda não foi implementada.");
        }
    }
}

String formatarMoeda(double valor) {
    return String.format(java.util.Locale.of("pt", "BR"), "R$ %.2f", valor);
}
