package my.first.makeup_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null){
            actionBar.setTitle("Choose a makeup brand " + "💄");
        }
        Spinner spinner = (Spinner) findViewById(R.id.brand_name_spinnner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brand_names, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.favouites) {
            Intent intent = new Intent(this, Favourites_Activity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.about_us) {
            Intent intent = new Intent(this, About_Us.class);
            startActivity(intent);
        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String brandSelected = String.valueOf(parent.getItemAtPosition(position));
        System.out.println(brandSelected);
        if(position!=0){
            ( (MyApp)getApplication()).setProductType(brandSelected);

            Toast.makeText(this, brandSelected + " selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Product_Types_Activity.class);
           // intent.putExtra("Selected Brand Name",brandSelected);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}