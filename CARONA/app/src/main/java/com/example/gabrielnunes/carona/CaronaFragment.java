package com.example.gabrielnunes.carona;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.app.DatePickerDialog.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaronaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaronaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaronaFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    final Context context = getActivity();

    Calendar mCurrentDate;
    TextView Data, horario;
    private int day, month, year, horas, minutos;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    Button cadastro_carona;
    String url = "";
    String parametros = "";
    int valor;
    String destino,partida;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CaronaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaronaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaronaFragment newInstance(String param1, String param2) {
        CaronaFragment fragment = new CaronaFragment();
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
        // Inflate the layout for this fragment

        View v = inflater.inflate((R.layout.fragment_carona),container,false);
        String teste = Login.aux;
        valor = Integer.parseInt(teste.trim());

        spinner = (Spinner) v.findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        list.add("IFTM Campus Ituiutaba");
        list.add("UEMG");
        list.add("UFU Campus Pontal");
        list.add("Praça da Prefeitura");
        list.add("Praça da av 26");
        list.add("Ituiutaba Clube");
        list.add("Parque de Exposições");
        list.add("Rodoviária");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = parent.getItemAtPosition(position).toString();
                 partida = spinner.getSelectedItem().toString();
                //Toast.makeText(getActivity(), "Partida: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        List<String> list2 = new ArrayList<>();
        list2.add("IFTM Campus Ituiutaba");
        list2.add("UEMG");
        list2.add("UFU Campus Pontal");
        list2.add("Praça da Prefeitura");
        list2.add("Praça da av 26");
        list2.add("Ituiutaba Clube");
        list2.add("Parque de Exposições");
        list2.add("Rodoviária");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue2 = parent.getItemAtPosition(position).toString();
                 destino = spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner3 = (Spinner) v.findViewById(R.id.spinner3);
        List<String> list3 = new ArrayList<>();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4+");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue3 = parent.getItemAtPosition(position).toString();
                String teste = spinner3.getSelectedItem().toString();
                //Toast.makeText(getActivity(), "id: "+ Login.aux, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Data = (TextView) v.findViewById(R.id.Data);
        horario = (TextView) v.findViewById(R.id.hora);
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Data.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        Data.setText(day+"/"+(month+1)+"/"+year);
        mCurrentDate = Calendar.getInstance();
        horas = mCurrentDate.get(Calendar.HOUR_OF_DAY);
        minutos = mCurrentDate.get(Calendar.MINUTE);
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horario.setText(hourOfDay+":"+minute);
                    }
                },horas, minutos,false);
                timePickerDialog.show();
            }
        });
        horario.setText(horas+":"+minutos);
        cadastro_carona = (Button) v.findViewById(R.id.cadastro_carona);
        cadastro_carona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(partida==destino)
                {
                    Toast.makeText(getActivity(), "Os locais de origem e destino devem ser diferentes", Toast.LENGTH_SHORT).show();
                }else{
                    cadastrar();
                }
            }
        });
        return v;
    }

    public void  cadastrar()
    {

        StringRequest request = new StringRequest(Request.Method.POST, "http://www.kdcarona.16mb.com/carona_controller.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1"))
               {
                 startActivity(new Intent(getActivity(),MenuInferior.class));
                   Toast.makeText(getActivity(), "Carona Cadastrada com Sucesso ", Toast.LENGTH_SHORT).show();
              }else{
                   Toast.makeText(getActivity(), "Essa Carona não pode ser cadastrada ", Toast.LENGTH_SHORT).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("data", Data.getText().toString());
                params.put("hora", horario.getText().toString());
                params.put("origem", spinner.getSelectedItem().toString());
                params.put("destino", spinner2.getSelectedItem().toString());
                params.put("vagas", spinner3.getSelectedItem().toString());
                params.put("id_user", String.valueOf(valor));
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(request);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            System.out.print("add");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View v) {
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
