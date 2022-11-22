package com.example.imagetovolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class Upload_Photo_Actvity extends AppCompatActivity {
    ImageView img;
    ImageView btncamera;
    Button btnupload;
    Button btnupload_again;
    Bitmap bitmap;
    String encodedimage;
    Context context;
    Button back;
    private static final String apiurl="https://sapna.dev.nojoto.com/api/beta/content.php?cid=7ec99b415af3e88205250e3514ce0fa7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo_actvity);

        img = findViewById(R.id.profile_image);
        btnupload_again = (Button) findViewById(R.id.sbmit_upload_again);
        back = (Button) findViewById(R.id.back);
        context = this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class destinationClass = MainActivity.class;
                Intent intentToStartDetailActivity = new Intent(context, destinationClass);
                startActivity(intentToStartDetailActivity);
                finish();
            }
        });


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 111);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

//        btnupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadtoserver();
//            }
//        });
        btnupload_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class destinationClass = Upload_Photo_Actvity.class;
                Intent intentToStartDetailActivity = new Intent(context, destinationClass);
                startActivity(intentToStartDetailActivity);
                finish();
            }
        });

    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(requestCode==111 && resultCode==RESULT_OK)
            {
                bitmap=(Bitmap)data.getExtras().get("data");
                img.setImageBitmap(bitmap);
                encodebitmap(bitmap);
                uploadtoserver();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        private void encodebitmap(Bitmap bitmap)
        {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

            byte[] byteofimages=byteArrayOutputStream.toByteArray();
            encodedimage=android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
        }

        private void uploadtoserver()
        {


            StringRequest request=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {

                    //img.setImageResource(R.drawable.ic_launcher_background);
                    Toast.makeText(getApplicationContext(),"FileUploaded Successfully",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> map=new HashMap<String, String>();

                    map.put("upload",encodedimage);
                    return map;
                }
            };

            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        }
    }
