package adverencia;
/**
 *
 * @author JSQLGen
 */
public final class Adverencia {


    /** Atributos */
    private Integer id;
    private alunos.Alunos aluno;
    private coordenador.Coordenador coord;
    private java.util.Date data;
    private String observacao;

    /** Construtor */
    public Adverencia() {
        id = null;
        aluno = new alunos.Alunos();
        coord = new coordenador.Coordenador();
        data = new java.util.Date();
        observacao = "";
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
     * @return aluno
     */
    public alunos.Alunos getAluno() { return aluno; }
    /**
     * @param aluno Aluno to set
     */
    public void setAluno(alunos.Alunos aluno) { this.aluno = aluno; }

    /**
     * @return coord
     */
    public coordenador.Coordenador getCoord() { return coord; }
    /**
     * @param coord Coord to set
     */
    public void setCoord(coordenador.Coordenador coord) { this.coord = coord; }

    /**
     * @return Data
     */
    public java.util.Date getData() { return data; }
    /**
     * @param pattern Formato de Data. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @return Data Formatado
     */
    public String getDataF(String pattern) { return new java.text.SimpleDateFormat(pattern).format(data); }
    /**
     * @param data Data to set
     */
    public void setData(java.util.Date data) { this.data = data; }
    /**
     * @param pattern Formato de Data. Ex "yyyy-MM-dd" ou "dd/MM/yyyy"
     * @param data - String Data to set
     */
    public void setDataF(String pattern, String data) { this.data = new java.text.SimpleDateFormat(pattern).parse(data, new java.text.ParsePosition(0)); }

    /**
     * @return observacao
     */
    public String getObservacao() { return observacao; }
    /**
     * @param observacao Observacao to set
     */
    public void setObservacao(String observacao) { this.observacao = (observacao.length()>500?observacao.substring(0,500):observacao).replace('\'','`'); }
}
