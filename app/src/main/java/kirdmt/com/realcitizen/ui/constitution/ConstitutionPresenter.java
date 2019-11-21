package kirdmt.com.realcitizen.ui.constitution;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;

import kirdmt.com.realcitizen.data.ConstitutionData;
import kirdmt.com.realcitizen.data.PoliceOrderData;
import kirdmt.com.realcitizen.model.DataModel;
import kirdmt.com.realcitizen.model.ModelCallback;
import kirdmt.com.realcitizen.ui.base.ViewBase;

public class ConstitutionPresenter implements ConstitutionFragmentContract.Presenter {

    private ConstitutionFragmentContract.View view;
    private DataModel model = new DataModel();

    static public ArrayList<ConstitutionData> constitutionDataArrayList = new ArrayList<ConstitutionData>();
    static public ArrayList<String> chaptersConstitutionNames = new ArrayList<>();

    ConstitutionPresenter() {
        getDataFromFB();
    }


    @Override
    public void attachView(ConstitutionFragmentContract.View view) {
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

                ConstitutionData constitutionData;

                for (DataSnapshot snap : snapshot.getChildren()) {


                    if (snap.getKey().contains("constitution")) {


                        constitutionData = new ConstitutionData();
                        constitutionData.setChapterName(snap.child("chapter_name").getValue().toString());
                        constitutionData.setChapterText(snap.child("chapter_content").getValue().toString());

                        chaptersConstitutionNames.add(constitutionData.getChapterName());

                        constitutionDataArrayList.add(constitutionData);


                    }
                }
                Collections.reverse(constitutionDataArrayList);
                view.setRV(constitutionDataArrayList);

            }
        });


    }
}
