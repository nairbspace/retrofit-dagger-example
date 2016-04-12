package com.nairbspace.retrofitdaggerexample.retrofit;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class ExampleInterceptor implements Interceptor {
    private static ExampleInterceptor sInterceptor;
    private String mScheme;
    private String mHost;

    @Inject
    public static ExampleInterceptor get() {
        if (sInterceptor == null) {
            sInterceptor = new ExampleInterceptor();
        }
        return sInterceptor;
    }

    private ExampleInterceptor() {
        // Intentionally blank
    }

    public void setInterceptor(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        mScheme = httpUrl.scheme();
        mHost = httpUrl.host();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (mScheme != null && mHost != null) {
            HttpUrl newUrl = original.url().newBuilder()
                    .scheme(mScheme)
                    .host(mHost)
                    .build();
            original = original.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(original);
    }
}
