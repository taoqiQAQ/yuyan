package com.example.android.miwok;

/**
 * {@link Word}表示用户想要学习的词汇单词。
 * 它包含默认翻译和该词的Miwok翻译。
 */

public class Word {
    /**
     * 单词的默认翻译
     */
    private String mDefaultTranslation;

    /**
     * Miwok翻译的单词
     */
    private String mMiwokTranslation;
    // 图像资源 ID
    private int mImageResourceId;

    /**
     * 创建一个新的Word对象。
     *
     * @param defaultTranslation 是用户已经熟悉的语言中的单词
     *                           (such as English)
     * @param miwokTranslation   是Miyok语言中的词
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     * 获取单词的默认翻译。
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * 获得该词的Miwok翻译。
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
    /**
     * 获取图像资源ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

}
