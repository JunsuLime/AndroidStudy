package org.osori.androidstudy.week0;

/**
 * Created by junsu on 2017-06-22.
 */

public interface ActivityContract {

    interface View {
        public void addItem();
        public void showToast(Integer data);
    }

    interface Presenter {
        public void addItem();
        public void viewData(Integer data);
    }
}
