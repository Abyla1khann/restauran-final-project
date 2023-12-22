package pkgfinal;

class Product {
        private String name;
        private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Useful for displaying the product information
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
