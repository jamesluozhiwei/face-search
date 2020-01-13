# face-search
## 项目介绍

基于face_recoginition+tensorflow的实现的人脸对多搜索、提供web接口。

- python完成具体的人脸对比搜索服务
- Java完成人脸Api的调用并提供对外的web接口

## 环境需求

若需要部署项目，您需要以下环境：

- Java8
- mysql
- docker
- ubuntu(非必需)
- redis(非必需)
- nginx(非必需)

## 初始化数据库

创建数据库：db_face_search_service；

使用数据库文件[db_face_search_service.sql](https://github.com/jamesluozhiwei/face-search/blob/master/db/db_face_search_service.sql)初始化数据库。

## 部署Python人脸服务

提供两种方式：

​	1、安装python依赖运行脚本；

​	2、使用Docker构建；

修改[face_encoding_data_service.py](https://github.com/jamesluozhiwei/face-search/blob/master/face-search-service/app/face_encoding_data_service.py)中的mysql数据库连接信息：

| db_host = 'localhost'              | 数据库地址 |
| ---------------------------------- | ---------- |
| db_user='root'                     | 数据库账户 |
| db_password = '123456'             | 数据库密码 |
| db_name = 'db_face_search_service' | 数据库名   |

### 安装python依赖运行脚本

1、安装gcc(请自行百度)；

2、安装cmake(请自行百度)；

3、安装项目依赖：

​	请安装[requirements.txt](https://github.com/jamesluozhiwei/face-search/blob/master/face-search-service/app/requirements.txt)中的所有依赖

```sh
pip install -r requirement.txt
```

4、运行 [app.py](https://github.com/jamesluozhiwei/face-search/blob/master/face-search-service/app/app.py)即可。

### 使用Docker运行(推荐)

使用Docker可以将环境隔离，不得不说，python需要安装的依赖有点多；

[Ubuntu安装Docker](https://blog.csdn.net/qq_38403662/article/details/103912445)

其它系统请自行查阅官网；

此处默认您已成功安装Docker；

使用Docker构建镜像:

进入到[Dockerfile](https://github.com/jamesluozhiwei/face-search/blob/master/face-search-service/app/Dockerfile)所在目录

```sh
docker build -t <username>/face-search-service:1 -f Dockerfile .
```

注意后面有个点<code> < username ></code>表示你的用户名，可以在<code>DockerHub</code>上注册一个账户，后面可以<code>push</code>自己的镜像上去;

运行镜像：

```sh
docker run -d --name face-search-service1 -p 5002:5002 --mount type=bind,source=<your local dir>,target=/usr/src/app/model jamesluozhiwei/face-search-service:1
```

命令中的<code>< your local dir ></code>指你系统的一个目录，用于保存训练模型；

至此，您已经运行了人脸搜索的服务；

使用命令查看日志

```sh
docker logs face-search-service1
```

即可查看python 服务日志