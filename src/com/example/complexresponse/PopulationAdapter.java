package com.example.complexresponse;

import java.io.InputStream;
import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PopulationAdapter extends ArrayAdapter<Worldpopulation>{

	ArrayList<Worldpopulation> list;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;
	
	public PopulationAdapter(Context context, int resource,  ArrayList<Worldpopulation> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		list = objects;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			
			holder.imageview = (ImageView) v.findViewById(R.id.imageViewFlag);
			holder.tvRank = (TextView) v.findViewById(R.id.textViewRank);
			holder.tvCountry = (TextView) v.findViewById(R.id.textViewCountry);
			holder.tvPopulation = (TextView) v.findViewById(R.id.textViewPopulation);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
			holder.imageview.setImageResource(R.drawable.ic_launcher);
			new DownloadImageTask(holder.imageview).execute(list.get(position).getImage());
			holder.tvRank.setText("Rank : " + list.get(position).getRank());
			holder.tvCountry.setText("Country : " + list.get(position).getCountry());
			holder.tvPopulation.setText("Population : " + list.get(position).getPopulation());
			return v;

	}

			
			
		static class ViewHolder {
			public ImageView imageview;
			public TextView tvRank;
			public TextView tvCountry;
			public TextView tvPopulation;
		}
	
		private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
			ImageView bmImage;
	
			public DownloadImageTask(ImageView bmImage) {
				this.bmImage = bmImage;
			}
	
			protected Bitmap doInBackground(String... urls) {
				String urldisplay = urls[0];
				Bitmap mIcon11 = null;
				try {
					InputStream in = new java.net.URL(urldisplay).openStream();
					mIcon11 = BitmapFactory.decodeStream(in);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return mIcon11;
			}
	
			protected void onPostExecute(Bitmap result) {
				bmImage.setImageBitmap(result);
			}
	
		}
	}
