package alunos.alunomateria;
/**
 *
 * @author JSQLGen
 */
public final class AlunoMateria {


    /** Atributos */
    private Integer id;
    private materia.Materia materia;

    /** Construtor */
    public AlunoMateria() {
        id = null;
        materia = new materia.Materia();
    }
    /** Metodos */

    /**
     * @return id
     */
    public Integer getId() { return id; }
    /**
     * @param id Id to set
     */
    public void setId(Integer id) { this.id = id; }
    /**
     * @param id - String id to set
     */
    public void setId(String id) { this.id = (id.equals("null") || id.isEmpty())?null:Integer.parseInt(id); }

    /**
     * @return materia
     */
    public materia.Materia getMateria() { return materia; }
    /**
     * @param materia Materia to set
     */
    public void setMateria(materia.Materia materia) { this.materia = materia; }
}
