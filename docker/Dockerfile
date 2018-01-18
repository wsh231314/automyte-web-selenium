FROM java:8
VOLUME /tmp
ADD auto-web-1.0.0-SNAPSHOT.jar myte.jar
RUN bash -c 'touch /myte.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myte.jar"]

#创建一个名字是cloud-lch的镜像，并且tag是v1
#docker build -t cloud-lch:v1 /opt/docker-file/cloud/
#执行
#执行 docker run -d -p 8080:8080 cloud-lch:v1 /opt/docker-file/cloud/