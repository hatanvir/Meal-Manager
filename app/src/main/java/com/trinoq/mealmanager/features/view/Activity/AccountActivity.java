package com.trinoq.mealmanager.features.view.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trinoq.mealmanager.R;
import com.trinoq.mealmanager.features.model.models.UserInformation;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;
import com.trinoq.mealmanager.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.trinoq.mealmanager.R.color.colorAccent;

public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.backBt)
    ImageView backBt;
    @BindView(R.id.editFab)
    FloatingActionButton editFab;
    @BindView(R.id.nameEt)
    EditText nameEt;
    @BindView(R.id.phoneNumberEt)
    EditText phoneNumberEt;
    @BindView(R.id.emailEt)
    EditText emailEt;
    @BindView(R.id.addImagebackground)
    LinearLayout addImagebackground;
    @BindView(R.id.addImage)
    ImageView addImage;
    @BindView(R.id.profileCircleImageView)
    CircleImageView profileCircleImageView;

    String check="false";
    Uri uri;
    String filePath="";
    UserInformation userInformation;
    Retrofit retrofit;
    Api api;
    int groupId,userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences myPreferences=getSharedPreferences("MyPreferences",MODE_PRIVATE);
        if (myPreferences.getString("theme","").equals("true")){
            setTheme(R.style.LightTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        //myPreferences=getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        userId=myPreferences.getInt("UserId",0);
        groupId=myPreferences.getInt("GroupId",0);

        setContentView(R.layout.activity_account);
        ButterKnife.bind(AccountActivity.this);


        nameEt.setClickable(false);
        nameEt.setEnabled(false);
        phoneNumberEt.setEnabled(false);
        emailEt.setEnabled(false);
        nameEt.setFocusableInTouchMode(false);
        emailEt.setFocusableInTouchMode(false);
        phoneNumberEt.setFocusableInTouchMode(false);

        nameEt.setText(Utils.userInformations.get(0).getUserName());
        emailEt.setText(Utils.userInformations.get(0).getEmail());
        phoneNumberEt.setText(Utils.userInformations.get(0).getPhoneNumber());

        checkPermissio();

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //if (check.equals("false")){
            editFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (check.equals("false")){
                        addImage.setVisibility(View.VISIBLE);
                        addImagebackground.setVisibility(View.VISIBLE);
                        nameEt.setClickable(true);
                        nameEt.setEnabled(true);
                        nameEt.setFocusable(true);
                        emailEt.setEnabled(true);
                        phoneNumberEt.setEnabled(true);
                        nameEt.setFocusableInTouchMode(true);
                        emailEt.setFocusableInTouchMode(true);
                        phoneNumberEt.setFocusableInTouchMode(true);

                        editFab.setImageDrawable(ContextCompat.getDrawable(AccountActivity.this,R.drawable.ic_check_black_24dp));

                        Toast.makeText(AccountActivity.this, "call", Toast.LENGTH_SHORT).show();
                        check="true";
                    }
                    else if (check.equals("true"))
                    {


                        addImage.setVisibility(View.INVISIBLE);
                        addImagebackground.setVisibility(View.INVISIBLE);
                        nameEt.setClickable(false);
                        nameEt.setEnabled(false);
                        phoneNumberEt.setEnabled(false);
                        emailEt.setEnabled(false);
                        nameEt.setFocusableInTouchMode(false);
                        emailEt.setFocusableInTouchMode(false);
                        phoneNumberEt.setFocusableInTouchMode(false);

                        editFab.setImageDrawable(ContextCompat.getDrawable(AccountActivity.this,R.drawable.ic_edit_black_24dp));
                        check="false";
                        Toast.makeText(AccountActivity.this, "faile", Toast.LENGTH_SHORT).show();

                        Log.d("HOOO",filePath);
                        updateuser(filePath);


                    }

                }
            });

            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (ActivityCompat.checkSelfPermission(AccountActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(AccountActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            imagePickerTypeBottomSheet();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

    }

    private void checkPermissio() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateuser(String path){
        if(path!=null){
            File file=new File(path);
            RequestBody fileRequsetbody=RequestBody.create(MediaType.parse("image/*"),file);
            MultipartBody.Part part=MultipartBody.Part.createFormData("image",file.getName(),fileRequsetbody);

            uplodedata(part);
        }
    }
    private  void uplodedata(MultipartBody.Part part){

        retrofit=RetrofitClient.getClient();
        api=retrofit.create(Api.class);

        Log.d("ttttttt",part+" "+emailEt.getText().toString());
        Call<ResponseBody> call=api.updatedUser(userId,part,ResponseBody.create(MultipartBody.FORM,emailEt.getText().toString()));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d("DDDD",String.valueOf(response.code()+" "+response.message()));

                if (response.code()==200){

                    Toast.makeText(AccountActivity.this, "Seccess", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(AccountActivity.this, "Faild", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void imagePickerTypeBottomSheet() {
        final BottomSheetDialog dialog = new BottomSheetDialog(AccountActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_selected_image);

        ImageButton cameraDialogImageBt = dialog.findViewById(R.id.cameraDialogImageBt);
        ImageButton gallaryDialogImageBt = dialog.findViewById(R.id.gallaryDialogImageBt);


        cameraDialogImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
                dialog.dismiss();
            }
        });

        gallaryDialogImageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                uri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex1 = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(columnIndex1);
                if(checkImageSize(filePath)){
                    profileCircleImageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                   // Log.d("OOO",BitmapFactory.decodeFile(filePath).toString());
                }else {
                    maximumImageAlertDialog();
                }
            }
        }else if(requestCode == 2){
            try{
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                if (thumbnail!=null){
                    filePath = saveImage(thumbnail);
                    if(checkImageSize(filePath)){
                        profileCircleImageView.setImageBitmap(thumbnail);
                    }else {
                        maximumImageAlertDialog();
                    }
                    //Toast.makeText(getApplicationContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Camera connection failed try again.", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception  e){}

        }
    }


    private boolean checkImageSize(String filePath) {
        File file = new File(filePath);
        if (file.length()/1024<=5000) {//minimum upload mb in server is 5 mb
            return true;
        }
        return false;
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/TscPhoto");
        if (!wallpaperDirectory.exists()) {  // have the object build the directory structure, if needed.
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    private void maximumImageAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Maximum file size is 5 Mb. Select file between 5 mb and upload");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imagePickerTypeBottomSheet();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
