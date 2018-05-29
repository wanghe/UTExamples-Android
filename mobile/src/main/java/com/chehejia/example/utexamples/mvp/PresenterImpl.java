package com.chehejia.example.utexamples.mvp;

public class PresenterImpl implements Contract.Presenter {

    private Contract.View mView = null;
    private Contract.Model mModel = null;

    @Override
    public Contract.View view() {
        return mView;
    }

    @Override
    public void view(Contract.View view) {
        mView = view;
    }

    @Override
    public Contract.Model model() {
        return mModel;
    }

    @Override
    public void model(Contract.Model model) {
        mModel = model;
    }

    @Override
    public void saySomething(String text) {
        model().words(text);
        view().displayWords(model().words());
    }

    @Override
    public void changeCountingWay(boolean isIncreasing) {
        model().isIncreasing(isIncreasing);
    }

    @Override
    public void changeCounter() {
        if (model().isIncreasing()) {
            model().counter(model().counter() + 1);
        } else {
            model().counter(model().counter() - 1);
        }

        view().displayCounter(model().counter());
    }
}
