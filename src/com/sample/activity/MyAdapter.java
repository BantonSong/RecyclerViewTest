package com.sample.activity;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewtest.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MyAdapter extends Adapter<MyAdapter.ViewHolder> {

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void onBindViewHolder(final MyAdapter.ViewHolder viewHolder, int arg1) {
		// TODO Auto-generated method stub
		viewHolder.mTextView.setText("我是超人" + arg1);
		OkHttpClient mOkHttpClient = new OkHttpClient();
		// 创建一个Request
		final Request request = new Request.Builder().url("https://github.com/hongyangAndroid")
				.build();
		// new call
		Call call = mOkHttpClient.newCall(request);

		final Handler handler = new Handler();

		// 请求加入调度
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
			}

			@Override
			public void onResponse(final Response response) throws IOException {
				final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
				handler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						updateUI(bitmap, viewHolder.imageView);
					}
				});
//				viewHolder.imageView.setImageBitmap(bitmap);
//				viewHolder.imageView.setVisibility(View.VISIBLE);
			}
		});
	}

	private void updateUI(Bitmap bitmap, ImageView imageView) {
		imageView.setImageBitmap(bitmap);
		imageView.setVisibility(View.VISIBLE);
	}

	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup,
				false);
		return new ViewHolder(v);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		private ImageView imageView;

		public ViewHolder(View itemView) {
			super(itemView);
			mTextView = (TextView) itemView.findViewById(R.id.mTextView);
			imageView = (ImageView) itemView.findViewById(R.id.imageView);
		}
	}
}
