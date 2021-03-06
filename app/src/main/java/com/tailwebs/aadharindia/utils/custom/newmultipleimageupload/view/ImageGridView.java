package com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.loopeer.android.librarys.imagegroupview.OnImageClickListener;
import com.tailwebs.aadharindia.R;
import com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.NavigatorImage;
import com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.model.Image;
import com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.model.SquareImage;
import com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.utils.DisplayUtils;
import com.tailwebs.aadharindia.utils.custom.newmultipleimageupload.utils.ImageGroupSavedState;
import com.tailwebs.baseproject.newimageupload.activity.AlbumActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ImageGridView extends GridView implements GridImageAdapter.OnSquareClickListener {
    private static final String TAG = "ImageGridView";

    private final static int MAX_VALUE = -1;
    private int GRID_TYPE = 2;

    private String itemValue;

    private ImageGroupSavedState imageGroupSavedState;
    private List<SquareImage> preImages;
    private OnImageClickListener clickListener;
    private boolean mShowAddButton, mRoundAsCircle, mShowTextDelete, mShowDeleteDialog, mShowDeleteButton;
    private int mAddButtonDrawable;
    private int mPlaceholderDrawable;
    private int maxImageNum;
    private boolean mDragDismiss;
    private boolean mDoUploadShowDialog;
    private GridImageAdapter mGridImageAdapter;
    private Context mContext;

    public ImageGridView(Context context) {
        this(context, null);
    }

    public ImageGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        getAttrs(context, attrs, defStyle);

        init();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs == null) return;
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageGroupView, defStyleAttr, 0);
        if (a == null) return;

        mShowAddButton = a.getBoolean(R.styleable.ImageGroupView_showAddButton, false);
        mRoundAsCircle = false;
        maxImageNum = a.getInteger(R.styleable.ImageGroupView_maxImageNum, MAX_VALUE);
        mAddButtonDrawable = a.getResourceId(R.styleable.ImageGroupView_addButtonDrawable, R.drawable.add_photos_multiple);
        mShowTextDelete = a.getBoolean(R.styleable.ImageGroupView_showDeleteText, false);
        mPlaceholderDrawable = a.getResourceId(R.styleable.ImageGroupView_imagePlaceholderDrawable, R.drawable.img_placeholder);
        mDragDismiss = a.getBoolean(R.styleable.ImageGroupView_dragDismiss, true);
        mDoUploadShowDialog = a.getBoolean(R.styleable.ImageGroupView_showDialog, false);
        mShowDeleteDialog = a.getBoolean(R.styleable.ImageGroupView_showDeleteDialog, false);
        mShowDeleteButton = a.getBoolean(R.styleable.ImageGroupView_showDeleteButton, true);
        a.recycle();
    }

    public int setMaxValue(int val){

        maxImageNum = val;

        return maxImageNum;

    }

    private void init() {
        /*setEnabled(true);
        setClickable(true);*/
        preImages = new ArrayList<>();
        mGridImageAdapter = new GridImageAdapter(getContext(), this);
        setAdapter(mGridImageAdapter);
        updateImages();
    }


    private void updateImages() {
        mGridImageAdapter.updateData(preImages, mShowAddButton && (getCanSelectMaxNum() != 0 || maxImageNum == MAX_VALUE));
        mGridImageAdapter.updateParam(mAddButtonDrawable, mPlaceholderDrawable, mRoundAsCircle);
    }

    private void updateImagesPosition() {
        int[] position = new int[2];
        this.getLocationOnScreen(position);

        int columnWidth = getColumnWidth();
        int numColumns = getNumColumns();
        int horizontalSpacing = getHorizontalSpacing();
        int verticalSpacing = getVerticalSpacing();
        int left, top;
        for (int i = 0; i < preImages.size(); i++) {
            left = position[0] + getPaddingLeft() + i % numColumns * columnWidth + i % numColumns * horizontalSpacing;
            top = position[1] + getPaddingTop() - DisplayUtils.getStatusBarHeight(getContext()) + i / numColumns * columnWidth + i / numColumns * verticalSpacing;
            preImages.get(i).setPosition(left, top, columnWidth, columnWidth);
        }
    }

    public void setGridImageAdapter(GridImageAdapter gridImageAdapter) {
        mGridImageAdapter = gridImageAdapter;
        updateImages();
    }

    public void updateNetPhotos(List<String> photos) {
        if (photos == null) return;
        preImages.clear();
        for (String url : photos) {
            SquareImage squareImage = new SquareImage(null, url, null, SquareImage.PhotoType.NETWORK);
            squareImage.setId(createIndex());
            preImages.add(squareImage);
        }
        updateImages();
    }

    public void setNetworkPhotosWithKey(List<String> urls) {
        if (urls == null) return;
        preImages.clear();
        for (String url : urls) {
            String[] headWithKey = url.split("/");
            preImages.add(new SquareImage(null, url, headWithKey[headWithKey.length - 1], SquareImage.PhotoType.NETWORK));
        }
        updateImages();
    }

    public void setNetworkPhotosWithKey(List<String> urls, List<String> keys) {
        if (urls == null || keys == null || urls.size() != keys.size()) {
            return;
        }
        for (int i = 0; i < urls.size(); i++) {

        }
    }

    public void updateLocalPhotos(List<String> photos) {
        if (photos == null) return;
        preImages.clear();
        for (String url : photos) {
            SquareImage squareImage = new SquareImage(url, null, null, SquareImage.PhotoType.LOCAL);
            squareImage.setId(createIndex());
            preImages.add(squareImage);
        }
        updateImages();
    }

    public void updateImage(List<SquareImage> photos) {
        if (photos == null) return;
        preImages.clear();
        for (SquareImage squareImage : photos) {
            squareImage.setId(createIndex());
            preImages.add(squareImage);
        }
        updateImages();
    }

    public void showAddButton(boolean showAddButton) {
        mShowAddButton = showAddButton;
        updateImages();
    }

    @Override
    public void photoClick(View v, SquareImage squareImage, int position) {
        if (squareImage == null) {
            doUpLoadPhotoClick();
        } else if (clickListener != null) {
            clickListener.onImageClick(v, squareImage);
        } else {
            updateImagesPosition();
            NavigatorImage.INSTANCE.startImageSwitcherActivity(getContext(), getSquarePhotos(), position,
                    mShowAddButton, mPlaceholderDrawable, getId(), mDragDismiss, mShowTextDelete, mShowDeleteDialog, mShowDeleteButton);
        }
    }


    private List<SquareImage> getSquarePhotos() {
        return preImages;
    }

    private void doUpLoadPhotoClick() {
        if (mDoUploadShowDialog) {
            new AlertDialog.Builder(getContext())
                    .setItems(new String[]{getContext().getString(R.string.take_photo),
                                    getContext().getString(R.string.select_images)},
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        NavigatorImage.INSTANCE.startCustomAlbumActivity(getContext(), getCanSelectMaxNum(), getId(), AlbumActivity.Companion.getTAKE_PHOTO(), getGRID_TYPE());
                                    } else {
                                        NavigatorImage.INSTANCE.startCustomAlbumActivity(getContext(), getCanSelectMaxNum(), getId(), AlbumActivity.Companion.getALBUM(), getGRID_TYPE());
                                    }
                                }
                            })
                    .show();
        } else {

            NavigatorImage.INSTANCE.startCustomAlbumActivity(getContext(), getCanSelectMaxNum(), getId(), GRID_TYPE);
        }
    }

    private int getCanSelectMaxNum() {
        if (maxImageNum == MAX_VALUE) return 0;
        return maxImageNum - preImages.size();
    }

    public void onParentResult(int requestCode, Intent data) {
        if (data == null || data.getIntExtra(NavigatorImage.EXTRA_IMAGE_GROUP_ID, 0) != getId())
            return;
        List<Image> images = (List<Image>) data.getSerializableExtra(NavigatorImage.EXTRA_PHOTOS_URL);
        Log.i("Shahana",""+ data.getSerializableExtra(NavigatorImage.EXTRA_PHOTOS_URL));
        ArrayList<Integer> positions = data.getIntegerArrayListExtra(NavigatorImage.EXTRA_IMAGE_URL_POSITION);
        if (requestCode == NavigatorImage.RESULT_IMAGE_SWITCHER && null != positions) {
            doPhotosDelete(positions);
        } else if (requestCode == NavigatorImage.RESULT_SELECT_PHOTOS && null != images) {
            doSelectPhotos(images);
        } 
    }



    public void onParentResults(int requestCode, Intent data) {
        if (data == null)
            return;
        List<Image> images = (List<Image>) data.getSerializableExtra(NavigatorImage.EXTRA_PHOTOS_URL);
        ArrayList<Integer> positions = data.getIntegerArrayListExtra(NavigatorImage.EXTRA_IMAGE_URL_POSITION);
        if (requestCode == NavigatorImage.RESULT_IMAGE_SWITCHER && null != positions) {
            doPhotosDelete(positions);
        } else if (requestCode == NavigatorImage.RESULT_SELECT_PHOTOS && null != images) {
            doSelectPhotos(images);
        }
    }

    public ArrayList<String> getImageKeys() {
        ArrayList<String> result = new ArrayList<>();
        for (SquareImage squareImage : preImages) {
            if (!TextUtils.isEmpty(squareImage.interNetUrl) || !TextUtils.isEmpty(squareImage.localUrl)) {
                result.add(squareImage.urlKey);
            }
        }
        return result;
    }

    public HashMap<String, String> getUploadKeyUrlMap() {
        HashMap<String, String> map = new HashMap<>();
        for (SquareImage squareImage : preImages) {
            if (TextUtils.isEmpty(squareImage.interNetUrl) && !TextUtils.isEmpty(squareImage.localUrl)) {
                map.put(squareImage.urlKey, squareImage.localUrl);
            }
        }
        return map;
    }

    public String getImageKeyString() {
        ArrayList<String> keys = getImageKeys();
        if (keys.isEmpty()) return null;
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            sb.append(key);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public ArrayList<String> getLocalUrls() {
        ArrayList<String> result = new ArrayList<>();
        for (SquareImage squareImage : preImages) {
            if (TextUtils.isEmpty(squareImage.interNetUrl) && !TextUtils.isEmpty(squareImage.localUrl)) {
                result.add(squareImage.localUrl);
            }
        }
        return result;
    }

    public ArrayList<String> getInternetUrls() {
        ArrayList<String> result = new ArrayList<>();
        for (SquareImage squareImage : preImages) {
            if (!TextUtils.isEmpty(squareImage.interNetUrl)) {
                result.add(squareImage.interNetUrl);
            }
        }
        return result;
    }

    public void doPhotosDelete(final ArrayList<Integer> positions) {
        if (positions == null) return;
        List<SquareImage> deleteImages = new ArrayList<>();
        for (int position : positions) {
            deleteImages.add(preImages.get(position));
        }
        for (SquareImage squareImage : deleteImages) {
            if (preImages.contains(squareImage)) preImages.remove(squareImage);
        }
        updateImages();
    }

    public void doSelectPhotos(List<Image> images) {
        for (Image image : images) {
            Log.i("Shahana",""+ image.url);
            SquareImage squareImage = new SquareImage(image.url, null, getPhotoKey(image.time), SquareImage.PhotoType.LOCAL);
            preImages.add(squareImage);
        }
        updateImages();
    }

    @NonNull
    private String getPhotoKey(long time) {
        return "image_" + time;
    }

    private int createIndex() {
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("mmss");
        String date = sdf.format(new Date(time));
        int num1 = Integer.valueOf(date + Long.toString(time % 1000));
        int i = num1 * 10 + preImages.size();
        return i;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        final Parcelable parcelable = super.onSaveInstanceState();
        final ImageGroupSavedState imageSaveState = new ImageGroupSavedState(parcelable);
        imageSaveState.setSquarePhotos(getSquarePhotos());
        return imageSaveState;
    }

    @Override
    public void onRestoreInstanceState(final Parcelable state) {
        if (!(state instanceof ImageGroupSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        final ImageGroupSavedState ss = (ImageGroupSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        imageGroupSavedState = ss;
        restoreView();
    }

    private void restoreView() {
        if (imageGroupSavedState != null) {
            preImages.clear();
            preImages.addAll(imageGroupSavedState.getSquarePhotos());
            updateImages();
            imageGroupSavedState = null;
        }
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        clickListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isEnabled() || !isClickable()) return false;
        if (pointToPosition((int) ev.getX(), (int) ev.getY()) == -1 && ev.getAction() == MotionEvent.ACTION_DOWN) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    public void setItemClicked(String n) {
       this.itemValue = n;
    }

    public String  getItemClicked(){
        return itemValue;
    }

    public void setGRID_TYPE(int n) {
        this.GRID_TYPE = n;
    }

    public int getGRID_TYPE() {
        return GRID_TYPE;
    }

}