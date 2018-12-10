package th.ac.kmitl.it59070117;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class UploadPhoto extends Fragment {

    ImageView imageView;
    ImageView imageView2;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photoController();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload_photo, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == getActivity().RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageView2.setImageBitmap(imageBitmap);
                }
                break;
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        /*
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getActivity().getContentResolver().openInputStream(uri)
                        );

                        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 800, 600,true);
                        imageView.setImageBitmap(bitmap1);
                        */
                        imageView.setImageURI(uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please Choose Image", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    private void photoController() {
        imageView = getView().findViewById(R.id.imvPhoto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose App"), 1);
            }
        });

        imageView2 = getView().findViewById(R.id.imvPhoto2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }
}
