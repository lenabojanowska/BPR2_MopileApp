package com.example.mobileapp.activities.products;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.activities.products.adapter.ProductsAdapter;
import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.viewmodels.ProductViewModel;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    private List<ProductModel> productList;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        /*productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        recyclerView = (RecyclerView) findViewById(R.id.productsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductsAdapter(productList, this);
        recyclerView.setAdapter(adapter);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProductListByCat().observe(this, new Observer<List<ProductModel>>() {
            @Override
            public void onChanged(List<ProductModel> productModels) {
                if(productModels != null){
                    productList = productModels;
                    adapter.setProductList(productModels);
                }
            }
        });*/
      /*  if(getIntent().getExtras() != null){
            long id = getIntent().getExtras().getLong(String.valueOf(2));
            productViewModel.GetProductListByCategory(id);
        }*/

    }

}