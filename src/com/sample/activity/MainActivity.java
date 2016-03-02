package com.sample.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.widget.AbsListView;

import com.example.recyclerviewtest.R;

public class MainActivity extends Activity {
	private RecyclerView recyclerView;
	private boolean isScrollDown;
	private boolean isLoading;
	private View listViewBottomLayout; // listview下面加载中布局
	private MyAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		// 设置LinearLayoutManager
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		// 设置ItemAnimator
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		// 设置固定大小
		recyclerView.setHasFixedSize(true);

		listViewBottomLayout = findViewById(R.id.listViewBottomLayout);

		myAdapter = new MyAdapter(10);
		recyclerView.setAdapter(myAdapter);
		recyclerView.addOnScrollListener(onScrollListener);
	}

	OnScrollListener onScrollListener = new OnScrollListener() {
		public void onScrollStateChanged(RecyclerView recyclerview, int scrollState) {
			// 当滚到最后一行且停止滚动时，执行加载
			if (!isLoading && isScrollDown && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
				LinearLayoutManager manager = (LinearLayoutManager) recyclerview.getLayoutManager();
				int lastVisible = manager.findLastCompletelyVisibleItemPosition();
				int totalCount = manager.getItemCount();

				if (lastVisible == totalCount - 1) {
					recyclerView.scrollToPosition(lastVisible);
					listViewBottomLayout.setVisibility(View.VISIBLE);
				} else {
					return;
				}
				isLoading = true;
				AsyncTask<Void, Void, Void> asynctask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						isLoading = false;
						listViewBottomLayout.setVisibility(View.GONE);
						myAdapter.setCount(myAdapter.getCount() + 5);
						myAdapter.notifyDataSetChanged();
					}
				};

				asynctask.execute();
			}
		}

		public void onScrolled(RecyclerView recyclerview, int dx, int dy) {
			// TODO
			if (dy > 0) {
				isScrollDown = true;
			} else {
				isScrollDown = false;
			}
		}
	};
}
