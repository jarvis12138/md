


```css
  .box{padding:10px; background:gray;}
  .fix{*zoom:1;}
  .fix:after{display:block; content:"clear"; height:0; clear:both; overflow:hidden; visibility:hidden;}
  .l{float:left;}
```

```html
  <div class="box fix">
    <img class="l" src="http://image.zhangxinxu.com/image/study/s/s256/mm1.jpg" />
  </div>
```
