package materia;
/**
 *
 * @author JSQLGen
 */
public final class Materia {


    /** Atributos */
    private Integer id;
    private String codMateria;
    private String nome;
    private String nomeAbrev;
    private java.util.List<materia.materiaaula.MateriaAula> aulas;
    private String descricao;

    /** Construtor */
    public Materia() {
        id = null;
        codMateria = null;
        nome = null;
        nomeAbrev = null;
        aulas = new java.util.ArrayList<materia.materiaaula.MateriaAula>();
        descricao = "";
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
     * @return codMateria
     */
    public String getCodMateria() { return codMateria; }
    /**
     * @param codMateria CodMateria to set
     */
    public void setCodMateria(String codMateria) { this.codMateria = (codMateria.length()>20?codMateria.substring(0,20):codMateria).replace('\'','`'); }

    /**
     * @return nome
     */
    public String getNome() { return nome; }
    /**
     * @param nome Nome to set
     */
    public void setNome(String nome) { this.nome = (nome.length()>50?nome.substring(0,50):nome).replace('\'','`'); }

    /**
     * @return nomeAbrev
     */
    public String getNomeAbrev() { return nomeAbrev; }
    /**
     * @param nomeAbrev NomeAbrev to set
     */
    public void setNomeAbrev(String nomeAbrev) { this.nomeAbrev = (nomeAbrev.length()>20?nomeAbrev.substring(0,20):nomeAbrev).replace('\'','`'); }

    /**
     * @return the aulas
     */
    public java.util.List<materia.materiaaula.MateriaAula> getAulas() { return aulas; }
    /**
     * @param aulas the aulas to set
     */
    public void setAulas(java.util.List<materia.materiaaula.MateriaAula> aulas) { this.aulas = aulas; }

    /**
     * @return descricao
     */
    public String getDescricao() { return descricao; }
    /**
     * @param descricao Descricao to set
     */
    public void setDescricao(String descricao) { this.descricao = (descricao.length()>250?descricao.substring(0,250):descricao).replace('\'','`'); }
}
