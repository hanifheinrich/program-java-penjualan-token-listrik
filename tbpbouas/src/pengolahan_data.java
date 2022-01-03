import java.sql.SQLException;

public interface pengolahan_data {
    public void insert()  throws SQLException;
    public void display() throws SQLException; 
    public void update()  throws SQLException;
    public void search()  throws SQLException; 
    public void delete()  throws SQLException;
}
