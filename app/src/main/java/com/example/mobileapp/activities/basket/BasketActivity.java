package com.example.mobileapp.activities.basket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.activities.basket.adapter.BasketAdapter;
import com.example.mobileapp.activities.firebase.LoginActivity;
import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.scan.ScanActivity;
import com.example.mobileapp.activities.search.SearchActivity;

import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.activities.wishlist.adapter.WishlistProductsAdapter;
import com.example.mobileapp.connection.ServiceGenerator;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.connection.apis.SoldProductsApi;
import com.example.mobileapp.fragments.store.StoreFragment;
import com.example.mobileapp.fragments.wishlist.WishlistFragment;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.models.SoldProductsModel;
import com.example.mobileapp.repositories.SoldProductsRepository;
import com.example.mobileapp.viewmodels.BasketViewModel;
import com.example.mobileapp.viewmodels.ProductViewModel;
import com.example.mobileapp.viewmodels.SoldProductsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.LongBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketActivity extends AppCompatActivity {

    private Button scanButton;
    BottomNavigationView bottomNavigationView;

    private Button buyButton;

    private Button storeButton;
    private TextView storeTextView;
    private FloatingActionButton floatingActionButton;

    private BasketViewModel basketViewModel;

    public String name;

    private RecyclerView recyclerView;
    private BasketAdapter adapter;

    private List<ProductModel> productList;

    private TextView textView, price;

    private ImageView clearBasketButton;

    private long id;
    private ArrayList<Long> ids;

    private  int quantity = 1;

    private SoldProductsViewModel soldProductsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        textView = findViewById(R.id.basketItem);
        buyButton = findViewById(R.id.buyButton);
        price = findViewById(R.id.price);
        storeButton = findViewById(R.id.searchStore);
        storeTextView = findViewById(R.id.basketStoreName);
        clearBasketButton = findViewById(R.id.clearBasket);

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreFragment storeFragment = new StoreFragment();
                storeFragment.show(getSupportFragmentManager(), "store fragment");
            }
        });


        Intent intent = getIntent();
        String storeName = intent.getStringExtra("storeBasketName");
        storeTextView.setText(storeName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String currentDateandTime = sdf.format(new Date());

        Log.v("Tag", "current time" + currentDateandTime);

        recyclerView = findViewById(R.id.basketsth);
        floatingActionButton = findViewById(R.id.scanFloatingButton);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(productList, this);
        recyclerView.setAdapter(adapter);




        basketViewModel = ViewModelProviders.of(this).get(BasketViewModel.class);
        basketViewModel.getAllProducts().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                if (productModels != null) {
                    productList = productModels;
                    adapter.setProductList(productList);



                    Log.v("Tag", "onChanged");
                    Log.v("Tag", "product list" + productList.toString());
                }
            }
        });

        insertRegisteredProduct();

        Log.v("Tag", "save to sqlite product " + name);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(intent);
                /*getData();*/
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storeName == null) {
                    storeAlert(v);
                } else {
                    getRegisteredProductData();
                    soldProductsViewModel.postSoldProducts(storeName, id, quantity);
                    basketViewModel.deleteAllProducts();
                }
            }



                                         });
        clearBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // soldProductsRepository.postSoldProducts("Bilka",1, 1);
                //soldProductsRepository.postSoldProducts("Rema 1000", 1, 1);
                //basketViewModel.deleteAllProducts();
            }
        });




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                basketViewModel.deleteProduct(adapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(BasketActivity.this, "Product deleted from basket", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //initialize and assign variable
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.basket);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), NewsletterActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.basket:

                        return true;
                }
                return false;
            }
        });

    }


    public void insertRegisteredProduct(){

        Intent intent = getIntent();
        long id = intent.getLongExtra("scannedPId",0);
        long barcode = intent.getLongExtra("scannedPBarcode",0);
        String name = intent.getStringExtra("scannedPName");
        String category = intent.getStringExtra("scannedPCategory");
        double price = intent.getDoubleExtra("scannedPPrice",0);
        String brand = intent.getStringExtra("scannedPBrand");

        if(name == null){
            Log.v("Tag", "Name equals zerooooooooooooo");
        }else{
            ProductModel productModel = new ProductModel(id, barcode, name, category, price, brand);
            basketViewModel.insertProduct(productModel);
        }

    }



    public void storeAlert(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Store");
        alert.setMessage("Please choose store to purchase");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.create().show();
    }

    public void getRegisteredProductData() {
        SQLiteDatabase simpleDB = getApplicationContext().openOrCreateDatabase("basket_database", Context.MODE_PRIVATE, null);
        Cursor cursor = simpleDB.rawQuery("select * from product_table", null);
        if (cursor.getCount() >= 1) {
            return;
        }
            while(cursor.moveToNext()) {
                id = cursor.getLong(1);
                ids = new ArrayList<Long>();
                ids.add(id);

                Log.v("Tag", "list ids " + id);

            }
        }
    }




