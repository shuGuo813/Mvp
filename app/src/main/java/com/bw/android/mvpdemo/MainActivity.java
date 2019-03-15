package com.bw.android.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.android.mvpdemo.entity.getDataBean;
import com.bw.android.mvpdemo.presenter.BookPresenter;
import com.bw.android.mvpdemo.view.BookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.btn)
    Button btn;
    private BookPresenter bookPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        bookPresenter = new BookPresenter(this,"https://api.douban.com/v2/");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookPresenter.getSearchBooks("金瓶梅",null,0,1);
            }
        });

        bookPresenter.onCreate();
        bookPresenter.attachView(bookView);
    }
    private BookView bookView = new BookView() {
        @Override
        public void onSuccess(getDataBean getDataBean) {
            text.setText(getDataBean.toString());
        }

        @Override
        public void onError(String error) {
            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
        }
    };
}
