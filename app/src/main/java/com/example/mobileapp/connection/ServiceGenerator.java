package com.example.mobileapp.connection;

import com.example.mobileapp.connection.apis.NewsletterApi;
import com.example.mobileapp.connection.apis.ProductApi;
import com.example.mobileapp.connection.apis.ReviewApi;
import com.example.mobileapp.connection.apis.SoldProductsApi;
import com.example.mobileapp.connection.apis.StoreApi;
import com.example.mobileapp.connection.apis.WishlistApi;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //comment
    private static OkHttpClient client = new OkHttpClient.Builder().build();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://172.20.10.2:5001/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient()
                    .build());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static WishlistApi wishlistApi = retrofit.create(WishlistApi.class);

    private static ProductApi productApi = retrofit.create(ProductApi.class);

    private static NewsletterApi newsletterApi = retrofit.create(NewsletterApi.class);

    private static StoreApi storeApi = retrofit.create(StoreApi.class);

    private static ReviewApi reviewApi = retrofit.create(ReviewApi.class);

    private static SoldProductsApi soldProductsApi = retrofit.create(SoldProductsApi.class);

    public static WishlistApi getWishListApi() { return wishlistApi; }

    public static ProductApi getProductApi() { return productApi; }

    public static NewsletterApi getNewsletterApi() { return newsletterApi; }

    public static StoreApi getStoreApi() { return storeApi; }

    public static ReviewApi getReviewApi(){ return  reviewApi; }

    public static SoldProductsApi getSoldProductsApi(){ return  soldProductsApi; }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
