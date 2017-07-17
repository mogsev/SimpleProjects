package com.mogsev.simpleprojects.fragment;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogsev.basecontent.utils.AssetsHelper;
import com.mogsev.simpleprojects.R;
import com.mogsev.simpleprojects.adapter.GoodsRvAdapter;
import com.mogsev.simpleprojects.adapter.TariffsRvAdapter;
import com.mogsev.simpleprojects.adapter.UsersRvAdapter;
import com.mogsev.simpleprojects.data.entity.Goods;
import com.mogsev.simpleprojects.data.entity.Tariff;
import com.mogsev.simpleprojects.data.entity.User;
import com.mogsev.simpleprojects.databinding.FragmentExpandableBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpandableFragment extends Fragment {
    private static final String TAG = ExpandableFragment.class.getSimpleName();

    public final ObservableBoolean mGoodsRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mUsersRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mTariffsRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mFourthRvVisible = new ObservableBoolean(false);
    public final ObservableBoolean mFifthRvVisible = new ObservableBoolean(false);

    private FragmentExpandableBinding mBinding;

    private GoodsRvAdapter mGoodsRvAdapter = new GoodsRvAdapter();
    private UsersRvAdapter mUsersRvAdapter = new UsersRvAdapter();
    private TariffsRvAdapter mTariffsRvAdapter = new TariffsRvAdapter();

    public ExpandableFragment() {
        // Required empty public constructor
    }

    public static ExpandableFragment newInstance() {
        ExpandableFragment fragment = new ExpandableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_expandable, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        mBinding.setFragment(this);

        // initialize recycler view goods
        initGoodsRecyclerView();

        // initialize recycler view users
        initUsersRecyclerView();

        // initialize recycler view tariffs
    }

    public boolean onClickFirst() {
        Log.i(TAG, "onClickFirst");
        mGoodsRvVisible.set(!mGoodsRvVisible.get());
        return true;
    }

    public boolean onClickSecond() {
        Log.i(TAG, "onClickSecond");
        mUsersRvVisible.set(!mUsersRvVisible.get());
        return true;
    }

    public boolean onClickTariffs() {
        Log.i(TAG, "onClickSecond");
        mTariffsRvVisible.set(!mTariffsRvVisible.get());
        return true;
    }

    private void initGoodsRecyclerView() {
        mBinding.recyclerViewGoods.setScrollContainer(false);
        mBinding.recyclerViewGoods.setAdapter(mGoodsRvAdapter);

        String json = AssetsHelper.loadJsonFromAssets(getContext(), "goods.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Goods>>() {}.getType();
        List<Goods> list = gson.fromJson(json, type);
        mGoodsRvAdapter.addAll(list);
    }

    private void initUsersRecyclerView() {
        mBinding.recyclerViewUsers.setScrollContainer(false);
        mBinding.recyclerViewUsers.setAdapter(mUsersRvAdapter);

        String json = AssetsHelper.loadJsonFromAssets(getContext(), "users.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {}.getType();
        List<User> list = gson.fromJson(json, type);
        mUsersRvAdapter.addAll(list);
    }

    private void initTariffsRecyclerView() {
        mBinding.recyclerViewTariffs.setScrollContainer(false);
        mBinding.recyclerViewTariffs.setAdapter(mTariffsRvAdapter);

        String json = AssetsHelper.loadJsonFromAssets(getContext(), "tariffs.json");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Tariff>>() {}.getType();
        List<Tariff> list = gson.fromJson(json, type);
        mUsersRvAdapter.addAll(list);
    }
}
