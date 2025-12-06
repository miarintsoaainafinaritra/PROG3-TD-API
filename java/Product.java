import java.time.Instant;
import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private Instant creationDateTime;

   
    public Product(int id, String name, BigDecimal price, Instant creationDateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDateTime = creationDateTime;
    }

 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Instant getCreationDateTime() { return creationDateTime; }
    public void setCreationDateTime(Instant creationDateTime) { this.creationDateTime = creationDateTime; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", creationDateTime=" + creationDateTime + "}";
    }
}
