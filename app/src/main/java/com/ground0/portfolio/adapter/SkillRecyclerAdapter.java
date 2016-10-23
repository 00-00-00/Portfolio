package com.ground0.portfolio.adapter;

import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import az.plainpie.PieView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.model.Skill;
import com.ground0.portfolio.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by zer0 on 10/10/16.
 */

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolder> {

  List<Object> data;
  LayoutInflater layoutInflater;

  @Retention(RetentionPolicy.SOURCE) @IntDef({ PRIMARY, DIVIDER }) public @interface ViewType {
  }

  public static final int PRIMARY = 0;
  public static final int DIVIDER = 1;

  public SkillRecyclerAdapter(List<Object> data) {
    this.data = data;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.getContext());
    View view = null;
    switch (viewType) {
      case PRIMARY:
        view = layoutInflater.inflate(R.layout.item_skill, parent, false);
        return new PrimaryViewHolder(view);
      case DIVIDER:
        view = layoutInflater.inflate(R.layout.item_skill_divider, parent, false);
        return new DividerViewHolder(view);
    }
    return new ViewHolder(view, viewType);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case PRIMARY:
        ((PrimaryViewHolder) holder).setPieChart(
            ((Skill) data.get(position)).getProficiencyPercent());
        ((PrimaryViewHolder) holder).textView.setText(((Skill) data.get(position)).getName());
        break;
      case DIVIDER:
        ((DividerViewHolder) holder).dividerText.setText((String) data.get(position));
    }
  }

  @Override public int getItemCount() {
    return data.size();
  }

  @ViewType @Override public int getItemViewType(int position) {
    //Logic for deciding the divider goes here
    if (data.get(position) instanceof String) return DIVIDER;
    return PRIMARY;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @ViewType public int viewType;

    public ViewHolder(View itemView, @ViewType int viewType) {
      super(itemView);
      this.viewType = viewType;
    }
  }

  public class PrimaryViewHolder extends ViewHolder {

    @BindView(R.id.i_skill_pie) public PieView pieChart;
    @BindView(R.id.i_skill_text) public TextView textView;

    public PrimaryViewHolder(View itemView) {
      super(itemView, PRIMARY);
      ButterKnife.bind(this, itemView);
    }

    public void setPieChart(int percentage) {
      pieChart.setInnerBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.translucent_apricot));
      pieChart.setmPercentage(percentage);
      pieChart.setMainBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.transparent));
      pieChart.setPercentageBackgroundColor(
          ContextCompat.getColor(itemView.getContext(), R.color.key_lime));
    }
  }

  public class DividerViewHolder extends ViewHolder {

    @BindView(R.id.i_skill_divider_text) public TextView dividerText;

    public DividerViewHolder(View itemView) {
      super(itemView, DIVIDER);
      ButterKnife.bind(this, itemView);
    }
  }
}
