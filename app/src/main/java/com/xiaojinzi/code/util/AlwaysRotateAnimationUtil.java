package com.xiaojinzi.code.util;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import xiaojinzi.animation.RotateAnimationUtil;

/**
 * Created by cxj on 2016/11/12.
 */
public class AlwaysRotateAnimationUtil {

    public static void start(View view) {
        RotateAnimation rotateAnimation = RotateAnimationUtil.rotateSelf(0f, 360f);
        rotateAnimation.setRepeatMode(RotateAnimation.RESTART);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);
        rotateAnimation.setRepeatCount(-1);
        view.startAnimation(rotateAnimation);
    }

}
