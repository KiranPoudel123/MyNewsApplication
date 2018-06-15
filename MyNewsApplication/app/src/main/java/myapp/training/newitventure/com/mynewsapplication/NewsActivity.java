package myapp.training.newitventure.com.mynewsapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class NewsActivity extends WebViewDialogActivity{
    private Dialog webViewDialog;
    TextView textTitle;
    TextView textPublisheddate;
    TextView textDescription;
    ImageView image;
    Button News_Site;
    Button btn_Share;
    Button btnBack;

    public CallbackManager callbackManager;
    public ShareDialog shareDialog;

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();

            if(ShareDialog.canShow(SharePhotoContent.class)){
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(content);
                ShareDialog.show(NewsActivity.this, content);
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        getIncomingIntent();

        textTitle = (TextView) findViewById(R.id.textTitle);
        textPublisheddate = (TextView) findViewById(R.id.textPublisheddate);
        textDescription = (TextView) findViewById(R.id.textDescription);
        News_Site = (Button) findViewById(R.id.News_Site);
        btnBack = (Button) findViewById(R.id.btnBack);
        image = (ImageView) findViewById(R.id.image);
        btn_Share = (Button) findViewById(R.id.btn_Share);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        btn_Share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                            @Override
                            public void onSuccess(Sharer.Result result) {
                                Toast.makeText(NewsActivity.this, "Share successfull! ", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(NewsActivity.this, "Share cancel! ", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(FacebookException error) {
                                Toast.makeText(NewsActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                        Picasso.with(getBaseContext())
                             .load("http://www.nepaljapan.com/wp-content/uploads/2018/06/rishi.jpg")
                                .into(target);
                        }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

                Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


        News_Site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsActivity.this, WebViewDialogActivity.class);
                startActivity(intent);
            }
        });
}

private void getIncomingIntent(){

        if(getIntent().hasExtra("title")&&getIntent().hasExtra("image")&&getIntent().hasExtra("description")&&getIntent().hasExtra("category")&&getIntent().hasExtra("publisheddate")){

            String title = getIntent().getStringExtra("title");
            String image = getIntent().getStringExtra("image");
            String description = getIntent().getStringExtra("description");
            String category = getIntent().getStringExtra("category");
            String published_date = getIntent().getStringExtra("publisheddate");

            setNews(title,image,description,category,published_date);
        }
}

private void setNews( String title, String image,String description,String category, String published_date){
         TextView txt_title = findViewById(R.id.textTitle);
         txt_title.setText(title);

    TextView txt_description = findViewById(R.id.textDescription);
    txt_description.setText(description);

    TextView txt_category = findViewById(R.id.textCategory);
    txt_category.setText(category);

    TextView txt_publisheddate = findViewById(R.id.textPublisheddate);
    txt_publisheddate.setText(published_date);

    ImageView image_view= findViewById(R.id.image);

   Picasso.with(this).load(image).into(image_view);
}

}
