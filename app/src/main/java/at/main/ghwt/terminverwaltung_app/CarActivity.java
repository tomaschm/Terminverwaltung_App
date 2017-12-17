package at.main.ghwt.terminverwaltung_app;

/**
 * Created by Martin on 15.12.2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.NumberPicker;

public class CarActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_cars, container, false);

        ListView lvCars = (ListView) v.findViewById(R.id.lvCars);
        //Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), constraint, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        return v;
    }
}
