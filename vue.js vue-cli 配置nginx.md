


通过vue-cli搭建的基于webpack结构的工程项目

1、build前的配置，防止js、css文件路径错误

在目录build下面找到webpack.prod.conf.js文件，找到output节点，添加【publicPath:'./'】

2、执行命令【npm run build】build项目

3、在跟目录下会生成dist目录，里面的内容就是构建的结果，包含html、css、js文件等

4、将构建结果dist目录下面的内容放在nginx的发布目录【/usr/share/nginx/html/vuedemo】中

5、通过http://172.10.10.69/vuedemo/访问测试

PS：
1、npm run dev命令是开发环境的启动应用，npm
 run dev 可以跑起来一般是配置了个node server.js小型服务器，比如
 webpack 的 hot-dev 等，实际生产环境不会跑这个东西。
