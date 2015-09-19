package com.qjay.test;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qjay.android_widget.recyclerview.SuperRecyclerView;
import com.qjay.android_widget.recyclerview.SuperRecyclerViewAdapter;
import com.qjay.android_widget.recyclerview.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SuperRecyclerView mRvList = (SuperRecyclerView) findViewById(R.id.rvList);


        View viewById = findViewById(R.id.tv);

        mRvList.setEmptyView(viewById);

        myAdapter = new MyAdapter(new ArrayList<String>(), R.layout.test_item);
        mRvList.setAdapter(myAdapter);

    }


    class MyAdapter extends SuperRecyclerViewAdapter<String>{

        public MyAdapter(List<String> items, @LayoutRes int layout) {
            super(items, layout);
        }

        @Override
        public void onBindViewHolder(SuperViewHolder holder, String item) {
            TextView tv = holder.getView(R.id.tv);
            tv.setText(item);
        }
    }


    public void designTabLayout(View view) {
        if(myAdapter.isEmpty()) {
            myAdapter.setData(getList());
        }else{
            myAdapter.setData(new ArrayList<String>());
        }
//        startActivity(new Intent(this, DesignTabLayoutActivity.class));
    }

    private List<String> getList() {
        if (mList == null) {
            mList = new ArrayList<String>();
            for (int i = 0; i < 100; i++) {
                mList.add("当前是第" + i + "个条目");
            }
        }


        return mList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
