package at.main.ghwt.terminverwaltung_app;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * Created by Martin on 15.12.2017.
 */

public class PersonalActivity extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NavigationView navigationView = ((MainActivity)getActivity()).getNavigationView();

        //navigationView.setNavigationItemSelectedListener(this);
        System.out.println("MUST HAVE BEEN INITIALISED HERE");
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static PersonalActivity newInstance(String param1, String param2) {
        PersonalActivity fragment = new PersonalActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_group) {
            System.out.println("groupactivity2");
            GroupActivity fragment = new GroupActivity();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, fragment,"findThisFragment")
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_cars) {
            System.out.println("caractivity2");
            CarActivity fragment = new CarActivity();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, fragment,"findThisFragment")
                    .addToBackStack(null)
                    .commit();

        }
        else if (id == R.id.nav_personal) {
            System.out.println("personalactivity2");
            PersonalActivity fragment = new PersonalActivity();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, fragment,"findThisFragment")
                    .addToBackStack(null)
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
