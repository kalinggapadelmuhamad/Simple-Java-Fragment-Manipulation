package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean isFragmentBInContainer2 = true;

    public FragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);


        Button showFragmentButton = view.findViewById(R.id.show_fragment);
        Button switchFragmentButton = view.findViewById(R.id.switch_fragment);
        Button closeFragmentButton = view.findViewById(R.id.close_fragment);

        showFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan Fragment B di kontainer pertama
                FragmentB fragmentB = new FragmentB();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container2, fragmentB);
                transaction.commit();

                // Menampilkan Fragment C di kontainer kedua
                FragmentC fragmentC = new FragmentC();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container3, fragmentC);
                transaction.commit();
            }
        });

        switchFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                if (isFragmentBInContainer2) {
                    // Pindahkan Fragment B ke Container 3
                    FragmentB fragmentB = new FragmentB();
                    transaction.replace(R.id.container3, fragmentB);

                    // Pindahkan Fragment C ke Container 2
                    FragmentC fragmentC = new FragmentC();
                    transaction.replace(R.id.container2, fragmentC);

                    isFragmentBInContainer2 = false; // Sekarang Fragment C ada di Container 2
                } else {
                    // Pindahkan Fragment B ke Container 2
                    FragmentB fragmentB = new FragmentB();
                    transaction.replace(R.id.container2, fragmentB);

                    // Pindahkan Fragment C ke Container 3
                    FragmentC fragmentC = new FragmentC();
                    transaction.replace(R.id.container3, fragmentC);

                    isFragmentBInContainer2 = true; // Sekarang Fragment B ada di Container 2
                }

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        closeFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Gantikan konten di container2 dengan fragmen kosong
                transaction.replace(R.id.container2, new Fragment());

                // Gantikan konten di container3 dengan fragmen kosong
                transaction.replace(R.id.container3, new Fragment());

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }
}