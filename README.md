# **凯之渡论坛 Android 客户端** #

本项目基于凯之渡论坛开放的接口开发完成。可通过在凯之渡各页面url后添加styleid=12参数查看各页面返回的JSON数据。项目中采用了一些经过修改的第三方控件，如AmazingListView和XListView。

**源码结构**<br>

> src<br>
> ├ android.util<br>
> ├ com.can2do.bbs<br>
> ├ com.can2do.bbs.net<br>
> ├ com.can2do.bbs.util<br>
> ├ com.can2do.bbs.widget<br>


- android.util — 为了兼容低版本，从安卓sdk中复制出来的base64工具包
- com.pediy.bbs.kanxue — 界面包
- com.pediy.bbs.kanxue.net — 网络包
- com.pediy.bbs.kanxue.util — 工具包
- com.pediy.bbs.kanxue.widget — 控件包

> libs<br>
> └ fastjson-1.1.26-android.jar<br>

- fastjson-1.1.26-android.jar — 快速的json数据解析包

**看雪开放的api文档地址：**http://bbs.can2do.com/

