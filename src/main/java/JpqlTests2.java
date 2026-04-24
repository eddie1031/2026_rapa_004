import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s03.Member;
import model.s03.Order;
import model.s03.OrderItem;
import model.s03.Product;

public class JpqlTests2 {


    void main() {

        final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-exp-6");

        setup(emf);

        tearDown(emf);

    }

    void setup(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            Member m1 = new Member("오지원", "pass1", "ojiwon@chicken.com");
            Member m2 = new Member("베라자", "pass1", "beraja@chicken.com");

            Product p1 = new Product("나무배트", 150000, 50);
            Product p2 = new Product("인조 가죽 글러브", 50000, 10);

            Order o1 = new Order(m1);
            OrderItem oi1 = new OrderItem(p1, 1);
            OrderItem oi2 = new OrderItem(p2, 1);

            o1.addOrderItem(oi1);
            o1.addOrderItem(oi2);

            entityManager.persist(m1);
            entityManager.persist(m2);

            entityManager.persist(p1);
            entityManager.persist(p2);

            entityManager.persist(o1);



            tx.commit();

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }

    void tearDown(EntityManagerFactory emf) {
        emf.close();
    }


}
