package org.solent.com504.factoryandfacade.test;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.AnimalObjectFactory;
import org.solent.com504.factoryandfacade.model.FarmFacade;

/**
 *
 * @author cgallen
 */
public class FarmFacadeTest {

    @Test
    public void farmFacadeTest() {

        FarmFacade ff = AnimalObjectFactory.createFarmFacade();
        assertNotNull(ff);
        
        ff.addDog("Poppy");
        
        ff.addCat("Seth");
        
        ff.addCow("Daisy");
        
        List<Animal> animalList = ff.getAllAnimals();
        assertNotNull (animalList);
        
        for (Animal a : animalList) {

            System.out.println("animal '" + a.getName()
                    + "' makes this sound '" + a.getSound()
                    + "' because it is a '" + a.getClass().getSimpleName()
                    + "' implemented by " + a.getClass().getTypeName());
        }

    }
}
