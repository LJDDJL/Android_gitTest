package com.example.draft;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button leftButton;
    Button rightButton;
    Button confirm;
    SwitchCompat aSwitch;
    ProgressBar progressBar;
    EditText number;
    ImageView imageView;
    RadioGroup radioGroup;
    SeekBar seekBar;
    CheckBox chinese;
    CheckBox math;
    CheckBox english;
    RatingBar ratingBar;


    String yuWen = "";
    String shuXue = "";
    String yingYu = "";

    String TAG = "test";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = findViewById(R.id.textView3);
        leftButton = findViewById(R.id.button3);
        rightButton = findViewById(R.id.button4);
        confirm = findViewById(R.id.button5);
        aSwitch = findViewById(R.id.switch1);
        progressBar = findViewById(R.id.progressBar3);
        number = findViewById(R.id.editTextNumber);
        imageView = findViewById(R.id.imageView);
        radioGroup = findViewById(androidx.constraintlayout.widget.R.id.radio);
        seekBar = findViewById(R.id.seekBar);
        chinese = findViewById(R.id.checkBox);
        math = findViewById(R.id.checkBox2);
        english = findViewById(R.id.checkBox3);
        ratingBar = findViewById(R.id.ratingBar);

        //判断是否有临时存储的数据
        if(savedInstanceState!=null){
            number.setText(savedInstanceState.getString("number"));
        }

        //左按钮
        leftButton.setOnClickListener((v) -> text.setText(R.string.button1));//lambda表达式更加简便，格式：(这里为传入的参数)-> 这里为表达式
        //右按钮
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(R.string.button2);
            }
        });


        //switch设置
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text.setText("开");
                } else text.setText("关");
            }
        });


        //number输入设置
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取number
                String s = number.getText().toString();
                //判断输入的字符是否为空
                if (TextUtils.isEmpty(s)) {
                    s = "0";
                }
                progressBar.setProgress(Integer.parseInt(s));//传入参数用到包装类,第二个参数，true表示有过渡动画（有版本限制），可以不填
            }
        });


        //radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton) {
                    imageView.setImageResource(R.drawable.logo_android);
                } else {
                    imageView.setImageResource(R.drawable.apple);
                }
            }
        });


        //seekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //过程改变时
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //语文checkBox
        chinese.setOnCheckedChangeListener((buttonView, isChecked) ->
                {
                    if (isChecked) yuWen = getString(R.string.checkbox1);
                    else yuWen = "";
                    text.setText(yuWen + shuXue + yingYu);
                }
        );
        //数学box
        math.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shuXue = getString(R.string.checkbox2);
                } else shuXue = "";
                text.setText(yuWen + shuXue + yingYu);
            }
        });
        //英语
        english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yingYu = getString(R.string.checkbox3);
                } else yingYu = "";
                text.setText(yuWen + shuXue + yingYu);
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), rating + "星", Toast.LENGTH_SHORT).show();//弹窗显示
            }
        });

        Log.d(TAG, "onCreate");
    }


    //临时保存数据（如屏幕反转），不能永久保存
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        number=findViewById(R.id.editTextNumber);
        outState.putString("number",number.getText().toString());
    }

}