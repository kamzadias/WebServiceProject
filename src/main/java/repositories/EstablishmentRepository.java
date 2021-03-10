package repositories;

import data.interfaces.IDB;
import entities.Desire;
import entities.Establishment;
import entities.User;
import repositories.interfaces.IEstablishmentRepository;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstablishmentRepository implements IEstablishmentRepository{
    @Inject
    private IDB db;

    @Override
    public boolean create(Establishment establishment) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO establishment(establishment_name,country,rating,establishment_type,founder,desire_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, establishment.getName());
            st.setString(2,establishment.getCountry());
            st.setInt(3,establishment.getRating());
            st.setString(4,establishment.getType());
            st.setString(5,establishment.getFounder());
            st.setInt(6,establishment.getDesire_id());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Establishment get(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT e.establishment_id, e.establishment_name, e.country, e.rating, " +
                    "e.establishment_type, e.founder ,e.desire_id ,d.desire_name, d.desire_description" +
                    " FROM establishment e, desire d WHERE e.desire_id = d.desire_id AND e.establishment_id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Establishment establishment = new Establishment(
                        rs.getInt("id"),
                        rs.getString("establishment_name"),
                        rs.getString("country"),
                        rs.getInt("rating"),
                        rs.getString("establishment_type"),
                        rs.getString("founder"),
                        rs.getInt("desire_id"),
                        new Desire(
                                rs.getInt("desire_id"),
                                rs.getString("desire_name"),
                                rs.getString("desire_description")
                        )

                );

                return establishment;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Establishment> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
