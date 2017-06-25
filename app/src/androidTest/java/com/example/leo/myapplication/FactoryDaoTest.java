package com.example.leo.myapplication;

import android.content.Context;
import android.content.pm.InstrumentationInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.utils.FactoryDao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by leo on 25/06/17.
 */

@RunWith(AndroidJUnit4.class)
public class FactoryDaoTest extends BaseTestClass{

    @Test
    public void getInstanceTest() throws Exception{
        IAnimalDao dao1 = FactoryDao.getInstance(IAnimalDao.class, context);
        Assert.assertNotEquals(null, dao1);
        IAnimalDao dao2 = FactoryDao.getInstance(IAnimalDao.class, context);
        Assert.assertEquals(dao1, dao2);
    }
}