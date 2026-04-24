import jakarta.persistence.*;
import model.s03.Member;

public class JpqlTests {

    // JPQL(Java Persistence Query Language)
    // 특정 데이터베이스에 종속되지 않는 객체지향 쿼리 언어
    // SQL이랑 유사하지만 테이블 대신 엔티티이름으로 쿼리를 작성

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate-exp-6");

    void main() {

        setup();

        jpql_basic_select_test();


        tearDown();

    }

    void setup() {

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            Member m1 = new Member("오지원", "pass1", "ojiwon@chicken.com");
            Member m2 = new Member("베라자", "pass1", "beraja@chicken.com");
            Member m3 = new Member("문현민", "pass1", "mhm@chicken.com");

            entityManager.persist(m1);
            entityManager.persist(m2);
            entityManager.persist(m3);

            tx.commit();

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }

    void tearDown() {
        emf.close();
    }

    void jpql_basic_select_test() {

        /*
        SELECT
            m.id,
            m.name,
            m.password,
            m.email,
            m.balance,
            ...
        FROM
            member m
         */

        EntityManager entityManager = emf.createEntityManager();

        String jpql = "SELECT m FROM Member m";

        Query query = entityManager.createQuery(jpql);

        Member singleResult = (Member) query.getSingleResult();



    }

}
