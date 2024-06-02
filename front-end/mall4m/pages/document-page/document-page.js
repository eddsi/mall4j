Page({
  data: {
    description: '', // 文档简介
    keywords: '', // 文档关键字
    file: null,
    isChecked: false // 用户是否勾选了同意协议
  },

  // 处理复选框变更
  checkboxChange: function (e) {
    console.log('Checkbox change event:', e.detail.value);
    // 直接判断数组中是否包含特定值（如果是单个复选框，通常使用布尔值来表示是否选中）
    this.setData({
      isChecked: e.detail.value.includes('agree') // 假设复选框的value设置为"agree"
    });
  },


  // 绑定输入框数据
  bindInput: function (e) {
    this.setData({
      [e.currentTarget.dataset.field]: e.detail.value
    });
  },

  // 用户选择文件
  chooseFile: function () {
    const that = this;
    wx.chooseMessageFile({
      count: 1,
      type: 'file',
      success(res) {
        // 检查文件大小不超过20MB
        if (res.tempFiles[0].size <= 20 * 1024 * 1024) {
          that.setData({
            file: res.tempFiles[0]
          });
        } else {
          wx.showToast({
            title: '文件大小不能超过20MB',
            icon: 'none'
          });
        }
      }
    })
  },

  // 上传文件
  uploadFile: function () {
    const that = this;
    if (!that.data.isChecked) {
      wx.showToast({
        title: '请先勾选同意用户协议和隐私政策',
        icon: 'none'
      });
      return;
    }

    if (that.data.file) {
      wx.showToast({
        title: '上传成功',
        icon: 'none'
      });
      const {
        path
      } = that.data.file;
      wx.uploadFile({
        url: 'your_upload_url', // 这里替换成你的上传URL
        filePath: path,
        name: 'file',
        formData: {
          'description': that.data.description,
          'keywords': that.data.keywords
        },
        success(res) {
          wx.showToast({
            title: '上传成功',
            icon: 'success'
          });
          wx.navigateBack(); // 成功后返回上一页
        },
        fail() {
          wx.showToast({
            title: '上传失败',
            icon: 'none'
          });
        }
      });
    } else {
      wx.showToast({
        title: '请选择文件',
        icon: 'none'
      });
    }
  }
});
