package dbaccess;
/**
 *
 * @author JSQLGen
 */
public abstract class DAO {
    /**
     * 
     * @param connection
     * @param query
     * @return 
     */
    protected static java.util.List execQuery(java.sql.Connection connection, String query) {
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet rs = statement.executeQuery(query);
            java.util.List result = new java.util.ArrayList();
            while (rs.next()) {
                java.util.List line = new java.util.ArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    switch (rs.getMetaData().getColumnType(i)) {
                        case java.sql.Types.DATE:
                            line.add(rs.getDate(i));
                            break;
                        case java.sql.Types.TIME:
                            line.add(rs.getTime(i));
                            break;
                        case java.sql.Types.BOOLEAN:
                        case java.sql.Types.BIT:
                            line.add(rs.getBoolean(i));
                            break;
                        case java.sql.Types.NUMERIC:
                        case java.sql.Types.DECIMAL:
                        case java.sql.Types.FLOAT:
                        case java.sql.Types.DOUBLE:
                        case java.sql.Types.REAL:
                            line.add(new Float(rs.getFloat(i)));
                            break;
                        case java.sql.Types.INTEGER:
                            line.add(new Integer(rs.getInt(i)));
                            break;
                        case java.sql.Types.TINYINT:
                            line.add(new Byte(rs.getByte(i)));
                            break;
                        default:
                            line.add(rs.getString(i));
                            break;
                    }
                }
                if (line.size() == 1) {
                    result.add(line.get(0));
                } else {
                    result.add(line);
                }
            }
            statement.close();
            return (result);
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nFalha na leitura do banco de dados !");
            return (null);
        }
    }
    /**
     * @param connection
     * @param query
     * @return 
     */
    protected static java.util.List execQueryF(java.sql.Connection connection, String query) {
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet rs = statement.executeQuery(query);
            java.util.List result = new java.util.ArrayList();
            while (rs.next()) {
                java.util.List line = new java.util.ArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    switch (rs.getMetaData().getColumnType(i)) {
                        case java.sql.Types.DATE:
                            line.add(new java.text.SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(i)));
                            break;
                        case java.sql.Types.TIME:
                            line.add(new java.text.SimpleDateFormat("HH:mm:ss").format(rs.getTime(i)));
                            break;
                        case java.sql.Types.BOOLEAN:
                        case java.sql.Types.BIT:
                            line.add(rs.getBoolean(i));
                            break;
                        case java.sql.Types.NUMERIC:
                        case java.sql.Types.DECIMAL:
                        case java.sql.Types.FLOAT:
                        case java.sql.Types.DOUBLE:
                        case java.sql.Types.REAL:
                            line.add(new java.text.DecimalFormat("#,##0.00").format(new Float(rs.getFloat(i))));
                            break;
                        case java.sql.Types.INTEGER:
                            line.add(new Integer(rs.getInt(i)));
                            break;
                        case java.sql.Types.TINYINT:
                            line.add(new Byte(rs.getByte(i)));
                            break;
                        default:
                            line.add(rs.getString(i));
                            break;
                    }
                }
                if (line.size() == 1) {
                    result.add(line.get(0));
                } else {
                    result.add(line);
                }
            }
            statement.close();
            return (result);
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("\nFalha na leitura do banco de dados !");
            return (null);
        }
    }
    
}
