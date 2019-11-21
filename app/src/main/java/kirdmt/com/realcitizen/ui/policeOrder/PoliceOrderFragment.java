package kirdmt.com.realcitizen.ui.policeOrder;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kirdmt.com.realcitizen.R;
import kirdmt.com.realcitizen.data.PoliceOrderData;

public class PoliceOrderFragment extends Fragment implements PoliceOrderFragmentContract.View {

    private PoliceOrderPresenter presenter = new PoliceOrderPresenter();

    private PoliceOrderCardAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<PoliceOrderData> policeOrderDataList;
    private View activityView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter.attachView(this);

        return inflater.inflate(R.layout.fragment_police_order, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityView = view;

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_police);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        //view.setBackgroundColor(getResources().getColor(R.color.lightGreen));

    }

    @Override
    public void setRV(List<?> list) {
        policeOrderDataList = (List<PoliceOrderData>) list;
        adapter = new PoliceOrderCardAdapter(policeOrderDataList, activityView.getContext());
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
