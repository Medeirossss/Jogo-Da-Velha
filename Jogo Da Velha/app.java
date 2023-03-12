import java.util.Scanner;

public class JogoDaVelha {
    private char[][] tabuleiro = new char[3][3];
    private char jogadorAtual;
    private boolean fimDeJogo;

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha!");
        System.out.println("Jogador 1, escolha X ou O:");
        char escolhaJogador1 = scanner.nextLine().charAt(0);
        char escolhaJogador2 = (escolhaJogador1 == 'X') ? 'O' : 'X';
        jogadorAtual = escolhaJogador1;

        inicializarTabuleiro();

        while (!fimDeJogo) {
            exibirTabuleiro();
            int[] jogada = obterJogada();
            realizarJogada(jogada[0], jogada[1]);
            verificarFimDeJogo();
            jogadorAtual = (jogadorAtual == escolhaJogador1) ? escolhaJogador2 : escolhaJogador1;
        }

        exibirTabuleiro();
        scanner.close();
    }

    private void inicializarTabuleiro() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                tabuleiro[linha][coluna] = '-';
            }
        }
    }

    private void exibirTabuleiro() {
        System.out.println("  0 1 2");
        for (int linha = 0; linha < 3; linha++) {
            System.out.print(linha + " ");
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.print(tabuleiro[linha][coluna] + " ");
            }
            System.out.println();
        }
    }

    private int[] obterJogada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jogador " + (jogadorAtual == 'X' ? "1" : "2") + ", faça sua jogada (linha coluna):");
        int linha = scanner.nextInt();
        int coluna = scanner.nextInt();
        while (!jogadaValida(linha, coluna)) {
            System.out.println("Jogada inválida. Tente novamente:");
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
        }
        return new int[]{linha, coluna};
    }

    private boolean jogadaValida(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            return false;
        }
        return tabuleiro[linha][coluna] == '-';
    }

    private void realizarJogada(int linha, int coluna) {
        tabuleiro[linha][coluna] = jogadorAtual;
    }

    private void verificarFimDeJogo() {
        if (verificarVitoria(jogadorAtual)) {
            System.out.println("Jogador " + (jogadorAtual == 'X' ? "1" : "2") + " venceu!");
            fimDeJogo = true;
        } else if (verificarEmpate()) {
            System.out.println("Empate!");
            fimDeJogo = true;
        }
    }

    private boolean verificarVitoria
