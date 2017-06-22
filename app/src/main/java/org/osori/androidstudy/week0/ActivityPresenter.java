package org.osori.androidstudy.week0;

import java.util.List;

/**
 * Created by junsu on 2017-06-22.
 */

public class ActivityPresenter implements ActivityContract.Presenter {
    private final ActivityContract.View view;

    public ActivityPresenter(ActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void addItem() {
        view.addItem();
    }

    @Override
    public void viewData(Integer data) {
        view.showToast(data);
    }
}
