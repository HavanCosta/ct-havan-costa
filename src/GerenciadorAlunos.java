import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class GerenciadorAlunos {
    private final List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    public Aluno buscarAlunoPorNome(String nomeBusca) {
        String nomeNormalizado = Normalizer.normalize(
                nomeBusca.trim(), Normalizer.Form.NFD
        ).replaceAll("\\p{M}", "");

        for (Aluno aluno : alunos) {
            String nomeAluno = Normalizer.normalize(
                    aluno.getNome().trim(), Normalizer.Form.NFD
            ).replaceAll("\\p{M}", "");

            if (nomeAluno.equalsIgnoreCase(nomeNormalizado)) {
                return aluno;
            }
        }
        return null;
    }

    public double calcularFaturamento() {
        double total = 0;
        for (Aluno aluno : alunos) {
            if (aluno.isAtivo()) {
                total += aluno.getMensalidade();
            }
        }
        return total;
    }

    public int contarAlunosAtivos() {
        int quantidade = 0;
        for (Aluno aluno : alunos) {
            if (aluno.isAtivo()) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public double encontrarMaiorMensalidade() {
        double maiorValor = 0;
        for (Aluno aluno : alunos) {
            if (aluno.isAtivo() && aluno.getMensalidade() > maiorValor) {
                maiorValor = aluno.getMensalidade();
            }
        }
        return maiorValor;
    }

    public List<Aluno> listarAlunosComMaiorMensalidade() {
        double maiorValor = encontrarMaiorMensalidade();
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.isAtivo() && aluno.getMensalidade() == maiorValor) {
                resultado.add(aluno);
            }
        }
        return resultado;
    }

    public List<Aluno> buscarAlunosPorMensalidade(double valor) {
        List<Aluno> resultado = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getMensalidade() == valor) {
                resultado.add(aluno);
            }
        }
        return resultado;
    }
}
