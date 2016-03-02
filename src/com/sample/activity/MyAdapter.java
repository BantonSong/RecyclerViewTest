package com.sample.activity;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	private int count;

	public MyAdapter(int count) {
		this.count = count;
	}

	@Override
	public int getItemCount() {
		return count;
	}

	@Override
	public void onBindViewHolder(final MyAdapter.ViewHolder viewHolder, int arg1) {
		// TODO Auto-generated method stub
		viewHolder.mTextView.setText("我是超人" + arg1);
		OkHttpClient mOkHttpClient = new OkHttpClient();
		// 创建一个Request
		String url = "http://static.adesk.com/wallpaper?imgid=56d2fc1069401b79f1a42a20&reso=720x600";
		final Request request = new Request.Builder().url(url).build();
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
						updateUI(bitmap, viewHolder.imageView);
					}
				});
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
