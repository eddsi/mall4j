FROM openjdk:17


RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/mall4j

WORKDIR /opt/projects/mall4j

EXPOSE 8086

ADD ./yami-shop-api/target/yami-shop-api-0.0.1-SNAPSHOT.jar ./

CMD java -jar -Xms256m -Xmx256m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -Dspring.profiles.active=docker yami-shop-api-0.0.1-SNAPSHOT.jar