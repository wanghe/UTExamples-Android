package com.chehejia.example.utexamples.mvp;

import android.view.View;

public interface Contract {

    /**
     * 视图抽象接口
     */
    interface View {

        /**
         * 绑定 android.view.View 实例
         * @param root Activity 中的根视图.
         */
        void bind(android.view.View root);

        /**
         * 获取Presenter实例引用
         * @return 关联的 Presenter 实例
         */
        Presenter presenter();

        /**
         * 设置 Presenter 实例引用
         * @param presenter 设置需要关联的 Presenter 实例
         */
        void presenter(Presenter presenter);

        /**
         * 当switch 更改后调用
         * @param on 开(true)/关(false)状态
         */
        void onChangeSwitch(boolean on);

        /**
         * 点击按钮
         */
        void onPressButton();

        /**
         * 输入文字
         * @param text 输入的文字
         */
        void onInputText(String text);

        /**
         * 在视图上显示文字
         * @param text 要现实的文字
         */
        void displayWords(String text);

        /**
         * 在视图上显示数字
         * @param number 给定的数字
         */
        void displayCounter(int number);
    }

    /**
     * Presenter 接口
     */
    interface Presenter {

        /**
         * 获取视图实例
         * @return 关联视图实例
         */
        View view();

        /**
         * 设置视图实例
         * @param view 要关联的视图实例
         */
        void view(View view);

        /**
         * 获取模型实例
         * @return 关联模型实例
         */
        Model model();

        /**
         * 设置模型实例
         * @param model 要关联的模型实例
         */
        void model(Model model);

        /**
         * 业务方法(说出指定的话)
         * @param text 要说的话
         */
        void saySomething(String text);

        /**
         * 业务方法(更改计数器)
         */
        void changeCounter();

        /**
         * 业务方法(更改计数方式)
         * @param isIncreasing 增长(true)/减少(false)
         */
        void changeCountingWay(boolean isIncreasing);
    }

    /**
     * 模型接口
     */
    interface Model {

        /**
         * 获取 Presenter 实例
         * @return 关联 Presenter 实例
         */
        Presenter presenter();

        /**
         * 设置 Presenter 实例
         * @param presenter 要关联的 Presenter 实例
         */
        void presenter(Presenter presenter);

        /**
         * 获取计数方式 增长(true)/减少(false)
         */
        boolean isIncreasing();

        /**
         * 设置计数方式
         * @param value 增长(true)/减少(false)
         */
        void isIncreasing(boolean value);

        /**
         * 获取计数器值
         * @return 计数器值
         */
        int counter();

        /**
         * 设置计数器值
         * @param value 计数器值
         */
        void counter(int value);

        /**
         * 获取文字内容
         * @return 文字内容
         */
        String words();

        /**
         * 设置文字内容
         * @param words 文字内容
         */
        void words(String words);

        /**
         * 长时工作计数器
         * @return 长时工作次数
         */
        int longTimeWorkTimes();

        /**
         * 做一次长时工作.
         * @param runnable 长时工作完成后回调
         */
        void doSomeLongTimeWork(Runnable runnable);
    }

}
