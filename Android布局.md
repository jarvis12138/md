
#### [布局](https://github.com/iiijarvis/w/blob/master/md/Android%E5%B8%83%E5%B1%80.md#linearlayout-布局)
#### [Activity跳转和传值](https://github.com/iiijarvis/w/blob/master/md/Android%E5%B8%83%E5%B1%80.md#activity跳转和传值-1)
#### [RecycleView实现](https://github.com/iiijarvis/w/blob/master/md/Android%E5%B8%83%E5%B1%80.md#recycleview实现-1)

### LinearLayout 布局

```
orientation:   horizontal | vertical   //LinearLayout
gravity:   center                      //决定其子类xy的位置
layout_gravity:   center               //子类属性，确定相对于父类位置
layout_weight:  1                      //子类属性，确定相对于父类比例
```

### RelativeLayout

```
layout_alignParentLeft:    true        //子类控件，相对于父类容器靠左边
layout_centerInParent:    true         //子类控件，相对于父类容器水平、垂直居中
layout_centerHorizontal:   true        //子类控件，相对于父类水平居中

layout_below:   id                     //子类控件，相对于兄弟子类的底部
layout_above:   id                     //子类控件，相对于兄弟子类的顶部
layout_toLeftOf | layout_toRightOf :   id //子类控件，相对于兄弟子类的左、右
layout_alignBottom | layout_alignTop | layout_alignLeft | layout_alignRight   //子类控件，控件边缘相对于给定兄弟子类id边缘对齐
```

### Activity跳转和传值
```
新建first.xml 和 second.xml
first.xml : BUTTON id为btn1 ， TEXTVIEW id为txt1
second.xml : BUTTON id为btn2 ， TEXTVIEW id为txt2 
```

```
// 新建First.java 和 Second.java 
// 两个都创建onCreate方法

// First.java
btn1 = findViewById(R.id.btn1);
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(First.this, Second.class);
        startActivityForResult(intent, 1);
    }
});

// Second.java 
btn2 = findViewById(R.id.btn2);
btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("data", "msgsggg");
        setResult(2, intent);
        finish();
    }
});

// First.java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1 && resultCode == 2){
        String s = data.getStringExtra("data");
        Toast.makeText(First.this, s, Toast.LENGTH_SHORT).show();
    }
}
```


```
// 在\AndroidManifest.xml中添加first.xml 和 second.xml 如：
<activity
    android:name="com.example.admin.test.First"
    android:label="@string/app_name"
    android:theme="@style/AppTheme.NoActionBar">

</activity>
```

### RecycleView实现
```
// 创建recycle_view.xml 
<android.support.v7.widget.RecyclerView
    android:id="@+id/clv_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

</android.support.v7.widget.RecyclerView>

// 创建 JavaBean.java
public class JavaBean {
    String name;
    int age;

    public JavaBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// 创建RecycleView.java
private SwipeRefreshLayout swipeRefreshLayout;
private RecyclerView recyclerView;
private DemoAdapter demoAdapter;
private RecyclerView.LayoutManager mlayoutManager;
private List<JavaBean> mBeen = new ArrayList<JavaBean>();
private final String NAME = "普朗特";

private void init() {
    for (int i = 0; i < 50; i++) {
        JavaBean javaBean = new JavaBean(NAME + i, i);
        mBeen.add(javaBean);
    }
}


setContentView(R.layout.recycle_view);
init();
recyclerView = findViewById(R.id.clv_content);
recyclerView.setAdapter(new DemoAdapter(mBeen));
recyclerView.setLayoutManager(new LinearLayoutManager(RecycleView.this));


// 创建 DemoAdapter.java

public class DemoAdapter extends RecyclerView.Adapter<DemoHolder> {

    private LayoutInflater layoutInflater;
    private List<String> list = new ArrayList<String>();
    private String[] mData;
    private List<JavaBean> mBeen;

    public DemoAdapter(List<JavaBean> been) {
        mBeen = been;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.refresh_activity, null);
        return new DemoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        holder.bindData(mBeen.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeen.size();
    }
}


// 创建 DemoHolder.java
public class DemoHolder extends RecyclerView.ViewHolder {

    public TextView txt4;

    public DemoHolder(View itemView) {
        super(itemView);
        txt4 = itemView.findViewById(R.id.txt4);
    }

    public void bindData(JavaBean bean) {
        txt4.setText(bean.name);
    }
}

```

### animation弹跳小球
```
imageView = findViewById(R.id.image);
TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 200);
translateAnimation.setDuration(1000);
translateAnimation.setFillAfter(true);
translateAnimation.setInterpolator(new BounceInterpolator());//弹跳动画,要其它效果的当然也可以设置为其它的值
imageView.startAnimation(translateAnimation);
```



