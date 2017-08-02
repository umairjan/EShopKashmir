package com.example.eshopkashmir.eshopkashmir;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Umair on 10-Jun-17.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

        // Associate searchable configuration with the SearchView
        android.widget.SearchView searchView = (android.widget.SearchView) item.getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String url = "http://eshopkashmir.com/catalogsearch/result/?cat=&q=";
                String[] queryArray = query.split(" ");
                for (int i = 0; i < queryArray.length; i++){
                    url += queryArray[i];
                }

                displayCategory(url);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void displayCategory(String url){
        Intent intent = new Intent(this,OpenUrl.class);
        intent.putExtra("url",url);
        startActivity(intent);
        finish();

    }
}
