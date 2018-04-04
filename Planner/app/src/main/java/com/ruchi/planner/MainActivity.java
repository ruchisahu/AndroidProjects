package com.ruchi.planner;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity {

    EditText text1,text2;
    DBSQLHelper dbHelp;
    ListView alist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (EditText) findViewById(R.id.EText1);
        text2 = (EditText) findViewById(R.id.EText2);
        dbHelp = new DBSQLHelper(this);
        alist = (ListView) findViewById(R.id.newlist);
        // alist = getListView();
        getData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.game) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNew(View view) {
        String s1 = text1.getText().toString();
        String s2 = text2.getText().toString();
        dbHelp.addData(s1,s2);
        getData();
    }
    public void getData()
    {
        Cursor cursor = dbHelp.fetchData();

        ListAdapter myAdapter = new SimpleCursorAdapter(this,R.layout.tasks,
                cursor,
                new String[]{dbHelp._ID,dbHelp.COLUMN_1,dbHelp.COLUMN_2},
                new int[]{R.id.idnum,R.id.c1,R.id.c2},0);
        alist.setAdapter(myAdapter);

    }
}