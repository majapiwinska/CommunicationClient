package com.example.snapchat.database;

import android.content.Context;
import android.database.SQLException;

import com.example.snapchat.database.model.Friend;
import com.example.snapchat.database.model.Snap;
import com.example.snapchat.database.model.User;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Created by maja on 31.03.17.
 */

public class DatabaseFacade {

    private DatabaseHelper databaseHelper;

    public DatabaseFacade(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Tworzy uzytkownika jezeli nie ma innego o tym samym e-mailu,
     * w przeciwnym wypadku nic nie robi - aby nie tworzyć użytkowników przy błędnym logowaniu
     * @param user obecny uzytkownik
     * @throws SQLException
     */
    public void createOrUpdateUser(User user) throws java.sql.SQLException {
        Dao<User, Long> userDao = databaseHelper.getDao(User.class);

        List<User> query = userDao.queryBuilder().where().eq(User.COLUMN_EMAIL, user.getEmail()).query();
        if (query.size() == 0) {
            userDao.create(user);
        }
    }

    public void createSnap(Snap snap, Long id) throws java.sql.SQLException {
        Dao<Snap, Long> snapDao = databaseHelper.getDao(Snap.class);
        Dao<User, Long> userDao = databaseHelper.getDao(User.class);
        snap.setSenderId(id);
        snapDao.create(snap);
    }




}

