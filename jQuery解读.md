


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


[isEmptyObject](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L532)

[inArray](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L687)

[style](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L6208)

[css](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L6259)

### addClass、removeClass、toggleClass
[addClass](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L1911)
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
在代码[第47行](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#47)和[第652行](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L652)
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
[在第889行](https://github.com/iiijarvis/w/blob/master/md/jQuery.v1.6.js#L889)
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







