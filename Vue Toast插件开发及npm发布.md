
## 前言
由于要将web端做的尽可能模拟原生Android，需要实现一个模拟Android中Toast组件，效果：

![模拟Android Toast组件](https://github.com/iiijarvis/w/blob/master/md/img/ezgif-1-2d21466e9c.gif?raw=true)

### 项目初始化
只是封装一个插件，我们就使用简易点的安装：
```
vue init webpack-simple vue-toast
```

项目一路enter之后，创建的文件结构是这样的：

![项目结构](https://github.com/iiijarvis/w/blob/master/md/img/TIM%E6%88%AA%E5%9B%BE20171024164915.png?raw=true)

### 1、首先创建vue文件
在src文件夹中创建lib文件夹,再在lib文件夹中创建index.js、toast.vue

### 2、编写vue文件代码
模拟的Toast效果 toast.vue代码：
```html
<template>
	<div :class="{'vue-toast-close': !show}" class="vue-toast">
		{{msg}}
	</div>
</template>

<script>

export default {
	name: 'toast',
	data(){
		return {
			show: true
		}
	},
	methods: {

	},
	created(){
		let that = this;
		setTimeout(() => {
			that.show = false;
			that.$el.parentNode.removeChild(that.$el);
		}, 2000);
	}
}
</script>

<style scoped>
	.vue-toast{
		position: fixed;
		bottom: 0;
		left: 50%;
		font-size: 12px;
		-webkit-transform: translateX(-50%);
		-moz-transform: translateX(-50%);
		-o-transform: translateX(-50%);
		-ms-transform: translateX(-50%);
		transform: translateX(-50%);
		opacity: 0;
		background-color: #000;
		border-radius: 4px;
		color: #fff;
		padding: 8px 10px;
		max-width: 80%;

		-webkit-transition: all 0.2s cubic-bezier(.22,1.43,1,1.09);
		-o-transition: all 0.2s cubic-bezier(.22,1.43,1,1.09);
		-ms-transition: all 0.2s cubic-bezier(.22,1.43,1,1.09);
		-moz-transition: all 0.2s cubic-bezier(.22,1.43,1,1.09);
		transition: all 0.2s cubic-bezier(.22,1.43,1,1.09);
	}
	.vue-toast-show{
		bottom: 60px!important;
		opacity: 0.8!important;
	}
	.vue-toast-close{
		opacity: 0;
		bottom: 0;
	}
</style>
```
这段代码比较简单，就不做过多解释

### 3、编写index.js
```javascript
import Vue from 'vue'
import ToastComponent from './toast.vue'

const ToastConstructor = Vue.extend(ToastComponent);

let i = 1;
let instance ;

const Toast = (msg) => {
	instance = new ToastConstructor({
		data: {
			msg: msg
			// show: true
		}
	});
	instance.vm = instance.$mount();
	instance.dom = instance.vm.$el;
	document.getElementsByTagName('body')[0].appendChild(instance.dom);
	instance.dom.style.zIndex = i + 10000;
	setTimeout(() => {
		instance.dom.className = instance.dom.className + ' vue-toast-show';
	}, 0);
		
	i++;

	return instance.vm;
}

export default {
	install: Vue => {
		Vue.prototype.$toast = Toast;
	}
}
```
其中的vue.extend是vue官网的插件开发接口

官网地址：[https://cn.vuejs.org/v2/api/#Vue-extend](https://cn.vuejs.org/v2/api/#Vue-extend)

### 3、使用效果预览
只需要在 ``` main.js ``` 中引入即可：
```
import Toast from './lib/index.js'
Vue.use(Toast)
```
之后，就可以直接在组件中使用了
```
this.$toast('Hello World!');
```

### 发布到npm
1、需要修改 package.json文件，添加以下代码：
```

  "private": false,
  "license": "MIT",
  "main": "dist/build.js",
```
2、修改 webpack.config.js文件代码：
```javascript
// entry: './src/main.js',
entry: './src/lib/index.js',
```
3、修改lib/index.js文件代码：
```javascript
export default {
	install: Vue => {
		if (typeof window !== 'undefined' && window.Vue) {
			window.Vue.use(vue-plugin-toast)
		}
		Vue.prototype.$toast = Toast;
	}
}
```

