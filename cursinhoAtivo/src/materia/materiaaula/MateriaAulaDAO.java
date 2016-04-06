package materia.materiaaula;

/**
 * Class MateriaAulaDAO responsável pela 
 * leitura/escrita de objetos MateriaAula no BD
 *
 * @author JSQLGen
 */
public final class MateriaAulaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela MateriaAula no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE MateriaAula ("
                   + "materiaOwner INT NOT NULL,"
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "codigo VARCHAR(20) UNIQUE NOT NULL,"
                   + "codMateria VARCHAR(20) NOT NULL,"
                   + "nomeAula VARCHAR(100) NOT NULL,"
                   + "descriAula VARCHAR(250) NOT NULL,"
                   + "CONSTRAINT PK_MateriaAula PRIMARY KEY (id),"
                   + "CONSTRAINT FKC_MateriaAula_MateriaOwner FOREIGN KEY (materiaOwner) REFERENCES Materia ON DELETE CASCADE"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        sql = "CREATE INDEX IDX_MateriaAula_codMateria ON MateriaAula (codMateria)";
        statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param materiaAulaSave MateriaAula a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     * @param materiaOwner Materia owner
     */
    private static void obj2stmt(MateriaAula materiaAulaSave, materia.Materia materiaOwner, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, materiaOwner.getId());
        statement.setString(2, materiaAulaSave.getCodigo());
        statement.setString(3, materiaAulaSave.getCodMateria());
        statement.setString(4, materiaAulaSave.getNomeAula());
        statement.setString(5, materiaAulaSave.getDescriAula());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaAulaInsert MateriaAula a ser inserido
     * @param materiaOwner Materia owner
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, MateriaAula materiaAulaInsert, materia.Materia materiaOwner) throws java.sql.SQLException {
        String sql = "INSERT INTO MateriaAula (materiaOwner,codigo,codMateria,nomeAula,descriAula) "
                   + "VALUES (?,?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(materiaAulaInsert, materiaOwner, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM MateriaAula";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            materiaAulaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaAulaUpdate MateriaAula a ser atualizado
     * @param materiaOwner Materia owner
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, MateriaAula materiaAulaUpdate, materia.Materia materiaOwner) throws java.sql.SQLException {
        String sql = "UPDATE MateriaAula SET "
                   + "materiaOwner = ?,"
                   + "codigo = ?,"
                   + "codMateria = ?,"
                   + "nomeAula = ?,"
                   + "descriAula = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(materiaAulaUpdate, materiaOwner, statement);
        statement.setInt(6, materiaAulaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaAulaDelete MateriaAula a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, MateriaAula materiaAulaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM MateriaAula WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, materiaAulaDelete.getId());
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
    private static MateriaAula rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        MateriaAula materiaAulaLoad = new MateriaAula();
        materiaAulaLoad.setId(resultSet.getInt("id"));
        materiaAulaLoad.setCodigo(resultSet.getString("codigo"));
        materiaAulaLoad.setCodMateria(resultSet.getString("codMateria"));
        materiaAulaLoad.setNomeAula(resultSet.getString("nomeAula"));
        materiaAulaLoad.setDescriAula(resultSet.getString("descriAula"));
        return materiaAulaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto MateriaAula || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static MateriaAula load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,codigo,codMateria,nomeAula,descriAula "
                       + "FROM MateriaAula "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            MateriaAula materiaAulaLoad;
            if (resultSet.next()) {
                materiaAulaLoad = rs2obj(connection, resultSet);
            } else {
                materiaAulaLoad = null;
            }
            statement.close();
            return materiaAulaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos MateriaAula
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<MateriaAula> loadList(java.sql.Connection connection, String condition) {
        java.util.List<MateriaAula> list = new java.util.ArrayList<MateriaAula>();
        try {
            String sql = "SELECT id,codigo,codMateria,nomeAula,descriAula "
                       + "FROM MateriaAula "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MateriaAula materiaAulaLoad = rs2obj(connection, resultSet);
                list.add(materiaAulaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos MateriaAula
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM MateriaAula "
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
     * @param id campo identificador de MateriaAula
     * @return objeto MateriaAula || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static MateriaAula loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    /** loadByCodigo - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param codigo campo identificador de MateriaAula
     * @return objeto MateriaAula || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static MateriaAula loadByCodigo(java.sql.Connection connection, String codigo) throws java.sql.SQLException {
        return load(connection, "codigo = '"+codigo+"'");
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de MateriaAula
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<MateriaAula> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    /** existsByCodigo - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codigo campo identificador de MateriaAula
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByCodigo(java.sql.Connection connection, String codigo) {
        java.util.List<MateriaAula> l = loadList(connection, "codigo='"+codigo+"'");
        return !l.isEmpty();
    }

    //*****************************************
    //GET Unique Attribute BY 
    //*****************************************

    /** getCodigoById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de MateriaAula
     * @return codigo || null caso não exista
     */
    public static String getCodigoById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT codigo FROM MateriaAula WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByCodigo - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codigo campo identificador de MateriaAula
     * @return id || null caso não exista
     */
    public static Integer getIdByCodigo(java.sql.Connection connection, String codigo) {
        java.util.List l = execQuery(connection, "SELECT id FROM MateriaAula WHERE codigo='"+codigo+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos MateriaAula
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    /** loadCodigoList - Carrega lista de codigo de objetos MateriaAula
     * @param connection Conexão com BD
     * @return List contendo Codigo
     */
    public static java.util.List<String> loadCodigoList(java.sql.Connection connection) {
        return loadView(connection, "codigo", "", "codigo");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Retorna Lista de objetos MateriaAula por Materia
     * @param connection Conexão com BD
     * @param materiaOwner Materia
     * @return List contendo os objetos
     */
    public static java.util.List<MateriaAula> loadList(java.sql.Connection connection, materia.Materia materiaOwner) {
        return loadList(connection, "materiaOwner = " + materiaOwner.getId());
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

}
