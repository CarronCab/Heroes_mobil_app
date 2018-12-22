package com.example.charl.heroes;

import android.app.Application;
import android.databinding.DataBindingUtil;
import com.example.charl.heroes.helpers.RecyclerViewDataBinding;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DataBindingUtil.setDefaultComponent(new android.databinding.DataBindingComponent() {
                                                @Override
                                                public RecyclerViewDataBinding getRecyclerViewDataBinding() {
                                                    return new RecyclerViewDataBinding();
                                                }
                                            }
        );
    }
}
