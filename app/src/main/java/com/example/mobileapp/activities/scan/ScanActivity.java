package com.example.mobileapp.activities.scan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.mobileapp.activities.firebase.LoginActivity;
import com.example.mobileapp.activities.main.NewsletterActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.activities.basket.BasketActivity;
import com.example.mobileapp.activities.profile.ProfileActivity;
import com.example.mobileapp.activities.search.SearchActivity;
import com.example.mobileapp.activities.wishlist.WishlistsActivity;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.viewmodels.AllProductsViewModel;
import com.example.mobileapp.viewmodels.BasketViewModel;
import com.example.mobileapp.viewmodels.ProductViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.Result;

import java.util.List;

public class ScanActivity extends AppCompatActivity {


    Toolbar toolbar;
    private CodeScanner codeScanner;
    BottomNavigationView bottomNavigationView;

    private BasketViewModel basketViewModel;

    private ProductViewModel productViewModel;
    private ProductModel product;

    private long bar;

   /* private long id;
    private long barcode;
    private String name;
    private String category;
    private double price;
    private String brand;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        //toolbar = findViewById(R.id.toolbarScanner);

        /*allProductsViewModel = ViewModelProviders.of(this).get(AllProductsViewModel.class);
        allProductsViewModel.getProducts().observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModels) {
                if(productModels != null){
                    product = productModels;
                    searchAdapter.setAllProductsList(productModels);
                }

            }
        });*/


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.basket);
        //perform ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), NewsletterActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), BasketActivity.class));
                        overridePendingTransition(0,0);
                        return true;




                }
                return false;
            }
        });

        CodeScannerView codeScannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this, codeScannerView);

        codeScannerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                codeScanner.startPreview();

            }
        });

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        bar = Long.parseLong(result.getText());

                        addToBasketDialog();
                        getProductByBarcode();


                        //Toast.makeText(ScanActivity.this, ""+ barcode  , Toast.LENGTH_LONG).show();
                        Log.v("Tag", "barcode" + result.getText());
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    public void addToBasketDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("");
        alert.setMessage("Do you want to add this item to basket?");

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //takeScannedProductData();


                Intent data = new Intent(getApplicationContext(), BasketActivity.class);
                data.putExtra("scannedPId", product.getId());
                data.putExtra("scannedPName",product.getName());
                data.putExtra("scannedPBarcode",product.getBarcode());
                data.putExtra("scannedPCategory",product.getCategory());
                data.putExtra("scannedPPrice",product.getPrice());
                data.putExtra("scannedPBrand",product.getBrand());
                Log.v("Tag", "scanned: " + product.getName());
                startActivity(data);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create().show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getProductByBarcode(){

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductById().observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {
                if (productModel != null) {
                    product = productModel;


                }
            }
        });

        productViewModel.getProductByBarcode(bar);
    }

    public void takeScannedProductData(){
        /*long id = product.getId();
        String name = product.getName();
        long barcode = product.getBarcode();
        String category = product.getCategory();
        double price = product.getPrice();
        String brand = product.getBrand();*/
       // Log.v("Tag", "taken" + brand);

/*

        Intent data = new Intent(this, BasketActivity.class);
        data.putExtra("scannedPId", product.getId());
        data.putExtra("scannedPName",product.getName());
        data.putExtra("scannedPBarcode",product.getBarcode());
        data.putExtra("scannedPCategory",product.getCategory());
        data.putExtra("scannedPPrice",product.getPrice());
        data.putExtra("scannedPBrand",product.getBrand());
        startActivity(data);
*/

        Log.v("tag", "product name " + product.getName());

    }
}
