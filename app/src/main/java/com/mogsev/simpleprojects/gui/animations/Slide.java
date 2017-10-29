package com.mogsev.simpleprojects.gui.animations;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mogsev.simpleprojects.R;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Slide {

    public static void slide_up(Context context, View view){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.expandable_up);
        if(animation != null){
            animation.reset();
            if(view != null){
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

    public static void slide_down(Context context, View view){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.expandable_down);
        if(animation != null){
            animation.reset();
            if(view != null){
                view.clearAnimation();
                view.startAnimation(animation);
            }
        }
    }

}
