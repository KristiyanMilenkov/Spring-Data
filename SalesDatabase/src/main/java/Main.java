import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product("productExample",123, BigDecimal.TEN);
        Customer customer = new Customer("example","example@gmail.com","exampleNumber");
        StoreLocation storeLocation = new StoreLocation("exampleLocation");
        Sale sale = new Sale(product,customer,storeLocation);

        em.persist(product);
        em.persist(customer);
        em.persist(storeLocation);
        em.persist(sale);



        em.getTransaction().commit();
        em.close();
    }
}
