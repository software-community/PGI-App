package com.mapps.stroleapp.feedback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mapps.stroleapp.FeedbackData;
import com.mapps.stroleapp.R;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class FeedbackActivity extends AppCompatActivity {

    private EditText query , suggestion;
    private Button buttonSubmit , buttonpickvideo;
    private VideoView video ;
    private FirebaseAuth auth;
    private DatabaseReference databaseFeedback ;
    private static final int videoRequestCode = 101 ;
    private Uri filePath ;
    private StorageReference vStorageRef ;
    private String userEmail ;
    private String uploadedFilePath = null ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        databaseFeedback = FirebaseDatabase.getInstance().getReference("feedback");


        query = (EditText) findViewById(R.id.query);
        suggestion = (EditText) findViewById(R.id.suggestion);
        buttonSubmit = (Button) findViewById(R.id.btnsubmit);
        buttonpickvideo = findViewById(R.id.uploadvideoButton);
        video = findViewById(R.id.video_feedback);

        vStorageRef = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail();



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadFeedback();
            }
        });

        buttonpickvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                startActivityForResult(Intent.createChooser(intent , "Select a video"),videoRequestCode);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == videoRequestCode && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();


            video.setVideoURI(Uri.parse(String.valueOf(filePath)));
            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.evp_action_play);
            video.start();
        }
    }





    public void uploadFeedback(){
        uploadedFilePath = null ;
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading ...");
            progressDialog.show();
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            String folder_name = "Feedback Video/";
            String fileName = userEmail + currentDateTimeString;
            StorageReference bedsoreRef = vStorageRef.child(folder_name + fileName);

            bedsoreRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            progressDialog.dismiss();
                            uploadedFilePath = downloadUrl.toString();
                            //Toast.makeText(getApplicationContext(),"Upload finish", Toast.LENGTH_SHORT).show();
                            submitInfo(uploadedFilePath);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100*taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploaded...");
                        }
                    });


        }else {
            Toast.makeText(this,"File not found", Toast.LENGTH_SHORT).show();
        }



    }















    public void submitInfo(String uploadedFilePath){



        String id = databaseFeedback.push().getKey();
        String Query = query.getText().toString().trim();
        String Suggestion = suggestion.getText().toString().trim();


        FeedbackData data = new FeedbackData(id , userEmail ,  Query , Suggestion , uploadedFilePath);

        databaseFeedback.child(id).setValue(data);

        Toast.makeText(this , "Thank you for your valuable feedback" , Toast.LENGTH_LONG).show();

    }









}
