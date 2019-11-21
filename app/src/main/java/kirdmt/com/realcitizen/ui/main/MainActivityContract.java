package kirdmt.com.realcitizen.ui.main;

import android.content.Context;

import java.util.List;

import kirdmt.com.realcitizen.ui.base.PresenterBase;
import kirdmt.com.realcitizen.ui.base.ViewBase;
import kirdmt.com.realcitizen.ui.constitution.ConstitutionFragmentContract;

public class MainActivityContract {

    interface View extends ViewBase {


        void callMailIntent();

        Context getContext();

        void showToast(String message);
    }


    interface Presenter extends PresenterBase<MainActivityContract.View> {


    }

}
