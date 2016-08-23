package cn.gamedog.dayspeedassist.nucleus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.utils.AppManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged()  ;

        // 绑定 ButterKnife 服务
        ButterKnife.bind(this);
    }

    protected void startNewActivity(Class clz) {
        startNewActivity(new Intent(this, clz));
    }

    protected void startNewActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}
