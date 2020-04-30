//package com.example.bourbon.activities.harish_activities;
//
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import com.example.bourbon.R;
//import com.example.bourbon.activities.clement_activities.Main2Activity;
//import com.example.bourbon.activities.clement_activities.Startact;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import frame_transition.Transition;
//import print.Print;
//
//public class MainActivity extends AppCompatActivity {
//
//    @BindView(R.id.GeoFencing)
//    CardView GeoFencing;
//    @BindView(R.id.btn2)
//    CardView btn2;
//    @BindView(R.id.btn3)
//    CardView btn3;
//    @BindView(R.id.btn4)
//    CardView btn4;
//    private Print p;
//    private Transition transition;
//
//    void init() {
//        p = new Print(MainActivity.this);
//        transition = new Transition(MainActivity.this);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        init();
//        p.sprintf("Hello");
//    }
//
//    @OnClick({R.id.GeoFencing, R.id.btn2, R.id.btn3, R.id.btn4})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.GeoFencing:
//                transition.goTo(com.example.bourbon.activities.arumugam_activities.MainActivity.class);
//                p.sprintf("Aru Success");
//                break;
//            case R.id.btn2:
//                transition.goTo(Startact.class);
//                p.fprintf("Clement Success");
//                break;
//            case R.id.btn3:
//                break;
//            case R.id.btn4:
//                break;
//        }
//    }
//
//
//
//}
