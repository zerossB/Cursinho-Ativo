package alunos;

/**
 * Class AlunosDAO responsável pela 
 * leitura/escrita de objetos Alunos no BD
 *
 * @author JSQLGen
 */
public final class AlunosDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Alunos no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Alunos ("
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
                   + "uF VARCHAR(2) NOT NULL,"
                   + "validade VARCHAR(20) NOT NULL,"
                   + "ativo BOOLEAN NOT NULL,"
                   + "CONSTRAINT PK_Alunos PRIMARY KEY (id)"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        sql = "CREATE INDEX IDX_Alunos_cep ON Alunos (cep)";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        alunos.alunomateria.AlunoMateriaDAO.createTable(connection);
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param alunosSave Alunos a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Alunos alunosSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, alunosSave.getMatricula());
        statement.setString(2, alunosSave.getFoto());
        statement.setString(3, alunosSave.getNome());
        statement.setString(4, alunosSave.getPeriodo());
        statement.setInt(5, alunosSave.getRg());
        statement.setString(6, alunosSave.getCpf());
        statement.setString(7, alunosSave.getTelefone());
        statement.setString(8, alunosSave.getCelular());
        statement.setString(9, alunosSave.getCep());
        statement.setString(10, alunosSave.getRua());
        statement.setString(11, alunosSave.getBairro());
        statement.setString(12, alunosSave.getCidade());
        statement.setString(13, alunosSave.getComplemento());
        statement.setString(14, alunosSave.getUF());
        statement.setString(15, alunosSave.getValidade());
        statement.setBoolean(16, alunosSave.getAtivo());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunosInsert Alunos a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Alunos alunosInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Alunos (matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,uF,validade,ativo) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(alunosInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Alunos";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            alunosInsert.setId(resultSet.getInt(1));
        }
        statement.close();
        for (alunos.alunomateria.AlunoMateria alunomateriaInsert : alunosInsert.getMaterias()) {
            alunos.alunomateria.AlunoMateriaDAO.insert(connection, alunomateriaInsert, alunosInsert);
        }
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunosUpdate Alunos a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Alunos alunosUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Alunos SET "
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
                   + "uF = ?,"
                   + "validade = ?,"
                   + "ativo = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(alunosUpdate, statement);
        statement.setInt(17, alunosUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param alunosDelete Alunos a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Alunos alunosDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Alunos WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, alunosDelete.getId());
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
    private static Alunos rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Alunos alunosLoad = new Alunos();
        alunosLoad.setId(resultSet.getInt("id"));
        alunosLoad.setMatricula(resultSet.getInt("matricula"));
        alunosLoad.setFoto(resultSet.getString("foto"));
        alunosLoad.setNome(resultSet.getString("nome"));
        alunosLoad.setPeriodo(resultSet.getString("periodo"));
        alunosLoad.setRg(resultSet.getInt("rg"));
        alunosLoad.setCpf(resultSet.getString("cpf"));
        alunosLoad.setTelefone(resultSet.getString("telefone"));
        alunosLoad.setCelular(resultSet.getString("celular"));
        alunosLoad.setCep(resultSet.getString("cep"));
        alunosLoad.setRua(resultSet.getString("rua"));
        alunosLoad.setBairro(resultSet.getString("bairro"));
        alunosLoad.setCidade(resultSet.getString("cidade"));
        alunosLoad.setComplemento(resultSet.getString("complemento"));
        alunosLoad.setUF(resultSet.getString("uF"));
        alunosLoad.setMaterias(alunos.alunomateria.AlunoMateriaDAO.loadList(connection, alunosLoad));
        alunosLoad.setValidade(resultSet.getString("validade"));
        alunosLoad.setAtivo(resultSet.getBoolean("ativo"));
        return alunosLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Alunos || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Alunos load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,uF,validade,ativo "
                       + "FROM Alunos "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Alunos alunosLoad;
            if (resultSet.next()) {
                alunosLoad = rs2obj(connection, resultSet);
            } else {
                alunosLoad = null;
            }
            statement.close();
            return alunosLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Alunos
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Alunos> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Alunos> list = new java.util.ArrayList<Alunos>();
        try {
            String sql = "SELECT id,matricula,foto,nome,periodo,rg,cpf,telefone,celular,cep,rua,bairro,cidade,complemento,uF,validade,ativo "
                       + "FROM Alunos "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Alunos alunosLoad = rs2obj(connection, resultSet);
                list.add(alunosLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Alunos
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Alunos "
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
     * @param id campo identificador de Alunos
     * @return objeto Alunos || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Alunos loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    /** loadByMatricula - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Alunos
     * @return objeto Alunos || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Alunos loadByMatricula(java.sql.Connection connection, Integer matricula) throws java.sql.SQLException {
        return load(connection, "matricula = "+matricula);
    }

    /** loadByRg - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Alunos
     * @return objeto Alunos || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Alunos loadByRg(java.sql.Connection connection, Integer rg) throws java.sql.SQLException {
        return load(connection, "rg = "+rg);
    }

    /** loadByCpf - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Alunos
     * @return objeto Alunos || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Alunos loadByCpf(java.sql.Connection connection, String cpf) throws java.sql.SQLException {
        return load(connection, "cpf = '"+cpf+"'");
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Alunos
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Alunos> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    /** existsByMatricula - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Alunos
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List<Alunos> l = loadList(connection, "matricula="+matricula);
        return !l.isEmpty();
    }

    /** existsByRg - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Alunos
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByRg(java.sql.Connection connection, Integer rg) {
        java.util.List<Alunos> l = loadList(connection, "rg="+rg);
        return !l.isEmpty();
    }

    /** existsByCpf - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Alunos
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByCpf(java.sql.Connection connection, String cpf) {
        java.util.List<Alunos> l = loadList(connection, "cpf='"+cpf+"'");
        return !l.isEmpty();
    }

    //*****************************************
    //GET Unique Attribute BY 
    //*****************************************
    
    /**
     * Retorna tamanho da tabela para criação de numero da Matricula
     * @param connection Conexão com BD
     * @param ano ano em que o aluno ingressou
     * @return tamaho da table || 1 caso seja 0
     */
    public static Integer getSizeOfTable(java.sql.Connection connection, int ano){
        java.util.List l = execQuery(connection, "SELECT matricula FROM Alunos WHERE validade = '"+ano+"'");
        if(!l.isEmpty()){
            return l.size() + 1;
        } else {
            return null;
        }
    }

    /** getMatriculaById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Alunos
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Alunos WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Alunos
     * @return rg || null caso não exista
     */
    public static Integer getRgById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Alunos WHERE id="+id);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Alunos
     * @return cpf || null caso não exista
     */
    public static String getCpfById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Alunos WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Alunos
     * @return id || null caso não exista
     */
    public static Integer getIdByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT id FROM Alunos WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Alunos
     * @return rg || null caso não exista
     */
    public static Integer getRgByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Alunos WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByMatricula - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param matricula campo identificador de Alunos
     * @return cpf || null caso não exista
     */
    public static String getCpfByMatricula(java.sql.Connection connection, Integer matricula) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Alunos WHERE matricula="+matricula);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Alunos
     * @return id || null caso não exista
     */
    public static Integer getIdByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT id FROM Alunos WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Alunos
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Alunos WHERE rg="+rg);
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCpfByRg - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param rg campo identificador de Alunos
     * @return cpf || null caso não exista
     */
    public static String getCpfByRg(java.sql.Connection connection, Integer rg) {
        java.util.List l = execQuery(connection, "SELECT cpf FROM Alunos WHERE rg="+rg);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Alunos
     * @return id || null caso não exista
     */
    public static Integer getIdByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT id FROM Alunos WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getMatriculaByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Alunos
     * @return matricula || null caso não exista
     */
    public static Integer getMatriculaByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT matricula FROM Alunos WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getRgByCpf - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param cpf campo identificador de Alunos
     * @return rg || null caso não exista
     */
    public static Integer getRgByCpf(java.sql.Connection connection, String cpf) {
        java.util.List l = execQuery(connection, "SELECT rg FROM Alunos WHERE cpf='"+cpf+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Alunos
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    /** loadMatriculaList - Carrega lista de matricula de objetos Alunos
     * @param connection Conexão com BD
     * @return List contendo Matricula
     */
    public static java.util.List<String> loadMatriculaList(java.sql.Connection connection) {
        return loadView(connection, "matricula", "", "matricula");
    }

    /** loadRgList - Carrega lista de rg de objetos Alunos
     * @param connection Conexão com BD
     * @return List contendo Rg
     */
    public static java.util.List<String> loadRgList(java.sql.Connection connection) {
        return loadView(connection, "rg", "", "rg");
    }

    /** loadCpfList - Carrega lista de cpf de objetos Alunos
     * @param connection Conexão com BD
     * @return List contendo Cpf
     */
    public static java.util.List<String> loadCpfList(java.sql.Connection connection) {
        return loadView(connection, "cpf", "", "cpf");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Alunos
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Alunos> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Alunos
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Alunos.id,"
                   + "Alunos.matricula,"
                   + "Alunos.foto,"
                   + "Alunos.nome,"
                   + "Alunos.periodo,"
                   + "Alunos.rg,"
                   + "Alunos.cpf,"
                   + "Alunos.telefone,"
                   + "Alunos.celular,"
                   + "Alunos.cep,"
                   + "Alunos.rua,"
                   + "Alunos.bairro,"
                   + "Alunos.cidade,"
                   + "Alunos.complemento,"
                   + "Alunos.uF,"
                   + "Alunos.validade,"
                   + "Alunos.ativo "
                   + "FROM Alunos ";
        return execQueryF(connection, sql);
    }

}
