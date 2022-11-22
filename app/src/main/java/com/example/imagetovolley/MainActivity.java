package com.example.imagetovolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.security.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    ImageView btncamera;

    Context context;
    RecyclerView recyclerview;
    LinearLayoutManager linearlayoutmanager;
    List<ModelClass> userList;
    Adapter adapter;

    private static final String apiurl = "https://sapna.dev.nojoto.com/api/beta/content.php?cid=7ec99b415af3e88205250e3514ce0fa7";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this;


        btncamera = (ImageView) findViewById(R.id.sbmit_camera);



        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class destinationClass = Upload_Photo_Actvity.class;
                Intent intentToStartDetailActivity = new Intent(context, destinationClass);
                startActivity(intentToStartDetailActivity);
                finish();

            }
        });
    init();
    initRecyclerview();

    }

    private void initRecyclerview() {
        recyclerview=findViewById(R.id.recyclerview);
        linearlayoutmanager = new LinearLayoutManager(this);
        linearlayoutmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerview.setLayoutManager(linearlayoutmanager);
        adapter = new Adapter(userList);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void init() {
        userList  = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
        userList.add(new ModelClass(R.drawable.dp, R.drawable.image));
    }
}

