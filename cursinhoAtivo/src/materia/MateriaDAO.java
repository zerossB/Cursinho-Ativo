package materia;

/**
 * Class MateriaDAO responsável pela 
 * leitura/escrita de objetos Materia no BD
 *
 * @author JSQLGen
 */
public final class MateriaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Materia no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Materia ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "codMateria VARCHAR(20) UNIQUE NOT NULL,"
                   + "nome VARCHAR(50) UNIQUE NOT NULL,"
                   + "nomeAbrev VARCHAR(20) UNIQUE NOT NULL,"
                   + "descricao VARCHAR(250) NOT NULL,"
                   + "CONSTRAINT PK_Materia PRIMARY KEY (id)"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        materia.materiaaula.MateriaAulaDAO.createTable(connection);
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param materiaSave Materia a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Materia materiaSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setString(1, materiaSave.getCodMateria());
        statement.setString(2, materiaSave.getNome());
        statement.setString(3, materiaSave.getNomeAbrev());
        statement.setString(4, materiaSave.getDescricao());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaInsert Materia a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Materia materiaInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Materia (codMateria,nome,nomeAbrev,descricao) "
                   + "VALUES (?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(materiaInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT IDENTITY_VAL_LOCAL() FROM Materia";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            materiaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
        for (materia.materiaaula.MateriaAula materiaaulaInsert : materiaInsert.getAulas()) {
            materia.materiaaula.MateriaAulaDAO.insert(connection, materiaaulaInsert, materiaInsert);
        }
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaUpdate Materia a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Materia materiaUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Materia SET "
                   + "codMateria = ?,"
                   + "nome = ?,"
                   + "nomeAbrev = ?,"
                   + "descricao = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(materiaUpdate, statement);
        statement.setInt(5, materiaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param materiaDelete Materia a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Materia materiaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Materia WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, materiaDelete.getId());
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
    private static Materia rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Materia materiaLoad = new Materia();
        materiaLoad.setId(resultSet.getInt("id"));
        materiaLoad.setCodMateria(resultSet.getString("codMateria"));
        materiaLoad.setNome(resultSet.getString("nome"));
        materiaLoad.setNomeAbrev(resultSet.getString("nomeAbrev"));
        materiaLoad.setAulas(materia.materiaaula.MateriaAulaDAO.loadList(connection, materiaLoad));
        materiaLoad.setDescricao(resultSet.getString("descricao"));
        return materiaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Materia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Materia load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,codMateria,nome,nomeAbrev,descricao "
                       + "FROM Materia "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Materia materiaLoad;
            if (resultSet.next()) {
                materiaLoad = rs2obj(connection, resultSet);
            } else {
                materiaLoad = null;
            }
            statement.close();
            return materiaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Materia
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Materia> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Materia> list = new java.util.ArrayList<Materia>();
        try {
            String sql = "SELECT id,codMateria,nome,nomeAbrev,descricao "
                       + "FROM Materia "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Materia materiaLoad = rs2obj(connection, resultSet);
                list.add(materiaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Materia
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Materia "
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
     * @param id campo identificador de Materia
     * @return objeto Materia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Materia loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    /** loadByCodMateria - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param codMateria campo identificador de Materia
     * @return objeto Materia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Materia loadByCodMateria(java.sql.Connection connection, String codMateria) throws java.sql.SQLException {
        return load(connection, "codMateria = '"+codMateria+"'");
    }

    /** loadByNome - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param nome campo identificador de Materia
     * @return objeto Materia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Materia loadByNome(java.sql.Connection connection, String nome) throws java.sql.SQLException {
        return load(connection, "nome = '"+nome+"'");
    }

    /** loadByNomeAbrev - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param nomeAbrev campo identificador de Materia
     * @return objeto Materia || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Materia loadByNomeAbrev(java.sql.Connection connection, String nomeAbrev) throws java.sql.SQLException {
        return load(connection, "nomeAbrev = '"+nomeAbrev+"'");
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Materia
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Materia> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    /** existsByCodMateria - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codMateria campo identificador de Materia
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByCodMateria(java.sql.Connection connection, String codMateria) {
        java.util.List<Materia> l = loadList(connection, "codMateria='"+codMateria+"'");
        return !l.isEmpty();
    }

    /** existsByNome - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nome campo identificador de Materia
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByNome(java.sql.Connection connection, String nome) {
        java.util.List<Materia> l = loadList(connection, "nome='"+nome+"'");
        return !l.isEmpty();
    }

    /** existsByNomeAbrev - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nomeAbrev campo identificador de Materia
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsByNomeAbrev(java.sql.Connection connection, String nomeAbrev) {
        java.util.List<Materia> l = loadList(connection, "nomeAbrev='"+nomeAbrev+"'");
        return !l.isEmpty();
    }

    //*****************************************
    //GET Unique Attribute BY 
    //*****************************************

    /** getCodMateriaById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Materia
     * @return codMateria || null caso não exista
     */
    public static String getCodMateriaById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT codMateria FROM Materia WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Materia
     * @return nome || null caso não exista
     */
    public static String getNomeById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT nome FROM Materia WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeAbrevById - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Materia
     * @return nomeAbrev || null caso não exista
     */
    public static String getNomeAbrevById(java.sql.Connection connection, Integer id) {
        java.util.List l = execQuery(connection, "SELECT nomeAbrev FROM Materia WHERE id="+id);
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByCodMateria - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codMateria campo identificador de Materia
     * @return id || null caso não exista
     */
    public static Integer getIdByCodMateria(java.sql.Connection connection, String codMateria) {
        java.util.List l = execQuery(connection, "SELECT id FROM Materia WHERE codMateria='"+codMateria+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeByCodMateria - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codMateria campo identificador de Materia
     * @return nome || null caso não exista
     */
    public static String getNomeByCodMateria(java.sql.Connection connection, String codMateria) {
        java.util.List l = execQuery(connection, "SELECT nome FROM Materia WHERE codMateria='"+codMateria+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeAbrevByCodMateria - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param codMateria campo identificador de Materia
     * @return nomeAbrev || null caso não exista
     */
    public static String getNomeAbrevByCodMateria(java.sql.Connection connection, String codMateria) {
        java.util.List l = execQuery(connection, "SELECT nomeAbrev FROM Materia WHERE codMateria='"+codMateria+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByNome - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nome campo identificador de Materia
     * @return id || null caso não exista
     */
    public static Integer getIdByNome(java.sql.Connection connection, String nome) {
        java.util.List l = execQuery(connection, "SELECT id FROM Materia WHERE nome='"+nome+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCodMateriaByNome - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nome campo identificador de Materia
     * @return codMateria || null caso não exista
     */
    public static String getCodMateriaByNome(java.sql.Connection connection, String nome) {
        java.util.List l = execQuery(connection, "SELECT codMateria FROM Materia WHERE nome='"+nome+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeAbrevByNome - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nome campo identificador de Materia
     * @return nomeAbrev || null caso não exista
     */
    public static String getNomeAbrevByNome(java.sql.Connection connection, String nome) {
        java.util.List l = execQuery(connection, "SELECT nomeAbrev FROM Materia WHERE nome='"+nome+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getIdByNomeAbrev - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nomeAbrev campo identificador de Materia
     * @return id || null caso não exista
     */
    public static Integer getIdByNomeAbrev(java.sql.Connection connection, String nomeAbrev) {
        java.util.List l = execQuery(connection, "SELECT id FROM Materia WHERE nomeAbrev='"+nomeAbrev+"'");
        if(!l.isEmpty()){
            return(Integer)l.get(0);
        } else {
            return null;
        }
    }

    /** getCodMateriaByNomeAbrev - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nomeAbrev campo identificador de Materia
     * @return codMateria || null caso não exista
     */
    public static String getCodMateriaByNomeAbrev(java.sql.Connection connection, String nomeAbrev) {
        java.util.List l = execQuery(connection, "SELECT codMateria FROM Materia WHERE nomeAbrev='"+nomeAbrev+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    /** getNomeByNomeAbrev - Este método carrega o campo de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param nomeAbrev campo identificador de Materia
     * @return nome || null caso não exista
     */
    public static String getNomeByNomeAbrev(java.sql.Connection connection, String nomeAbrev) {
        java.util.List l = execQuery(connection, "SELECT nome FROM Materia WHERE nomeAbrev='"+nomeAbrev+"'");
        if(!l.isEmpty()){
            return(String)l.get(0);
        } else {
            return null;
        }
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Materia
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    /** loadCodMateriaList - Carrega lista de codMateria de objetos Materia
     * @param connection Conexão com BD
     * @return List contendo CodMateria
     */
    public static java.util.List<String> loadCodMateriaList(java.sql.Connection connection) {
        return loadView(connection, "codMateria", "", "codMateria");
    }

    /** loadNomeList - Carrega lista de nome de objetos Materia
     * @param connection Conexão com BD
     * @return List contendo Nome
     */
    public static java.util.List<String> loadNomeList(java.sql.Connection connection) {
        return loadView(connection, "nome", "", "nome");
    }

    /** loadNomeAbrevList - Carrega lista de nomeAbrev de objetos Materia
     * @param connection Conexão com BD
     * @return List contendo NomeAbrev
     */
    public static java.util.List<String> loadNomeAbrevList(java.sql.Connection connection) {
        return loadView(connection, "nomeAbrev", "", "nomeAbrev");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Materia
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Materia> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Materia
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Materia.id,"
                   + "Materia.codMateria,"
                   + "Materia.nome,"
                   + "Materia.nomeAbrev,"
                   + "Materia.descricao "
                   + "FROM Materia ";
        return execQueryF(connection, sql);
    }

}
