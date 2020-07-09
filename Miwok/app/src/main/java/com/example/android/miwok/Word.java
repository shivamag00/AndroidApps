package com.example.android.miwok;

public class Word {
    public String mDefaultTranslation;
    public String mMiwokTranslation;
    private int  mResourceId = NO_IMAGE;
    private int mAudioResourceId;
    private static final int NO_IMAGE = -1;

    public Word(String defaultTranslation, String miwokTranslation, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int ResourceId, int AudioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mResourceId = ResourceId;
        mAudioResourceId = AudioResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmResourceId() {
        return mResourceId;
    }

    public boolean hasImage(){
        return mResourceId != NO_IMAGE;
    }

    public int getmAudioResourceId(){
        return mAudioResourceId;
    }
}
