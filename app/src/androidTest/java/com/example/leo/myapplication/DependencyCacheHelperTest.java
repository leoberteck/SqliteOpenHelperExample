package com.example.leo.myapplication;

import android.support.test.runner.AndroidJUnit4;

import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.utils.DependencyCacheHelper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by leo on 25/06/17.
 */

@RunWith(AndroidJUnit4.class)
public class DependencyCacheHelperTest extends BaseTestClass{

    @Test
    public void getInstanceTest() throws Exception{
        IAnimalDao dao1 = DependencyCacheHelper.getInstance(IAnimalDao.class);
        Assert.assertNotEquals(null, dao1);
        IAnimalDao dao2 = DependencyCacheHelper.getInstance(IAnimalDao.class);
        Assert.assertEquals(dao1, dao2);
    }
}