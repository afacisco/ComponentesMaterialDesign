package com.example.componentesmaterialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

/*
Autor: Juan Francisco Sánchez González
Fecha: 28/01/2023
Clase: Actividad que contiene diferentes componentes de la librería Material Design (ToolBar, NavigatorDrawer,
 FAB, TextInputLayout, SnackBar, ...) con sus respectivos listener para los eventos
*/

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Button validarBtn;
    private TextInputLayout campoCorreo;
    private com.google.android.material.tabs.TabLayout tabs;
    private DrawerLayout drawerLayout;
    private NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componente ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Componente NavigatorDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item_one:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item1_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_two:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item2_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_three:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item3_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_four:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item4_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_five:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item5_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_six:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item6_nav), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_item_seven:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.item7_nav), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                // Close the navigation drawer when an item is selected
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Componente TabLayout
        tabs = (com.google.android.material.tabs.TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText(getResources().getString(R.string.item1_tab)));
        tabs.addTab(tabs.newTab().setText(getResources().getString(R.string.item2_tab)));
        tabs.addTab(tabs.newTab().setText(getResources().getString(R.string.item3_tab)));
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.btn_plus));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String mensaje = getResources().getString(R.string.mens_op) + " " + tab.getPosition();
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Componente Floating Action Button (FAB)
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Componente SnackBar
                Snackbar snack = Snackbar.make(v, getResources().getString(R.string.mens_fab), Snackbar.LENGTH_INDEFINITE);
                snack.setAction(getResources().getString(R.string.aceptar_snack), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snack.dismiss();
                    }
                });
                snack.show();
            }
        });

        // Componente TextInputLayout
        campoCorreo = (TextInputLayout) findViewById(R.id.correoControl);
        validarBtn = (Button) findViewById(R.id.validar_btn);
        validarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos(v);
            }
        });


    }

    // OptionsMenu de la ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // Listener OptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, getResources().getString(R.string.item1_tab), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.barraNuevo:
                Toast.makeText(this, getResources().getString(R.string.item2_tab), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.barraEditar:
                Toast.makeText(this, getResources().getString(R.string.item3_tab), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void validarDatos(View v) {
        String correo = campoCorreo.getEditText().getText().toString();
        boolean c = esCorreoValido(correo);
        if (c) {
            // Componente SnackBar
            Snackbar.make(v, getResources().getString(R.string.guardar_registro), Snackbar.LENGTH_LONG).show();
        }

    }

    private boolean esCorreoValido(String correo) {
        // Patrón para validar el correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            campoCorreo.setError(getResources().getString(R.string.error_correo));
            return false;
        } else {
            campoCorreo.setError(null);
        }
        return true;
    }


}