package ca.sheridancollege.waryad.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.waryad.beans.HalloweenCandyConsumption;

@Repository
public class DatabaseAccess {
    
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void insertHalloweenCandyConsumption(HalloweenCandyConsumption halloweenCandyConsumption) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO halloweencandyconsumption (quantity, candyBrandname, beforeDate, description, category) " +
                       "VALUES (:quantity, :candyBrandname, :beforeDate, :description, :category)";
        namedParameters.addValue("quantity", halloweenCandyConsumption.getQuantity());
        namedParameters.addValue("candyBrandname", halloweenCandyConsumption.getCandyBrandname());
        namedParameters.addValue("beforeDate", halloweenCandyConsumption.getBeforeDate());
        namedParameters.addValue("description", halloweenCandyConsumption.getDescription());
        namedParameters.addValue("category", halloweenCandyConsumption.getCategory());
        jdbc.update(query, namedParameters);
    }

    public List<HalloweenCandyConsumption> getHalloweenCandyConsumptionList() {
        String query = "SELECT * FROM halloweencandyconsumption";
        return jdbc.query(query, new BeanPropertyRowMapper<>(HalloweenCandyConsumption.class));
    }

    public void deleteHalloweenCandyConsumptionById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM halloweencandyconsumption WHERE id = :id";
        namedParameters.addValue("id", id);
        jdbc.update(query, namedParameters);
    }

    public List<HalloweenCandyConsumption> getHalloweenCandyConsumptionListById(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM halloweencandyconsumption WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(HalloweenCandyConsumption.class));
    }

    public void updateHalloweenCandyConsumption(HalloweenCandyConsumption halloweenCandyConsumption) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE halloweencandyconsumption " +
                       "SET quantity = :quantity, candyBrandname = :candyBrandname, " +
                       "beforeDate = :beforeDate, description = :description, " +
                       "category = :category " +
                       "WHERE id = :id";
        namedParameters.addValue("quantity", halloweenCandyConsumption.getQuantity());
        namedParameters.addValue("candyBrandname", halloweenCandyConsumption.getCandyBrandname());
        namedParameters.addValue("beforeDate", halloweenCandyConsumption.getBeforeDate());
        namedParameters.addValue("description", halloweenCandyConsumption.getDescription());
        namedParameters.addValue("category", halloweenCandyConsumption.getCategory());
        namedParameters.addValue("id", halloweenCandyConsumption.getId());
        
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0) {
            System.out.println("Updated halloweencandyconsumption with ID: " + halloweenCandyConsumption.getId());
        }
    }
}
