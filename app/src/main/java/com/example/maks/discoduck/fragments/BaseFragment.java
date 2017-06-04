package com.example.maks.discoduck.fragments;

import android.support.v4.app.Fragment;

import com.example.maks.discoduck.R;
import com.example.maks.discoduck.activities.MainActivity;

public class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();

        ((MainActivity)getActivity()).setTitle(getScreenName());
    }

    public String getScreenName() {
        return getString(R.string.app_name);
    }

}
