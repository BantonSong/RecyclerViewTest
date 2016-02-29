package com.sample.activity;

import com.example.recyclerviewtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {
	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		// 设置LinearLayoutManager
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		// 设置ItemAnimator
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		// 设置固定大小
		recyclerView.setHasFixedSize(true);

		MyAdapter myAdapter = new MyAdapter();
		recyclerView.setAdapter(myAdapter);
	}
}
