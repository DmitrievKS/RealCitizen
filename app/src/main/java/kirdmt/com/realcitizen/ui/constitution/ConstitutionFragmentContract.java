package kirdmt.com.realcitizen.ui.constitution;

import android.view.View;

import java.util.List;

import kirdmt.com.realcitizen.ui.base.PresenterBase;
import kirdmt.com.realcitizen.ui.base.ViewBase;

public class ConstitutionFragmentContract {

    interface View extends ViewBase {

        void setRV(List<?> list);
    }


    interface Presenter extends PresenterBase<View> {

    }
}
