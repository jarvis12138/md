

### [ 面向对象的程序设计 ](#面向对象的程序设计)

### [ 算法设计模式http ](#算法设计模式http)

### [ 柯里化 ](#柯里化)

### [ 防抖与节流 ](#防抖与节流)

### [ 懒加载瀑布流 ](#懒加载瀑布流)

### [ Flex布局教程阮一峰 ](#Flex布局教程阮一峰)

### [ Vue.js 技术揭秘 ](#Vuejs技术揭秘)

### [ 前端性能优化 ](#前端性能优化)

### [ 浏览器缓存 ](#浏览器缓存)

### [ 大厂面试 ](#大厂面试)

### [ 面试题 ](#面试题)


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


### Flex布局教程阮一峰

[Flex布局教程阮一峰](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)


### Vuejs技术揭秘

[https://ustbhuangyi.github.io/vue-analysis/components/lifecycle.html#%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F](https://ustbhuangyi.github.io/vue-analysis/components/lifecycle.html#%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)

[http://hcysun.me/vue-design/art/3vue-example.html](http://hcysun.me/vue-design/art/3vue-example.html)


### 前端性能优化

[https://juejin.im/post/5c60ed6cf265da2dd4274724](https://juejin.im/post/5c60ed6cf265da2dd4274724)

[大公司里怎样开发和部署前端代码](https://www.zhihu.com/question/20790576/answer/32602154)


### 浏览器缓存

[彻底弄懂HTTP缓存机制及原理](https://www.cnblogs.com/chenqf/p/6386163.html)

[浅谈浏览器缓存](https://segmentfault.com/a/1190000012573337)


### 大厂面试

头条面试一、二、三   [https://www.cnblogs.com/QH-Jimmy/p/9986775.html](https://www.cnblogs.com/QH-Jimmy/p/9986775.html) [https://www.cnblogs.com/QH-Jimmy/p/9986821.html](https://www.cnblogs.com/QH-Jimmy/p/9986821.html) [https://www.cnblogs.com/QH-Jimmy/p/10004060.html](https://www.cnblogs.com/QH-Jimmy/p/10004060.html)

[前端面试汇总1](https://juejin.im/post/5c62b92de51d457fd77b22ce)

[前端面试汇总2](https://juejin.im/post/5c64d15d6fb9a049d37f9c20)

[【面经】寒冬中的一年半前端跳槽](https://zhuanlan.zhihu.com/p/54397576)


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
```

数组去重：

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















