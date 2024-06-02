// documentUpload.js
Page({
  data: {
    description: '',  // 文档简介
    keywords: '',     // 文档关键字
    file: null        // 选择的文件
  },

  // 输入绑定
  bindInput: function(e) {
    this.setData({
      [e.currentTarget.dataset.field]: e.detail.value
    });
  },

  // 选择文件
  chooseFile: function() {
    const that = this;
    wx.chooseMessageFile({
      count: 1,
      type: 'file',
      success(res) {
        that.setData({
          file: res.tempFiles[0]
        });
      }
    })
  },

  // 上传文件
  uploadFile: function() {
    const that = this;
    if (this.data.file) {
      const { path } = this.data.file;
      wx.uploadFile({
        url: config.uploadUrl, // 你的上传接口地址
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
