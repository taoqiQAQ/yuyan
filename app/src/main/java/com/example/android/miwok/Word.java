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
    private int mImageResourceId = NO_IMAGE_PROVIDED;


    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * 创建一个新的Word对象。
     *
     * @param defaultTranslation 是用户已经熟悉的语言中的单词
     *                           (such as English)
     * @param miwokTranslation   是Miyok语言中的词
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * 创建一个新的Word对象。
     *
     * @param defaultTranslation 是用户已经熟悉的语言中的单词
     *                           (such as English)
     * @param miwokTranslation   是Miyok语言中的词
     * @param imageResourceId    是与该词相关联的图像的可绘制资源ID
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

    /**
     * 返回此字词是否有图片
     *
     */
    public boolean hadImage(){
        //mImageResourceId不等于-1 那么就存在有效的图片 该方法应返回true
        //mImageResourceId等于-1 那么该方法没有图片 该方法会返回false
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
