# 使用官方的Python镜像作为基础镜像
FROM python:3.8-slim-buster

# 设置工作目录
WORKDIR /app

# 将当前目录的内容复制到工作目录中
COPY . /app

# 安装项目依赖
RUN pip install --no-cache-dir -r requirements.txt

# 暴露端口，使得Flask应用可以被访问
EXPOSE 5000
EXPOSE 8000

# 运行应用
CMD ["gunicorn", "-w", "4", "app:app"]