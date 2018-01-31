package core;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import entity.Tableet;

public class LogonTest {
     private static final String PERSISTENCE_UNIT_NAME = "TableET";
     private static EntityManagerFactory factory;

     public static void main(String[] args) {
          factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
          EntityManager em = factory.createEntityManager();
          // Read the existing entries and write to console
          Query q = em.createQuery("SELECT t FROM TableET t");
          List<Tableet> tableList = q.getResultList();
          for (Tableet table : tableList) {
               System.out.println(table.getName());
          }
          System.out.println("Size: " + tableList.size());

          // Create new user
          //em.getTransaction().begin();
          //TableET table = new TableET();
          //table.setName("Tom Johnson");
          //table.setSalary("200000");
          //table.setId("23");
          //em.persist(table);
          //em.getTransaction().commit();

          //em.close();
     }
}
