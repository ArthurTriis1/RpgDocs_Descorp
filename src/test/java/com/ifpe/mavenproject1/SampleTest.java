package com.ifpe.mavenproject1;

import com.descorp.rpgdocs.beans.SignInBean;
//import com.descorp.rpgdocs.models.Sheet;
import com.descorp.rpgdocs.models.Skill;
import com.descorp.rpgdocs.models.Tool;
import com.descorp.rpgdocs.models.User;
import com.descorp.rpgdocs.services.AuthService;
import enums.Race;
import enums.ToolKind;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


public class SampleTest {

    @Test
    public void SignUpValidUserAndSingInTest() {
//        AuthService userControl = new AuthService();
//        User user = createUser();
//        userControl.SignUp(user);
//        
//        SignInBean bean = new SignInBean();
//        bean.setEmail(user.getEmail());
//        bean.setPassword(user.getPassword());
//        
//        user = userControl.SignIn(bean);
//        assertNotNull(user);
//        assertNotNull(user.getId());
//        
//        userControl.deleteUser(user);
    }
        
//    private User createUser() {
//        User user = new User();
//        Skill skill = new Skill();
//        skill.setCharm(10);
//        skill.setDexterity(10);
//        skill.setIntelligence(10);
//        skill.setStrong(10);
//
//        Tool tool = new Tool();
//        tool.setDamage("+2D20");
//        tool.setKind(ToolKind.Fire);
//        tool.setName("Arminha de fogo");
//
//        //Sheet sheet = new Sheet();
////        sheet.setId(10L);
////        sheet.setName("Nova ficha");
////        sheet.setAge(10);
////        sheet.setDescription("llalala");
////        sheet.setRace(Race.Fate);
////        sheet.setSkill(skill);
////        sheet.addTools(tool);
////
////        user.setEmail("lalala@alala.com");
//        user.setName("user");
//        user.setPassword("12345");
//        user.addSheet(sheet);
//        
//        return user;
//    }
}
