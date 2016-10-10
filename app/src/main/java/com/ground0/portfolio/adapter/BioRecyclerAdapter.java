package com.ground0.portfolio.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.portfolio.R;
import com.ground0.portfolio.util.Constants;
import com.squareup.picasso.Picasso;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioRecyclerAdapter extends RecyclerView.Adapter<BioRecyclerAdapter.ViewHolder> {

  List<Object> data;
  LayoutInflater layoutInflater;

  @Retention(RetentionPolicy.SOURCE) @IntDef({ PRIMARY, OTHER }) public @interface ViewType {
  }

  public static final int PRIMARY = 0;
  public static final int OTHER = 1;

  public BioRecyclerAdapter(List<Object> data) {
    this.data = data;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.getContext());
    switch (viewType) {
      case PRIMARY:
        View view = layoutInflater.inflate(R.layout.item_bio_primary, parent, false);
        return new ViewHolder(view, viewType);

      case OTHER:
        View view1 = layoutInflater.inflate(R.layout.item_bio_primary, parent, false);
        return new ViewHolder(view1, viewType);
    }
    return null;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case PRIMARY:
        Picasso.with(holder.imageView.getContext())
            .load(Constants.Image_URL)
            .placeholder(R.color.colorAccent)
            .into(holder.imageView);
        break;
      case OTHER:
        break;
    }
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @ViewType @Override public int getItemViewType(int position) {
    if (position == 0) {
      return PRIMARY;
    } else {
      return OTHER;
    }
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.i_bio_primary_image) public ImageView imageView;
    @ViewType public int viewType;

    public ViewHolder(View itemView, @ViewType int viewType) {
      super(itemView);
      this.viewType = viewType;
      ButterKnife.bind(this, itemView);
    }
  }
}
