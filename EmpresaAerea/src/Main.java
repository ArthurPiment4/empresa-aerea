import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Main {
	private static int[][] matrizVoosPassageiros = new int[10][10];
	private static Voo[] voosCadastrados = new Voo[10];
	private static Passageiro[] passageirosCadastrados = new Passageiro[10];

	public static void main(String[] args) {
		preencheMatrizVoosPassageiros();
		int opcaoMenu;
		do {
			opcaoMenu = mostraMenu();
			trataFluxoOpcaoMenuPrincipal(opcaoMenu);
		} while (opcaoMenu != 0);
	}

	private static void preencheMatrizVoosPassageiros() {
		int[][] matrizVoosPassageirosCadastrados = getMatrizVoosPassageiros();
		for (int linha = 0; linha < matrizVoosPassageirosCadastrados.length; linha++) {
			for (int coluna = 0; coluna < matrizVoosPassageirosCadastrados.length; coluna++) {
				matrizVoosPassageirosCadastrados[linha][coluna] = 0;
			}
		}
	}

	public static int mostraMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Opções:");
		System.out.println("1 - Cadastrar voo;");
		System.out.println("2 - Cadastrar passageiro;");
		System.out.println("3 - Ver voos;");
		System.out.println("4 - Ver passageiro;");
		System.out.println("5 - Alterar passageiro;");
		System.out.println("6 - Excluir passageiro;");
		System.out.println("7 - Alterar um voo;");
		System.out.println("8 - Excluir um voo;");
		System.out.println("0 - Encerrar programa.");
		int numeroOpcao = scanner.nextInt();
		return numeroOpcao;
	}

	public static void trataFluxoOpcaoMenuPrincipal(int opcaoEscolhida) {
		switch (opcaoEscolhida) {
		case 1:
			cadastraVoo();
			break;
		case 2:
			cadastraPassageiro();
			break;
		case 3:
			mostraVoos();
			break;
		case 4:
			mostraPassageiros();
			break;
		case 5:
			alteraPassageiro();
			break;
		case 6:
			excluiPassageiro();
			break;
		case 7:
			// alteraVoo();
			break;
		case 8:
			excluiVoo();
			break;
		default:
			break;
		}
	}

	//**//
	private static void excluiPassageiro() {
		Scanner sc = new Scanner(System.in);
		Passageiro[] passageiros = getPassageirosCadastrados();
		System.out.println("Insira o c�digo do passageiro que deseja excluir: ");
		int codigoPassageiro = sc.nextInt();
		System.out.println("Esse passageiro faz parte de algum voo?");
		System.out.println("Se sim, digite o codigo do voo, se nao, digite 0");
		int codigoVoo = sc.nextInt();
		for (Passageiro passageiro : passageiros) {
			if (passageiro != null) {
				if (passageiro.getCodigo() == codigoPassageiro) {
					passageiro.setNome(null, null);
					passageiro.setCodigo(0);
					System.out.println("Passageiro excluido com sucesso!");
					if (codigoVoo==0) {
						
					} else {
						desocupaAssentoVoo(codigoVoo);
					}
					
					for (int l = 0; l < matrizVoosPassageiros.length; l++) {
						for (int c = 0; c < matrizVoosPassageiros[l].length; c++) {
							if (matrizVoosPassageiros[l][c] == codigoPassageiro) {
								matrizVoosPassageiros[l][c] = 0;
							}
						}
					}
				}
			}
		}
	}

	private static void alteraPassageiro() {
		Scanner scanner = new Scanner(System.in);
		Passageiro[] passageiros = getPassageirosCadastrados();
		System.out.print("Insira o c�digo do passageiro que deseja alterar: ");
		int codigoPassageiro = scanner.nextInt();
		for (Passageiro passageiro : passageiros) {
			if (passageiro != null) {
				if (passageiro.getCodigo() == codigoPassageiro) {
					System.out.print("Insira o novo primeiro nome: ");
					String primeiroNome = scanner.next();
					System.out.print("Insira o �ltimo sobrenome: ");
					String ultimoSobrenome = scanner.next();
					passageiro.setNome(primeiroNome, ultimoSobrenome);
				}
			}
		}
	}

	private static void mostraPassageiros() {
		int opcaoMenu = mostraMenuPassageiros();
		trataFluxoOpcaoMenuVerPassageiros(opcaoMenu);
	}

	private static void trataFluxoOpcaoMenuVerPassageiros(int opcaoMenu) {
		switch (opcaoMenu) {
		case 1:
			mostraPassageiroEspecifico();
			break;
		case 2:
			mostraPassageirosDeUmVoo();
			break;
		default:
			break;
		}

	}

	private static void mostraPassageirosDeUmVoo() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o c�digo do v�o que deseja ver: ");
		int codigoVoo = scanner.nextInt();
		int[][] voosCadastrados = getMatrizVoosPassageiros();
		int[] codigoPassageiros = new int[10];
		for (int coluna = 0; coluna < voosCadastrados.length; coluna++) {
			if (matrizVoosPassageiros[0][coluna] == codigoVoo) {
				int linha = 1;
				while (linha != 10) {
					codigoPassageiros[linha - 1] = matrizVoosPassageiros[linha][coluna];
					linha++;
				}
			}
		}
		buscaEMostraInformacoesPassageiros(codigoPassageiros);
	}

	private static void buscaEMostraInformacoesPassageiros(int[] codigoPassageiros) {
		Passageiro[] passageiros = getPassageirosCadastrados();
		for (Passageiro passageiro : passageiros) {
			if (passageiro != null) {
				for (int codigoPassageiro : codigoPassageiros) {
					if (passageiro.getCodigo() == codigoPassageiro) {
						if (codigoPassageiro == 0) {
							//**//
						} else {
							System.out.println("Nome completo do passageiro: " + passageiro.getNome());
							System.out.println("C�digo do passageiro: " + passageiro.getCodigo());
						}
					}
				}
			}
		}
	}

	private static void mostraPassageiroEspecifico() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Insira o c�digo do passageiro que deseja consultar: ");
		int codigoPassageiro = scanner.nextInt();
		Passageiro[] passageirosCadastrados = getPassageirosCadastrados();
		for (Passageiro passageiro : passageirosCadastrados) {
			if (passageiro != null) {
				if (passageiro.getCodigo() == codigoPassageiro) {
					System.out.println("Nome completo: " + passageiro.getNome());
					System.out.println("Codigo: " + passageiro.getCodigo());
					System.out.println("\n");
					return;
				}
			}
		}
	}

	private static int mostraMenuPassageiros() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Op��es de visualiza��o de passageiro:");
		System.out.println("1 - Ver passageiro espec�fico");
		System.out.println("2 - Ver passageiros de um v�o");
		int numeroOpcao = scanner.nextInt();
		return numeroOpcao;
	}

	public static void cadastraVoo() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInsira o número do voo: ");
		int codigoVoo = scanner.nextInt();
		System.out.print("Insira a distância a ser percorrida:");
		float distanciaVoo = scanner.nextFloat();
		Voo voo = new Voo(codigoVoo, distanciaVoo);
		System.out.println("\nVoo cadastrado com sucesso.");
		voo.printaVoo();
		adicionaListaVoosCadastrados(voo);
		adicionaCadastroDeVoo(codigoVoo);
	}

	public static void adicionaListaVoosCadastrados(Voo voo) {
		Voo[] voosCadastrados = getVoosCadastrados();
		for (int i = 0; i < voosCadastrados.length; i++) {
			if (voosCadastrados[i] == null) {
				voosCadastrados[i] = voo;
				return;
			}
		}
	}

	private static void adicionaCadastroDeVoo(int codigoVoo) {
		int[][] matrizVoosPassageirosCadastrados = getMatrizVoosPassageiros();
		for (int coluna = 0; coluna < matrizVoosPassageirosCadastrados.length; coluna++) {
			if (matrizVoosPassageirosCadastrados[0][coluna] == 0) {
				setMatrizVoosPassageiros(codigoVoo, 0, coluna);
				break;
			}
		}
	}
	
	
	public static void excluiVoo() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInsira o n�mero do v�o: ");
		int codigoVoo = scanner.nextInt();
		Voo voo = new Voo(0, 0);
		System.out.println("\nVoo excluido com sucesso.");
		excluiCadastroDeVoo(codigoVoo);
		excluiListaVoosCadastrados(voo);
	}
	
	
	
	public static void excluiListaVoosCadastrados(Voo voo) {
		Voo[] voosCadastrados = getVoosCadastrados();
		for (int i = 0; i < voosCadastrados.length; i++) {
			if (voosCadastrados[i] == null) {
				voosCadastrados[i] = null;
				return;
			}
		}
	}

	private static void excluiCadastroDeVoo(int codigoVoo) {
		int[][] matrizVoosPassageirosCadastrados = getMatrizVoosPassageiros();
		for (int coluna = 0; coluna < matrizVoosPassageirosCadastrados.length; coluna++) {
			if (matrizVoosPassageirosCadastrados[0][coluna] == codigoVoo) {
				setMatrizVoosPassageiros(0, 0, coluna);
				
			
			for (int linha = 1; linha < matrizVoosPassageiros.length; linha++) {
				if (matrizVoosPassageiros[linha][coluna] != 0) {
					matrizVoosPassageiros[linha][coluna] = 0;
				}
			}
			break;
		}
	}
	}
	
	public static void cadastraPassageiro() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nInsira o Código do passageiro: ");
		int codigoPassageiro = scanner.nextInt();
		System.out.print("Insira o primeiro nome do passageiro:");
		String primeiroNome = scanner.next();
		System.out.print("Insira o último nome do passageiro:");
		String ultimoSobrenome = scanner.next();
		Passageiro passageiro = new Passageiro(codigoPassageiro, primeiroNome, ultimoSobrenome);
		System.out.println("\nPassageiro cadastrado com sucesso.");
		passageiro.printaPassageiro();
		adicionaPassageiroCadastrado(passageiro);
		System.out.println(
				"\nDeseja cadastrar o passageiro em um v�o?\nSe sim, digite o c�digo do v�o, se n�o digite 0.");
		int codigoVoo = scanner.nextInt();
		if (codigoVoo != 0) {
			adicionaPassageiroEmVoo(codigoVoo, codigoPassageiro);
			ocupaAssentoVoo(codigoVoo);
		}
	}

	private static void adicionaPassageiroCadastrado(Passageiro passageiro) {
		Passageiro[] passageirosCadastrados = getPassageirosCadastrados();
		for (int i = 0; i < passageirosCadastrados.length; i++) {
			if (passageirosCadastrados[i] == null) {
				passageirosCadastrados[i] = passageiro;
				return;
			}
		}
	}

	
	//**//
	private static void desocupaAssentoVoo(int codigoVoo) {
		Voo[] voosCadastrados = getVoosCadastrados();

		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getCodigo() == codigoVoo) {
					vooCadastrado.desocupaAssento();
				}
			}
		}
	}
	
	private static void ocupaAssentoVoo(int codigoVoo) {
		Voo[] voosCadastrados = getVoosCadastrados();

		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getCodigo() == codigoVoo) {
					vooCadastrado.ocupaAssento();
				}
			}
		}
	}

	public static void adicionaPassageiroEmVoo(int codigoVoo, int codigoPassageiro) {
		int[][] matrizVoosPassageiros = getMatrizVoosPassageiros();
		int colunaVooDoPassageiro = 0;
		for (int coluna = 0; coluna < matrizVoosPassageiros.length; coluna++) {
			if (matrizVoosPassageiros[0][coluna] == codigoVoo) {
				colunaVooDoPassageiro = coluna;
			}
		}
		for (int linha = 0; linha < matrizVoosPassageiros.length; linha++) {
			if (matrizVoosPassageiros[linha][colunaVooDoPassageiro] == 0) {
				matrizVoosPassageiros[linha][colunaVooDoPassageiro] = codigoPassageiro;
				return;
			}
		}
	}

	public static int[][] getMatrizVoosPassageiros() {
		return matrizVoosPassageiros;
	}

	public static void setMatrizVoosPassageiros(int codigo, int linha, int coluna) {
		matrizVoosPassageiros[linha][coluna] = codigo;
	}

	public static void mostraVoos() {
		int opcaoMenu = mostraMenuVerVoos();
		trataFluxoOpcaoMenuVerVoos(opcaoMenu);
	}

	private static int mostraMenuVerVoos() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Opções de visualização de voo:");
		System.out.println("1 - Ver todos os voos;");
		System.out.println("2 - Mais passageiros;");
		System.out.println("3 - Menos passageiros");
		System.out.println("4 - Maior distância;");
		System.out.println("5 - Menor distância;");
		System.out.println("6 - Ocupação média dos voos;");
		int numeroOpcao = scanner.nextInt();
		return numeroOpcao;
	}

	private static void trataFluxoOpcaoMenuVerVoos(int opcaoMenu) {
		switch (opcaoMenu) {
		case 1:
			mostraTodosOsVoos();
			break;
		case 2:
			mostraVooComMaisPassageiros();
			break;
		case 3:
			mostraVooComMenosPassageiros();
			break;
		case 4:
			mostraVooComMaiorDistancia();
			break;
		case 5:
			mostraVooComMenorDistancia();
			break;
		case 6:
			mostraOcupacaoMediaDosVoos();
			break;
		default:
			break;
		}
	}

	private static void mostraOcupacaoMediaDosVoos() {
		Voo[] voosCadastrados = getVoosCadastrados();
		float ocupacaoMedia = 0;
		int numVoos = 0;
		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				int numeroPassageiros = 10 - vooCadastrado.getAssentosDisponiveis();
				ocupacaoMedia += numeroPassageiros;
				numVoos++;
			}
		}
		System.out.println("A ocupação média dos voos é de " 
		+ ocupacaoMedia / numVoos + " pessoas.");

	}

	private static void mostraVooComMenorDistancia() {
		Voo[] voosCadastrados = getVoosCadastrados();
		float maiorDistancia = voosCadastrados[0].getDistancia();
		int codigoVoo = voosCadastrados[0].getCodigo();
		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getDistancia() < maiorDistancia) {
					maiorDistancia = vooCadastrado.getDistancia();
					codigoVoo = vooCadastrado.getCodigo();
				}
			}
		}
		System.out.println(
				"O voo " + codigoVoo + " � o que viajar� a menor dist�ncia, " + maiorDistancia + " quil�metros.");

	}

	private static void mostraVooComMaiorDistancia() {
		Voo[] voosCadastrados = getVoosCadastrados();
		float maiorDistancia = 0;
		int codigoVoo = 0;
		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getDistancia() > maiorDistancia) {
					maiorDistancia = vooCadastrado.getDistancia();
					codigoVoo = vooCadastrado.getCodigo();
				}
			}
		}
		System.out.println(
				"O voo " + codigoVoo + " � o que viajar� a maior dist�ncia, " + maiorDistancia + " quil�metros.");
	}

	private static void mostraVooComMenosPassageiros() {
		Voo[] voosCadastrados = getVoosCadastrados();
		int assentosDisponiveis = 0;
		int codigoVoo = 0;
		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getAssentosDisponiveis() > assentosDisponiveis) {
					assentosDisponiveis = vooCadastrado.getAssentosDisponiveis();
					codigoVoo = vooCadastrado.getCodigo();
				}
			}
		}
		int numeroPassageiros = 10 - assentosDisponiveis;
		System.out.println("O voo " + codigoVoo + " � o menos cheio, com " + numeroPassageiros + " passageiros.");

	}

	private static void mostraVooComMaisPassageiros() {
		Voo[] voosCadastrados = getVoosCadastrados();
		int assentosDisponiveis = 10;
		int codigoVoo = 0;
		for (Voo vooCadastrado : voosCadastrados) {
			if (vooCadastrado != null) {
				if (vooCadastrado.getAssentosDisponiveis() < assentosDisponiveis) {
					assentosDisponiveis = vooCadastrado.getAssentosDisponiveis();
					codigoVoo = vooCadastrado.getCodigo();
				}
			}
		}
		int numeroPassageiros = 10 - assentosDisponiveis;
		System.out.println("O voo " + codigoVoo + " � o mais cheio, com " + numeroPassageiros + " passageiros.");
	}

	private static void mostraTodosOsVoos() {
		int[][] matrizVoosPassageirosCadastrados = getMatrizVoosPassageiros();
		for (int linha = 0; linha < matrizVoosPassageirosCadastrados.length; linha++) {
			for (int coluna = 0; coluna < matrizVoosPassageirosCadastrados.length; coluna++) {
				System.out.print(matrizVoosPassageirosCadastrados[linha][coluna]);
				System.out.print(" | ");
			}
			System.out.print("\n");
		}
	}

	public static Voo[] getVoosCadastrados() {
		return voosCadastrados;
	}

	public static void setVoosCadastrados(Voo[] voosCadastrados) {
		Main.voosCadastrados = voosCadastrados;
	}

	public static Passageiro[] getPassageirosCadastrados() {
		return passageirosCadastrados;
	}

	public static void setPassageirosCadastrados(Passageiro[] passageirosCadastrados) {
		Main.passageirosCadastrados = passageirosCadastrados;
	}

}
