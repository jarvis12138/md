

### 如何在前端网页中使用markdown编辑
选用js： [markdown.js](https://github.com/evilstreak/markdown-js)

从这里下载其浏览器版：[https://github.com/evilstreak/markdown-js/releases](https://github.com/evilstreak/markdown-js/releases)
(下载这个文件： markdown-browser-0.6.0-beta1.tgz）

解压缩后在其js文件同目录下新建一个网页进行测试，代码如下：
```html
<!DOCTYPE html>
<html>
  <body>
    <textarea id="text-input" oninput="this.editor.update()"
              rows="6" cols="60">Type **Markdown** here.</textarea>
    <div id="preview"> </div>
    <script src="markdown.js"></script>
    <script>
      function Editor(input, preview) {
        this.update = function () {
          preview.innerHTML = markdown.toHTML(input.value);
        };
        input.editor = this;
        this.update();
      }
      var $ = function (id) { return document.getElementById(id); };
      new Editor($("text-input"), $("preview"));
    </script>
  </body>
</html>
```

转换后得到的只是最基础的HTML，可以用CSS美化一下，随手将Bootstarp的CSS引用过来看看：
```html
<!DOCTYPE html>
<html>
<head>
<link href="http://cdn.bootcss.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
  <body style="padding:30px">
    <textarea id="text-input" oninput="this.editor.update()"
              rows="6" cols="60">Type **Markdown** here.</textarea>
    <div id="preview"> </div>
    <script src="markdown.js"></script>
    <script>
      function Editor(input, preview) {
        this.update = function () {
          preview.innerHTML = markdown.toHTML(input.value);
        };
        input.editor = this;
        this.update();
      }
      var $ = function (id) { return document.getElementById(id); };
      new Editor($("text-input"), $("preview"));
    </script>
  </body>
</html>
```

还有markdown插件： [https://github.com/pandao/editor.md](https://github.com/pandao/editor.md)


