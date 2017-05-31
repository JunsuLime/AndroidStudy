package org.osori.androidstudy.week2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osori.androidstudy.R;

/**
 * Created by junsu on 2017-05-20.
 *
 * Second Fragment, Third Fragment 모두 동일한 구조이다.
 */

public class FirstFragment extends Fragment {

    public static FirstFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Fragment 의 초기화는 onCreateView 에서 해준다.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 아래와 같이 inflater 를 사용해 하면 된다. 이렇게 하면 해당 fragment 에 inflate 한 layout 이 뜨게된다.
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // 다른 view 조작이 하고 싶다면 TODO
        // 가 써진 부분에 코드를 작성하면 된다.

        // TODO: -

        return view;
    }
}
