/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.recuperacion2.main;

import dam.recuperacion2.Entity.Departament;
import dam.recuperacion2.Entity.Professor;
import dam.recuperacion2.hibernateutil.HibernateUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Reda
 */
public class HibernateRelational {

    static HibernateRelational HibeRelaMain = new HibernateRelational();
    static HibernateUtil HibeUtil = new HibernateUtil();

    static Professor p1;
    static Professor p2;
    static Professor p3;
    static Set set1;

    static Departament d1;
    static Departament d2;
    static Departament d3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //Insertar 
        d1 = new Departament("LLati");
        d2 = new Departament("Tecnologia");
        d3 = new Departament("Musica");
        Integer ID4 = HibeRelaMain.insertDepartament(d3);
        Integer ID5 = HibeRelaMain.insertDepartament(d2);
        Integer ID6 = HibeRelaMain.insertDepartament(d1); // <- Se elimina
        p1 = new Professor(d1, "Felipe", "imparteix informatica");
        p2 = new Professor(d2, "Reda", "imparteix Tecnologia");
        p3 = new Professor(d3, "Laura", "imparteix musica");
        Integer ID7 = HibeRelaMain.insertProfessor(p2);
        Integer ID8 = HibeRelaMain.insertProfessor(p3);
        Integer ID9 = HibeRelaMain.insertProfessor(p1);
        // Eliminar
        HibeRelaMain.deleteProfessor(ID6);

        //Mostrar listas
        HibeRelaMain.getListaDeProfessor();
        HibeRelaMain.getListaDepartament();

    }

    public Integer insertDepartament(Departament departament) {

        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer ID = null;

        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(departament);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ID;
    }

    public Integer insertProfessor(Professor professor) {

        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer ID = null;
        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(professor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ID;

    }

    public void deleteProfessor(Integer ID) {
        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Professor professor = (Professor) session.get(Professor.class, ID);
            session.delete(professor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateProfessora(Professor professor) {
        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(professor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateDepartament(Departament d) {
        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(d);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getListaDeProfessor() {
        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List professors = session.createQuery("FROM Professor").list();
            for (Iterator iterator = professors.iterator(); iterator.hasNext();) {
                Professor professor = (Professor) iterator.next();
                System.out.println("+---------------------------------------------+");
                System.out.println("ID Professor: " + professor.getIdProfessor() + "\n");
                System.out.println("Id Departament: " + professor.getDepartament() + "\n");
                System.out.println("Nom Professor: " + professor.getNomProfessor() + "\n");
                System.out.println("Especialitat: " + professor.getEspecialitat() + "\n");
                System.out.println("+---------------------------------------------+\n");
            }
            tx.commit();
            System.out.println("LISTA IMPRIMIDA CORRECTAMENTE");
            professors.clear();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void getListaDepartament() {
        Session session = HibeUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List departaments = session.createQuery("FROM Departament").list();
            for (Iterator iterator = departaments.iterator(); iterator.hasNext();) {
                Departament departament = (Departament) iterator.next();
                System.out.println("+---------------------------------------------+");
                System.out.println("ID Departament : " + departament.getIdDepartament() + "\n");
                System.out.println("Nom Departament : " + departament.getNomDepartament() + "\n");
                System.out.println("+---------------------------------------------+\n");
            }
            tx.commit();
            System.out.println("LISTA DE Departament IMPRIMIDA CORRECTAMENTE");
            departaments.clear();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
