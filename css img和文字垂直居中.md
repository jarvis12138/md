

```html
<div class="inner_box">
    <img src="http://www.zengkan.win/wp-content/uploads/2016/12/QQ图片20161202174828.jpg" alt="新垣结衣">
    <span class="s">1564548</span>
</div>
```

css样式：

```css
.inner_box {
  width: 600px;
  height: 300px;
  font-size: 0px;
  text-align: center;
  line-height: 300px;
  background-color: #e6e6e6;
}

.s {
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
}

.inner_box img {
  vertical-align: middle;
  height: 18px;
}
```

![ ](https://github.com/lao42981894zh/w/blob/master/md/img/QQ%E6%88%AA%E5%9B%BE20170704135711.png " ")
![ ](https://github.com/lao42981894zh/w/blob/master/md/img/QQ%E6%88%AA%E5%9B%BE20170704131243.png " ")


另外一种：

```css
    .pic_box{width:300px; height:300px; background-color:#beceeb; font-size:0; *font-size:200px; text-align:center;}
    .pic_box img{vertical-align:middle;}
    .pic_box:after{display:inline-block; width:0; height:100%; content:"center"; vertical-align:middle; overflow:hidden;}
```

```html
    <div class="pic_box">
        <img src="http://image.zhangxinxu.com/image/study/s/s256/mm1.jpg" />
    </div>
```

