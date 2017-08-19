package rory.lim.com.pickme;

        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    ImageView imageView, imagepickMe;
    Button share;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imagepickMe = (ImageView) findViewById(R.id.pickMe);
        share = (Button) findViewById(R.id.shareBtn);
    }


    public void btnPhoto(View view) {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
            imageView.setImageURI(uri);
            share.setVisibility(View.VISIBLE);

        }
    }
    public void btnShare(View view) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/*");
        //set photo
        i.setData(uri);
        i.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(i, getString(R.string.app_name)));
    }
}
