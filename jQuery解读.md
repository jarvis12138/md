


### 关于(function( window, undefined ) {})(window)
(function( window, undefined ) {})(window);
这个，为什么要将window和undefined作为参数传给它？

因为 ecmascript 执行JS代码是从里到外，因此把全局变量window或jQuery对象传进来，就避免了到外层去寻找，提高效率。undefined在老一辈的浏览器是不被支持的，直接使用会报错，js框架要考虑到兼容性，因此增加一个形参undefined。

还有，不要用window.undefined传递给形参，有可能window.undefined被其他人修改了，最好就是甚么都不传，形参的undefined就是真正的undefined了。

```javascript
var undefined = 8;  
(function( window ) {   
    alert(window.undefined); // 8  
    alert(undefined); // 8  
})(window);  
```

```javascript
var undefined = 8;  
(function( window, undefined ) {   
    alert(window.undefined);  // 8  
    alert(undefined); // 此处undefined参数为局部的名称为undefined变量，值为undefined  
})(window);  
```

区分上述两种方式：第一种每个语句都要去找一次window。第二种将window作为参数传递过去，是的不要每个语句都去找window，应该是提高了效率。 所以后者，就算外面的人把 undefined 定义了，里面的 undefined 依然不受影响。大概是为了最大程度防止外界的变量定义对所做封装的内部造成影响吧。

```javascript
//方式一  
(function(undefined ) {  
   window.property1 = ……;  
   window.property2 = ……;  
   ……  
})();  
//方式二  
(function( window, undefined ) {  
    ... // code goes here  
  
})(window);  
//方式三  
(function(undefined ) {  
   var tmp = window;  
   tmp.property1 = ……;  
   tmp.property2 = ……;  
   ……  
})();  
```

方式一的效率明显最低，方式二和方式三应该差不多。将window作为参数传递进去就可以让代码里面的语句可以直接用参数中的window，而不用再去找最外层的对象。假如要在函数中为window再设置 100000个属性，用参数传递过去只需要找一次最外层对象。不用参数传递，用到window的语句都要去找一次最外层对象。


[isEmptyObject](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L532)

[inArray](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L687)

[style](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L6208)

[css](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L6259)

### addClass、removeClass、toggleClass
[addClass](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L1911)
```js

addClass: function( value ) {
    if ( jQuery.isFunction( value ) ) {   // 如果value是函数
        return this.each(function(i) {
            var self = jQuery(this);
            self.addClass( value.call(this, i, self.attr("class") || "") );
        });
    }

    if ( value && typeof value === "string" ) {  // value 是string 
        var classNames = (value || "").split( rspace );

        for ( var i = 0, l = this.length; i < l; i++ ) {
            var elem = this[i];

            if ( elem.nodeType === 1 ) {
                if ( !elem.className ) {   // 如果没有class值，则直接添加 
                    elem.className = value;

                } else {            // 如果有class值，则去重添加新的     
                    var className = " " + elem.className + " ",
                        setClass = elem.className;

                    for ( var c = 0, cl = classNames.length; c < cl; c++ ) {
                        if ( className.indexOf( " " + classNames[c] + " " ) < 0 ) {
                            setClass += " " + classNames[c];
                        }
                    }
                    elem.className = jQuery.trim( setClass );
                }
            }
        }
    }

    return this;
},

```

### trim
```
去除字符串首尾两端的空白字符串
```
```js
trimLeft = /^\s+/,
trimRight = /\s+$/,
text.toString().replace( trimLeft, "" ).replace( trimRight, "" );
```
在代码[第47行](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#47)和[第652行](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L652)
但服务端某个配置文件编码是 UTF-8 + BOM
```js
<script>
var a = '﻿{"a":1}';
try {
    JSON.parse(a);
} catch(e) {
    alert(e.message);
}
</script>
```
[在第889行](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#L889)
添加了
```js
trimLeft = /^[\s\xA0]+/;
trimRight = /[\s\xA0]+$/;
```
[可参考](https://imququ.com/post/bom-and-javascript-trim.html)

### offset()
获取元素距离文档顶部距离：
```
getBoundingClientRect() 返回的对象包含left/top属性分别代表元素在x/y坐标轴上距离文档左上角的距离。(top不包含margin，包含border、padding)（需要注意的是IE6、7 获取top时会+2px，并且还会有一些其他问题，这里不予考虑，测试都是IE8及其以上版本）
获取文档的滚动距离方面，在IE9+浏览器及其它现代浏览器中，使用window.pageXOffset 和 window.pageYOffset 可以达到目的。对于IE8-，如果文档在混杂模式下，则document.body.scrollLeft(Top)为所求，此时，通过document.documentElement.scrollLeft(Top)获取的值为0；否则，document.documentElement.scrollLeft(Top) 为所求，此时通过document.body.scrollLeft(Top)获取的值为0。
```
```html
// css
html, body{
    margin: 0;
    padding: 0;
}

// body
<div style="margin: 10px; border: 10px solid #ccc; padding: 10px; ">
    <div style="position: relative; margin: 10px; border: 10px solid #ccc; padding: 10px; ">
        <div id="box" style="margin: 10px; border: 10px solid #ccc; padding: 10px; width: 100px; height: 1000px;; background: red;"></div>
    </div>
</div>

<script>
    
    window.onscroll = function(){
        var box = document.getElementById('box');
        var html = document.documentElement,
        body = document.body;

        var scrollTop = window.pageYOffset || html.scrollTop || body.scrollTop;
        
        var top = box.getBoundingClientRect().top;

        console.log(scrollTop);
    };

</script>

```

### offsetLeft
```
offsetLeft 指的是一个元素最近的父级定位元素，如果没有定位元素就是文档根节点。
值为： 父元素padding+自己的margin
```

### 获取滚动条高度
[在第8765行](https://github.com/jarvis12138/md/blob/master/jQuery.v1.6.js#8765)
```js
 win ? ("pageXOffset" in win) ? win[ i ? "pageYOffset" : "pageXOffset" ] :
                    jQuery.support.boxModel && win.document.documentElement[ method ] ||
                        win.document.body[ method ] :
                    elem[ method ];
```
```
IE6/7/8：
doctype：
window.pageYOffset：undefined
document.documentElement.scrollTop:100
document.body.scrollTop：0
无doctype：
window.pageYOffset：undefined
document.documentElement.scrollTop:0
document.body.scrollTop：100
Safari/Chrome:
window.pageYOffset：100
document.documentElement.scrollTop:0
document.body.scrollTop：100

Firefox/Opera:

doctype：
window.pageYOffset：100
document.documentElement.scrollTop:100
document.body.scrollTop：0

无doctype：
window.pageYOffset：100
document.documentElement.scrollTop:0
document.body.scrollTop：100

获取scrollTop 赋值简写 ：var scrollTop = window.pageYOffset|| document.documentElement.scrollTop || document.body.scrollTop;

```

### offsetTop,scrollTop,clientTop区别

```
1.offsetTop     :

当前对象到其上级层顶部的距离.

不能对其进行赋值.设置对象到页面顶部的距离请用style.top属性.

2.offsetLeft : <父级的padding+本级的margin>

当前对象到其上级层左边的距离.

不能对其进行赋值.设置对象到页面左部的距离请用style.left属性.

3.offsetWidth :<width+左右border+左右padding>

当前对象的宽度.

与style.width属性的区别在于:如对象的宽度设定值为百分比宽度,则无论页面变大还是变小,style.width都返回此百分比,而offsetWidth则返回在不同页面中对象的宽度值而不是百分比值

4.offsetHeight :

当前对象的高度。

与style.height属性的区别在于:如对象的宽度设定值为百分比高度,则无论页面变大还是变小,style.height都返回此百分比,而offsetHeight则返回在不同页面中对象的高度值而不是百分比值

5.offsetParent :

当前对象的上级层对象.

注意.如果对象是包括在一个DIV中时,此DIV不会被当做是此对象的上级层,(即对象的上级层会跳过DIV对象)上级层是Table时则不会有问题.

6.scrollLeft :

对象的最左边，到对象在当前窗口显示的范围内，的左边的距离．

即是在出现了横向滚动条的情况下，滚动条拉动的距离．

7.scrollTop：

对象的最顶部，到对象在当前窗口显示的范围内，的顶边的距离．

即是在出现了纵向滚动条的情况下，滚动条拉动的距离．

8.scrollHeight：
滚动条的高度

9.scrollWidth：

滚动条的宽度

10.clientLeft：

左侧边框的大小，即(offsetWidth-clientWidth)/2

11.clientTop：

12.clientWidth： <width+左右padding>

13.clientHeight：

大家对 clientHeight 都没有什么异议，都认为是内容可视区域的高度，也就是说页面浏览器中可以看到内容的这个区域的高度，一般是最后一个工具条以下到状态栏以上的这个区域，与页面内容无关。



二、offset操作
1、offsetParent:  元素的定位祖先元素，如果没有则为body;     (IE6~IE7有所区别：haslayout、html有关)
2、offsetLeft、offsetTop: 元素的边框到定位祖先元素的距离
3、offsetWidth:可肉眼看到元素的宽度
4、clientWidth: offsetWidth减去边框的大小
5、getBoundingClientRect().top ：元素到可视范围的距离
6、掌握获取元素到浏览器边框的距离：制作成函数，方便后期使用

三、scroll操作 （低版本浏览器没有滚动）
1、body滚动距离：

window.pageYOffset：    【获取】     <!低版本IE不支持>
document.documentElement.scrollTop:     【设置+获取】  chrome不支持(其他浏览器的方法) 
document.body.scrollTop                 【设置+获取】chrome支持(其他浏览器支持) 
2、元素的滚动距离：
   元素.scrolltop
3、滚动条的宽度、高度：
  scrollWidth、scrollHeight
```



