package com.lmz.zhuanpandemo.ani;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * 作者：LMZ on 2016/12/12 0012 17:13
 */
public class RotateUtills {
    private static float deepin = 0.0f;

    public static void applyRotation(View view, View nextView, int position,
                                     float start, float end, int type) {
        // Find the center of the container
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
                centerX, centerY, deepin, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(view, nextView, type));
        view.startAnimation(rotation);
    }

    private static final class DisplayNextView implements Animation.AnimationListener {
        private final View mView, mNextView;
        private int type;

        private DisplayNextView(View view, View nextView, int type) {
            mView = view;
            mNextView = nextView;
            this.type = type;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            mView.post(new SwapViews(mNextView, type));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    private static final class SwapViews implements Runnable {
        private final View mView;
        private int type;

        public SwapViews(View nextView, int type) {
            mView = nextView;
            this.type = type;
        }

        public void run() {
            final float centerX = mView.getWidth() / 2.0f;
            final float centerY = mView.getHeight() / 2.0f;
            Rotate3dAnimation rotation;

            if (type == 1) {
                rotation = new Rotate3dAnimation(0, 70, centerX, centerY, deepin,
                        false);
            } else {
                rotation = new Rotate3dAnimation(0, 0, centerX, centerY, deepin,
                        false);
            }


            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            mView.startAnimation(rotation);
        }
    }
}
