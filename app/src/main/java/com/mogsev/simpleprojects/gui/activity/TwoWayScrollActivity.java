package com.mogsev.simpleprojects.gui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.data.model.Task;
import com.mogsev.simpleprojects.databinding.ActivityTwoWayScrollBinding;
import com.mogsev.simpleprojects.mock.MockTask;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TwoWayScrollActivity extends AppCompatActivity {

    private ActivityTwoWayScrollBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_scroll);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_scroll_beta);
        Timber.i("onCreate");

        // start to fill view
        List<Task> tasks = MockTask.getFakeTasks();
        Timber.i("Tasks: %s", tasks);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_linear_layout_vertical_tasks, null, false);
        for (int i = 0; i < tasks.size(); i++) {

        }

    }


}
