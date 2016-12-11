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
}
