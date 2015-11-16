package com.example.mk.helios;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.helios.R;


public class project_title extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "mparam1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String title;
    private String notes;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment project_title.
     */
    // TODO: Rename and change types and number of parameters
    public static project_title newInstance(String param1, String param2) {
        project_title fragment = new project_title();

        return fragment;
    }

    public project_title() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = new Bundle();
        mBundle = getArguments();
        title = mBundle.getString("mparam1");
        notes = mBundle.getString("mparam2");
    }


    private Button savebutton;
    private Button notebutton;

    backwarninglistener mbackcallback;
    public interface backwarninglistener{
        void setbackwarning (boolean warning);
    }

    @Override
    public void onResume() {
        mbackcallback.setbackwarning(true);
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_title, container, false);
        TextView titlefield = (TextView) view.findViewById(R.id.titlefield);
        titlefield.setText(title);
        TextView notefield = (TextView) view.findViewById(R.id.notefield);
        notefield.setText(notes);
        savebutton = (Button) view.findViewById(R.id.titlebutton);
        notebutton = (Button) view.findViewById(R.id.notebutton);
        savebutton.setOnClickListener(namehandler);
        notebutton.setOnClickListener(notehandler);

        return view;
    }





    View.OnClickListener namehandler = new View.OnClickListener() {
        public void onClick(View view) {
            TextView titlefield = (TextView) getView().findViewById(R.id.titlefield);
            title = titlefield.getText().toString().replaceAll(" ", "_");




            mTitleCallback.onSaveTitleClicked(title);
            Toast.makeText(getActivity().getApplicationContext(), "Title Set", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener notehandler = new View.OnClickListener() {
        public void onClick(View view) {
            TextView notefield = (TextView) getView().findViewById(R.id.notefield);
            notes = notefield.getText().toString();
            mNoteCallback.onNoteClicked(notes);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mTitleCallback = (SaveTitleListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveTitleListener");
        }

        try {
            mNoteCallback = (SaveNotesListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveNotesListener");
        }
        try {
            mbackcallback = (backwarninglistener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement backwarninglistener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    SaveNotesListener mNoteCallback;
    public interface SaveNotesListener{
        void onNoteClicked(String title);
    }


    SaveTitleListener mTitleCallback;
    public interface SaveTitleListener{
        void onSaveTitleClicked(String title);
    }
}
