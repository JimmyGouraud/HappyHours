package com.krazyxx.happyhours.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Krazyxx on 18/12/2017.
 */

@Database(entities = {Date.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract DateDao dateDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "date-database")
                            // allow queries on the main thread.
                            // TODO : Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() { INSTANCE = null; }

    public void clearDatabase() {
        for ( Date date : this.dateDao().getAll()) {
            this.dateDao().delete(date);
        }
    }
}