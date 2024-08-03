# About AOP (Aspect-Oriented Programming)
- Imagine you have an online shopping cart.
- People will add or remove items and accordingly the quatities will be updated, calculation of total price will be done, if any discount is applicable then it will be applied , processing payment will be done etc..
- Now the core feature in this online shopping cart for individual user includes
  - User adding or removing items
  - Updating quantities
  - Calculation total price
- Other concerns which are essential but not part of core are
  - Logging users action
  - Applying discounts
  - Checking inventory
  - Processing payments
- Now these concerns could be bind with each core (each user will be assigned with inventory, logging, payments etc.) or cut it out from the core and have a generalize layer which deals with all users shopping cart.
- So which is better? to have a generalize layer rights which deals with all the users shopping cart , that is AOP.
- **AOP helps manage cross-cutting concerns—tasks ( like payments, logging, applying discounts etc..) that affect multiple parts (individual users) of an application—separately from the main logic (core features like adding or removing items from the cart)**.
- **Think of it as handling additional tasks (cross-cutting concerns) like logging user actions, checking inventory, and applying discounts. AOP allows you to define these tasks separately and automatically weave them into the shopping cart operations where needed.**
- **This cross-cutting concerns are referred as aspect**


![image](https://github.com/user-attachments/assets/f457afe9-98fc-457f-920b-977c5f7f8853)

- As per the defination it seems we would have a separate class which will consists of method and those method can be manually called before/after of execution of core functionalities, so what's the big difference without AOP?

### Manual Handling vs. AOP
- **Manual Handling Approach**:
  - Code Clutter: When you manually handle cross-cutting concerns, you have to explicitly call the methods for logging, security checks, transaction management, etc., within your main business logic. This leads to repetitive and cluttered code.
  - Tight Coupling: Your business logic becomes tightly coupled with cross-cutting concerns. Any change in the logging or security mechanism would require changes in multiple places.
  - Scalability Issues: As your application grows, managing these concerns manually can become cumbersome and error-prone.

- **AOP Approach**:
  - Separation of Concerns: AOP cleanly separates cross-cutting concerns from the main business logic. Aspects are defined separately and applied declaratively, making the core logic cleaner and more maintainable.
  - Reduced Code Duplication: You define an aspect (e.g., logging) once, and it can be applied to multiple methods or classes without duplicating the logging code in each place.
  - Loose Coupling: Your business logic remains independent of the cross-cutting concerns, allowing changes to aspects without modifying the core logic.
  - Dynamic Weaving: AOP allows you to dynamically apply or change aspects at runtime without altering the existing codebase.
 
- AOP improves the organization of your code by keeping different types of functionality separate, reducing repeated code, and making your main business logic clearer and easier to manage. It enhances modularity by separating concerns, reduces code duplication, and promotes cleaner and more focused core logic. 

## AOP VS OOP
#### Object-Oriented Programming (OOP):
- OOP is a programming paradigm that focuses on creating objects that represent entities and their behaviors. Considering online shopping car example,
  - Encapsulation: In the shopping cart example, encapsulation would mean bundling data (items, quantities) and methods (addItem, removeItem, calculateTotal) into the ShoppingCart class.
  - Inheritance: If there are different types of shopping carts (e.g., GuestCart, MemberCart or PremiumCart), they could inherit from a base ShoppingCart class.
  - Polymorphism: Different types of discounts (e.g., percentage-based, buy-one-get-one-free) can be applied in a polymorphic way.
- In OOP all the cross-cutting concerns and core functionalities are intermixed in some class which creates duplication of code. This is avoided in AOP thus providing modularity or seperation of concerns.

![image](https://github.com/user-attachments/assets/30766a4b-f0e9-4bd2-8eb8-6b6c3ab9ea90)

- In the online shopping cart example, let us consider transaction as our aspect which is require to be implemented post calculation of total amount. How will the transaction aspect will know where to be called and when to be call while execution of calculation of total amount? thats why AOP has concepts of JoinPoint, Advice, Weaving and Pointcut which determine where and when the transaction aspect should be executed.
- AOP is implemented by multiple frameworks but commonly used are AspectJ and Spring.

## AOP Core Concepts

![image](https://github.com/user-attachments/assets/3a92b7bb-236a-4695-b7b1-4c7edd469685)


1. **Join Point:**
  - In online shopping cart. Every action you take on the cart is a potential join point. This could be adding an item, removing an item, checking out, applying a discount, etc. These are specific moments in the shopping cart's lifecycle where something could happen.
  - A join point represents a specific point during the execution of a program.
2. **Pointcut:**
    - A pointcut is an expression that determines where advice should be applied in the application.
    - A pointcut is like a filter that selects which join points you're interested in. If you only want to apply a discount when the cart total exceeds a certain amount, your pointcut would be "cart total greater than 1000". This filter will only activate the advice when the cart total meets this condition.
3. **Advice**
    - Advice is the action you want to take at the selected join points out of all set of join points. In our shopping cart example, the advice could be applying a 10% discount to the cart total. This action will be executed whenever the pointcut condition is met (cart total greater than 1000).
4. **Weaving**
  - The process of combining the transaction management aspect with the checkout method. This can be imagined as weaving threads together to create a fabric.
  - Weaving is the process of integrating or combining core concepts (like the main business logic) with aspect units (like cross-cutting concerns such as transaction management, logging, or security).
  - In Spring, weaving can be accomplished through either compile-time or runtime weaving.
    - Compile-Time: The process of integrating aspects into the application code during the compilation process. The resulting code already includes the aspect logic.
    - Run-Time: The process of integrating aspects into the application code at runtime using dynamic proxies. The aspect logic is added on the fly when the application is running.
    - Spring AOP primarily uses runtime weaving. This means that the aspects are applied to your application's objects when they are created and used. This offers flexibility as you can modify aspects without recompiling the entire application. However, it might have a slight performance overhead compared to compile-time weaving.

## Spring AOP



