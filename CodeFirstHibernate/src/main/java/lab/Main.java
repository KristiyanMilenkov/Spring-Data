package lab;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lab.inheritance.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Vehicle car = new Car("BMW", BigDecimal.TEN,"Petrol", 5);
        Vehicle bike = new Bike("BMX", BigDecimal.ONE,"None");
        Vehicle plane = new Plane("Boeing", BigDecimal.TEN,"Petrol", 100);
        Vehicle truck = new Truck("Toyota", BigDecimal.ONE,"Diesel", 123);

        em.persist(car);
        em.persist(bike);
        em.persist(plane);
        em.persist(truck);

       em.getTransaction().commit();
        em.close();
    }
}
