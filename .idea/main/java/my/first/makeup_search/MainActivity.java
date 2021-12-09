package my.first.makeup_search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String brandSelected = String.valueOf(parent.getItemAtPosition(position));
        System.out.println(brandSelected);
        Toast.makeText(this, brandSelected + " selected", Toast.LENGTH_SHORT).show();
        if(position!=0){
            Intent intent = new Intent(this,Product_List_Activity.class);
            intent.putExtra("Selected Brand Name",brandSelected);
            startActivity(intent);
        }

        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}