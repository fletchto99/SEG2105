package fletchto99.com.footballpal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAvatarClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onMapsClick(View view) {
        EditText teamAddress = (EditText) findViewById(R.id.teamAddress);
        Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=" + teamAddress.getText());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        ImageView avatarImage = (ImageView) findViewById(R.id.teamAvatar);
        String drawableName;
        switch (data.getIntExtra("imageID", R.id.ava6)) {
            case R.id.ava6:
                drawableName = "ic_logo_00";
                break;
            case R.id.ava1:
                drawableName = "ic_logo_01";
                break;
            case R.id.ava2:
                drawableName = "ic_logo_02";
                break;
            case R.id.ava3:
                drawableName = "ic_logo_03";
                break;
            case R.id.ava4:
                drawableName = "ic_logo_04";
                break;
            case R.id.ava5:
                drawableName = "ic_logo_05";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        avatarImage.setImageResource(resID);
    }
}
