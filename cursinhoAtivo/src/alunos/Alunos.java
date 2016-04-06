package alunos;
/**
 *
 * @author JSQLGen
 */
public final class Alunos {

    /** Atributos estaticos */
    public static String[] PERIODO = {
        "Vespertino",
        "Noturno"
    };
    public static String[] UF = {
        "AC",
        "AL",
        "AP",
        "AM",
        "BA",
        "CE",
        "DF",
        "ES",
        "GO",
        "MA",
        "MT",
        "MS",
        "MG",
        "PR",
        "PB",
        "PA",
        "PE",
        "PI",
        "RJ",
        "RN",
        "RS",
        "RO",
        "RR",
        "SC",
        "SE",
        "SP",
        "TO"
    };

    /** Atributos */
    private Integer id;
    private Integer matricula;
    private String foto;
    private String nome;
    private String periodo;
    private Integer rg;
    private String cpf;
    private String telefone;
    private String celular;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String complemento;
    private String uF;
    private java.util.List<alunos.alunomateria.AlunoMateria> materias;
    private String validade;
    private Boolean ativo;

    /** Construtor */
    public Alunos() {
        id = null;
        matricula = null;
        foto = "";
        nome = "";
        periodo = "";
        rg = null;
        cpf = null;
        telefone = "";
        celular = "";
        cep = "";
        rua = "";
        bairro = "";
        cidade = "";
        complemento = "";
        uF = "";
        materias = new java.util.ArrayList<alunos.alunomateria.AlunoMateria>();
        validade = "";
        ativo = true;
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
     * @return matricula
     */
    public Integer getMatricula() { return matricula; }
    /**
     * @param matricula Matricula to set
     */
    public void setMatricula(Integer matricula) { this.matricula = matricula; }
    /**
     * @param matricula - String matricula to set
     */
    public void setMatricula(String matricula) { this.matricula = Integer.parseInt(matricula); }

    /**
     * @return foto
     */
    public String getFoto() { return foto; }
    /**
     * @param foto Foto to set
     */
    public void setFoto(String foto) { this.foto = (foto.length()>200?foto.substring(0,200):foto).replace('\'','`'); }

    /**
     * @return nome
     */
    public String getNome() { return nome; }
    /**
     * @param nome Nome to set
     */
    public void setNome(String nome) { this.nome = (nome.length()>200?nome.substring(0,200):nome).replace('\'','`'); }

    /**
     * @return the periodo
     */
    public String getPeriodo() { return periodo; }
    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    /**
     * @return rg
     */
    public Integer getRg() { return rg; }
    /**
     * @param rg Rg to set
     */
    public void setRg(Integer rg) { this.rg = rg; }
    /**
     * @param rg - String rg to set
     */
    public void setRg(String rg) { this.rg = Integer.parseInt(rg); }

    /**
     * @return cpf
     */
    public String getCpf() { return cpf; }
    /**
     * @param cpf Cpf to set
     */
    public void setCpf(String cpf) { this.cpf = (cpf.length()>15?cpf.substring(0,15):cpf).replace('\'','`'); }

    /**
     * @return telefone
     */
    public String getTelefone() { return telefone; }
    /**
     * @param telefone Telefone to set
     */
    public void setTelefone(String telefone) { this.telefone = (telefone.length()>15?telefone.substring(0,15):telefone).replace('\'','`'); }

    /**
     * @return celular
     */
    public String getCelular() { return celular; }
    /**
     * @param celular Celular to set
     */
    public void setCelular(String celular) { this.celular = (celular.length()>15?celular.substring(0,15):celular).replace('\'','`'); }

    /**
     * @return cep
     */
    public String getCep() { return cep; }
    /**
     * @param cep Cep to set
     */
    public void setCep(String cep) { this.cep = (cep.length()>12?cep.substring(0,12):cep).replace('\'','`'); }

    /**
     * @return rua
     */
    public String getRua() { return rua; }
    /**
     * @param rua Rua to set
     */
    public void setRua(String rua) { this.rua = (rua.length()>100?rua.substring(0,100):rua).replace('\'','`'); }

    /**
     * @return bairro
     */
    public String getBairro() { return bairro; }
    /**
     * @param bairro Bairro to set
     */
    public void setBairro(String bairro) { this.bairro = (bairro.length()>80?bairro.substring(0,80):bairro).replace('\'','`'); }

    /**
     * @return cidade
     */
    public String getCidade() { return cidade; }
    /**
     * @param cidade Cidade to set
     */
    public void setCidade(String cidade) { this.cidade = (cidade.length()>80?cidade.substring(0,80):cidade).replace('\'','`'); }

    /**
     * @return complemento
     */
    public String getComplemento() { return complemento; }
    /**
     * @param complemento Complemento to set
     */
    public void setComplemento(String complemento) { this.complemento = (complemento.length()>50?complemento.substring(0,50):complemento).replace('\'','`'); }

    /**
     * @return the uF
     */
    public String getUF() { return uF; }
    /**
     * @param uF the uF to set
     */
    public void setUF(String uF) { this.uF = uF; }

    /**
     * @return the materias
     */
    public java.util.List<alunos.alunomateria.AlunoMateria> getMaterias() { return materias; }
    /**
     * @param materias the materias to set
     */
    public void setMaterias(java.util.List<alunos.alunomateria.AlunoMateria> materias) { this.materias = materias; }

    /**
     * @return validade
     */
    public String getValidade() { return validade; }
    /**
     * @param validade Validade to set
     */
    public void setValidade(String validade) { this.validade = (validade.length()>20?validade.substring(0,20):validade).replace('\'','`'); }

    /**
     * @return ativo
     */
    public Boolean isAtivo() { return ativo; }
    public Boolean getAtivo() { return ativo; }
    /**
     * @param ativo Ativo to set
     */
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
