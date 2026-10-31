public class Aluno {
    private String nome;
    private int idade;
    private double mensalidade;
    private boolean ativo;

    public Aluno(String nome, int idade, double mensalidade, boolean ativo) {
        setNome(nome);
        setIdade(idade);
        this.ativo = ativo;
        setMensalidade(mensalidade);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido!");
        }
    }

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        if (ativo && mensalidade == 0) {
            throw new IllegalArgumentException("Não é possível ativar o aluno sem mensalidade!");
        }
        this.ativo = ativo;
    }
    public void desativar() {
        setAtivo(false);
    }
    public void ativar() {
        setAtivo(true);
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        if (idade > 0) {
            this.idade = idade;
        } else {
            throw new IllegalArgumentException("Idade inválida!");
        }
    }

    public double getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(double mensalidade) {
        if (mensalidade < 0) {
            throw new IllegalArgumentException("Mensalidade inválida!");
        } else if (ativo && mensalidade == 0) {
            throw new IllegalArgumentException("Aluno ativo não pode ter mensalidade zero!");
        } else {
            this.mensalidade = mensalidade;
        }
    }

    public boolean podeTreinar() {
        return isAtivo();
    }

    public boolean isIsento() {
        return getMensalidade() == 0;
    }

    public boolean podeTreinarGratis() {
        return isIsento() && isAtivo();
    }

    public String situacaoMensalidade() {
        if (getMensalidade() == 0) {
            return "Isento";
        } else {
            return "Pagante";
        }
    }
}

