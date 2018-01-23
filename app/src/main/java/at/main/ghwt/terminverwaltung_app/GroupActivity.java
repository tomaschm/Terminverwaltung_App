package at.main.ghwt.terminverwaltung_app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import at.main.ghwt.terminverwaltung_app.pkgData.Appointment;
import at.main.ghwt.terminverwaltung_app.pkgData.Database;
import at.main.ghwt.terminverwaltung_app.pkgData.SwipeDismissListViewTouchListener;

/**
 * Created by Martin on 15.12.2017.
 */

public class GroupActivity extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    Database db = new Database();
    private ListView lvMembers;
    private ListView lvGroupAppointments;
    private PersonalActivity.OnFragmentInteractionListener mListener;
    private ArrayAdapter<Appointment> arrayAdapter;
    private Button btnAdd;
    private Button btnUpdate;
    private int selectedItem;
    private EditText Beschreibung;
    private EditText Ort;
    private EditText Thema;
    private EditText Datum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_groupappointments, container, false);
        lvMembers = (ListView) v.findViewById(R.id.lVGroupMember);
        lvGroupAppointments = (ListView) v.findViewById(R.id.lVgroupAppointments);
        DrawerLayout constraint = (DrawerLayout) v.findViewById(R.id.drawer_layout);
       TextView tvHeading = (TextView) getActivity().findViewById(R.id.tvGroupHeading);
        handleMembers();
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnUpdate = (Button) v.findViewById(R.id.btnUpdate);
        btnAdd.setOnClickListener( new View.OnClickListener()
        {
            public void onClick (View v){
                arrayAdapter.add(new Appointment(new java.sql.Date(Calendar.getInstance().getTime().getTime()),"Villach","Reparatur","beschädigtes Teil reparieren"));
                arrayAdapter.notifyDataSetChanged();
                //db.addAppointment(new Appointment(new java.sql.Date(Calendar.getInstance().getTime().getTime()),"Villach","Reparatur","beschädigtes Teil reparieren"));
            }
        });

        Beschreibung = (EditText) v.findViewById(R.id.eTBeschreibung);

        Thema = (EditText) v.findViewById(R.id.eTThema);

        Ort = (EditText) v.findViewById(R.id.eTOrt);

        Datum = (EditText) v.findViewById(R.id.eTDate);


        return v;
    }

    private void handleMembers() {
        //if is groupadmin

        /*lvGroupAppointments.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectedItem = position;
                System.out.println(position);
            }
        });*/


        lvGroupAppointments.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {

            System.out.println("long clicked pose: " + pos);

                AlertDialog.Builder makeDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater li = getActivity().getLayoutInflater();
                View viewAdd = li.inflate(R.layout.dialog_delete, null);
                makeDialog.setView(viewAdd);

                //editexts,textviews and that stuff goes here

                makeDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        arrayAdapter.remove(arrayAdapter.getItem(pos));
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                makeDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog ad = makeDialog.create();
                ad.show();

                return true;
            }
        });

       /* btnUpdate.setOnClickListener( new View.OnClickListener()
        {
            public void onClick (View v){
                AlertDialog.Builder makeDialog = n


                new AlertDialog.Builder(getActivity());
                LayoutInflater li = getActivity().getLayoutInflater();
                View viewAdd = li.inflate(R.layout.dialog_update, null);
                makeDialog.setView(viewAdd);

                //editexts,textviews and that stuff goes here

                makeDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //update the selected appointment
                        Appointment app = (Appointment)lvGroupAppointments.getItemAtPosition(selectedItem);

                        app.setBeschreibung(Beschreibung.getText().toString());
                        app.setOrt(Ort.getText().toString());
                        app.setThema(Thema.getText().toString());

                        java.util.Date utilDate = null;
                        try {
                            utilDate = new SimpleDateFormat("dd MMM yyyy").parse(Thema.getText().toString());
                            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                            app.setDatum(sqlDate);

                            arrayAdapter.notifyDataSetChanged();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }



                    }
                });

                makeDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

                AlertDialog ad = makeDialog.create();
                ad.show();
            }
        });*/

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        lvGroupAppointments,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    arrayAdapter.remove(arrayAdapter.getItem(position));
                                    arrayAdapter.notifyDataSetChanged();

                                }


                            }
                        });
        lvGroupAppointments.setOnTouchListener(touchListener);
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
        ArrayList<Appointment> groupappointments = db.getAppointments();

         arrayAdapter =
                new ArrayAdapter<Appointment>(getActivity(),android.R.layout.simple_list_item_1, groupappointments);
        // Set The Adapter
        lvGroupAppointments.setAdapter(arrayAdapter);

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


