1.修改conf/nginx.conf中的alias路径，具体见说明
2.修改完成后，双击exe启动后，在浏览器中输入"localhost:8888/images"，如果出现static下的文件，表明nginx运行正常
3.打开项目，修改/serve/seller/seller_copyfile文件下的BASEPATH字符串，改为你项目static文件夹路径，与alias路径保持一致，!!!!注意字符串后必须加上"//"!!!，否则复制文件在上一级目录中

private String FINDPATH = "http://localhost:8888/images/";
如果出现there some errors类似的错误，打开任务管理器，找到nginx的进程，一一关闭，重新打开（问题是因为特殊PID号导致nginx异常bug）
如果是防火墙，端口相关的问题，表明nginx还没有正常启动