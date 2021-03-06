

### [ 面向对象的程序设计 ](#面向对象的程序设计)

### [ 算法设计模式http ](#算法设计模式http)

### [ 柯里化 ](#柯里化)

### [ 防抖与节流 ](#防抖与节流)

### [ 懒加载瀑布流 ](#懒加载瀑布流)

### [ promise ](#promise)

### [ Flex布局教程阮一峰 ](#Flex布局教程阮一峰)

### [ Vue.js 技术揭秘 ](#Vuejs技术揭秘)

### [ 前端性能优化 ](#前端性能优化)

### [ 缓存 ](#缓存)

### [ 浏览器缓存 ](#浏览器缓存)

### [ 大厂面试 ](#大厂面试)

### [ 面试题 ](#面试题)

### [ 图书 ](#图书)

### [ 图片压缩 ](#图片压缩)

### [ 从输入URL到页面加载发生了什么 ](#从输入URL到页面加载发生了什么)

### [ 深入浅出Webpack ](#深入浅出Webpack)

### [ jquery源码 ](#jquery源码)

### [ markdown-it ](#markdown-it)


```
格式： [最新一期](/content/34/HelloGitHub34.md)
```










### 面向对象的程序设计

《JavaScript高级程序设计》第六章 面向对象的程序设计   **重点**

深入JavaScript系列 博客园 汤姆大叔   [https://www.cnblogs.com/TomXu/archive/2011/12/15/2288411.html](https://www.cnblogs.com/TomXu/archive/2011/12/15/2288411.html)


### 柯里化

[柯里化](https://github.com/jarvis12138/blog/blob/b93379e7283e0859a17396d3ebbc3162aaa6e023/question/%E9%9D%A2%E8%AF%95%E9%A2%98%EF%BC%9A%E5%87%BD%E6%95%B0%E6%9F%AF%E9%87%8C%E5%8C%96.md)


### 防抖与节流

[防抖](https://github.com/mqyqingfeng/Blog/issues/22)

[节流](https://github.com/mqyqingfeng/Blog/issues/26)


### 懒加载瀑布流

### promise

[剖析Promise内部结构](https://github.com/xieranmaya/blog/issues/3)   [知乎地址](https://zhuanlan.zhihu.com/p/21834559)

[Q解析](https://div.io/topic/1351)   [Q解析](https://juejin.im/entry/599968f6518825244630f809)

[https://promisesaplus.com/](https://promisesaplus.com/)   [https://www.promisejs.org/implementing/](https://www.promisejs.org/implementing/)

[Promise 对象-阮一峰](http://es6.ruanyifeng.com/#docs/promise)

### Flex布局教程阮一峰

[Flex布局教程阮一峰](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)


### Vuejs技术揭秘

[https://ustbhuangyi.github.io/vue-analysis/components/lifecycle.html#%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F](https://ustbhuangyi.github.io/vue-analysis/components/lifecycle.html#%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)

[http://hcysun.me/vue-design/art/3vue-example.html](http://hcysun.me/vue-design/art/3vue-example.html)


### 前端性能优化

[https://juejin.im/post/5c60ed6cf265da2dd4274724](https://juejin.im/post/5c60ed6cf265da2dd4274724)

[大公司里怎样开发和部署前端代码](https://www.zhihu.com/question/20790576/answer/32602154)

### 缓存

[客户端缓存](https://juejin.im/entry/58c3d451da2f6056096b0dd6)

[缓存机制](https://juejin.im/entry/5ad86c16f265da505a77dca4#comment)

### 浏览器缓存

[彻底弄懂HTTP缓存机制及原理](https://www.cnblogs.com/chenqf/p/6386163.html)

[浅谈浏览器缓存](https://segmentfault.com/a/1190000012573337)


### 大厂面试

头条面试一、二、三   [https://www.cnblogs.com/QH-Jimmy/p/9986775.html](https://www.cnblogs.com/QH-Jimmy/p/9986775.html) [https://www.cnblogs.com/QH-Jimmy/p/9986821.html](https://www.cnblogs.com/QH-Jimmy/p/9986821.html) [https://www.cnblogs.com/QH-Jimmy/p/10004060.html](https://www.cnblogs.com/QH-Jimmy/p/10004060.html)

[前端面试汇总1](https://juejin.im/post/5c62b92de51d457fd77b22ce)

[前端面试汇总2](https://juejin.im/post/5c64d15d6fb9a049d37f9c20)

[【面经】寒冬中的一年半前端跳槽](https://zhuanlan.zhihu.com/p/54397576)

[https://yuchengkai.cn/docs/frontend/#%E5%86%85%E7%BD%AE%E7%B1%BB%E5%9E%8B](https://yuchengkai.cn/docs/frontend/#%E5%86%85%E7%BD%AE%E7%B1%BB%E5%9E%8B)

[一年半经验，百度、有赞、阿里前端面试总结](https://juejin.im/post/5befeb5051882511a8527dbe) [备份](https://segmentfault.com/a/1190000017049146)


### 面试题

考察闭包：

```javascript
var x = 20;
var a = {
    x : 15,
    fn : function(){
        var x = 30;
        return function(){
            return this.x;
        };
    }
};
console.log(a.fn());
console.log((a.fn())());
console.log(a.fn()());
console.log(a.fn()() == (a.fn())());
console.log(a.fn().call(this));
console.log(a.fn().call(a));

var x = 20;
var a = {
    x : 15,
    fn : function(){
        var x = 30;
        return this.x;
    }
};
console.log(a.fn());
```

数组去重：

[数组去重](https://github.com/mqyqingfeng/Blog/issues/27)

```javascript
var arr = ['a','g','q','d','a','e','q'];
Array.prototype.unique = function(){
    for(var i = 0; i < this.length; i++){
        for(var j = i+1; j < this.length; j++){
            if(this[i] == this[j]){
                this.splice(j,1);
            }
        }
    }
    return this;
};
console.log(arr.unique());
```

编写一个函数fn(Number n),将数字转为大写输出，如输入123，输出一百二十三。

```javascript
function fn(n){
    if(!/^([1-9]\d*)/.test(n)){
        return '非法数据';
    }
    var unit = '千百十亿千百十万千百十个';
    if(n.length > unit.length){
        return '数据过长';
    }
    var newStr = '';
    var nlength = n.length;
    unit = unit.substr(unit.length - nlength);
    for(var i = 0; i < nlength; i++){
        newStr += '零一二三四五六七八九'.charAt(n[i]) + unit.charAt(i);
    }
    newStr = newStr.substr(0,newStr.length-1);
    newStr = newStr.replace(/零(千|百|十)/g,'零').replace(/(零)+/g,'零').replace(/零(亿|万)/g,'$1');
    return newStr;
}
console.log(fn('205402002103'));
```

[一次弄懂Event Loop（彻底解决此类面试问题）](https://zhuanlan.zhihu.com/p/55511602)
[一次弄懂Event Loop（彻底解决此类面试问题）](https://juejin.im/post/5c3d8956e51d4511dc72c200?utm_source=gold_browser_extension)


### 图书

《JavaScript权威指南》、《JavaScript算法与数据结构》、《JavaScript设计模式与开发实践》、《你不知道的JavaScript》、《HTTP权威指南》

### 图片压缩

[https://github.com/think2011/localResizeIMG](https://github.com/think2011/localResizeIMG)

[https://github.com/stomita/ios-imagefile-megapixel/blob/master/src/megapix-image.js](https://github.com/stomita/ios-imagefile-megapixel/blob/master/src/megapix-image.js)

[https://www.zhihu.com/question/30692677](https://www.zhihu.com/question/30692677)

[https://www.zhangxinxu.com/wordpress/2017/07/html5-canvas-image-compress-upload/](https://www.zhangxinxu.com/wordpress/2017/07/html5-canvas-image-compress-upload/)

nginx html 文件 304 缓存问题：

[https://github.com/ant-design/ant-design-pro/issues/1365](https://github.com/ant-design/ant-design-pro/issues/1365)

```
# 前端静态文件
location ~* \.(gif|jpg|jpeg|png|css|js|ico|eot|otf|fon|font|ttf|ttc|woff|woff2)$ {
    root /var/www/example-fe/dist/;
}

# 前端html文件
location / {
    # disable cache html
    add_header Cache-Control 'no-cache, must-revalidate, proxy-revalidate, max-age=0';

    root /var/www/example-fe/dist/;
    index index.html index.htm;
    try_files $uri /index.html;
}
```

由于浏览器缓存静态文件的时间不可控，我们可以在nginx上自己配置expires 1M（1个月）

```
# 前端静态文件
location ~* \.(gif|jpg|jpeg|png|css|js|ico|eot|otf|fon|font|ttf|ttc|woff|woff2)$ {
    root /var/www/example-fe/dist/;
    expires 1M;
    add_header Cache-Control "public";
}
```

### 从输入URL到页面加载发生了什么

[https://segmentfault.com/a/1190000006879700](https://segmentfault.com/a/1190000006879700)

[https://www.zhihu.com/question/34873227/answer/518086565](https://www.zhihu.com/question/34873227/answer/518086565)

### 深入浅出Webpack

[http://webpack.wuhaolin.cn/](http://webpack.wuhaolin.cn/)

### jquery源码

[css 源码](https://www.cnblogs.com/chyingp/archive/2012/08/12/jquery-css.html)

[css 源码](http://www.cnblogs.com/nuysoft/archive/2011/12/26/2297923.html)

[css 源码](http://www.webfront-js.com/articaldetail/13.html)

### markdown-it

[使用 markdown-it 解析 markdown 代码（读 vuepress 三）
](https://zhuanlan.zhihu.com/p/46355549)

[谈谈 Element 文档中的 Markdown 解析](https://zhuanlan.zhihu.com/p/65174076)









