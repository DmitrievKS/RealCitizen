package kirdmt.com.realcitizen.ui.main;

public class Presenter implements MainActivityContract.Presenter{

    private MainActivityContract.View view;

    Presenter(MainActivityContract.View mainView) {

        this.view = mainView;

    }


    public void sendMail() {
        view.callMailIntent();
    }

    @Override
    public void attachView(MainActivityContract.View view) {

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
}
