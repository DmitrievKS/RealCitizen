package kirdmt.com.realcitizen.ui.policeOrder;

        import android.view.View;

        import java.util.List;

        import kirdmt.com.realcitizen.ui.base.PresenterBase;
        import kirdmt.com.realcitizen.ui.base.ViewBase;

public class PoliceOrderFragmentContract {

    interface View extends ViewBase {
        void setRV(List<?> list);
    }

    interface Presenter extends PresenterBase<View> {

    }
}
