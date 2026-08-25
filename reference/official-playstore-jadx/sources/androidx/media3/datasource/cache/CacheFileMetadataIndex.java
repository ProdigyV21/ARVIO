package androidx.media3.datasource.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import androidx.compose.material3.d;
import androidx.media3.database.DatabaseIOException;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.database.VersionTable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
final class CacheFileMetadataIndex {
    private static final int COLUMN_INDEX_LAST_TOUCH_TIMESTAMP = 2;
    private static final int COLUMN_INDEX_LENGTH = 1;
    private static final int COLUMN_INDEX_NAME = 0;
    private static final String COLUMN_NAME = "name";
    private static final String TABLE_PREFIX = "ExoPlayerCacheFileMetadata";
    private static final String TABLE_SCHEMA = "(name TEXT PRIMARY KEY NOT NULL,length INTEGER NOT NULL,last_touch_timestamp INTEGER NOT NULL)";
    private static final int TABLE_VERSION = 1;
    private static final String WHERE_NAME_EQUALS = "name = ?";
    private final DatabaseProvider databaseProvider;
    private String tableName;
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_LAST_TOUCH_TIMESTAMP = "last_touch_timestamp";
    private static final String[] COLUMNS = {"name", COLUMN_LENGTH, COLUMN_LAST_TOUCH_TIMESTAMP};

    public CacheFileMetadataIndex(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    public static void delete(DatabaseProvider databaseProvider, long j10) throws DatabaseIOException {
        String hexString = Long.toHexString(j10);
        try {
            String tableName = getTableName(hexString);
            SQLiteDatabase writableDatabase = databaseProvider.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                VersionTable.removeVersion(writableDatabase, 2, hexString);
                dropTable(writableDatabase, tableName);
                writableDatabase.setTransactionSuccessful();
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }

    private static void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
    }

    private Cursor getCursor() {
        this.tableName.getClass();
        return this.databaseProvider.getReadableDatabase().query(this.tableName, COLUMNS, null, null, null, null, null);
    }

    private static String getTableName(String str) {
        return d.C(TABLE_PREFIX, str);
    }

    public Map<String, CacheFileMetadata> getAll() throws DatabaseIOException {
        try {
            Cursor cursor = getCursor();
            try {
                HashMap map = new HashMap(cursor.getCount());
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    string.getClass();
                    map.put(string, new CacheFileMetadata(cursor.getLong(1), cursor.getLong(2)));
                }
                cursor.close();
                return map;
            } finally {
            }
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }

    public void initialize(long j10) throws DatabaseIOException {
        try {
            String hexString = Long.toHexString(j10);
            this.tableName = getTableName(hexString);
            if (VersionTable.getVersion(this.databaseProvider.getReadableDatabase(), 2, hexString) != 1) {
                SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    VersionTable.setVersion(writableDatabase, 2, hexString, 1);
                    dropTable(writableDatabase, this.tableName);
                    writableDatabase.execSQL("CREATE TABLE " + this.tableName + " (name TEXT PRIMARY KEY NOT NULL,length INTEGER NOT NULL,last_touch_timestamp INTEGER NOT NULL)");
                    writableDatabase.setTransactionSuccessful();
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }

    public void remove(String str) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            this.databaseProvider.getWritableDatabase().delete(this.tableName, WHERE_NAME_EQUALS, new String[]{str});
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }

    public void removeAll(Set<String> set) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    writableDatabase.delete(this.tableName, WHERE_NAME_EQUALS, new String[]{it.next()});
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }

    public void set(String str, long j10, long j11) throws DatabaseIOException {
        this.tableName.getClass();
        try {
            SQLiteDatabase writableDatabase = this.databaseProvider.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put(COLUMN_LENGTH, Long.valueOf(j10));
            contentValues.put(COLUMN_LAST_TOUCH_TIMESTAMP, Long.valueOf(j11));
            writableDatabase.replaceOrThrow(this.tableName, null, contentValues);
        } catch (SQLException e5) {
            throw new DatabaseIOException(e5);
        }
    }
}
