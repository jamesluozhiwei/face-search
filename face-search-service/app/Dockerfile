FROM jamesluozhiwei/face-search-env:1

WORKDIR /usr/src/app

#ADD requirements.txt /usr/src/app/requirements.txt

########## REQUIRED DEPENDENCIES ################
#RUN pip3 config set global.index-url http://mirrors.aliyun.com/pypi/simple
#RUN pip3 config set install.trusted-host mirrors.aliyun.com
#RUN pip3 install --no-cache-dir -r requirements.txt \
#    && rm -rf .cache/pip

ADD . /usr/src/app

EXPOSE 5002

CMD ["python3","app.py"]
