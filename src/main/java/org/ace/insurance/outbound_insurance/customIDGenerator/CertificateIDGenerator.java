package org.ace.insurance.outbound_insurance.customIDGenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.*;

public class CertificateIDGenerator implements IdentifierGenerator {
    private static final String SEQUENCE_NAME = "certificate_id_seq";
    private static  final String PREFIX = "OB/2024-";
    private static final String URL= "jdbc:sqlserver://localhost:1433;databaseName=Outbound_Insurance;encrypt=true;trustServerCertificate=true;";

    private static final String USERNAME= "sa";
    private static final String PASSWORD = "root";


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NEXT VALUE For "+SEQUENCE_NAME);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Long nextValue = resultSet.getLong(1);
                return PREFIX + String.format("%04d",nextValue);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
