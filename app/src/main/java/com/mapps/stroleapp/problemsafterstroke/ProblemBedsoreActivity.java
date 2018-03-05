package com.mapps.stroleapp.problemsafterstroke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.mapps.stroleapp.R;
import com.mapps.stroleapp.registration.LoginActivity;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class ProblemBedsoreActivity extends AppCompatActivity {

    private Button pickImageBedsorePatient, submitBedsoreInfo, bedsoreBack;
    private EditText bedsoreDurationInDays , bedsoreDegree;
    private ImageView imageBedsorePatient;
    private FirebaseAuth auth;
    private DatabaseReference databaseBedSore ;
    private static final int imageRequestCode = 1410 ;
    private Uri filePath ;
    private StorageReference mStorageRef;
    private String userEmail ;

    private String uploadedFilePath = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_bedsore);
        imageBedsorePatient = findViewById(R.id.image_bedsore_patient);
        pickImageBedsorePatient = findViewById(R.id.pick_image_bedsore_patient);
        submitBedsoreInfo = findViewById(R.id.submit);
        bedsoreBack = findViewById(R.id.bedsore_back);

        databaseBedSore = FirebaseDatabase.getInstance().getReference("bedsore");

        bedsoreDurationInDays = findViewById(R.id.bedsore_duration_in_days);
        bedsoreDegree = findViewById(R.id.bedsore_degree);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        bedsoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemBedsoreActivity.this, ProblemsAfterStrokeActivity.class));
            }
        }
        );

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail= user.getEmail();
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();

        pickImageBedsorePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent , "Select an Image"),imageRequestCode);
            }
        });

        submitBedsoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim())){
                    Toast.makeText(getApplicationContext() , "Please fill the bedsore days" , Toast.LENGTH_LONG).show();
                }
                else {
                    uploadFile();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imageRequestCode && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageBedsorePatient.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void uploadFile(){
        uploadedFilePath = null ;
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading ...");
            progressDialog.show();
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            String folder_name = "bedsoreImages/";
            String fileName = userEmail + currentDateTimeString;
            StorageReference bedsoreRef = mStorageRef.child(folder_name + fileName);

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
                            startActivity(new Intent(ProblemBedsoreActivity.this, ProblemsAfterStrokeActivity.class));
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
        int bedsoreDays = Integer.parseInt(bedsoreDurationInDays.getText().toString().trim());

        if (!TextUtils.isEmpty(uploadedFilePath) && !TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim())){
            String id = databaseBedSore.push().getKey();
            ProblemBedsoreModel user = new ProblemBedsoreModel(id,userEmail,bedsoreDays,uploadedFilePath) ;
            databaseBedSore.child(id).setValue(user);

            Toast.makeText(this , "Bedsore added" , Toast.LENGTH_LONG).show();
        }
        else {
            if (TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim())){
                Toast.makeText(this , "Please fill the bedsore days" , Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(uploadedFilePath)){
                Toast.makeText(this , "Please select file to upload" , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this , "Unexpected Error happen" , Toast.LENGTH_LONG).show();
            }

        }
    }

}
