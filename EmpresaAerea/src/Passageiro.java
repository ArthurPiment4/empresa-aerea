
public class Passageiro {

	private int codigo;
	private String nome;
	

	public Passageiro(int codigo, String primeiroNome, String ultimoSobrenome) {
		setCodigo(codigo);
		setNome(primeiroNome, ultimoSobrenome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String primeiroNome, String ultimoSobrenome) {
		this.nome = primeiroNome + " " + ultimoSobrenome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void printaPassageiro() {
		System.out.println("Código do passageiro: " + getCodigo());
		System.out.println("Nome do passageiro: " + getNome() + "\n");
	}
	
}
