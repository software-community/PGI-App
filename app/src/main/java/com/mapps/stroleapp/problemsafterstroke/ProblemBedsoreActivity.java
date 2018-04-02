package com.mapps.stroleapp.problemsafterstroke;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.mapps.stroleapp.Manifest;
import com.mapps.stroleapp.R;
import com.mapps.stroleapp.registration.LoginActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProblemBedsoreActivity extends AppCompatActivity {

    private Button pickImageBedsorePatient,clickImageBedsorePatient, submitBedsoreInfo, bedsoreBack;
    private EditText bedsoreDurationInDays , bedsoreDegree;
    private ImageView imageBedsorePatient;
    private FirebaseAuth auth;
    private DatabaseReference databaseBedSore ;
    private static final int imageRequestCode = 1410 ;
    private static final int CAMERA_REQUEST = 1411 ;
    private Uri filePath ;
    private StorageReference mStorageRef;
    private String userEmail ;

    private int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 11;

    private String uploadedFilePath = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_bedsore);
        imageBedsorePatient = findViewById(R.id.image_bedsore_patient);
        clickImageBedsorePatient = findViewById(R.id.click_image_bedsore_patient);
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
        //Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();

        pickImageBedsorePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent , "Select an Image"),imageRequestCode);
            }
        });

        clickImageBedsorePatient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(ProblemBedsoreActivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {


                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(ProblemBedsoreActivity.this ,
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.

                } else {
                    // Permission has already been granted
                    Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraintent, CAMERA_REQUEST);
                }

            }
        });

        submitBedsoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim()) || TextUtils.isEmpty(bedsoreDegree.getText().toString().trim()) ){
                    Toast.makeText(getApplicationContext() , R.string.specify_bedsore_detail, Toast.LENGTH_LONG).show();
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
        Bitmap bm = null ;
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            System.out.println("camera intent successful");
            bm = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100 , bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            Date date = new Date();
            String formattedDate = new SimpleDateFormat("HH:mm").format(date);
            imageBedsorePatient.setImageBitmap(bm);
            filePath = saveImageTOExternalStorage(bm, formattedDate);


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
                            System.out.println(exception.getMessage());
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
            Toast.makeText(this,R.string.file_not_found, Toast.LENGTH_SHORT).show();
        }



    }
    public void submitInfo(String uploadedFilePath){
        int bedsoreDays = Integer.parseInt(bedsoreDurationInDays.getText().toString().trim());
        String bedsoreDegrees = bedsoreDegree.getText().toString().trim() ;
        if (!TextUtils.isEmpty(uploadedFilePath) && !TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim())){
            String id = databaseBedSore.push().getKey();
            ProblemBedsoreModel user = new ProblemBedsoreModel(id,userEmail,bedsoreDays,uploadedFilePath,bedsoreDegrees) ;
            databaseBedSore.child(id).setValue(user);

            Toast.makeText(this , "Bedsore added" , Toast.LENGTH_LONG).show();
        }
        else {
            if (TextUtils.isEmpty(bedsoreDurationInDays.getText().toString().trim())){
                Toast.makeText(this , R.string.select_bedsore_day , Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(uploadedFilePath)){
                Toast.makeText(this , R.string.select_file , Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this , "Unexpected Error happen" , Toast.LENGTH_LONG).show();
            }

        }
    }

    private Uri saveImageTOExternalStorage(Bitmap bm, String formattedDate){
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
System.out.println("save image reached");
        File path = new File(root);
        File myDir = new File(path + "/saveImages");
        if (!myDir.exists()) {
            myDir.mkdirs();
            System.out.println("directory created");
        }

        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String fname = "Image-" + formattedDate + ".jpg";
        File file = new File(myDir, fname);

        System.out.println("root dir = " + root);
        System.out.println("filename dir = " + fname);

//        if (file.exists()){
//
//        }
        String filepathuri ="";
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            filepathuri = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bm, "Title", null);
            out.flush();
            out.close();


        }
        catch (Exception e) {
            e.printStackTrace();

        }

        Uri filePathUri =  Uri.parse(filepathuri);
        String text = traverse(myDir);
        return  filePathUri ;

    }
    public String traverse (File dir) {
        String name = "";
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null){
                for (File file : files){
                    name = name + file.getName() + ",";

                }
            }

        }
        return name ;
    }


}
