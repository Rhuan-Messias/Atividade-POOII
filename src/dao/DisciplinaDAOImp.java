package dao;

import entidade.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAOImp implements DisciplinaDAO {

    private final Connection conexao;

    public DisciplinaDAOImp(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insert(Disciplina obj) {
        if (obj == null) throw new IllegalArgumentException("Disciplina não pode ser nula");
        
        String sql = "INSERT INTO disciplina (nome_disciplina, carga_horaria) VALUES (?, ?)";
        try (PreparedStatement pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, obj.getNomeDisciplina());
            pst.setInt(2, obj.getCargaHoraria());
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setIdDisciplina(rs.getInt(1));
                    }
                }
                System.out.println("Disciplina inserida com sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir disciplina", e);
        }
    }

    @Override
    public void update(Disciplina obj) {
        String sql = "UPDATE disciplina SET nome_disciplina = ?, carga_horaria = ? WHERE id_disciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, obj.getNomeDisciplina());
            pst.setInt(2, obj.getCargaHoraria());
            pst.setInt(3, obj.getIdDisciplina());
            pst.executeUpdate();
            System.out.println("Disciplina atualizada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disciplina", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM disciplina WHERE id_disciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Disciplina removida com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar disciplina", e);
        }
    }

    @Override
    public Disciplina findById(Integer id) {
        String sql = "SELECT * FROM disciplina WHERE id_disciplina = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Disciplina(
                        rs.getInt("id_disciplina"),
                        rs.getString("nome_disciplina"),
                        rs.getInt("carga_horaria")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar disciplina", e);
        }
        return null;
    }

    @Override
    public List<Disciplina> findAll() {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try (PreparedStatement pst = conexao.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(new Disciplina(
                    rs.getInt("id_disciplina"),
                    rs.getString("nome_disciplina"),
                    rs.getInt("carga_horaria")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar disciplinas", e);
        }
        return lista;
    }
}