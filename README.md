
Shopping Cart System



This Java code represents a simple shopping cart system with features such as order placement, payment processing, and decoration. Below is an overview of the key components and functionality of the system.

Cart Class


The Cart class is implemented as a singleton, ensuring that only one instance of the shopping cart exists throughout the application. It includes methods to attach observers, notify observers of placed orders, and place orders.

ProductFactory Class

The ProductFactory class is responsible for creating instances of the Product class. It has a method createProduct that takes the name and price of a product and returns a new Product object.

OrderObserver Interface and OrderNotifier Class
The OrderObserver interface defines a method update that is implemented by classes interested in observing orders. The OrderNotifier class is one such observer that prints a message when an order is created.

Adapter Class

The Adapter class serves as an adapter for different payment methods (oplata). It has a method processPayment that delegates the payment processing to the corresponding oplata implementation.

Oplata Interface, Paywithcard Class, and KaspiPayment Class

The oplata interface defines the method toleu for processing payments. The Paywithcard and KaspiPayment classes implement this interface, representing different payment methods.

Decorator Interface and GiftDecorator Class

The Decorator interface declares a method decorate that can be implemented by decorators. The GiftDecorator class adds a cost for gift wrapping to the total cost of an order and prints a message indicating that wrapping has been added.

Product Class

The Product class represents a product with a name and price. It includes methods to get the name and price of the product, and an overridden toString method for better representation.

Order Class

The Order class represents an order with a list of products and a total cost. It includes methods to add products to the order, get the total cost, set the total cost, and an overridden toString method for better representation.

Main Class

The Main class contains the main method, which serves as the entry point for the application. It demonstrates the usage of the shopping cart system by creating a cart, attaching an order observer, creating payment adapters, adding products to an order, placing the order, processing payments, and decorating the order.
