package com.lmz.zhuanpandemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmz.zhuanpandemo.ani.RotateUtills;
import com.lmz.zhuanpandemo.view.LuckyPanView;

public class MainActivity extends AppCompatActivity {
    private LuckyPanView mLuckyPanView;
    private ImageView mAni1Iv;
    private ImageView mAni2Iv;
    private ImageView mAni3Iv;
    private ImageView mStartIv;
    private ImageView mDengIv;
    private TextView djs_iv;

    //时间
    private int time = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
        mAni1Iv = (ImageView) findViewById(R.id.id_ani1_iv);
        mAni2Iv = (ImageView) findViewById(R.id.id_ani2_iv);
        mAni3Iv = (ImageView) findViewById(R.id.id_ani3_iv);
        djs_iv = (TextView) findViewById(R.id.djs_tv);
        mStartIv = (ImageView) findViewById(R.id.start_game_iv);
        mDengIv = (ImageView) findViewById(R.id.ani_deng);
//        Glide.with(this)
//                .load(R.drawable.gif)
//                .asGif()
//                .into(mDengIv);
        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (time > 2) {
                time--;
                djs_iv.setText(time + "");
                handler.postDelayed(this, 1000);
            } else if (time > 0 && time <= 2) {
                mStartIv.setVisibility(View.VISIBLE);
                time--;
                djs_iv.setText(time + "");
                handler.postDelayed(this, 1000);
            } else {
                mStartIv.setVisibility(View.GONE);
                RotateUtills.applyRotation(mAni1Iv, mAni1Iv, 1, 0, 0, 1);
                RotateUtills.applyRotation(mAni2Iv, mAni2Iv, 1, 0, 0, 1);
                RotateUtills.applyRotation(mAni3Iv, mAni3Iv, 1, 0, 0, 1);
                mLuckyPanView.luckyStart(1);
                //5S之后停止转盘
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLuckyPanView.luckyEnd();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mStartIv.setVisibility(View.VISIBLE);
                                mStartIv.setImageDrawable(getResources().getDrawable(R.drawable.jiayou));
                                RotateUtills.applyRotation(mAni1Iv, mAni1Iv, 1, 0, 0, 2);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mStartIv.setVisibility(View.GONE);
                                        mStartIv.setImageDrawable(getResources().getDrawable(R.drawable.start_game));
                                        RotateUtills.applyRotation(mAni1Iv, mAni1Iv, 1, 0, 0, 2);
                                        RotateUtills.applyRotation(mAni2Iv, mAni2Iv, 1, 0, 0, 2);
                                        RotateUtills.applyRotation(mAni3Iv, mAni3Iv, 1, 0, 0, 2);
                                        time = 5;
                                        handler.postDelayed(runnable, 1000);
                                    }
                                }, 3000);
                            }
                        }, 4000);
                    }
                }, 5000);
            }

        }
    };

}
