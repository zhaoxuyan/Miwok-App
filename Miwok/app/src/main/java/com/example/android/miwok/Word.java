package com.example.android.miwok;

public class Word {
    private String mDefaultTranslation; // 英语
    private String mMiwokTranslation; // Miwok翻译

    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourveId = NO_IMAGE_PROVIDED; // 图片ID

    private int mRawResourceId; // 音频ID

    /**
     * 有图片的构造函数
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int rawResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourveId = imageResourceId;
        mRawResourceId = rawResourceId;
    }

    /**
     * 无图片的构造函数
     */
    public Word(String defaultTranslation, String miwokTranslation, int rawResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mRawResourceId = rawResourceId;
    }

    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getmImageResourveId() {
        return mImageResourveId;
    }

    public int getmRawResourceId(){
        return mRawResourceId;
    }

    public boolean hasImage() {
//        if(this.mImageResourveId == -1){
//            return false;
//        }
//        else
//            return true;
        return mImageResourveId != NO_IMAGE_PROVIDED;
    }

    /**
     * toString()方法
     * 方便在Log中查看
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourveId=" + mImageResourveId +
                ", mRawResourceId=" + mRawResourceId +
                '}';
    }
}
