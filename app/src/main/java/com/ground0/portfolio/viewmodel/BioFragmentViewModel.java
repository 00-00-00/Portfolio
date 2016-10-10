package com.ground0.portfolio.viewmodel;

import android.support.v7.widget.RecyclerView;
import com.ground0.portfolio.adapter.BioRecyclerAdapter;
import com.ground0.portfolio.core.viewmodel.BaseFragmentViewModel;
import com.ground0.portfolio.fragment.BioFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Created by zer0 on 10/10/16.
 */

public class BioFragmentViewModel extends BaseFragmentViewModel<BioFragment> {

  BioRecyclerAdapter bioRecyclerAdapter;
  List<Object> data = new ArrayList<Object>() {{
    add(new Object());
  }};

  @Inject public BioFragmentViewModel() {

  }
}
