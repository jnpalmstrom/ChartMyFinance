package wpi.jnpalmstrom.chartmyfinance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpendingEntryDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SpendingEntries.db";

    public SpendingEntryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SpendingDbSchema.SpendingEntry.TABLE_NAME + " (" +
                SpendingDbSchema.SpendingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SpendingDbSchema.SpendingEntry.COLUMN_NAME_TRAVEL + " REAL," +
                SpendingDbSchema.SpendingEntry.COLUMN_NAME_FOOD + " REAL," +
                SpendingDbSchema.SpendingEntry.COLUMN_NAME_HEALTH + " REAL," +
                SpendingDbSchema.SpendingEntry.COLUMN_NAME_SHOPPING + " REAL," +
                SpendingDbSchema.SpendingEntry.COLUMN_NAME_OTHER + " REAL" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(...);
        onCreate(db);
    }
}
