
### 第一步：下载Windows版的nginx
[下载Windows版nginx](http://nginx.org/en/download.html)



### 第二步：解压至任意文件夹下
[nginx for Windows配置文档](http://nginx.org/en/docs/windows.html)



### 第三步：启动nginx
注意：nginx默认是80端口  localhost:80



### 第四步：在html文件夹下新建ajax.html
写代码如下：
```html
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<script type="text/javascript" src="./jquery.min.js"></script>
</head>
<body>
<h1>HAHAHAH</h1>

<script type="text/javascript">
$(function(){
	$.ajax({
		url: 'http://ip.taobao.com/service/getIpInfo.php?ip=127.0.0.1',
		// url: '/api/service/getIpInfo.php?ip=127.0.0.1',
		success: function(data){
			console.log(data);
		}
	})
})
</script>


</body>
</html>

```
启动nginx ： start nginx
报错：
```
localhost/:1 XMLHttpRequest cannot load http://ip.taobao.com/service/getIpInfo.php?ip=127.0.0.1. No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost' is therefore not allowed access.
```


可在conf/nginx.conf文件内，在server节点里面加上以下Location配置，添加
```
location /api {
        	proxy_pass http://ip.taobao.com/;
        }
```

在编辑之前的ajax.html代码，把ajax之前的url改成 ``` /api/service/getIpInfo.php?ip=127.0.0.1 ```

```

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

	#server_tokens off
    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;
	
	upstream uapi {  
	   # server 192.168.0.103:8888;
        server 192.168.0.200:8888;
	}

	upstream admin {  
		server 192.168.0.49:8070;
		#server 192.168.31.246:8081;
	}
	
	upstream vue {  
		server 127.0.0.1:8080;
	}

	upstream ng2 {  
		server 127.0.0.1:4200;
	}

	upstream handred {  
		server 127.0.0.1:8020;
	}	

	
	#upstream api {
    #    server 127.0.0.1:3003;
    #}
	
	upstream meanjs {
        server 127.0.0.1:3000;
    	}
	
	proxy_set_header Host $host;
	proxy_set_header X-Real-IP $remote_addr;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	
    #  改这里
    # www.xlt.com
    #
    server {
        listen       80;
        server_name  192.168.0.49;

	location / {
           proxy_pass http://vue;
        }

	#set $htdocs  D:\my-project1\dist;
	 #   location / {
         # root $htdocs;
	 # try_files $uri $uri/ /index.html =404;
       # }
		
	location ~* /uapi/ {
           proxy_pass http://uapi;
	}
		


        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
           root   html;
        }
    }


	
	server {
        listen       80;
        server_name  localhost;
	#set $htdocs  D:/work/;
	#    location / {
        #   root $htdocs;
	#	   try_files $uri $uri/ /index.html =404;
        #}

	location / {
           proxy_pass http://ng2;
        }
		
		#location ~ .*\.(gif|jpg|jpeg|bmp|png|ico|txt|js|css)$ 
		#{ 
		#	root D:/work/;
		#	expires      7d; 
		#} 
	location ~* /uapi/ {
            proxy_pass http://uapi;
	}
	
	location ~* /admin/ {
            proxy_pass http://admin;
	}
	  
	
		


        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
           root   html;
        }
    }

	server {
    listen       80;
    server_name  127.0.0.1;
	
		location / {
			proxy_pass http://handred;
		}
		
		location ~* /uapi/ {
				proxy_pass http://uapi;
		}
	
		error_page   500 502 503 504  /50x.html;
        location = /50x.html {
           root   html;
        }
	}
	
	
	

    # HTTPS servern
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}
	
	

}

```






