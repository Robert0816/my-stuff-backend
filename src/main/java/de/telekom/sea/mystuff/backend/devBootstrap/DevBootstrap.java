package de.telekom.sea.mystuff.backend.devBootstrap;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.MystuffRepo;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
    private MystuffRepo mystuffRepo;
   
    @Autowired
    public DevBootstrap(MystuffRepo mystuffRepo) {
        this.mystuffRepo = mystuffRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
    	Item itemOne = new Item();
        itemOne.setName("Robert Duschek");
        itemOne.setAmount(4);
        itemOne.setLocation("Frankfurt");
        itemOne.setDescription("Mein erster Eintrag in der DB");
        java.util.Date dataFormat = new Date();
        java.sql.Date sqlDate = new java.sql.Date(dataFormat.getTime()); 
        itemOne.setLastUsed(sqlDate);
        mystuffRepo.save(itemOne);
        
        Item itemTwo = new Item();
        itemTwo.setName("Hans Mueller");
        itemTwo.setAmount(2);
        itemTwo.setLocation("Darmstadt");
        itemTwo.setDescription("Mein zweiter Eintrag in der DB");
        java.util.Date dataFormatTwo = new Date();
        java.sql.Date sqlDateTwo = new java.sql.Date(dataFormatTwo.getTime()); 
        itemTwo.setLastUsed(sqlDateTwo);
        mystuffRepo.save(itemTwo);
        
        Item itemThree = new Item();
        itemThree.setName("Jochen Fery");
        itemThree.setAmount(4);
        itemThree.setLocation("London");
        itemThree.setDescription("Mein dritter Eintrag in der DB");
        java.util.Date dataFormatThree = new Date();
        java.sql.Date sqlDateThree = new java.sql.Date(dataFormatThree.getTime()); 
        itemThree.setLastUsed(sqlDateThree);
        mystuffRepo.save(itemThree);
        
 
    }
}





