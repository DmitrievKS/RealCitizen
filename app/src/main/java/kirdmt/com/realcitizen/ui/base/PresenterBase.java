package kirdmt.com.realcitizen.ui.base;

public interface PresenterBase<V extends ViewBase> {

    void attachView(V view);

    void viewIsReady();

    void detachView();

    void destroy();
}
