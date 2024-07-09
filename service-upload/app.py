from flask import Flask, request
from minio import Minio
from minio.error import S3Error
from config import *

app = Flask(__name__)


@app.route('/upload', methods=['POST'])
def upload_file():
    file = request.files['file']
    description = request.form.get('description')
    keywords = request.form.get('keywords')

    # 创建一个Minio客户端实例
    client = Minio(
        minio_ip_port,  # MinIO服务器的地址和端口
        access_key,  # 你的访问密钥
        secret_key,  # 你的秘密密钥
        secure
    )

    try:
        # 确保存储桶存在
        bucket_name = bucket
        if not client.bucket_exists(bucket_name):
            client.make_bucket(bucket_name)

        # 上传文件
        client.put_object(bucket_name, file.filename, file, file.content_length)
        return 'File uploaded successfully'
    except S3Error as err:
        return f'Error: {err}'


if __name__ == '__main__':
    app.run(debug=True)
