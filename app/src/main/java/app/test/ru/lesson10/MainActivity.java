package app.test.ru.lesson10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        insertItem();
        insertItem();
        insertItem();

        showTable();
    }

    public void insertItem() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", "dsafadgadfg");
        cv.put("email", "dsfdsf@xvv.cpm");
        db.insert("myTable", null, cv);

        db.close();

        db.execSQL("select ");

    }

    public void showTable() {

        Log.d("TAG", "MyTable");

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.query("myTable", null, null, null, null, null, null);
        if (c.moveToFirst()) {

            int idIndex = c.getColumnIndex("id");
            int nameIndex = c.getColumnIndex("name");
            int emailIndex = c.getColumnIndex("email");

            do {
                Log.d("TAG",
                        " ID = " + c.getInt(idIndex) +
                        " Name = " + c.getString(nameIndex) +
                        " Email = " + c.getString(emailIndex)
                );
            } while (c.moveToNext());
        }

        dbHelper.close();

    }


    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table myTable (" +
                    "id integer primary key autoincrement, " +
                    "name text, " +
                    "email text);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }


}
