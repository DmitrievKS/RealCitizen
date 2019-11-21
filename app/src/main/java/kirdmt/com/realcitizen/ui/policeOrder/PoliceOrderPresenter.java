package kirdmt.com.realcitizen.ui.policeOrder;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;

import kirdmt.com.realcitizen.data.PoliceOrderData;
import kirdmt.com.realcitizen.model.DataModel;
import kirdmt.com.realcitizen.model.ModelCallback;
import kirdmt.com.realcitizen.ui.base.ViewBase;


public class PoliceOrderPresenter implements PoliceOrderFragmentContract.Presenter {

    private PoliceOrderFragmentContract.View view;
    private DataModel model = new DataModel();

    static public ArrayList<PoliceOrderData> policeOrderDataArrayList = new ArrayList<PoliceOrderData>();
    static public ArrayList<String> chaptersPoliceOrderNames = new ArrayList<>();

    PoliceOrderPresenter() {
        getDataFromFB();
    }


    @Override
    public void attachView(PoliceOrderFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {

    }

    public void getDataFromFB() {

        model.getFireBaseData(new ModelCallback() {
            @Override
            public void onCallBack(DataSnapshot snapshot) {

                PoliceOrderData policeOrderData;

                for (DataSnapshot snap : snapshot.getChildren()) {


                    if (snap.getKey().contains("policeorder")) {

                        policeOrderData = new PoliceOrderData();
                        policeOrderData.setChapterName(snap.child("chapter_name").getValue().toString());
                        policeOrderData.setChapterText(snap.child("chapter_content").getValue().toString());

                        chaptersPoliceOrderNames.add(policeOrderData.getChapterName());

                        policeOrderDataArrayList.add(policeOrderData);

                    }
                }
                Collections.reverse(policeOrderDataArrayList);
                view.setRV(policeOrderDataArrayList);

            }
        });


    }
}
