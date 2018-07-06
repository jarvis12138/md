### pip查看包
在命令行下输入
```
pip list
```
比较老的版本可能需要输入
```
pip freeze
```


```
Pip3 install beautifulsoup4
```

(bs4中文文档)[https://www.crummy.com/software/BeautifulSoup/bs4/doc.zh/]

### 坑！！！
requests.get和requests.post方法在请求 <b>https</b> 上时会报错：
```
C:\Python27\lib\site-packages\urllib3\util\ssl_.py:339: SNIMissingWarning: An HTTPS request has been made, but the SNI (Subject Name Indication) extension to TLS is not available on this platform. This may cause the server to present an incorrect TLS certificate, which can cause validation failures. You can upgrade to a newer version of Python to solve this. For more information, see https://urllib3.readthedocs.io/en/latest/advanced-usage.html#ssl-warnings
  SNIMissingWarning
```
最简单的就是关闭校验：
```
response = requests.get(url, verify=False)
```
也可参考：【https://www.zhihu.com/question/40025043   】

### xlwt创建xls xlrd读取xls  openpyxl

[https://www.cnblogs.com/BlueSkyyj/p/7571787.html](https://www.cnblogs.com/BlueSkyyj/p/7571787.html)

```python

from openpyxl import Workbook
from openpyxl import load_workbook
 # 读取excel
wb = load_workbook("./test.xlsx")
sheetNames = wb.get_sheet_names()
getSheet = wb.get_sheet_by_name(sheetNames[0])
for item in range(1, getSheet.max_row+1):
	print( getSheet.cell(row=item, column=1).value )

 # 填写某个具体位置
getSheet.cell(row=4, column=2, value=1111)
getSheet['A5'] = 444
wb.save("./test.xlsx")

 # 创建excel
wb = Workbook()
ws = wb.active
ws['A4'] = 42

```

```python

import xlwt
 
def write_excel():
	f = xlwt.Workbook() #创建工作簿
 
	sheet1 = f.add_sheet(u'sheet1',cell_overwrite_ok=True) #创建sheet
	row0= [u'编号',u'姓名',u'性别',u'生日']
	
	#生成第0行
	for i in range(0,len(row0)):
		sheet1.write(0,i,row0[i])
 
	row1 = [u'1',u'张三',u'男']
	row2 = [u'2',u'李四',u'女']
	date = '1990-01-04'
 
	#生成第一行
	for i in range(0,len(row1)):
		sheet1.write(1,i,row1[i])
 
	#生成第二行
	for i in range(0,len(row2)):
		sheet1.write(2,i,row2[i])
 
	#写入合并的单元格数据1990-01-04
	sheet1.write_merge(1,2,3,3,date)
 
	f.save(r'E:\python\learn\demo1.xls') #保存文件
 
#python写入Excel文档
if __name__ == '__main__':  
	#generate_workbook()  
	#read_excel()  
	write_excel()
```

### Python3中如何得到Unicode码对应的中文？
先检查text是什么类型如果type(text) is bytes，那么 ``` text.decode('unicode_escape') ```
如果type(text) is str，那么 ``` text.encode('latin-1').decode('unicode_escape') ```

### Unicode 和 UTF-8 有何区别？
[知乎](https://www.zhihu.com/question/23374078)

### encode decode
str(unicode)类型是基准！要将str类型转化为bytes类型，使用encode()内置函数；反过来，使用decode()函数。
```python
>>>oath = ‘我爱妞’
>>>type(oath)
<class 'str'>
>>>oath = oath.encode(‘utf-8’)
>>> type(oath)
<class 'bytes'>
>>> oath
b'\xe6\x88\x91\xe7\x88\xb1\xe5\xa6\x9e'
>>> oath = oath.decode()
>>> oath
'我爱妞'
```

### python 文件读写模式 r+ w+ a+

模式   |可做操作  | 若文件不存在 |是否覆盖
-------|---------|-------------|-------
r      |只能读    |报错         |-
r+     |可读可写  |报错         |是
w      |只能写    |创建文件     |是
w+     |可读可写  |创建文件     |是
a      |只能写    |创建文件     |否，追加写
a+     |可读可写  |创建文件     |否，追加写

### python 读取线上RUL地址并打开图片

```
img_src = 'http://wx2.sinaimg.cn/mw690/ac38503ely1fesz8m0ov6j20qo140dix.jpg'
```

<b> 1.用OpenCV </b>
```
import cv2
cap = cv2.VideoCapture(img_src)
if( cap.isOpened() ) :
    ret,img = cap.read()
    cv2.imshow("image",img)
    cv2.waitKey()
```

<b> 2. OpenCV+Numpy+urllib </b>
```
import numpy as np
import urllib
import cv2
resp = urllib.urlopen(img_src)
image = np.asarray(bytearray(resp.read()), dtype="uint8")
image = cv2.imdecode(image, cv2.IMREAD_COLOR)
cv2.imshow("Image", image)
cv2.waitKey(0)
```

<b> 3.PIL+requests </b>
```
import requests as req
from PIL import Image
from io import BytesIO
response = req.get(img_src)
image = Image.open(BytesIO(response.content))
image.show()
```

<b> 4. skimage </b>
```
from skimage import io
image = io.imread(img_src)
io.imshow(image)
io.show()
```

### selenium登录163邮箱
```python
from selenium import webdriver
from time import sleep

driver = webdriver.Chrome()
#最大化窗口
driver.maximize_window()
driver.get('http://mail.163.com/')
sleep(2)
#切换到表单
driver.switch_to.frame("x-URS-iframe")
driver.find_element_by_name("email").clear()
driver.find_element_by_name("email").send_keys('Qaz_wza')
driver.find_element_by_name("password").clear()
driver.find_element_by_name("password").send_keys('*********')
driver.find_element_by_id("dologin").click()
```

### python 操作文件及文件夹 os
```python
在Python中，文件操作主要来自os模块，主要方法如下：
os.listdir(dirname)：列出dirname下的目录和文件
os.getcwd()：获得当前工作目录
os.curdir:返回当前目录（'.')
os.chdir(dirname):改变工作目录到dirname
os.path.isdir(name):判断name是不是一个目录，name不是目录就返回false
os.path.isfile(name):判断name是不是一个文件，不存在name也返回false
os.path.exists(name):判断是否存在文件或目录name
os.path.getsize(name):获得文件大小，如果name是目录返回0L
os.path.abspath(name):获得绝对路径
os.path.normpath(path):规范path字符串形式
os.path.split(name):分割文件名与目录（事实上，如果你完全使用目录，它也会将最后一个目录作为文件名而分离，同时它不会判断文件或目录是否存在）
os.path.splitext():分离文件名与扩展名
os.path.join(path,name):连接目录与文件名或目录
os.path.basename(path):返回文件名
os.path.dirname(path):返回文件路径
os.remove(dir) #dir为要删除的文件夹或者文件路径
os.rmdir(path) #path要删除的目录的路径。需要说明的是，使用os.rmdir删除的目录必须为空目录，否则函数出错。
os.path.getmtime(name) ＃获取文件的修改时间 
os.stat(path).st_mtime＃获取文件的修改时间
os.stat(path).st_ctime #获取文件修改时间
os.path.getctime(name)#获取文件的创建时间

```

```python
Python的字符串本身也用\转义
使用Python的r前缀，就不用考虑转义的问题了

s = r'ABC\-001' # Python的字符串
# 对应的正则表达式字符串不变：
# 'ABC\-001'

s = 'ABC\\-001' # Python的字符串
# 对应的正则表达式字符串变成：
# 'ABC\-001'
```

### 中文转url问题
```python
import urllib.request
url = '三国演义'
print (url)   # 三国演义
print (urllib.request.quote(url))   # %E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89
```
```python
# 但在当当网搜索图书时却发现他 三国演义 四个字输出的是：%C8%FD%B9%FA%D1%DD%D2%E5
# 发现他是gbk 因此修改代码如下：
import urllib.request
url = '三国演义'
print (url)   # 三国演义
url = url.encode('gbk')
print (urllib.request.quote(url))
```

### python 代理IP
[CSDN](http://blog.csdn.net/c406495762/article/details/60137956)
[查询ip](http://www.whatismyip.com.tw/)

### python从字符串中获取数字
```python
a = "he33llo 42 I'm a 32 string 30"
b = re.findall(r'\d+', a)        # ['33', '42', '32', '30']
```
```python
# 如果只想用字边界（空格，句号，逗号）分隔的数：

a = "he33llo 42 I'm a 32 string 30"
b = re.findall(r'\b\d+\b', a)    # ['42', '32', '30']
```

### smtplib 163邮箱发送邮件
```python
import smtplib  
import email.mime.multipart  
import email.mime.text  
  
msg=email.mime.multipart.MIMEMultipart()  
msg['from']='your@163.com'  
msg['to']='send@qq.com'  
msg['subject']='test'  
content='python test'
txt=email.mime.text.MIMEText(content, 'plain', 'utf-8')  
msg.attach(txt)  
  
smtp=smtplib  
smtp=smtplib.SMTP()  
smtp.connect('smtp.163.com','25')  
smtp.login('your@163.com','password')  
smtp.sendmail('your@163.com','send@qq.com',str(msg))  
smtp.quit()  
```

### 安装pywin32问题
```python
import sys
from winreg import *
 
# tweak as necessary
version = sys.version[:3]
installpath = sys.prefix
 
regpath = "SOFTWARE\\Python\\Pythoncore\\%s\\" % (version)
installkey = "InstallPath"
pythonkey = "PythonPath"
pythonpath = "%s;%s\\Lib\\;%s\\DLLs\\" % (
    installpath, installpath, installpath
)
 
def RegisterPy():
    try:
        reg = OpenKey(HKEY_CURRENT_USER, regpath)
    except EnvironmentError as e:
        try:
            reg = CreateKey(HKEY_CURRENT_USER, regpath)
            SetValue(reg, installkey, REG_SZ, installpath)
            SetValue(reg, pythonkey, REG_SZ, pythonpath)
            CloseKey(reg)
        except:
            print ("*** Unable to register!")
            return
        print (" Python", version, "is now registered!")
        return
    if (QueryValue(reg, installkey) == installpath and
        QueryValue(reg, pythonkey) == pythonpath):
        CloseKey(reg)
        print ("=== Python", version, "is already registered!")
        return
    CloseKey(reg)
    print ("*** Unable to register!")
    print ("*** You probably have another Python installation!")
 
if __name__ == "__main__":
    RegisterPy() 
```

### python安装pywin32
下载pywin32地址：[https://pypi.python.org/pypi/pypiwin32/220](https://pypi.python.org/pypi/pypiwin32/220)
查看python安装路径:
```python
import sys     
print (sys.path)  
```
将下载的pywin32.whl放到python安装目录\Scripts下
执行 ```python pip install whl全名 ```

```python
# 遍历所有主窗口title
from win32gui import *
titles = set()
def foo(hwnd,mouse):
 #去掉下面这句就所有都输出了，但是我不需要那么多
 if IsWindow(hwnd) and IsWindowEnabled(hwnd) and IsWindowVisible(hwnd):
  titles.add(GetWindowText(hwnd))
EnumWindows(foo, 0)
lt = [t for t in titles if t]
lt.sort()
for t in lt:
 print (t)
```


### tkinter 打包exe

```python
from tkinter import *
import tkinter.filedialog     # askdirectory   askopenfilenames
root = Tk()

def xz():
    filenames = tkinter.filedialog.askdirectory()
    if len(filenames) != 0:
        string_filename =""
        for i in range(0,len(filenames)):
            string_filename += str(filenames[i])+""
        lb.config(text = "您选择的文件是："+string_filename)

        print(string_filename)
    else:
        lb.config(text = "您没有选择任何文件");

lb = Label(root,text = '')
lb.pack()
btn = Button(root,text="弹出选择文件对话框",command=xz)
btn.pack()
root.mainloop()
```

### 爬虫
```python



import requests
import json
import re
import sys
import codecs
import time
from bs4 import BeautifulSoup
from urllib.request import quote


inputText = input("input:")
inputText = quote(inputText)

# r = requests.get('https://sclub.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98vv373&productId=10332776081&score=0&sortType=5&page=0&pageSize=10&isShadowSku=0&fold=1')

# print(r.text)
# r = r.text
# r = r[25:-2]

# print(r[25:-2])

# print(json.loads(  r  )['comments'])

headers = {
	# 'Accept':'*/*',
	# 'Accept-Encoding':'gzip, deflate, br',
	# 'Accept-Language':'zh-CN,zh;q=0.8',
	# 'Connection':'keep-alive',
	# 'Cookie':'ipLoc-djd=1-72-2799-0; qrsc=3; __jdv=122270672|baidu|-|organic|not set|1514511848411; ipLocation=%u5317%u4EAC; areaId=1; PCSYCityID=1607; mobilev=html5; mba_muid=9622011; sid=e9f6a21ba2a585bc3d5d02274ff37190; mt_xid=V2_52007VwsVUV5ZUlovHktfVmEKEVZBXFsPH1UcXVc1HxpaXVFOX0hAEAxQMAIVUFsNYlkeSR9ABWQfEFNMWVpRGkESWAJXASJSbVhVXhxKH18AYzMSV1tf; unpl=V2_ZzNtbUZRSxFyXUcAc0ldAGJTGl4SA0ZGd1tPBHsbXwJiAxZZclRCFXMURldnG10UZwMZWENcQBJFCHZXchBYAWcCGllyBBNNIEwHDCRSBUE3XHxcFVUWF3RaTwEoSVoAYwtBDkZUFBYhW0IAKElVVTUFR21yVEMldQl2XHwaXwRnAyJtRWdzJX0BQF1yHmwEVwIiHxYLRRZwDkdSNhFbBmQCEl1yVnMW; CCC_SE=ADC_8WAUSdEHzKIpyHuk%2flXTXXZSiNhUTxBs3cRvYzEJKZ%2bzeysq8IxkUWawr6coQhnAP9Mksr8sA7UtMVEwzawUhVDSok6cedGjBuH7DTbVUo0kshd4iZJUM88mfXFeAFRiSDYPtvYQPRXk2m9F48hYTHlyJtRzdcjc5mahIHcJ6x%2b8uYpHu1XqFgKNWuUbkk2u3%2f2xOQLv9yR%2bDiEn5Nn%2fy%2be0eGzYbVVlBe7WTzYFfaoo7jMp8Ca52e%2bSjY%2bnB1NZ0rcP27tE0MEVFTX3ropAxEdFVScYSHa6aHSIJpPOXvqWewpZbS%2fmNpIP2Potjl6gHmyNYW7Iw%2bwz%2bhKiYz1bvE8JKE6s3k3rPzFIjfMVv%2batuxXjGUgaeJxteBI4a7TBL%2fi0Ck4baTAE7eUSKjDSlV%2fKWDL%2fkbaYYUemtwbsc9%2bdExDNRlXfZ%2b6mnjkSaKu%2bxAMCLHyg0w5ZEYLpGjAvEwLXoJf4ZlNkehygTwsVZZ9uHq1tZ7fbv%2fTKtIuOxfXp0UXyNRJ50F5yplgVGVSdDWLW34nxgGCSM3roR9DHy%2bFigBicwfzQaf9IMpS9VjqZiQrxD2nrs77qXc4j2MgALtQwvNfRjavUHYFiGR0sMVKf9l51Xejg%2fok7gtbSTADDeQr6qLZlCXq1smAkWt2I5FHJc0zcT5gpL9TNZ3a1moMrdWQDGyR47eJAxiR2N2wtEXakUU4D2rqVJfHBFnFDjQ%3d%3d; wlfstk_smdl=v7xb9rub8sv548xme99q71f0p8evkdlg; _jrda=1; rkv=V0900; __jda=122270672.9622011.1506429250.1514605224.1514614397.10; __jdb=122270672.4.9622011|10.1514614397; __jdc=122270672; user-key=db879b6d-f3ef-4213-93e4-dd0c9e10a433; cn=0; 3AB9D23F7A4B3C9B=BYTTZCZT2VWKKGBB3UITXAPI7BBH6AHBTDD7SSWOIR6U2YFJY3BXGE3BOUVCGV7BSADET7RS23SMILWTEAWOTP7Q4I; xtest=4656.cf6b6759; __jdu=9622011',
	# 'Host':'search.jd.com',
	'Referer':'https://search.jd.com/Search?keyword=' + inputText + '&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=' + inputText + '&page=1&click=0',
	# 'User-Agent':'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36',
	# 'X-Requested-With':'XMLHttpRequest'
}

# s = requests.get('http://list.jd.com/list.html?cat=9987,653,655&page=1&JL=6_0_0&ms=5#J_main')
s = requests.get('https://search.jd.com/s_new.php?keyword=' + inputText + '&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=' + inputText + '&page=1&scrolling=y', headers=headers)

s = s.text

# s = "<html><head></head><body>" + s + "</body></html>";

# print(sys.getdefaultencoding())

# with open('test.txt', 'w') as f:
	# f.write(s)

# codecs.open('test.txt', 'w', 'utf-8').write(s)


soup = BeautifulSoup(s, 'html.parser')

# for item in soup.find_all("div", class_="p-name-type-2"):
	# print(item.find("em"))

#                  page_count:"

pageCount = re.findall(r'page_count:\"(.+?)\"', str(soup.find_all('script')[0]))[0]
print("总共有：" + pageCount + "页")

# print(soup.find_all('script')[0])
# print(     re.findall(r'page_count:\"(.+?)\"', str(soup.find_all('script')[0]))[0]     )

# print(len(soup.find_all("div", class_="p-name-type-2")))


def reqq(inputText, page):

	headers = {
		# 'Accept':'*/*',
		# 'Accept-Encoding':'gzip, deflate, br',
		# 'Accept-Language':'zh-CN,zh;q=0.8',
		# 'Connection':'keep-alive',
		# 'Cookie':'ipLoc-djd=1-72-2799-0; qrsc=3; __jdv=122270672|baidu|-|organic|not set|1514511848411; ipLocation=%u5317%u4EAC; areaId=1; PCSYCityID=1607; mobilev=html5; mba_muid=9622011; sid=e9f6a21ba2a585bc3d5d02274ff37190; mt_xid=V2_52007VwsVUV5ZUlovHktfVmEKEVZBXFsPH1UcXVc1HxpaXVFOX0hAEAxQMAIVUFsNYlkeSR9ABWQfEFNMWVpRGkESWAJXASJSbVhVXhxKH18AYzMSV1tf; unpl=V2_ZzNtbUZRSxFyXUcAc0ldAGJTGl4SA0ZGd1tPBHsbXwJiAxZZclRCFXMURldnG10UZwMZWENcQBJFCHZXchBYAWcCGllyBBNNIEwHDCRSBUE3XHxcFVUWF3RaTwEoSVoAYwtBDkZUFBYhW0IAKElVVTUFR21yVEMldQl2XHwaXwRnAyJtRWdzJX0BQF1yHmwEVwIiHxYLRRZwDkdSNhFbBmQCEl1yVnMW; CCC_SE=ADC_8WAUSdEHzKIpyHuk%2flXTXXZSiNhUTxBs3cRvYzEJKZ%2bzeysq8IxkUWawr6coQhnAP9Mksr8sA7UtMVEwzawUhVDSok6cedGjBuH7DTbVUo0kshd4iZJUM88mfXFeAFRiSDYPtvYQPRXk2m9F48hYTHlyJtRzdcjc5mahIHcJ6x%2b8uYpHu1XqFgKNWuUbkk2u3%2f2xOQLv9yR%2bDiEn5Nn%2fy%2be0eGzYbVVlBe7WTzYFfaoo7jMp8Ca52e%2bSjY%2bnB1NZ0rcP27tE0MEVFTX3ropAxEdFVScYSHa6aHSIJpPOXvqWewpZbS%2fmNpIP2Potjl6gHmyNYW7Iw%2bwz%2bhKiYz1bvE8JKE6s3k3rPzFIjfMVv%2batuxXjGUgaeJxteBI4a7TBL%2fi0Ck4baTAE7eUSKjDSlV%2fKWDL%2fkbaYYUemtwbsc9%2bdExDNRlXfZ%2b6mnjkSaKu%2bxAMCLHyg0w5ZEYLpGjAvEwLXoJf4ZlNkehygTwsVZZ9uHq1tZ7fbv%2fTKtIuOxfXp0UXyNRJ50F5yplgVGVSdDWLW34nxgGCSM3roR9DHy%2bFigBicwfzQaf9IMpS9VjqZiQrxD2nrs77qXc4j2MgALtQwvNfRjavUHYFiGR0sMVKf9l51Xejg%2fok7gtbSTADDeQr6qLZlCXq1smAkWt2I5FHJc0zcT5gpL9TNZ3a1moMrdWQDGyR47eJAxiR2N2wtEXakUU4D2rqVJfHBFnFDjQ%3d%3d; wlfstk_smdl=v7xb9rub8sv548xme99q71f0p8evkdlg; _jrda=1; rkv=V0900; __jda=122270672.9622011.1506429250.1514605224.1514614397.10; __jdb=122270672.4.9622011|10.1514614397; __jdc=122270672; user-key=db879b6d-f3ef-4213-93e4-dd0c9e10a433; cn=0; 3AB9D23F7A4B3C9B=BYTTZCZT2VWKKGBB3UITXAPI7BBH6AHBTDD7SSWOIR6U2YFJY3BXGE3BOUVCGV7BSADET7RS23SMILWTEAWOTP7Q4I; xtest=4656.cf6b6759; __jdu=9622011',
		# 'Host':'search.jd.com',
		'Referer':'https://search.jd.com/Search?keyword=' + inputText + '&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=' + inputText + '&page=' + str(page) + '&click=0',
		# 'User-Agent':'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36',
		# 'X-Requested-With':'XMLHttpRequest'
	}

	# s = requests.get('http://list.jd.com/list.html?cat=9987,653,655&page=1&JL=6_0_0&ms=5#J_main')
	s = requests.get('https://search.jd.com/s_new.php?keyword=' + inputText + '&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=' + inputText + '&page=' + str(page) + '&scrolling=y', headers=headers)

	s = s.text
	print(page)
	findUrl(s)
	# return s

def findUrl(ss):
	soup = BeautifulSoup(ss, 'html.parser')
	for item in soup.find_all("div", class_="p-name-type-2"):
		print(item.find("a").attrs['href'])

# print(int(pageCount)*2)
pageCount = int(pageCount)*2
for pageItem in range(pageCount) :
	print("搜索：第" + str(pageItem) + "页")
	reqq(inputText, pageItem)
	time.sleep(0.5)

```

### adb
```
adb devices    （找到设备 ID）
adb shell /system/bin/screencap -p /sdcard/screenshot.png（保存到SDCard）
adb pull /sdcard/screenshot.png d:/screenshot.png（保存到电脑）
```

### python Image库
(http://blog.csdn.net/icamera0/article/details/50683106)[http://blog.csdn.net/icamera0/article/details/50683106]


### python大法好，word, pdf变身html没烦恼
[https://zhuanlan.zhihu.com/p/28798859](https://zhuanlan.zhihu.com/p/28798859)
### python 将指定文件中的图片整合成PDF
[https://zhuanlan.zhihu.com/p/20709824](https://zhuanlan.zhihu.com/p/20709824)

### python html转pdf
```python
import pdfkit
config = pdfkit.configuration(wkhtmltopdf="C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe")
import requests
import os
from bs4 import BeautifulSoup
import pdfkit

req = requests.get('http://www.book110.com/5867.html')
# print(req.text)

soup = BeautifulSoup(req.text, "html.parser")
content = soup.find_all(class_="content")

htmltopdf = "<html><head></head><body>"
htmltopdf = htmltopdf + str(content[0])
htmltopdf = htmltopdf + "</body></html>"

options = {
    'page-size': 'Letter',
    'encoding': "UTF-8",
    'custom-header': [
        ('Accept-Encoding', 'gzip')
    ]
}

with open("1.html", 'w', encoding='UTF-8') as f:
	f.write(htmltopdf)

pdfkit.from_url("1.html", "1.pdf", options=options, configuration=config)

# pdfkit.from_url('http://www.book110.com/5867.html', 'out.pdf',configuration = config)
# pdfkit.from_file('http://www.book110.com/5867.html', 'out.pdf')


```
```
1、需要下载pdfkit pip install pdfkit
2、直接运行会报错，
OSError: No wkhtmltopdf executable found: "b''"
If this file exists please check that this process can read it. Otherwise please install wkhtmltopdf - https://github.com/JazzCore/python-pdfkit/wiki/Installing-wkhtmltopdf
打开连接下载wkhtmltopddf， 再在环境变量path中加入wkhtmltopdf安装文件夹

```

### pyqt5 引入文件
```python
from PyQt5 import QtWidgets  
from PyQt5.QtWidgets import QFileDialog  
  
class MyWindow(QtWidgets.QWidget):  
    def __init__(self):  
        super(MyWindow,self).__init__()  
        self.myButton = QtWidgets.QPushButton(self)  
        self.myButton.setObjectName("myButton")  
        self.myButton.setText("Test")  
        self.myButton.clicked.connect(self.msg)  
  
    def msg(self):  
        directory1 = QFileDialog.getExistingDirectory(self,  
                                    "选取文件夹",  
                                    "C:/")                                 #起始路径  
        print(directory1)  
  
        fileName1, filetype = QFileDialog.getOpenFileName(self,  
                                    "选取文件",  
                                    "C:/",  
                                    "All Files (*);;Text Files (*.txt)")   #设置文件扩展名过滤,注意用双分号间隔  
        print(fileName1,filetype)  
  
        files, ok1 = QFileDialog.getOpenFileNames(self,  
                                    "多文件选择",  
                                    "C:/",  
                                    "All Files (*);;Text Files (*.txt)")  
        print(files,ok1)  
  
        fileName2, ok2 = QFileDialog.getSaveFileName(self,  
                                    "文件保存",  
                                    "C:/",  
                                    "All Files (*);;Text Files (*.txt)")  
  
if __name__=="__main__":    
    import sys    
    
    app=QtWidgets.QApplication(sys.argv)    
    myshow=MyWindow()  
    myshow.show()  
    sys.exit(app.exec_())    
```

### pymysql mysql数据库
```python
import pymysql  #导入 pymysql  
  
#打开数据库连接  
db= pymysql.connect(host="localhost",user="root",  
    password="123456",db="test",port=3307)  
  
# 使用cursor()方法获取操作游标  
cur = db.cursor()  
  
#1.查询操作  
# 编写sql 查询语句  user 对应我的表名  
sql = "select * from user"  
try:  
    cur.execute(sql)    #执行sql语句  
  
    results = cur.fetchall()    #获取查询的所有记录  
    print("id","name","password")  
    #遍历结果  
    for row in results :  
        id = row[0]  
        name = row[1]  
        password = row[2]  
        print(id,name,password)  
except Exception as e:  
    raise e  
finally:  
    db.close()  #关闭连接  
```

### python 爬取代理ip并检测
```python

from bs4 import BeautifulSoup
import requests

headers = {
    'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
    'Accept-Encoding':'gzip, deflate',
    'Accept-Language':'zh-CN,zh;q=0.9',
    'Connection':'keep-alive',
    'Host':'www.xicidaili.com',
    'Referer':'http://www.xicidaili.com/wt/1',
    'User-Agent':'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36'
}

url = 'http://www.xicidaili.com/wt/1'

r = requests.get(url, headers=headers)
# print(r.content)

soup = BeautifulSoup(r.content, 'lxml')
# for i in soup.find_all(id="ip_list"):
    # print()
# print( soup.find_all('table', id="ip_list") )
temp = []
for i in soup.find_all('table', id="ip_list"):
    for y in i.find_all('tr'):
        if y.find_all('td'):
            # print(y.find_all('td')[1].text)
            temp.append(y.find_all('td')[1].text + ':' + y.find_all('td')[2].text)



# try:
#     requests.get('http://wenshu.court.gov.cn/', proxies={"http":"http://121.31.154.12:8123"})

#    http://2017.ip138.com/ic.asp

headers['Host'] = 'httpbin.org'
headers['Referer'] = 'http://httpbin.org/ip'
# headers['Cookie'] = 'ASPSESSIONIDCCTQRSTB=CPKHIPGAAIMEKKHPOFCBJMEM; ASPSESSIONIDAATSQSTA=AENAKPGAJLLHMLKIJFKMDNCO'
import telnetlib
import time



for i in temp[0:20]:
    files = {
        'data': i,
        'type': 'proxycheck',
        'arg': 'p=8080_t=0_o=3'
    }
    proxies = {
        "http": 'http://' + i ,
        "https": 'http://' + i ,
    }
    # print(proxies)
    try:
        r = requests.get('http://httpbin.org/ip', headers=headers, proxies = proxies, files=files, timeout=2)
        time.sleep(1)
        # if r.status_code == 200:
            
    except:
        print ('ip ' , i, 'connect failed')
    else:
        print (BeautifulSoup(r.content, 'lxml').find('body'))
        # print(r.content)
        print ('ip ' , i, 'success---------------------')




```

### xpath 节点选择
```html
<ul>
  <li>
	<a>节点</a> 
  </li>
  <li>
	<a>节点</a> 
  </li>
  <li>
	<a>节点</a> 
  </li>
  <li>
	<a>节点</a> 
  </li>
  <li>
	<a>
	  <a>节点</a> 
	</a> 
  </li>
  <li>
	<a>节点</a> 
	<a>节点</a> 
  </li>
  <li>
	<a>节点</a> 
	<a>节点</a> 
	<a>节点</a> 
  </li>
  <li>
	节点 
  </li>
</ul>
```
```python
html = etree.parse("./t.html")
con = html.xpath('/ul[@id="atozlist"]/li')

for i in con:
    print(i.xpath('*'))
    



#    *  a a      选取所有子节点
#    /* ul       选取根元素
#    //* li a    选取所有子元素，而不管它们在文档中的位置

```

```
etree.parse('./a.html')
etree.HTML("<p>data</p>")
```

### requests 获取并保存文件
```
import requests
html = requests.get("http://www.baidu.com")
with open('test.txt','w',encoding='utf-8') as f:
 f.write(html.text)
```








