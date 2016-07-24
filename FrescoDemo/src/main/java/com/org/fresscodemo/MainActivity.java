package com.org.fresscodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {
    private String url="http://img2.imgtn.bdimg.com/it/u=736338723,2604200345&fm=21&gp=0.jpg";
    private SimpleDraweeView simpleDraweeView;
    private AbstractDraweeController contriller;
    private Uri uri;
    private ImageRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleDraweeView = ((SimpleDraweeView) findViewById(R.id.fresco_drawee));
        uri = Uri.parse(url);

        request = ImageRequestBuilder
                .newBuilderWithResourceId(R.mipmap.icon_17girl_dance)
                .build();

        contriller = Fresco
                .newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setImageRequest(request)
                .build();

        simpleDraweeView.setController(contriller);

    }
}
