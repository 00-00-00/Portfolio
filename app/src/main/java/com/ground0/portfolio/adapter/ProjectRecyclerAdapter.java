package com.ground0.portfolio.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ground0.model.Project;
import com.ground0.portfolio.R;
import com.ground0.portfolio.databinding.ItemProjectBinding;
import com.ground0.portfolio.util.ProjectItemViewModelHandler;
import com.ground0.portfolio.viewmodel.ProjectItemViewModelFactory;
import java.util.List;

/**
 * Created by zer0 on 11/10/16.
 */

public class ProjectRecyclerAdapter
    extends RecyclerView.Adapter<ProjectRecyclerAdapter.ViewHolder> {

  List<Project> data;
  LayoutInflater layoutInflater;
  ProjectItemViewModelFactory projectItemViewModelFactory;
  ProjectItemViewModelHandler projectItemViewModelHandler;

  public ProjectRecyclerAdapter(List<Project> data,
      ProjectItemViewModelHandler projectItemViewModelHandler) {
    this.data = data;
    projectItemViewModelFactory = new ProjectItemViewModelFactory();
    this.projectItemViewModelHandler = projectItemViewModelHandler;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (layoutInflater == null) layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_project, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.itemProjectBinding = DataBindingUtil.bind(holder.itemView);
    ProjectItemViewModelFactory.ProjectItemViewModel itemViewModel =
        projectItemViewModelFactory.createViewModel(data.get(position), projectItemViewModelHandler);
    holder.itemProjectBinding.setViewModel(itemViewModel);
  }

  @Override public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    public ItemProjectBinding itemProjectBinding;

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
