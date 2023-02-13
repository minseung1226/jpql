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
                //member.setUsername("teamA");
                member.setAge(10);
                member.setMemberType(MemberType.ADMIN);

            Team team = new Team();
            team.setName("teamA");

            em.persist(team);
            em.persist(member);



            em.flush();
            em.clear();


            String query1="select " +
                    " case when m.age<= 10 then '학생요금'"+
                    "      when m.age >=60 then '경로요금'"+
                    "      else '일반요금' end"+
                    "  from Member m";

            String query2="select coalesce(m.username,'이름없는 회원') from Member m ";

            String query3 ="select nullif(m.username,'관리자') from Member m ";

            String query4 ="select concat('a','b') from Member m ";

            String query5="select subString(m.username,2,3) from Member m";

            String query6="select locate('de','abcdefg') from Member m ";

            String query7= "select size(t.members) from Team t ";

            List<Integer> resultList = em.createQuery(query7, Integer.class).getResultList();

            for (Integer integer : resultList) {
                System.out.println(integer);
            }
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
