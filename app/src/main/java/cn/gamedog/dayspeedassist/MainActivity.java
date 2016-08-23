package cn.gamedog.dayspeedassist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.LayoutRipple;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.gamedog.dayspeedassist.adapter.ImagePagerAdapter;
import cn.gamedog.dayspeedassist.adapter.MyFragmentPagerAdapter;
import cn.gamedog.dayspeedassist.bean.BaseData;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.fragment.GudegFragmentone;
import cn.gamedog.dayspeedassist.fragment.GudgeFragmentwo;
import cn.gamedog.dayspeedassist.fragment.TjFragment;
import cn.gamedog.dayspeedassist.http.api.HttpUtils;
import cn.gamedog.dayspeedassist.nucleus.NucleusAppCompatActivity;
import cn.gamedog.dayspeedassist.presenter.Mainpresenter;
import cn.gamedog.dayspeedassist.utils.ListUtils;
import cn.gamedog.dayspeedassist.views.AutoScrollViewPager;
import cn.gamedog.dayspeedassist.views.JazzyViewPager;
import nucleus.factory.RequiresPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
@RequiresPresenter(Mainpresenter.class)
public class MainActivity extends NucleusAppCompatActivity<Mainpresenter> {


    @Bind(R.id.mode_btn)
    ImageView modeBtn;
    @Bind(R.id.mode_btn_layout)
    LayoutRipple modeBtnLayout;
    @Bind(R.id.searched)
    EditText searched;
    @Bind(R.id.cancle)
    ImageView cancle;
    @Bind(R.id.btn_search)
    ButtonFlat btnSearch;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tuijian_pager)
    AutoScrollViewPager tuijianPager;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.guide_pager)
    JazzyViewPager guidePager;
    @Bind(R.id.img_one)
    ImageView imgOne;
    @Bind(R.id.img_two)
    ImageView imgTwo;
    @Bind(R.id.lin_guide)
    LinearLayout linGuide;
    @Bind(R.id.huand)
    RelativeLayout huand;
    @Bind(R.id.radio0)
    RadioButton radio0;
    @Bind(R.id.radio1)
    RadioButton radio1;
    @Bind(R.id.radio2)
    RadioButton radio2;
    @Bind(R.id.radio3)
    RadioButton radio3;
    @Bind(R.id.radio4)
    RadioButton radio4;
    @Bind(R.id.radioGroup1)
    RadioGroup radioGroup1;

    /**
     * 应用主界面
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loadlisten();
        loadGuidePage();
        if (savedInstanceState == null)
           getPresenter().request(Mainpresenter.DEFAULT_NAME);


    }


    private void loadlisten() {
        searched.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (!content.equals("")) {
                    cancle.setVisibility(View.VISIBLE);
                } else {
                    cancle.setVisibility(View.GONE);
                }
            }
        });

        // searched获取焦点时hint消失
        searched.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {// 失去焦点
                    searched.setHint(searched.getTag().toString());
                } else {
                    String hint = searched.getHint().toString();
                    searched.setTag(hint);
                    searched.setHint("");
                }
            }
        });
    }

    @OnClick(R.id.mode_btn)
     void modeclick(){

        Toast.makeText(getApplicationContext(),"mode",1).show();

    }

    @OnClick(R.id.cancle)
    void cancleclick() {

        searched.setText("");
    }


    @OnClick(R.id.btn_search)
    void searchclick() {
        Toast.makeText(getApplicationContext(), "search", 1).show();
    }


    public void setBannerData(List<Itemdata> list) {


        initData(list);


    }

    /**
     * 图片加载
     */
    private void initData(final List<Itemdata> list) {
        // 设置自动滚动
        tuijianPager.startAutoScroll();
        // 创建image
        tuijianPager.setAdapter(new ImagePagerAdapter(MainActivity.this, list)
                .setInfiniteLoop(true));
        // 设置自动滚动的间隔时间，单位为毫秒
        tuijianPager.setInterval(5000);
        // pager.setDirection(AutoScrollViewPager.RIGHT);//设置自动滚动的方向，默认向右
        // pager.setCycle(false);//是否自动循环轮播，默认为true
        tuijianPager.setStopScrollWhenTouch(true);// 当手指碰到ViewPager时是否停止自动滚动，默认为true
        tuijianPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch ((position) % ListUtils.getSize(list) + 1) {
                    case 1:
                        radioGroup1.check(R.id.radio0);
                        break;
                    case 2:
                        radioGroup1.check(R.id.radio1);
                        break;
                    case 3:
                        radioGroup1.check(R.id.radio2);
                        break;
                    case 4:
                        radioGroup1.check(R.id.radio3);
                        break;
                    case 5:
                        radioGroup1.check(R.id.radio4);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }


    public  void loadGuidePage(){
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new GudegFragmentone());
     //   fragmentList.add(new GudegFragmentone());
        fragmentList.add(new GudgeFragmentwo());
        guidePager.setTransitionEffect(JazzyViewPager.TransitionEffect.ZoomIn);
        guidePager.setAdapter(new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentList));

        guidePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                if (arg0 == 0) {
                    imgOne.setImageResource(R.mipmap.guide_point_norm);
                    imgTwo.setImageResource(R.mipmap.guide_point_selct);
                } else {
                    imgTwo.setImageResource(R.mipmap.guide_point_norm);
                    imgOne.setImageResource(R.mipmap.guide_point_selct);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }



}
