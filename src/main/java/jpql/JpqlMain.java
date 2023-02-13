package jpql;

import jpql.dto.MemberDto;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team teamA = new Team();
            teamA.setName("팀A");
            Team teamB = new Team();
            teamB.setName("팀B");

            em.persist(teamA);
            em.persist(teamB);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(10);
            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(10);
            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(10);

            member1.setTeam(teamA);
            member2.setTeam(teamA);
            member3.setTeam(teamB);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();


            int resultCount = em.createQuery("update Member m set m.age=20").executeUpdate();

            System.out.println(resultCount);


            tx.commit();
        }catch (Exception e){
            System.out.println("예욉 ㅏㅂㄹ생");
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }


        emf.close();
    }
}
