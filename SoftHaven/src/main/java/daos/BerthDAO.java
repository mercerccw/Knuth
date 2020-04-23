package daos;

import config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BerthDAO {
    public ResultSet getAllBerthingData() throws SQLException, ClassNotFoundException {
        Connection connection = Database.dbconnect();
        String sql = "select ports.name, quays.name, quays.type, b.number, b.ship_imo from ports\n" +
                "    join quay_list on ports.quay_list_id = quay_list.id\n" +
                "    join quays on quay_list.quay_id = quays.id\n" +
                "    join berth_list bl on quays.berth_list_id = bl.id\n" +
                "    join berths b on bl.berth_id = b.id";
        PreparedStatement statement = connection.prepareStatement(sql);

        return statement.executeQuery();
    }
}
