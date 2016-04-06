package professor;

/**
 * Class ProfessorDAO responsável pela 
 * leitura/escrita de objetos Professor no BD
 *
 * @author JSQLGen
 */
public final class ProfessorDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Professor no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Professor ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "matricula INT UNIQUE NOT NULL,"
                   + "foto VARCHAR(200) NOT NULL,"
                   + "nome VARCHAR(200) NOT NULL,"
                   + "periodo VARCHAR(10) NOT NULL,"
                   + "rg INT UNIQUE NOT NULL,"
                   + "cpf VARCHAR(15) UNIQUE NOT NULL,"
                   + "telefone VARCHAR(15) NOT NULL,"
                   + "celular VARCHAR(15) NOT NULL,"
                   + "cep VARCHAR(12) NOT NULL,"
                   + "rua VARCHAR(100) NOT NULL,"
                   + "bairro VARCHAR(80) NOT NULL,"
                   + "cidade VARCHAR(80) NOT NULL,"
                   + "complemento VARCHAR(50) NOT NULL,"
                   + "materia INT NOT NULL,"
                   + "tipo VARCHAR(9) NOT NULL,"
                   + "validade VARCHAR(20) NOT NULL,"
                   + "ativo BOOLEAN NOT NULL,"
                   + "CONSTRAINT PK_Professor PRIMARY KEY (id),"
                   + "CONSTRAINT FKA_Professor_Materia FOREIGN KEY (materia) REFERENCES Materia ON DELETE CASCADE"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        sql = "CREATE INDEX IDX_Professor_cep ON Professor (cep)";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param professorSave Professor a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Professor professorSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, professorSave.getMatricula());
        statement.setString(2, professorSave.getFoto());
        statement.setString(3, professorSave.getNome());
        statement.setString(4, professorSave.getPeriodo());
        statement.setInt(5, professorSave.getRg());
        statement.setString(6, professorSave.getCpf());
        statement.setString(7, professorSave.getTelefone());
        statement.setString(8, professorSave.getCelular());
        statement.setString(9, professorSave.getCep());
        statement.setString(10, professorSave.getRua());
        statement.setString(11, professorSave.getBairro());
        statement.setString(12, professorSave.getCidade());
        statement.setString(13, professorSave.getComplemento());
        statement.setInt(14, professorSave.getMateria().getId());
        statement.setString(15, professorSave.getTipo());
        statement.setString(16, professorSave.getValidade());
        statement.setBoolean(17, professorSave.getAtivo());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param professorInsert Professor a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Professor professorInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Professor (matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,materia,tipo,validade,ativo) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(professorInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Professor";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            professorInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param professorUpdate Professor a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Professor professorUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Professor SET "
                   + "matricula = ?,"
                   + "foto = ?,"
                   + "nome = ?,"
                   + "periodo = ?,"
                   + "rg = ?,"
                   + "cpf = ?,"
                   + "telefone = ?,"
                   + "celular = ?,"
                   + "cep = ?,"
                   + "rua = ?,"
                   + "bairro = ?,"
                   + "cidade = ?,"
                   + "complemento = ?,"
                   + "materia = ?,"
                   + "tipo = ?,"
                   + "validade = ?,"
                   + "ativo = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(professorUpdate, statement);
        statement.setInt(18, professorUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param professorDelete Professor a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Professor professorDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Professor WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, professorDelete.getId());
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //QUERY private
    //*****************************************

    /**
     * rs2obj - Transfere do ResultSet da Query SQL para um novo objeto
     * @param connection
     * @param resultSet to parse
     * @return novo objeto
     * @throws java.sql.SQLException 
     */
    private static Professor rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Professor professorLoad = new Professor();
        professorLoad.setId(resultSet.getInt("id"));
        professorLoad.setMatricula(resultSet.getInt("matricula"));
        professorLoad.setFoto(resultSet.getString("foto"));
        professorLoad.setNome(resultSet.getString("nome"));
        professorLoad.setPeriodo(resultSet.getString("periodo"));
        professorLoad.setRg(resultSet.getInt("rg"));
        professorLoad.setCpf(resultSet.getString("cpf"));
        professorLoad.setTelefone(resultSet.getString("telefone"));
        professorLoad.setCelular(resultSet.getString("celular"));
        professorLoad.setCep(resultSet.getString("cep"));
        professorLoad.setRua(resultSet.getString("rua"));
        professorLoad.setBairro(resultSet.getString("bairro"));
        professorLoad.setCidade(resultSet.getString("cidade"));
        professorLoad.setComplemento(resultSet.getString("complemento"));
        try {
            professorLoad.setMateria(materia.MateriaDAO.loadById(connection, resultSet.getInt("materia")));
        } catch (java.sql.SQLException e) {
            professorLoad.setMateria(new materia.Materia());
        }
        professorLoad.setTipo(resultSet.getString("tipo"));
        professorLoad.setValidade(resultSet.getString("validade"));
        professorLoad.setAtivo(resultSet.getBoolean("ativo"));
        return professorLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Professor || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Professor load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,materia,tipo,validade,ativo "
                       + "FROM Professor "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Professor professorLoad;
            if (resultSet.next()) {
                professorLoad = rs2obj(connection, resultSet);
            } else {
                professorLoad = null;
            }
            statement.close();
            return professorLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Professor
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Professor> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Professor> list = new java.util.ArrayList<Professor>();
        try {
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,materia,tipo,validade,ativo "
                       + "FROM Professor "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Professor professorLoad = rs2obj(connection, resultSet);
                list.add(professorLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Professor
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Professor "
                   + (condition.isEmpty() ? "" : "WHERE " + condition)
                   + (order.isEmpty() ? "" : "ORDER BY " + order);
        return execQueryF(connection, sql);
    }

    //*****************************************
    //QUERY public
    //*****************************************

    //*****************************************
    //LOAD Object BY
    //*****************************************

    /** loadById - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Professor
     * @return objeto Professor || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Professor loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    /** loadByMatricula - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Professor
     * @return objeto Professor || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Professor loadByMatricula(java.sql.Connection connection, Integer matricula) throws java.sql.SQLException {
        return load(connection, "matricula = "+matricula);
    }

    /** loadByRg - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Professor
     * @return objeto Professor || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Professor loadByRg(java.sql.Connection connection, Integer rg) throws java.sql.SQLException {
        return load(connection, "rg = "+rg);
    }

    /** loadByCpf - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Professor
     * @return objeto Professor || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Professor loadByCpf(java.sql.Connection connection, String cpf) throws java.sql.SQLException {
        return load(connection, "cpf = '"+cpf+"'");
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Professor
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Professor> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    /** existsByMatricula - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Professor
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List<Professor> l = loadList(connection, "matricula="+matricula);
        return !l.isEmpty();
    }

    /** existsByRg - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Professor
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByRg(java.sql.Connection connection, Integer rg) {
        java.util.List<Professor> l = loadList(connection, "rg="+rg);
        return !l.isEmpty();
    }

    /** existsByCpf - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Professor
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByCpf(java.sql.Connection connection, String cpf) {
        java.util.List<Professor> l = loadList(connection, "cpf='"+cpf+"'");
        return !l.isEmpty();
    }

    //*****************************************
    //GET Unique Attribute BY 
    //*****************************************

    /** getMatriculaById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Professor
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Professor WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Professor
     * @return rg || null caso não exista
     */
    public static Integer getRgById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Professor WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Professor
     * @return cpf || null caso não exista
     */
    public static String getCpfById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Professor WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Professor
     * @return id || null caso não exista
     */
    public static Integer getIdByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT id FROM Professor WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Professor
     * @return rg || null caso não exista
     */
    public static Integer getRgByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Professor WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Professor
     * @return cpf || null caso não exista
     */
    public static String getCpfByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Professor WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Professor
     * @return id || null caso não exista
     */
    public static Integer getIdByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT id FROM Professor WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Professor
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Professor WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Professor
     * @return cpf || null caso não exista
     */
    public static String getCpfByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Professor WHERE rg="+rg);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Professor
     * @return id || null caso não exista
     */
    public static Integer getIdByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT id FROM Professor WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Professor
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Professor WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Professor
     * @return rg || null caso não exista
     */
    public static Integer getRgByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Professor WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Professor
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    /** loadMatriculaList - Carrega lista de matricula de objetos Professor
     * @param connection Conexão com BD
     * @return List contendo Matricula
     */
    public static java.util.List<String> loadMatriculaList(java.sql.Connection connection) {
        return loadView(connection, "matricula", "", "matricula");
    }

    /** loadRgList - Carrega lista de rg de objetos Professor
     * @param connection Conexão com BD
     * @return List contendo Rg
     */
    public static java.util.List<String> loadRgList(java.sql.Connection connection) {
        return loadView(connection, "rg", "", "rg");
    }

    /** loadCpfList - Carrega lista de cpf de objetos Professor
     * @param connection Conexão com BD
     * @return List contendo Cpf
     */
    public static java.util.List<String> loadCpfList(java.sql.Connection connection) {
        return loadView(connection, "cpf", "", "cpf");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Professor
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Professor> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Professor
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Professor.id,"
                   + "Professor.matricula,"
                   + "Professor.foto,"
                   + "Professor.nome,"
                   + "Professor.periodo,"
                   + "Professor.rg,"
                   + "Professor.cpf,"
                   + "Professor.telefone,"
                   + "Professor.celular,"
                   + "Professor.cep,"
                   + "Professor.rua,"
                   + "Professor.bairro,"
                   + "Professor.cidade,"
                   + "Professor.complemento,"
                   + "Materia.id,"
                   + "Professor.tipo,"
                   + "Professor.validade,"
                   + "Professor.ativo "
                   + "FROM Professor, Materia "
                   + "WHERE Professor.materia = Materia.id ";
        return execQueryF(connection, sql);
    }

}
