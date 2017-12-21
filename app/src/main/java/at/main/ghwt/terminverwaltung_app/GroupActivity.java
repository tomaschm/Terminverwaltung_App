package at.main.ghwt.terminverwaltung_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Martin on 15.12.2017.
 */

public class GroupActivity extends Fragment implements NavigationView.OnNavigationItemSelectedListener {


    private PersonalActivity.OnFragmentInteractionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_groupappointments, container, false);

        DrawerLayout constraint = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        //Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), constraint, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        TextView tvHeading = (TextView) getActivity().findViewById(R.id.tvGroupHeading);



        return v;
    }

    public GroupActivity() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NavigationView navigationView = ((MainActivity)getActivity()).getNavigationView();;

        //navigationView.setNavigationItemSelectedListener(this);
        System.out.println("MUST HAVE BEEN INITIALISED HERE");
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
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PersonalActivity.OnFragmentInteractionListener) {
            mListener = (PersonalActivity.OnFragmentInteractionListener) context;
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

    public static GroupActivity newInstance(String param1, String param2) {
        GroupActivity fragment = new GroupActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }







}


