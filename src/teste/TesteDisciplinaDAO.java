package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.DisciplinaDAOImp;
import entidade.Disciplina;
import jdbc.DB;

public class TesteDisciplinaDAO {

    public static void main(String[] args) {

        Connection conexao = null;
        DisciplinaDAOImp disciplinaDAO = null;

        try {
            // Inicia conexão
            conexao = DB.getConexao();
            disciplinaDAO = new DisciplinaDAOImp(conexao);

            // Limpa a tabela para o teste começar do zero 
            try (Statement stmt = conexao.createStatement()) {
                stmt.executeUpdate("TRUNCATE TABLE disciplina RESTART IDENTITY");
                System.out.println("Tabela disciplina limpa e IDs reiniciados.");
            } catch (SQLException e) {
                System.out.println("Aviso: Não foi possível truncar a tabela (talvez ela esteja vazia).");
            }

            System.out.println("\n===== TESTE DA DISCIPLINA DAO =====\n");

            // insert
            System.out.println("--- Inserindo disciplinas ---");
            Disciplina d1 = new Disciplina(null, "Algoritmos", 80);
            Disciplina d2 = new Disciplina(null, "Banco de Dados", 60);
            Disciplina d3 = new Disciplina(null, "Programação Orientada a Objetos", 80);

            disciplinaDAO.insert(d1);
            disciplinaDAO.insert(d2);
            disciplinaDAO.insert(d3);
            System.out.println("Disciplinas inseridas com sucesso.\n");

            // findAll
            System.out.println("--- Listando todas as disciplinas ---");
            List<Disciplina> lista = disciplinaDAO.findAll();
            for (Disciplina d : lista) {
                System.out.println(d.getIdDisciplina() + " | " + d.getNomeDisciplina() + " | " + d.getCargaHoraria() + "h");
            }
            System.out.println();

            // findById
            System.out.println("--- Buscando disciplina por ID (2) ---");
            Disciplina buscada = disciplinaDAO.findById(2);
            if (buscada != null) {
                System.out.println("Encontrada: " + buscada.getNomeDisciplina());
            }
            System.out.println();

            // update
            System.out.println("--- Atualizando carga horária da disciplina ID 1 ---");
            d1.setCargaHoraria(100);
            d1.setNomeDisciplina("Algoritmos e Estrutura de Dados");
            disciplinaDAO.update(d1);
            System.out.println("Nova versão: " + disciplinaDAO.findById(1));
            System.out.println();

            // deleteById
            System.out.println("--- Removendo disciplina ID 3 ---");
            disciplinaDAO.deleteById(3);

            System.out.println("--- Lista Final após Deleção ---");
            for (Disciplina d : disciplinaDAO.findAll()) {
                System.out.println(d);
            }

        } catch (Exception e) {
            System.err.println("Erro durante os testes de Disciplina: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha a conexão 
            DB.fechaConexao();
        }
    }
}