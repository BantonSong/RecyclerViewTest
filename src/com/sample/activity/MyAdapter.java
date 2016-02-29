package com.sample.activity;

import com.example.recyclerviewtest.R;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends Adapter<MyAdapter.ViewHolder> {

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int arg1) {
		// TODO Auto-generated method stub
		viewHolder.mTextView.setText("我是超人" + arg1);
	}

	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup,
				false);
		return new ViewHolder(v);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;

		public ViewHolder(View itemView) {
			super(itemView);
			mTextView = (TextView) itemView.findViewById(R.id.mTextView);
		}
	}
}
