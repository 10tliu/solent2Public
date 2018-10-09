/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3liut48
 */
public class FarmFacadeImpl implements FarmFacade{

    private List<Animal> listOfAnimals = new ArrayList<Animal>();
        
    public List<Animal> getAllAnimals() {
        return  listOfAnimals;
    }

    public void addDog(String name) {
        Animal dog = AnimalObjectFactory.createDog();
        dog.setName(name);
        listOfAnimals.add(dog);
    }

    public void addCat(String name) {
        Animal cat = AnimalObjectFactory.createCat();
        cat.setName(name);
        listOfAnimals.add(cat);
    }

    public void addCow(String name) {
        Animal cow = AnimalObjectFactory.createCow();
        cow.setName(name);
        listOfAnimals.add(cow);
    }
  
}
