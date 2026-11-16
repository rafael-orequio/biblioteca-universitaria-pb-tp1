import java.util.Scanner;

/**
 * Projeto de Bloco: Desenvolvimento Back-End - TP1
 * Sistema de Gestão de Biblioteca Universitária (Protótipo Standalone)
 * Aluno: Rafael Rainha de Orequio
 */
public class BibliotecaStandaloneTP1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // =====================================================================
        // REGRAS DE NEGÓCIO (Constantes)
        // =====================================================================
        final int PRAZO_EMPRESTIMO_DIAS = 14;
        final int LIMITE_EMPRESTIMOS_ATIVOS = 3;
        final double VALOR_MULTA_DIARIA = 2.00; // Valor ilustrativo (Suposição A-02)

        // =====================================================================
        // ESTADO SIMULADO EM MEMÓRIA (Apenas tipos primitivos)
        // =====================================================================
        // Dados do Membro simulado (Matrícula: 202601)
        int matriculaCadastrada = 202601;
        int emprestimosAtivos = 0;
        double multasAcumuladas = 0.0;

        // Dados do Livro simulado (ISBN: 9788533302273 - Clean Code) com 3 exemplares
        boolean exemplar1Disponivel = true;
        boolean exemplar2Disponivel = true;
        boolean exemplar3Disponivel = true;

        // Controle dos dias de retirada e prazos previstos de devolução
        int retiradaExemplar1 = 0;
        int retiradaExemplar2 = 0;
        int retiradaExemplar3 = 0;

        int prazoExemplar1 = 0;
        int prazoExemplar2 = 0;
        int prazoExemplar3 = 0;

        // Contador de reservas do livro
        int reservasLivro = 0;

        boolean executando = true;

        System.out.println("=========================================================");
        System.out.println("   SISTEMA DE GESTÃO DE BIBLIOTECA UNIVERSITÁRIA (TP1)   ");
        System.out.println("=========================================================");
        System.out.println("Membro de teste -> Matrícula: " + matriculaCadastrada);
        System.out.println("Livro de teste  -> Clean Code (3 exemplares físicos: 1, 2 e 3)");

        while (executando) {
            System.out.println("\n---------------- MENU PRINCIPAL ----------------");
            System.out.println("1 - Consultar situação do acervo e do membro");
            System.out.println("2 - Realizar empréstimo");
            System.out.println("3 - Registrar devolução e calcular multa");
            System.out.println("4 - Solicitar reserva do livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("[ERRO] Entrada inválida. Digite apenas o número da opção desejada.");
                scanner.next();
                continue;
            }

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // CONSULTA DE DISPONIBILIDADE E SITUAÇÃO DO MEMBRO
                    System.out.println("\n--- SITUAÇÃO ATUAL ---");
                    System.out.println("Membro (Matrícula " + matriculaCadastrada + "):");
                    System.out.println("  Empréstimos ativos: " + emprestimosAtivos + "/" + LIMITE_EMPRESTIMOS_ATIVOS);
                    System.out.printf("  Multas acumuladas: R$ %.2f%n", multasAcumuladas);

                    System.out.println("\nLivro: Clean Code (3 exemplares)");
                    System.out.println("  Exemplar 1: " + (exemplar1Disponivel ? "DISPONÍVEL" : "EMPRESTADO (Retirada: dia " + retiradaExemplar1 + " | Prazo: dia " + prazoExemplar1 + ")"));
                    System.out.println("  Exemplar 2: " + (exemplar2Disponivel ? "DISPONÍVEL" : "EMPRESTADO (Retirada: dia " + retiradaExemplar2 + " | Prazo: dia " + prazoExemplar2 + ")"));
                    System.out.println("  Exemplar 3: " + (exemplar3Disponivel ? "DISPONÍVEL" : "EMPRESTADO (Retirada: dia " + retiradaExemplar3 + " | Prazo: dia " + prazoExemplar3 + ")"));
                    System.out.println("  Reservas na fila: " + reservasLivro);
                    break;

                case 2:
                    // REALIZAR EMPRÉSTIMO
                    System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
                    System.out.print("Informe a matrícula do membro (ex: 202601): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Matrícula inválida. Digite apenas números.");
                        scanner.next();
                        break;
                    }
                    int matriculaInformada = scanner.nextInt();

                    if (matriculaInformada != matriculaCadastrada) {
                        System.out.println("[RECUSADO] Matrícula não encontrada no sistema.");
                        break;
                    }

                    // Regra: máximo de 3 empréstimos ativos simultâneos
                    if (emprestimosAtivos >= LIMITE_EMPRESTIMOS_ATIVOS) {
                        System.out.println("[RECUSADO] O membro já atingiu o limite de " + LIMITE_EMPRESTIMOS_ATIVOS + " empréstimos ativos.");
                        break;
                    }

                    System.out.print("Informe o número do exemplar desejado (1, 2 ou 3): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Número de exemplar inválido. Digite 1, 2 ou 3.");
                        scanner.next();
                        break;
                    }
                    int numExemplarEmp = scanner.nextInt();

                    System.out.print("Informe o dia atual da retirada (número inteiro, ex: 10): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Dia inválido. Digite apenas um número inteiro.");
                        scanner.next();
                        break;
                    }
                    int diaRetirada = scanner.nextInt();

                    if (diaRetirada <= 0) {
                        System.out.println("[ERRO] O dia de retirada deve ser maior que zero.");
                        break;
                    }

                    int diaPrevistoDevolucao = diaRetirada + PRAZO_EMPRESTIMO_DIAS;

                    if (numExemplarEmp == 1) {
                        if (exemplar1Disponivel) {
                            exemplar1Disponivel = false;
                            retiradaExemplar1 = diaRetirada;
                            prazoExemplar1 = diaPrevistoDevolucao;
                            emprestimosAtivos++;
                            System.out.println("[SUCESSO] Exemplar 1 emprestado! Devolução prevista até o dia " + prazoExemplar1 + ".");
                        } else {
                            System.out.println("[RECUSADO] O Exemplar 1 já está emprestado.");
                        }
                    } else if (numExemplarEmp == 2) {
                        if (exemplar2Disponivel) {
                            exemplar2Disponivel = false;
                            retiradaExemplar2 = diaRetirada;
                            prazoExemplar2 = diaPrevistoDevolucao;
                            emprestimosAtivos++;
                            System.out.println("[SUCESSO] Exemplar 2 emprestado! Devolução prevista até o dia " + prazoExemplar2 + ".");
                        } else {
                            System.out.println("[RECUSADO] O Exemplar 2 já está emprestado.");
                        }
                    } else if (numExemplarEmp == 3) {
                        if (exemplar3Disponivel) {
                            exemplar3Disponivel = false;
                            retiradaExemplar3 = diaRetirada;
                            prazoExemplar3 = diaPrevistoDevolucao;
                            emprestimosAtivos++;
                            System.out.println("[SUCESSO] Exemplar 3 emprestado! Devolução prevista até o dia " + prazoExemplar3 + ".");
                        } else {
                            System.out.println("[RECUSADO] O Exemplar 3 já está emprestado.");
                        }
                    } else {
                        System.out.println("[ERRO] Exemplar inválido. Escolha 1, 2 ou 3.");
                    }
                    break;

                case 3:
                    // REGISTRAR DEVOLUÇÃO E CALCULAR MULTA
                    System.out.println("\n--- REGISTRAR DEVOLUÇÃO ---");
                    System.out.print("Informe o número do exemplar devolvido (1, 2 ou 3): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Número de exemplar inválido. Digite 1, 2 ou 3.");
                        scanner.next();
                        break;
                    }
                    int numExemplarDev = scanner.nextInt();

                    boolean exemplarEstavaEmprestado = false;
                    int diaRetiradaRegistrado = 0;
                    int prazoLimite = 0;

                    if (numExemplarDev == 1 && !exemplar1Disponivel) {
                        exemplarEstavaEmprestado = true;
                        diaRetiradaRegistrado = retiradaExemplar1;
                        prazoLimite = prazoExemplar1;
                    } else if (numExemplarDev == 2 && !exemplar2Disponivel) {
                        exemplarEstavaEmprestado = true;
                        diaRetiradaRegistrado = retiradaExemplar2;
                        prazoLimite = prazoExemplar2;
                    } else if (numExemplarDev == 3 && !exemplar3Disponivel) {
                        exemplarEstavaEmprestado = true;
                        diaRetiradaRegistrado = retiradaExemplar3;
                        prazoLimite = prazoExemplar3;
                    } else if (numExemplarDev < 1 || numExemplarDev > 3) {
                        System.out.println("[ERRO] Exemplar inválido. Escolha 1, 2 ou 3.");
                        break;
                    }

                    if (!exemplarEstavaEmprestado) {
                        System.out.println("[RECUSADO] Não há empréstimo ativo para o Exemplar " + numExemplarDev + ".");
                        break;
                    }

                    System.out.print("Informe o dia efetivo da devolução (ex: 24 no prazo, ou 27 com atraso): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Dia inválido. Digite apenas um número inteiro.");
                        scanner.next();
                        break;
                    }
                    int diaDevolucao = scanner.nextInt();

                    // Validação: impede devolução em data anterior à retirada
                    if (diaDevolucao < diaRetiradaRegistrado) {
                        System.out.println("[RECUSADO] O dia da devolução (" + diaDevolucao + ") não pode ser anterior ao dia da retirada (" + diaRetiradaRegistrado + ").");
                        break;
                    }

                    int diasAtraso = diaDevolucao - prazoLimite;

                    if (diasAtraso > 0) {
                        double valorMulta = diasAtraso * VALOR_MULTA_DIARIA;
                        multasAcumuladas += valorMulta;
                        System.out.println("[ATRASO] Devolução realizada com " + diasAtraso + " dia(s) corrido(s) de atraso.");
                        System.out.printf("[MULTA] Valor aplicado: R$ %.2f (R$ %.2f/dia).%n", valorMulta, VALOR_MULTA_DIARIA);
                    } else {
                        System.out.println("[NO PRAZO] Devolução concluída dentro do prazo. Nenhuma multa aplicada.");
                    }

                    // Libera o exemplar e atualiza contador do membro
                    if (numExemplarDev == 1) {
                        exemplar1Disponivel = true;
                        retiradaExemplar1 = 0;
                        prazoExemplar1 = 0;
                    } else if (numExemplarDev == 2) {
                        exemplar2Disponivel = true;
                        retiradaExemplar2 = 0;
                        prazoExemplar2 = 0;
                    } else {
                        exemplar3Disponivel = true;
                        retiradaExemplar3 = 0;
                        prazoExemplar3 = 0;
                    }
                    emprestimosAtivos--;
                    System.out.println("[SUCESSO] Exemplar " + numExemplarDev + " agora está DISPONÍVEL no acervo.");
                    break;

                case 4:
                    // SOLICITAR RESERVA DO LIVRO
                    System.out.println("\n--- SOLICITAR RESERVA ---");
                    System.out.print("Informe a matrícula do membro (ex: 202601): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("[ERRO] Matrícula inválida. Digite apenas números.");
                        scanner.next();
                        break;
                    }
                    int matReserva = scanner.nextInt();

                    if (matReserva != matriculaCadastrada) {
                        System.out.println("[RECUSADO] Matrícula não encontrada no sistema.");
                        break;
                    }

                    // Regra: só permite reserva se todos os exemplares estiverem emprestados
                    boolean haExemplarDisponivel = exemplar1Disponivel || exemplar2Disponivel || exemplar3Disponivel;

                    if (haExemplarDisponivel) {
                        System.out.println("[RECUSADO] Reserva não permitida: há exemplar disponível na estante.");
                        System.out.println("           Utilize a opção 2 para realizar o empréstimo diretamente.");
                    } else {
                        reservasLivro++;
                        System.out.println("[SUCESSO] Reserva registrada! Todos os exemplares estão emprestados.");
                        System.out.println("          Posição atual na fila de reserva: " + reservasLivro + "º.");
                    }
                    break;

                case 0:
                    executando = false;
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("[ERRO] Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}