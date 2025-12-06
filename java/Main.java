import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.runTests();
    }

    public void runTests() {
        DataRetriever dr = new DataRetriever();

        System.out.println("Test 1:");
        this.printResults(dr.getProducts(null, null, null, null, 1, 10));

        System.out.println("\nTest 2:");
        this.printResults(dr.getProducts("Dell", null, null, null, 1, 5));

        System.out.println("\nTest 3:");
        this.printResults(dr.getProducts(null, "Informatique", null, null, 1, 10));
    }

    private void printResults(List<Product> products) {
        products.forEach(p -> System.out.println(p.getName()));
    }
}
