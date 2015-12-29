package com.smartman.base.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.smartman.myroadrecord.R;

/**
 * Created by jiahui.chen on 2015/12/28.
 * 密码输入框
 */
public class PasswordEditText extends EditText {
    /**
     * 图标的引用
     */
    private Drawable mDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;

    private boolean isShowPwd = true;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        //密码框默认输入隐藏模式
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mDrawable = getCompoundDrawables()[2];
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.drawable.show_pwd);
        }
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        //展示默认图标
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], mDrawable, getCompoundDrawables()[3]);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    setIcon(isShowPwd);
                }
            }
        }

        return super.onTouchEvent(event);
    }


    private void showPassword() {
        mDrawable = getResources().getDrawable(R.drawable.show_pwd);
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], mDrawable, getCompoundDrawables()[3]);
    }

    private void hidePassword() {
        mDrawable = getResources().getDrawable(R.drawable.hide_pwd);
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], mDrawable, getCompoundDrawables()[3]);
    }


    /**
     * 设置图标的显示与隐藏
     *
     * @param isShowed
     */
    protected void setIcon(boolean isShowed) {
        if (isShowed) {
            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            hidePassword();
            isShowPwd = false;
        } else {
            setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPassword();
            isShowPwd = true;
        }
    }

}

