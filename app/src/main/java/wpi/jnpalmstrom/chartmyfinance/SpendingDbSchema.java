package wpi.jnpalmstrom.chartmyfinance;

import android.provider.BaseColumns;

public class SpendingDbSchema {

    private SpendingDbSchema() {}

    public static class SpendingEntry implements BaseColumns {
        public static final String TABLE_NAME = "SpendingEntries";

        public static final String COLUMN_NAME_TRAVEL = "travel";
        public static final String COLUMN_NAME_FOOD = "food";
        public static final String COLUMN_NAME_HEALTH = "health";
        public static final String COLUMN_NAME_SHOPPING = "shopping";
        public static final String COLUMN_NAME_OTHER = "other";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + SpendingEntry.TABLE_NAME + " (" +
                        SpendingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        SpendingEntry.COLUMN_NAME_TRAVEL + " REAL," +
                        SpendingEntry.COLUMN_NAME_FOOD + " REAL," +
                        SpendingEntry.COLUMN_NAME_HEALTH + " REAL," +
                        SpendingEntry.COLUMN_NAME_SHOPPING + " REAL," +
                        SpendingEntry.COLUMN_NAME_OTHER + " REAL" +
                        ")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpendingEntry.TABLE_NAME;
    }


}
