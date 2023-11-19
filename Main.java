import java.util.ArrayList;
import java.util.List;

class Cart {
    private static Cart instance;
    private List<OrderObserver> observers;
    private List<Order> orders;

    private Cart() {
        observers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void attachObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }

    public void placeOrder(Order order) {
        orders.add(order);
        notifyObservers(order);
    }
}

class ProductFactory {
    public Product createProduct(String name, double price) {
        return new Product(name, price);
    }
}

interface OrderObserver {
    void update(Order order);
}

class OrderNotifier implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Order created " + order);
    }
}

class Adapter {
    private oplata opl;

    public Adapter(oplata opl) {
        this.opl = opl;
    }

    public void processPayment(double amount) {
        opl.toleu(amount);
    }
}

interface oplata {
    void toleu(double amount);
}

class Paywithcard implements oplata {
    private String lastnum;

    public Paywithcard(String lastnum) {
        this.lastnum = lastnum;
    }

    @Override
    public void toleu(double amount) {
        System.out.println("Payment is " + amount + " paid with card which last 4 numbers are " + lastnum);
    }
}

class KaspiPayment implements oplata {
    private String email;

    public KaspiPayment(String email) {
        this.email = email;
    }

    @Override
    public void toleu(double amount) {
        System.out.println("Payment is " + amount + " using kaspi: " + email);
    }
}

interface Decorator {
    void decorate(Order order);
}

class GiftDecorator implements Decorator {
    @Override
    public void decorate(Order order) {
        order.setTotalCost(order.getTotalCost() + 5.0);
        System.out.println("Wrap added.");
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Order {
    private List<Product> products;
    private double totalCost;

    public Order() {
        products = new ArrayList<>();
        totalCost = 0.0;
    }

    public void addProduct(Product product) {
        products.add(product);
        totalCost += product.getPrice();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", totalCost=" + totalCost +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Cart korzina = Cart.getInstance();
        korzina.attachObserver(new OrderNotifier());

        oplata cardpay = new Paywithcard("1111");
        Adapter creditCardAdapter = new Adapter(cardpay);

        oplata kaspiPayment = new KaspiPayment("johnjones@ufc.com");
        Adapter kaspiAdapter = new Adapter(kaspiPayment);

        Decorator giftWrapDecorator = new GiftDecorator();

        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct("Laptop", 1000.0);
        Product headphones = productFactory.createProduct("Headphones", 150.0);

        Order order = new Order();
        order.addProduct(laptop);
        order.addProduct(headphones);

        korzina.placeOrder(order);

        creditCardAdapter.processPayment(order.getTotalCost());

        giftWrapDecorator.decorate(order);

        kaspiAdapter.processPayment(order.getTotalCost());
    }
}
