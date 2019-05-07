package db.pick.me.up;

import db.pick.me.up.json.ConfigInfo;
import db.pick.me.up.json.ConnectionInfo;
import db.pick.me.up.singleton.Config;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.FileReader;
import java.sql.*;

import static org.junit.Assert.*;


/**
 * Created by admin on 5/5/2019.
 */
public class configTest {
    @Test
    public void testLoadConfig1() throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/config-test1.json")) {
            JSONObject infoObj = (JSONObject) parser.parse(reader);
            JSONObject connectionObj = (JSONObject) infoObj.get("connection");

            ConnectionInfo connInfo = new ConnectionInfo(connectionObj);

            System.out.println("test: " + connInfo);
            System.out.println("url: " + connInfo.getConnectionString());

            ConfigInfo configInfo = new ConfigInfo((JSONObject) infoObj.get("config"));

            System.out.println("config: " + configInfo);

            Connection conn = DriverManager.getConnection(
                    connInfo.getConnectionString(),
                    connInfo.getUser(),
                    connInfo.getPass()
            );

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + connInfo.getTable());

            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + metaData.getColumnName(i));
                }
                System.out.println("");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testLoadConfig2() throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/config-test2.json")) {
            JSONObject infoObj = (JSONObject) parser.parse(reader);
            JSONObject connectionObj = (JSONObject) infoObj.get("connection");

            ConnectionInfo connInfo = new ConnectionInfo(connectionObj);

            System.out.println("test: " + connInfo);
            System.out.println("url: " + connInfo.getConnectionString());

            ConfigInfo configInfo = new ConfigInfo((JSONObject) infoObj.get("config"));

            System.out.println("config: " + configInfo);

            Connection conn = DriverManager.getConnection(
                    connInfo.getConnectionString(),
                    connInfo.getUser(),
                    connInfo.getPass()
            );

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + connInfo.getTable());

            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + metaData.getColumnName(i));
                }
                System.out.println("");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testLoadConfig3() throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/config-test1.json")) {
            JSONObject infoObj = (JSONObject) parser.parse(reader);
            JSONObject connectionObj = (JSONObject) infoObj.get("connection");

            ConnectionInfo connInfo = new ConnectionInfo(connectionObj);

            System.out.println("test: " + connInfo);
            System.out.println("url: " + connInfo.getConnectionString());

            Connection conn = DriverManager.getConnection(
                    connInfo.getConnectionString(),
                    connInfo.getUser(),
                    connInfo.getPass()
            );

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select column_name, data_type from information_schema.columns where table_name = '" + connInfo.getTable() + "'");

            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + metaData.getColumnName(i));
                }
                System.out.println("");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testLoadConfig4() throws Exception {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("src/test/resources/config-test2.json")) {
            JSONObject infoObj = (JSONObject) parser.parse(reader);
            JSONObject connectionObj = (JSONObject) infoObj.get("connection");

            ConnectionInfo connInfo = new ConnectionInfo(connectionObj);

            System.out.println("test: " + connInfo);
            System.out.println("url: " + connInfo.getConnectionString());

            Connection conn = DriverManager.getConnection(
                    connInfo.getConnectionString(),
                    connInfo.getUser(),
                    connInfo.getPass()
            );

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select column_name, data_type from information_schema.columns where table_name = '" + connInfo.getTable() + "'");

            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + metaData.getColumnName(i));
                }
                System.out.println("");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testLoadConfigCentral() throws Exception {
        Config config = Config.getInstance();
        config.init("src/test/resources/config-test1.json");

        System.out.println(config);
        assertTrue(true);
    }


}
