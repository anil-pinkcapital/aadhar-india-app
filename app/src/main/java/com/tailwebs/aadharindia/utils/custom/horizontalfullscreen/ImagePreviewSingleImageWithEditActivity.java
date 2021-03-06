package com.tailwebs.aadharindia.utils.custom.horizontalfullscreen;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.tailwebs.aadharindia.R;

import java.util.List;

public class ImagePreviewSingleImageWithEditActivity extends AppCompatActivity implements OnItemClickListener<PreviewFile> {


    public static String IMAGE_LIST = "intent_image_item";
    public static String CURRENT_ITEM = "current_item";

    public Toolbar toolbar;
    ViewPager vPager;
    private List<PreviewFile> mUriList;
    private Bitmap mBitmap;
    private String view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        vPager = findViewById(R.id.vPager);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setUpViews();
    }

    private void setUpViews() {

        mUriList = (List<PreviewFile>)
                getIntent().getSerializableExtra(IMAGE_LIST);

        SlideAdapter slideAdapter =
                new SlideAdapter(this, mUriList, this);
        vPager.setAdapter(slideAdapter);

        vPager.setCurrentItem(getIntent().getIntExtra(CURRENT_ITEM, 0));

        vPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float normalizedPosition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedPosition / 2 + 0.5f);
//                page.setScaleY(normalizedPosition / 2 + 0.5f);
            }
        });
    }

    @Override
    public void onItemClick(PreviewFile item) {
        toolbar.setVisibility(toolbar.getVisibility() == View.VISIBLE ?
                View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//       if (item.getItemId() == R.id.menu_share) {
//            view = "Delete";
//
//        }
        return true;
    }

    private void handleSavePermission(Bitmap resource) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (CheckPermission.hasPermission(permission, ImagePreviewSingleImageWithEditActivity.this)) {
                Util.saveImageToGallery(ImagePreviewSingleImageWithEditActivity.this, resource);
            } else {
                mBitmap = resource;
                CheckPermission.requestPerm(new String[]{permission},
                        CheckPermission.PERMISSION_STORAGE, ImagePreviewSingleImageWithEditActivity.this);
            }
        } else {
            Util.saveImageToGallery(ImagePreviewSingleImageWithEditActivity.this, resource);
        }
    }

    private void handleSharePermission(Bitmap resource) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (CheckPermission.hasPermission(permission, ImagePreviewSingleImageWithEditActivity.this)) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, Util.shareImage(ImagePreviewSingleImageWithEditActivity.this, resource));
                startActivity(Intent.createChooser(intent, "Share Image"));
            } else {
                mBitmap = resource;
                CheckPermission.requestPerm(new String[]{permission},
                        CheckPermission.PERMISSION_STORAGE, ImagePreviewSingleImageWithEditActivity.this);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpeg");
            intent.putExtra(Intent.EXTRA_STREAM, Util.shareImage(ImagePreviewSingleImageWithEditActivity.this, resource));
            startActivity(Intent.createChooser(intent, "Share Image"));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CheckPermission.PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (view.equals("Save")) {
                    handleSavePermission(mBitmap);
                } else {
                    handleSharePermission(mBitmap);
                }
            } else {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        // user rejected the permission
                        boolean showRationale = shouldShowRequestPermissionRationale(permission);
                        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)
                                || Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
                            if (!showRationale) {
                                showAlertDialog();
                            } else
                                shouldShowRequestPermissionRationale(permission);
                        }
                    }
                }
            }

        }

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ImagePreviewSingleImageWithEditActivity.this).setPositiveButton("GO TO SETTING",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Permission denied")
                        .setMessage("Without storage permission the app" +
                                " is unable to open gallery or to save photos." +
                                " Are you sure want to deny this permission?");
        builder.create().show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
