var config = require("../../utils/config.js");
Page({
  data: {
    messages: [],
    newTitle: '',
    newContent: '',
    currentPage: 1,
    pageSize: 10,
    total: 0
  },

  onLoad() {
    this.getMessages();
  },

  getMessages() {
    const { currentPage, pageSize } = this.data;
    const accessToken = wx.getStorageSync('token');
    wx.request({
      url: config.domain + '/p/message-board', // 替换为实际的接口地址
       header: {
         "content-type": "application/json",
        "Authorization": `${accessToken}`
    },
      method: 'GET',
      data: {
        basePage: {
          current: currentPage,
          size: pageSize
        }
      },
      success: (res) => {
        if (res.statusCode === 200 && res.data.success) {
          this.setData({
            messages: res.data.data.records,
            total: res.data.data.total
          });
        } else {
          wx.showToast({
            title: res.data.msg || '加载消息失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: () => {
        wx.showToast({
          title: '加载消息失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  handleTitleInputChange(e) {
    this.setData({
      newTitle: e.detail.value
    });
  },

  handleContentInputChange(e) {
    this.setData({
      newContent: e.detail.value
    });
  },

  submitMessage() {
    const { newTitle, newContent } = this.data;
    if (!newTitle || !newContent) return;
    const accessToken = wx.getStorageSync('token');
    wx.request({
      url: config.domain + '/p/message-board', // 替换为实际的接口地址
      header: {
        "content-type": "application/json",
        "Authorization": `${accessToken}`
    },
      method: 'POST',
      data: {
        title: newTitle,
        content: newContent
      },
      success: () => {
        this.setData({
          newTitle: '',
          newContent: ''
        });
        this.getMessages();
        wx.showToast({
          title: '留言提交成功',
          icon: 'success',
          duration: 2000
        });
      },
      fail: () => {
        wx.showToast({
          title: '留言提交失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  toMessageDetail(e) {
    const id = e.currentTarget.dataset.id;
    // 跳转到详情页面，可以根据需要实现
    wx.navigateTo({
      url: `/pages/message-detail/message-detail?id=${id}`
    });
  },

  onReachBottom() {
    // 触底加载更多数据
    const { currentPage, total, pageSize } = this.data;
    if (currentPage * pageSize < total) {
      this.setData({
        currentPage: currentPage + 1
      });
      this.getMessages();
    }
  }
});
