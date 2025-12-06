import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
public class DataRetriever {
    
    public List<Product> getProducts(String productName, String categoryName,
                                     Instant dateMin, Instant dateMax,
                                     int pageNumber, int pageSize) {
        List<Product> products = new ArrayList<>();
     
        String sql = createSQL(productName, categoryName, dateMin, dateMax, pageNumber, pageSize);
        
        try (Connection conn = DataConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            addParameters(stmt, productName, categoryName, dateMin, dateMax, pageNumber, pageSize);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product p = makeProduct(rs);
                    products.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    private String createSQL(String pName, String cName, Instant min, Instant max, int page, int size) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT p.* FROM Product p ");
        sql.append("LEFT JOIN Product_category pc ON p.id = pc.product_id WHERE 1=1");
        
        if (pName != null && !pName.isEmpty()) sql.append(" AND p.name ILIKE ?");
        if (cName != null && !cName.isEmpty()) sql.append(" AND pc.name ILIKE ?");
        if (min != null) sql.append(" AND p.creation_datetime >= ?");
        if (max != null) sql.append(" AND p.creation_datetime <= ?");
        
        sql.append(" ORDER BY p.id");
        if (page > 0 && size > 0) sql.append(" LIMIT ? OFFSET ?");
        
        return sql.toString();
    }
    
    private void addParameters(PreparedStatement stmt, String pName, String cName, 
                              Instant min, Instant max, int page, int size) throws SQLException {
        int index = 1;
        
        if (pName != null && !pName.isEmpty()) {
            stmt.setString(index, "%" + pName + "%");
            index++;
        }
        
        if (cName != null && !cName.isEmpty()) {
            stmt.setString(index, "%" + cName + "%");
            index++;
        }
        
        if (min != null) {
            stmt.setTimestamp(index, Timestamp.from(min));
            index++;
        }
        
        if (max != null) {
            stmt.setTimestamp(index, Timestamp.from(max));
            index++;
        }
        
        if (page > 0 && size > 0) {
            stmt.setInt(index, size);
            index++;
            stmt.setInt(index, (page - 1) * size);
        }
    }
    
    private Product makeProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getBigDecimal("price"),
            rs.getTimestamp("creation_datetime").toInstant()
        );
    }
}
