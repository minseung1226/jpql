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

                Member member = new Member();
                member.setUsername("teamA");
                member.setAge(10);

            Team team = new Team();
            team.setName("teamA");

            em.persist(team);
            em.persist(member);



            em.flush();
            em.clear();

            String query = "select m from Member m left join Team t on m.username=t.name";

            List<Member> members = em.createQuery(query, Member.class).getResultList();
               /*     .setFirstResult(0)
                    .setMaxResults(10)*/


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
