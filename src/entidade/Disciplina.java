package entidade;

import java.util.Objects;

public class Disciplina {
    private Integer idDisciplina;
    private String nomeDisciplina;
    private Integer cargaHoraria;

    public Disciplina() {}

    public Disciplina(Integer idDisciplina, String nomeDisciplina, Integer cargaHoraria) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters
    public Integer getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(Integer idDisciplina) { this.idDisciplina = idDisciplina; }

    public String getNomeDisciplina() { return nomeDisciplina; }
    public void setNomeDisciplina(String nomeDisciplina) { this.nomeDisciplina = nomeDisciplina; }

    public Integer getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(Integer cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(idDisciplina, that.idDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina [id=" + idDisciplina + ", nome=" + nomeDisciplina + ", carga=" + cargaHoraria + "]";
    }
}