package me.ele.uetool.sample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import me.ele.uetool.UETool;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwitchCompat control = (SwitchCompat) findViewById(R.id.control);
        control.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!UETool.showUETMenu()) {
                        control.setChecked(false);
                    }
                } else {
                    UETool.dismissUETMenu();
                }
            }
        });
        control.setChecked(true);

        updateDraweeView();
        updateSpanTextView();
        updateCustomView();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.btn2:
                new CustomDialog(this).show();
                break;
            case R.id.btn3:
                startActivity(new Intent(this, FragmentSampleActivity.class));
                break;
        }
    }

    private void updateDraweeView() {
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.drawee_view);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561443230828&di=066c39a584cfe5cdcb244cc3af74afff&imgtype=0&src=http%3A%2F%2Fzkres1.myzaker.com%2F201905%2F5cda353b77ac6420a360a53f_320.jpg")
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(draweeController);
    }

    private void updateSpanTextView() {
        TextView spanTextView = (TextView) findViewById(R.id.span);
        SpannableString spannableString = new SpannableString("  海底捞火锅");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_food_new);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        VerticalImageSpan imageSpan = new VerticalImageSpan(drawable);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanTextView.setText(spannableString);
    }

    private void updateCustomView() {
        final CustomView customView = (CustomView) findViewById(R.id.custom);
        customView.setMoreAttribution("more attribution");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_up_vote);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        customView.setCompoundDrawables(null, drawable, null, null);
    }
}
