package com.example.leo.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.leo.myapplication.interfaces.IDao;
import com.example.leo.myapplication.interfaces.IEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.example.leo.myapplication.database.DatabaseHelper.getInstance;

/**
 * Created by leo on 24/06/17.
 */

public abstract class BaseDao<T extends IEntity> implements IDao<T>{

    private Class<T> tClass;
    private Context context;

    private String getTableName(){
        return tClass.getSimpleName();
    }

    protected BaseDao(Class<T> tClass)
    {
        this.tClass = tClass;
    }

    @Override
    public long add(T entity) {
        SQLiteDatabase db = getInstance(context).getWritableDatabase();
        ContentValues values = createValues(entity);
        values.remove("id");
        Long count = db.insertOrThrow(getTableName(), null, values);
        return count;
    }

    @Override
    public int update(T entity) {
        SQLiteDatabase db = getInstance(context).getWritableDatabase();
        ContentValues values = createValues(entity);
        int count = db.update(getTableName(), values, "id = ?", new String[]{String.valueOf((entity.getId()))});
        return count;
    }

    @Override
    public void delete(T entity) {
        delete(entity.getId());
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = getInstance(context).getWritableDatabase();
        db.delete(getTableName(), "id = ?",  new String[] { String.valueOf(id) });
    }

    @Override
    public T getById(int id) throws InstantiationException {
        String query = "select * from " + getTableName() + " where id = " + id;
        T entity = null;
        SQLiteDatabase db = getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            try {
                entity = createNewInstanceFromCursor(cursor);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }finally {
                cursor.close();
            }
        }
        return entity;
    }

    @Override
    public List<T> getAll() throws InstantiationException {
        String query = "select * from " + getTableName();
        return query(query);
    }

    @Override
    public List<T> query(String query) throws InstantiationException {
        List<T> listEntity = new ArrayList<>();

        SQLiteDatabase db = getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            listEntity = serializeCursor(cursor);
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return listEntity;
    }

    @Override
    public int getCount() {
        String query = "select * from " + getTableName();
        SQLiteDatabase db = getInstance(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    protected ContentValues createValues(T entity){
        ContentValues values = new ContentValues();
        Field[] listField = tClass.getDeclaredFields();
        for (Field field : listField){
            field.setAccessible(true);
            try {
                Class fType = field.getType();
                if (fType.equals(String.class)) {
                    values.put(field.getName(), (String)field.get(entity));
                } else if (fType.equals(int.class)) {
                    values.put(field.getName(), field.getInt(entity));
                } else if (fType.equals(double.class)) {
                    values.put(field.getName(), field.getDouble(entity));
                } else if (fType.equals(float.class)) {
                    values.put(field.getName(), field.getFloat(entity));
                } else if (fType.equals(long.class)) {
                    values.put(field.getName(), field.getLong(entity));
                } else if (fType.equals(short.class)) {
                    values.put(field.getName(), field.getShort(entity));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return values;
    }

    protected T createNewInstanceFromCursor(Cursor cursor) throws IllegalAccessException, InstantiationException {
        T entity = tClass.newInstance();

        Field[] fields = tClass.getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            Class fType = field.getType();
            if (fType.equals(String.class)) {
                field.set(entity, cursor.getString(cursor.getColumnIndex(field.getName())));
            } else if (fType.equals(int.class)) {
                field.setInt(entity, cursor.getInt(cursor.getColumnIndex(field.getName())));
            } else if (fType.equals(double.class)) {
                field.setDouble(entity, cursor.getDouble(cursor.getColumnIndex(field.getName())));
            } else if (fType.equals(float.class)) {
                field.setFloat(entity, cursor.getFloat(cursor.getColumnIndex(field.getName())));
            } else if (fType.equals(long.class)) {
                field.setLong(entity, cursor.getLong(cursor.getColumnIndex(field.getName())));
            } else if (fType.equals(short.class)) {
                field.setShort(entity, cursor.getShort(cursor.getColumnIndex(field.getName())));
            }
        }
        return entity;
    }

    protected List<T> serializeCursor(Cursor cursor) throws InstantiationException, IllegalAccessException {
        List<T> listEntity = new ArrayList<>();
        if(cursor.moveToFirst()){
            Field[] listField = tClass.getFields();
            do {
                listEntity.add(createNewInstanceFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listEntity;
    }
}
