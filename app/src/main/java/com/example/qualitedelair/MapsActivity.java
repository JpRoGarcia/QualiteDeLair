package com.example.qualitedelair;

import Jama.LUDecomposition;
import Jama.Matrix;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;

import Jama.LUDecomposition;
import Jama.Matrix;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.qualitedelair.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    Button btnBuscar;
    EditText edLon, edLat;
    private GoogleMap mMap;
    private Marker Ubicacion;
    private ActivityMapsBinding binding;
    ArrayList<Estaciones> Estacion = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrearEstaciones();
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        edLat = findViewById(R.id.edLat);
        edLon = findViewById(R.id.edLon);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double LatNuevo = Double.parseDouble(edLat.getText().toString());
                double LonNuevo = Double.parseDouble(edLon.getText().toString());
                float concentracion = EcontrarValor(LatNuevo, LonNuevo);

                LatLng NuevoB = new LatLng(LatNuevo, LonNuevo);
                Marker marker = mMap.addMarker(new MarkerOptions().position(NuevoB).title("Nuevo").snippet(
                        "Concentracion: " + concentracion));
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(concentracion));

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for(int i = 0; i< Estacion.size(); i++){
            LatLng xd = new LatLng(Estacion.get(i).getLatitud(), Estacion.get(i).getLongitud());
            mMap.addMarker(new MarkerOptions().position(xd).title(Estacion.get(i).getNombre()).snippet(
                            "Concentracion: " + Estacion.get(i).getConcentracion()).icon(BitmapDescriptorFactory.defaultMarker(Color(Estacion.get(i).getConcentracion()))));
        }

        LatLng alv = new LatLng(6.217, -75.567);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alv, 12));

        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);



    }

    public void CrearEstaciones(){
        Estacion.add(new Estaciones("28", "Casa de Justicia Itagüí", "Medellin", 6.1856666, -75.59720609999999, 15));
        Estacion.add(new Estaciones("38", "I.E. Concejo Municipal de Itagüí", "Itagüí", 6.1684971, -75.6443558, 13));
        Estacion.add(new Estaciones("44", "Tanques La Ye EPM", "Poblado", 6.3732505, -75.4483109, 11));
        Estacion.add(new Estaciones("69", "E U Joaquín Aristizabal","Caldas", 6.0930777, -75.637764, 8));
        Estacion.add(new Estaciones("78", "Hospital", "La Estrella", 6.1555305, -75.6441727, 15));
        Estacion.add(new Estaciones("79", "I.E. Pedro Octavio Amado", "Medellin", 6.2218938, -75.61060329999999, 16));
        Estacion.add(new Estaciones("80", "Planta de producción de agua potable EPM", "Medellin", 6.2589092, -75.5482635, 12));
        Estacion.add(new Estaciones("81", "Torre Social", "Barbosa", 6.4369602, -75.3303986, 7));
        Estacion.add(new Estaciones("82", "Ciudadela Educativa La Vida", "Copacabana", 6.3453598, -75.5047531, 10));
        Estacion.add(new Estaciones("83", "I.E Pedro Justo Berrio", "Medellin", 6.2372341, -75.610466, 19));
        Estacion.add(new Estaciones("84", "I.E INEM sede Santa Catalina", "Medellin", 6.1998701, -75.56095120000001, 10));
        Estacion.add(new Estaciones("85", "Parque Biblioteca Fernando Botero", "Medellin", 6.2778502, -75.6364288, 10));
        Estacion.add(new Estaciones("86", "I.E Ciro Mendia", "Medellin", 6.2904806, -75.5555191, 15));
        Estacion.add(new Estaciones("87", "I.E. Fernando Vélez", "Bello", 6.3375502, -75.56780240000001, 11));
        Estacion.add(new Estaciones("88", "E.S.E. Santa Gertrudis", "Envigado", 6.1686831, -75.5819702, 9));
        Estacion.add(new Estaciones("90", "I.E. Rafael J. Mejía", "Sabaneta", 6.1455002, -75.6212616, 10));
        Estacion.add(new Estaciones("94", "Santa Elena", "Medellín", 6.236361, -75.4984741, 8));
        Estacion.add(new Estaciones("101", "Tanques EPM",  "Girardota", 6.3732505, -75.4483109, 7));
        Estacion.add(new Estaciones("103", "Estación Parque Biblioteca Tomás Carrasquilla",  "Medellín", 6.2849998, -75.5830536, 14));
    }

    public float Color(int Concentracion){
        float hue = 0;
        if(Concentracion <= 12){
            hue = BitmapDescriptorFactory.HUE_GREEN;
        } else if(Concentracion > 12 && Concentracion <= 35.4){
            hue = BitmapDescriptorFactory.HUE_YELLOW;
        } else if(Concentracion > 35.4 && Concentracion <= 55.4) {
            hue = BitmapDescriptorFactory.HUE_ORANGE;
        } else if(Concentracion > 55.4 && Concentracion <= 150.4) {
            hue = BitmapDescriptorFactory.HUE_RED;
        } else if(Concentracion > 150.4 && Concentracion <= 250.4) {
            hue = BitmapDescriptorFactory.HUE_MAGENTA;
        } else if(Concentracion > 250.4) {
            hue = BitmapDescriptorFactory.HUE_CYAN;
        }
        return hue;
    }

    public float EcontrarValor(double Lat, double Lot){

        //double [][] values = {{1, 1, 2}, {2, 4, -3}, {3, 6, -5}};  // each array is a row in the matrix
        //double [] rhs = { 9, 1, 0 }; // rhs vector
        //double [] answer = { 1, 2, 3 }; // this is the answer that you should get.

        int tamaño = Estacion.size();
        float Solucion = 0;

        double [][] A = new double[tamaño][tamaño];
        double [][] inversaA = matrizInversa(A);
        double [] Peso = new double[tamaño];


        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                A[i][j] = Math.sqrt(1 + Math.pow(DistanciaPuntos(Estacion.get(i), Estacion.get(j)), 2));
            }
        }

        for (int i = 0; i < tamaño; i++) {
            Peso[i] = Estacion.get(i).getConcentracion();
        }

        double [] matrixLanda = EncontrarLanda(inversaA, Peso);

        for (int i = 0; i < tamaño; i++){
            Solucion += matrixLanda[i] * Math.sqrt(1 + Math.pow(DistanciaPuntosNuevos(Estacion.get(i), Lat, Lot), 2));
        }

        //Matrix a = new Matrix(values);
        //Matrix i = a.inverse();
        //i.print(10, 2);
        //Matrix b = new Matrix(rhs, rhs.length);
        //Matrix x = i.times(b);
        //x.print(10, 2);

        return Solucion;
    }

    public double DistanciaPuntosNuevos(Estaciones objUno, double Lat, double Lon){
        double Lat1 = Math.toRadians(objUno.getLatitud());
        double Lon1 = Math.toRadians(objUno.getLongitud());
        double Lat2 = Math.toRadians(Lon);
        double Lon2 = Math.toRadians(Lat);

        double distancia = Math.sqrt(Math.abs(Math.pow((Lat2 - Lat1),2) + Math.pow((Lon2 - Lon1),2)));

        return distancia;
    }

    public double DistanciaPuntos(Estaciones objUno, Estaciones objDos){
        double Lat1 = Math.toRadians(objUno.getLatitud());
        double Lon1 = Math.toRadians(objUno.getLongitud());
        double Lat2 = Math.toRadians(objDos.getLatitud());
        double Lon2 = Math.toRadians(objDos.getLongitud());

        double distancia = Math.sqrt(Math.abs(Math.pow((Lat2 - Lat1),2) + Math.pow((Lon2 - Lon1),2)));

        return distancia;
    }

    public double[] EncontrarLanda(double[][] matrixInversa, double[] MatrixPeso){
        int Suma  = 0;
        int tamaño = matrixInversa.length;
        double [] Landa = new double[tamaño];
        for (int i = 0; i < tamaño; i++) {
            Suma = 0;
            for (int j = 0; j < tamaño; j++) {
                Suma += matrixInversa[i][j] * MatrixPeso[j];
            }
            Landa[i] = Suma;
        }

        for (int i = 0; i < tamaño; i++) {
            System.out.println("x:["+ i +"] " + Landa[i]);
        }

        return Landa;
    }

    public double[][] matrizInversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=matrizAdjunta(matriz);
        multiplicarMatriz(det,nmatriz);
        return nmatriz;
    }

    public void multiplicarMatriz(double n, double[][] matriz) {
        for(int i=0;i<matriz.length;i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] *= n;
            }
        }
    }

    public double[][] matrizAdjunta(double[][] matriz){
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    public double[][] matrizCofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j) {
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }

    public double[][] matrizTranspuesta(double [][] matriz){
        double[][]nuevam=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++) {
                nuevam[i][j] = matriz[j][i];
            }
        }
        return nuevam;
    }

    public double determinante(double[][] matriz){
        double det;
        if(matriz.length==2)
        {
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
            double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                        nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Double LatNuevo = Double.parseDouble(String.format(Locale.getDefault(),
                "%f", latLng.latitude));
        Double LonNuevo = Double.parseDouble(String.format(Locale.getDefault(),
                "%f", latLng.longitude));

        float concentracion = EcontrarValor(LatNuevo, LonNuevo);

        LatLng Nuevo = new LatLng(LatNuevo, LonNuevo);
        Marker marker = mMap.addMarker(new MarkerOptions().position(Nuevo).title("Nuevo").snippet(
                "Concentracion: " + concentracion));
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(concentracion));
    }

}