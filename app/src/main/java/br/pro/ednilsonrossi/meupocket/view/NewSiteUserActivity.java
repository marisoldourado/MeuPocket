package br.pro.ednilsonrossi.meupocket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.pro.ednilsonrossi.meupocket.R;
import br.pro.ednilsonrossi.meupocket.dao.SiteDao;
import br.pro.ednilsonrossi.meupocket.model.Site;

public class NewSiteUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextTitle;
    private EditText editTextURL;
    private Button btnSave;
    private Site site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site_user);

        editTextTitle = findViewById(R.id.edttxt_title);
        editTextURL = findViewById(R.id.edttxt_url);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if( v == btnSave ){
            String title, url;

            title = editTextTitle.getText().toString();
            url   = editTextURL.getText().toString();

            if(title.isEmpty() || url.isEmpty()){
                Toast.makeText(this, getString(R.string.fields_validade), Toast.LENGTH_LONG).show();
                return;
            }

            site = new Site(title, url);
            SiteDao.addSite(this, site);
            finish();
        }

    }
}
