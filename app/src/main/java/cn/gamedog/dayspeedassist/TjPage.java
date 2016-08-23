package cn.gamedog.dayspeedassist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.LayoutRipple;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.adapter.KapaIPageFragmentAdapter;
import cn.gamedog.dayspeedassist.nucleus.NucleusAppCompatActivity;
import cn.gamedog.dayspeedassist.views.JazzyViewPager;
import cn.gamedog.dayspeedassist.views.PagerSlidingTabStrip;

public class TjPage extends AppCompatActivity {

    @Bind(R.id.close_btn)
    ImageView closeBtn;
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
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @Bind(R.id.pager)
    JazzyViewPager pager;
    private KapaIPageFragmentAdapter adapter;
    private DisplayMetrics dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tj_page);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        adapter = new KapaIPageFragmentAdapter(getSupportFragmentManager());

        intview();
        setTabsValue();


    }


    private void intview(){
        dm = getResources().getDisplayMetrics();
      pager.setTransitionEffect(JazzyViewPager.TransitionEffect.Tablet);
       pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(8);
////搜索
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searched.setText("");
                searched.setHint("请输入搜索关键字");

            }
        });

        // 输入框获取焦点时hint消失
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

        // 搜索确定按钮
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = searched.getText().toString().trim();

                if (s.equals("")) {
                  //  ToastUtils.show(YXTJPage.this, "请输入搜索关键词");
                    return;
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                boolean isOpen = imm.isActive();// isOpen若返回true，则表示输入法打开
                if (isOpen) {
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

//                Intent intent = new Intent(YXTJPage.this, SearchPage.class);
//                intent.putExtra("typeid", 3455);
//                intent.putExtra("type", "typeid");
//                intent.putExtra("result", s);
//                startActivity(intent);

            }
        });
        //返回
        modeBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    /**
     * 关键字UTF-8编码
     *
     * @param keyword 关键字
     */
    private String initEncode(String keyword) {
        String keywordEncode = "";
        try {
            keywordEncode = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("", "");
        }

        return keywordEncode;
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.parseColor("#00000000"));
        // 设置Tab底部线的高度
        tabs.setTextColor(0xff9e9e9e);
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, dm));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#ff9e9e9e"));
//		tabs.setIndicatorHeight(3);
        tabs.setSelectedTextColor(Color.parseColor("#ffffffff"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

}
