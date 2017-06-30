package com.example.leo.myapplication;

import android.support.test.runner.AndroidJUnit4;

import com.example.leo.myapplication.database.DatabaseHelper;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.utils.DependencyCacheHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by leo on 25/06/17.
 */

@RunWith(AndroidJUnit4.class)
public class AnimalDaoTest extends BaseTestClass {

    @Before
    @Override
    public void setup() {
        super.setup();
        //Permite in memory database
        DatabaseHelper.DATABASE_NAME = null;
    }

    @Test
    public void addTest() throws Exception{
        IAnimalDao animalDao = DependencyCacheHelper.getInstance(IAnimalDao.class);

        Animal newAnimal = new Animal();
        newAnimal.setName("Elephant");
        newAnimal.setSpecie("Loxodonta cyclotis");
        newAnimal.setWeight(500);

        long count = animalDao.add(newAnimal);
        Assert.assertEquals(1, count);

        count = animalDao.add(newAnimal);
        Assert.assertEquals(2, count);
    }

    @Test
    public void updateTest() throws Exception{
        IAnimalDao animalDao = DependencyCacheHelper.getInstance(IAnimalDao.class);

        Animal newAnimal = new Animal();
        newAnimal.setName("Elephant");
        newAnimal.setSpecie("Loxodonta cyclotis");
        newAnimal.setWeight(500);

        long count = animalDao.add(newAnimal);
        Assert.assertEquals(1, count);

        Animal dbEntry = animalDao.getById(1);
        Assert.assertNotEquals(null, dbEntry);

        dbEntry.setWeight(600);
        count = animalDao.update(dbEntry);
        Assert.assertEquals(1, count);
        dbEntry = animalDao.getById(1);
        Assert.assertEquals(600, dbEntry.getWeight(), 0);
    }

    @Test
    public void deleteTest() throws Exception{
        IAnimalDao animalDao = DependencyCacheHelper.getInstance(IAnimalDao.class);

        Animal newAnimal = new Animal();
        newAnimal.setName("Elephant");
        newAnimal.setSpecie("Loxodonta cyclotis");
        newAnimal.setWeight(500);

        long count = animalDao.add(newAnimal);
        Assert.assertEquals(1, count);

        animalDao.delete(1);

        count = animalDao.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void getAllTest() throws Exception{
        IAnimalDao animalDao = DependencyCacheHelper.getInstance(IAnimalDao.class);

        Animal elephant = new Animal();
        elephant.setName("Elephant");
        elephant.setSpecie("Loxodonta cyclotis");
        elephant.setWeight(500);

        long count = animalDao.add(elephant);
        Assert.assertEquals(1, count);

        Animal human = new Animal();
        human.setName("Human");
        human.setSpecie("Homo sapiens");
        human.setWeight(80);

        count = animalDao.add(human);
        Assert.assertEquals(2, count);

        List<Animal> listAnimal = animalDao.getAll();
        Assert.assertEquals(2, listAnimal.size());
    }

    @Test
    public void queryTest() throws Exception{
        IAnimalDao animalDao = DependencyCacheHelper.getInstance(IAnimalDao.class);

        Animal elephant = new Animal();
        elephant.setName("Elephant");
        elephant.setSpecie("Loxodonta cyclotis");
        elephant.setWeight(500);

        long count = animalDao.add(elephant);
        Assert.assertEquals(1, count);

        Animal human = new Animal();
        human.setName("Human");
        human.setSpecie("Homo sapiens");
        human.setWeight(80);

        count = animalDao.add(human);
        Assert.assertEquals(2, count);

        List<Animal> listAnimal = animalDao.query("select * from Animal where name like 'Human'");
        Assert.assertEquals(1, listAnimal.size());

        human = listAnimal.get(0);

        Assert.assertEquals(2, human.getId());
        Assert.assertEquals("Human", human.getName());
        Assert.assertEquals("Homo sapiens", human.getSpecie());
        Assert.assertEquals(80, human.getWeight(), 0);
    }
}
