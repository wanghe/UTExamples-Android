package com.chehejia.example.utexamples.mvp;

import android.view.View;

public interface Contract {

    interface View {

        void bind(android.view.View root);

        Presenter presenter();
        void presenter(Presenter presenter);

        void changeSwitch(boolean on);
        void pressButton();
        void inputText(String text);

        void displayWords(String text);
        void displayCounter(int number);
    }

    interface Presenter {
        View view();
        void view(View view);

        Model model();
        void model(Model model);

        void saySomething(String text);

        void changeCounter();

        void changeCountingWay(boolean isIncreasing);
    }

    interface Model {

        int longTimeWorkTimes();

        Presenter presenter();
        void presenter(Presenter presenter);

        boolean isIncreasing();
        void isIncreasing(boolean value);

        int counter();
        void counter(int value);

        String words();
        void words(String words);

        void doSomeLongTimeWork(Runnable runnable);
    }

}
