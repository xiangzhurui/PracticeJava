package basic.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basic.PropertiesUtil;

/**
 * 调用示例：
 * 
 * <pre>
 * DataSource dataSource = TomcatDataSource.INSTANCE.getDataSource();
 * </pre>
 * 
 * @author XiangZhuRui
 *
 */
public enum TomcatDataSource {
    INSTANCE;
    private DataSource dataSource;
    final static Logger log = LoggerFactory.getLogger(TomcatDataSource.class);

    private TomcatDataSource() {
        String filePath = TomcatDataSource.class.getResource("/jndi.properites").getFile();
        PropertiesUtil pu = new PropertiesUtil(filePath);
        Properties jdbcProperties = pu.getProperties();
        String jndiName = jdbcProperties.getProperty("jndi.dataSource");
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(jndiName);
            if (dataSource != null) {
                Connection con = dataSource.getConnection();
                if (con != null) {
                    con.setAutoCommit(false);
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    String testQuerySQL = "select 1 from dual";
                    stmt.execute(testQuerySQL);
                    stmt.close();
                    con.close();
                    System.out.println("数据源获取成功");
                } else {
                    System.out.println("数据源连接测试失败");
                }
            } else {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return INSTANCE.dataSource;
    }

}
