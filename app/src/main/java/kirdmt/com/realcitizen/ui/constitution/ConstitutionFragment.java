package kirdmt.com.realcitizen.ui.constitution;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kirdmt.com.realcitizen.R;
import kirdmt.com.realcitizen.data.ConstitutionData;

public class ConstitutionFragment extends Fragment implements ConstitutionFragmentContract.View {

    private ConstitutionPresenter presenter = new ConstitutionPresenter();

    private ConstitutionCardAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<ConstitutionData> constitutionDataList;
    private View activityView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        presenter.attachView(this);

        return inflater.inflate(R.layout.fragment_constitution, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityView = view;

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_constitution);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        //view.setBackgroundColor(getResources().getColor(R.color.lightGreen));

    }

    /*@Override
    public void setRV(List<ConstitutionData> list) {
        constitutionDataList = list;
        adapter = new ConstitutionCardAdapter(constitutionDataList, activityView.getContext());
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(adapter);
    }*/

    @Override
    public void setRV(List<?> list) {
        constitutionDataList = (List<ConstitutionData>) list;
        adapter = new ConstitutionCardAdapter(constitutionDataList, activityView.getContext());
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
