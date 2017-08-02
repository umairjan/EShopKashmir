package com.example.eshopkashmir.eshopkashmir;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class LauncherActivity extends BaseActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] SLIDER = {R.drawable.slider_coming_soon, R.drawable.banner2};
    private ArrayList<Integer> sliderArrayList = new ArrayList<Integer>();
    private final String[] category_names = {
            "Grocery And Staples","Regular Food Items", "Bakery And Confectionery", "Non Veg", "Dry Fruits",
            "Baby Care And Toys", "Personal Care", "Cleaning Supplies", "Books And Stationary", "Medicines",
            "Electronics", "Special Category"
    };

    private final int[] image_url = {
            R.drawable.ic_groceries, R.drawable.ic_food, R.drawable.ic_bakery, R.drawable.ic_meat,
            R.drawable.ic_dry_fruits, R.drawable.ic_baby, R.drawable.ic_daily_supplies, R.drawable.ic_personal_care,
            R.drawable.ic_notebook, R.drawable.ic_medicine, R.drawable.ic_electronics, R.drawable.ic_specials
    };
    private NestedScrollView scrollView;
    String url;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.e_shop_kashmir);
        logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCategory(UrlValues.Home);

            }
        });
        scrollView = (NestedScrollView) findViewById(R.id.categoryScroll);
        scrollView.setSmoothScrollingEnabled(true);
        initSlider();
        initCategories();
    }


    public void initSlider(){
        for(int i=0; i<SLIDER.length; i++)
            sliderArrayList.add(SLIDER[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ImageAdapter(LauncherActivity.this,sliderArrayList));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final android.os.Handler handler = new android.os.Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == SLIDER.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }

    public void initCategories(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        final ArrayList<Category> categoryName = prepareData();
        CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),categoryName);
        recyclerView.setAdapter(adapter);
//        int alpha = (int)(.50f * 255.0f);
//        recyclerView.setBackgroundColor(Color.argb(alpha,244,162,52));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(LauncherActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        switch (position){
                            case 0:
                                url = UrlValues.GROCERY_AND_STAPLES;
                                break;
                            case 1:
                                url = UrlValues.REGULAR_FOOD_ITEMS;
                                break;
                            case 2:
                                url = UrlValues.BAKERY_AND_CONFECTIONARY;
                                break;
                            case 3:
                                url = UrlValues.NON_VEG;
                                break;
                            case 4:
                                url = UrlValues.DRY_FRUITS;
                                break;
                            case 5:
                                url = UrlValues.BABY_CARE_AND_TOYS;
                                break;
                            case 6:
                                url = UrlValues.PERSONAL_CARE;
                                break;
                            case 7:
                                url = UrlValues.CLEANING_SUPPLIES;
                                break;
                            case 8:
                                url = UrlValues.BOOKS_AND_STATIONARY;
                                break;
                            case 9:
                                url = UrlValues.MEDICINES;
                                break;
                            case 10:
                                url = UrlValues.ELECTRONICS;
                                break;
                            case 11:
                                url = UrlValues.SPECIAL_CATEGORY;
                        }

                        displayCategory(url);
                    }
                }));

    }
    private ArrayList<Category> prepareData(){

        ArrayList<Category> categoryItem = new ArrayList<>();
        for(int i=0;i<category_names.length;i++){
            Category category = new Category();
            category.setCategory_name(category_names[i]);
            category.setCategory_image_url(image_url[i]);
            categoryItem.add(category);
        }
        return categoryItem;
    }

    private void displayCategory(String url){
        Intent intent = new Intent(this,OpenUrl.class);
        intent.putExtra("url",url);

        startActivity(intent);
    }

}



