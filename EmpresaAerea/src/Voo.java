
public class Voo {
	private int codigo;
	private float distancia;
	private int assentosDisponiveis = 10;

	public Voo(int codigo, float distancia) {
		setCodigo(codigo);
		setDistancia(distancia);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public int getAssentosDisponiveis() {
		return assentosDisponiveis;
	}

	public void setAssentosDisponiveis(int assentosDisponiveis) {
		this.assentosDisponiveis = assentosDisponiveis;
	}
	
	public void printaVoo() {
		System.out.println("Número do vôo: " + getCodigo());
		System.out.println("Distancia a ser percorrida: " + getDistancia() + "\n");
	}
	
	//**//
	public void desocupaAssento() {
		this.assentosDisponiveis += 1;
	}
	
	public void ocupaAssento() {
		this.assentosDisponiveis -= 1;
	}
}
