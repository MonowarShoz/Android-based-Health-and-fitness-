package com.shoz.monowar.fitnessandhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    Context c;
    SQLiteDatabase db;
    DbHelper helper;

    public  DbAdapter(Context c){
        this.c = c;
        helper = new DbHelper(c);
    }
    public DbAdapter openDB()
    {
        try {
            db=helper.getWritableDatabase();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    //CLOSE DATABASE
    public void closeDB()
    {
        try {
            helper.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    //INSERT
    public long add(String name,String pos,String serv)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(dbConstants.NAME,name);
            cv.put(dbConstants.CALORIES, pos);
            cv.put(dbConstants.serv,serv);

            return db.insert(dbConstants.TB_NAME,dbConstants.ROW_ID,cv);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    //RETRIEVE
    public Cursor getAllFoods()
    {
        String[] columns={dbConstants.ROW_ID,dbConstants.NAME,dbConstants.CALORIES,dbConstants.serv};

        return db.query(dbConstants.TB_NAME,columns,null,null,null,null,null);

    }
    public boolean delete(int id)
    {
        try
        {
            int result=db.delete(dbConstants.TB_NAME,dbConstants.ROW_ID+" =?",new String[]{String.valueOf(id)});

            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
