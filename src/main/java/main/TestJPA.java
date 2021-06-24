/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.descorp.rpgdocs.connection.DatabaseConnection;
import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.repositories.UserRepository;
import com.descorp.rpgdocs.repositoriesImpl.UserRepositoryImpl;
import enums.Race;
import enums.ToolKind;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author arthur
 */
public class TestJPA {
    
    public static void main(String[] args) {
        TestJPA testeJPA = new TestJPA();
        //testeJPA.createData();
        UserRepository userRepo = new UserRepositoryImpl(DatabaseConnection.getCurrentInstance().createEntityManager());
        User u = new User();
        
        testeJPA.createUser(u);
        userRepo.saveUser(u);
    }
    
    public void createData() {
        User user = new User();
        createUser(user);
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            //EntityManagerFactory realiza a leitura das informações relativas à "persistence-unit".
            emf = Persistence.createEntityManagerFactory("rpg_docs");
            em = emf.createEntityManager(); //Criação do EntityManager.
            et = em.getTransaction(); //Recupera objeto responsável pelo gerenciamento de transação.
            et.begin();
            em.persist(user);
            et.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (et != null && et.isActive())
                et.rollback();
        } finally {
            if (em != null)
                em.close();       
            if (emf != null)
                emf.close();
        }
    }
    
    private void createUser(User user) {
        Skill skill = new Skill();
        skill.setCharm(10);
        skill.setDexterity(10);
        skill.setIntelligence(10);
        skill.setStrong(10);
        
        Tool tool = new Tool();
        tool.setDamage("+2D20");
        tool.setKind(ToolKind.Fire);
        tool.setName("Arminha de fogo");
        
        Sheet sheet = new Sheet();
        sheet.setId(10L);
        sheet.setName("Nova ficha");
        sheet.setAge(10);
        sheet.setDescription("llalala");
        sheet.setRace(Race.Fate);
        sheet.setSkill(skill);
        sheet.addTools(tool);
        
        user.setEmail("lalala@alala.com");
        user.setName("user");
        user.setPassword("12345");
        user.addSheet(sheet);

    }
}
