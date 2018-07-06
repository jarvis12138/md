

### scr.vue

```html
<template>
	<div class="vue-scroll"
	:class="{'down': (state == 0), 'up': (state == 1), refresh: (state == 2), touch: touching}"
	@touchstart="touchStart($event)"
	@touchmove="touchMove($event)"
	@touchend="touchEnd($event)"
	@scroll="(onInfinite || infiniteLoading) ? onScroll($event) : undefined">
		<div class="inner" :style="{'transform': 'translate(0, ' + top + 'px)', 'top': scrollHeaderHeight + 'px'}">
			<div class="pull-refresh" ref="scrollHeader" :class="{'scroll-header-hidden': scrollHeaderHeight == 0}">
				<slot name="pull-refresh">
					<span class="down-tip">下拉更新</span>
					<span class="up-tip">松开更新</span>
					<span class="refresh-tip">更新中...</span>
				</slot>
			</div>
			<slot></slot>
			<div class="load-more">
				<slot name="load-more">
					<span>加载中...</span>
				</slot>
			</div>
		</div>
	</div>
</template>

<script>

export default {
	props: {
		enableInfinite: {
			type: Boolean,
			default: true
		},
		enableRefresh: {
			type: Boolean,
			default: true
		},
		onRefresh: {
			type: Function,
			default: undefined,
			required: false
		},
		onInfinite: {
			type: Function,
			default: undefined,
			required: false
		},
		offset: {
			type: Number,
			default: 40
		}
	},
	data() {
		return {
			top: 0,
			state: 0,
			startY: 0,
			startScroll: 0,
			timer: null,
			scrollHeaderHeight: 0,
			touching: false,
			infiniteLoading: false
		}
	},
	mounted() {

		// setTimeout(() => {
			this.scrollHeaderHeight = -this.$refs.scrollHeader.clientHeight;
		// }, 600);

		// console.log(this.$el);

		clearInterval(this.timer);
		this.timer = setInterval(() => {
			if (!this.enableInfinite) {
				clearInterval(this.timer);
				// this.onScroll = null;
				return false;
			}

			let docScrollTop = this.$el.scrollTop;
			let visibleHeight = this.$el.clientHeight;
			let documentHeight = this.$el.scrollHeight;

			if ((visibleHeight + docScrollTop) >= documentHeight) {
				this.infiniteLoading = true;
				this.onInfinite(this.infiniteDone);
			} else {
				this.infiniteLoading = false;
				clearInterval(this.timer);
			}
		}, 1000);
	},
	methods: {

		touchStart(e){
			this.startY = e.targetTouches[0].pageY;
			this.startScroll = this.$el.scrollTop || 0;
			this.touching = true;
		},
		touchMove(e){
			if (!this.enableRefresh || this.$el.scrollTop > 0 || !this.touching) {
				return false;
			}
			if (this.state == 2) {
				return false;
			}

			let diff = e.targetTouches[0].pageY - this.startY - this.startScroll;
			if (diff > 0) {
				e.preventDefault();
			}
			this.top = Math.pow(diff, 0.8) + (this.state == 2 ? this.offset : 0);

			if (this.top >= this.offset) {
				this.state = 1;
			} else {
				this.state = 0;
			}
		},
		touchEnd(e){
			if (!this.enableRefresh) {
				return false;
			}
			this.touching = false;
			if (this.state == 2) {
				this.top = this.offset;
				return false;
			}
			if (this.top >= this.offset) {
				this.refresh();
			} else {
				this.top = 0;
				this.state = 0;
			}
		},
		refresh(){
			this.state = 2;
			this.top = this.offset;
			this.onRefresh(this.refreshDone);
		},
		refreshDone(){
			this.state = 0;
			this.top = 0;
		},
		infinite(){
			this.infiniteLoading = true;
			this.onInfinite(this.infiniteDone);
		},
		infiniteDone(){
			this.infiniteLoading = false;
		},
		onScroll(e){
			if (!this.enableInfinite || this.infiniteLoading) {
				clearInterval(this.timer);
				// this.onScroll = null;
				return false;
			}

			let docScrollTop = this.$el.scrollTop;
			let visibleHeight = this.$el.clientHeight;
			let documentHeight = this.$el.scrollHeight;


			if ((visibleHeight + docScrollTop + 10) >= documentHeight) {
				this.infiniteLoading = true;
				this.onInfinite(this.infiniteDone);
			}
		}
	}
}
</script>


<style scoped>

.vue-scroll{
	overflow: auto;
	position: relative;
	-webkit-overflow-scrolling: touch;
}
.vue-scroll .scroll-header-hidden{
	visibility: hidden;
}
.vue-scroll .inner{
	transition-duration: 300ms;
	position: absolute;
}
.vue-scroll.touch .inner{
	transition-duration: 0ms;
}
.vue-scroll.down .down-tip{
	display: block;
}
.vue-scroll.up .up-tip{
	display: block;
}
.vue-scroll.refresh .refresh-tip{
	display: block;
}
.vue-scroll .down-tip,
.vue-scroll .refresh-tip,
.vue-scroll .up-tip{
	display: none;
}

</style>

```

### Hello.vue


```html
<template>
  <div>
       <v-scroll :on-refresh="onRefresh" :on-infinite="onInfinite">
       <ul>
         <li v-for="(item,index) in listdata" >{{item.title}}</li>
         <!-- <li v-for="(item,index) in downdata" >{{item.name}}</li> -->
       </ul>
    </v-scroll>
  </div>
</template>
<script>
import Scroll from './scr';
import Vue from 'vue'
import Axios from 'axios'

export default{
 data () {
    return {
      counter : 1, //默认已经显示出15条数据 count等于一是让从16条开始加载
      num : 15,  // 一次显示多少条
      pageStart : 0, // 开始页数
      pageEnd : 0, // 结束页数
      listdata: [], // 下拉更新数据存放数组
      downdata: []  // 上拉更多的数据存放数组
    }
  },
  mounted : function(){
     // this.onRefresh('done');
  },
  methods: {
    // getList(){
    //    let vm = this;
    //       vm.$http.get('https://api.github.com/repos/typecho-fans/plugins/contents/').then((response) => {
    //                vm.listdata = response.data.slice(0,15);
    //              }, (response) => {
    //                 console.log('error');
    //             });
    // },
    onRefresh(callback) {
      Axios.get('/api/v2/movie/top250')
        .then(res => {
          console.log(res.data.subjects)
          this.listdata = res.data.subjects
          callback()
          // return res.data.subjects
        })
             // this.getList();
       
             // done() // call done
      
    },
    onInfinite(done) {
              // let vm = this;
              // vm.$http.get('https://api.github.com/repos/typecho-fans/plugins/contents/').then((response) => {
              //     vm.counter++;
              //     vm.pageEnd = vm.num * vm.counter;
              //     vm.pageStart = vm.pageEnd - vm.num;
              //     let arr = response.data;
              //        let i = vm.pageStart;
              //        let end = vm.pageEnd;
              //        for(; i<end; i++){
              //           let obj ={};
              //           obj["name"] = arr[i].name;
              //           vm.downdata.push(obj);
              //            if((i + 1) >= response.data.length){
              //             this.$el.querySelector('.load-more').style.display = 'none';
              //             return;
              //           }
              //           }
              //     done() // call done
              //      }, (response) => {
              //       console.log('error');
              //   });
           }
  },
  components : {
'v-scroll': Scroll
  }
}
</script>
```

### 跨域问题解决：
config文件夹下 ---> index.js 
```
dev:
```
```
    proxyTable: {
        '/api': {
            target: 'http://api.douban.com/',
            changeOrigin: true,
            pathRewrite: {
                '^/api': '/'
            }
        }
    },
```


