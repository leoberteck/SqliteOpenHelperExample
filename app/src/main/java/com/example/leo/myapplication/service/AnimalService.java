package com.example.leo.myapplication.service;

import com.example.leo.myapplication.R;
import com.example.leo.myapplication.customexceptions.ValidationException;
import com.example.leo.myapplication.entity.Animal;
import com.example.leo.myapplication.interfaces.IAnimalDao;
import com.example.leo.myapplication.interfaces.IAnimalService;
import com.example.leo.myapplication.interfaces.IDao;
import com.example.leo.myapplication.utils.DependencyCacheHelper;
import com.example.leo.myapplication.utils.StringUtils;

/**
 * Created by Trovata on 27/06/2017.
 */

public class AnimalService extends BaseService<Animal> implements IAnimalService{

    @Override
    public void validate(Animal entity) throws ValidationException {
        if(entity == null){
            throw new ValidationException("animal", "null entity exception", Animal.class, R.string.error_animal_null);
        } else if(StringUtils.isNullOrEmpty(entity.getName())){
            throw new ValidationException("name", "cannot be null", Animal.class, R.string.error_animal_name_null);
        } else if(StringUtils.isNullOrEmpty(entity.getSpecie())){
            throw new ValidationException("specie", "cannot be null", Animal.class, R.string.error_animal_specie_null);
        } else if(entity.getWeight() == 0){
            throw new ValidationException("weight", "cannot be zero", Animal.class, R.string.error_animal_weight_zero);
        }
    }

    @Override
    public IDao<Animal> getTDao() {
        return DependencyCacheHelper.getInstance(IAnimalDao.class);
    }
}
