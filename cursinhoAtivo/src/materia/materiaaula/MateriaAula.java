package materia.materiaaula;
/**
 *
 * @author JSQLGen
 */
public final class MateriaAula {


    /** Atributos */
    private Integer id;
    private String codigo;
    private String codMateria;
    private String nomeAula;
    private String descriAula;

    /** Construtor */
    public MateriaAula() {
        id = null;
        codigo = null;
        codMateria = "";
        nomeAula = "";
        descriAula = "";
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
     * @return codigo
     */
    public String getCodigo() { return codigo; }
    /**
     * @param codigo Codigo to set
     */
    public void setCodigo(String codigo) { this.codigo = (codigo.length()>20?codigo.substring(0,20):codigo).replace('\'','`'); }

    /**
     * @return codMateria
     */
    public String getCodMateria() { return codMateria; }
    /**
     * @param codMateria CodMateria to set
     */
    public void setCodMateria(String codMateria) { this.codMateria = (codMateria.length()>20?codMateria.substring(0,20):codMateria).replace('\'','`'); }

    /**
     * @return nomeAula
     */
    public String getNomeAula() { return nomeAula; }
    /**
     * @param nomeAula NomeAula to set
     */
    public void setNomeAula(String nomeAula) { this.nomeAula = (nomeAula.length()>100?nomeAula.substring(0,100):nomeAula).replace('\'','`'); }

    /**
     * @return descriAula
     */
    public String getDescriAula() { return descriAula; }
    /**
     * @param descriAula DescriAula to set
     */
    public void setDescriAula(String descriAula) { this.descriAula = (descriAula.length()>250?descriAula.substring(0,250):descriAula).replace('\'','`'); }
}
