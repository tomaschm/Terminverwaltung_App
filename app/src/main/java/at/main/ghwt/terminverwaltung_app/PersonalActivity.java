package at.main.ghwt.terminverwaltung_app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * Created by Martin on 15.12.2017.
 */

public class PersonalActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_personalappointments, container, false);

        DrawerLayout constraint = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        //Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), constraint, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        TextView tvHeading = (TextView) getActivity().findViewById(R.id.tvGroupHeading);

        return v;
    }
}
