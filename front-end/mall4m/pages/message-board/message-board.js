// pages/messageBoard/messageBoard.js
const app = getApp();

Page({
  data: {
    messages: [],
    newMessage: '',
    baseUrl: app.globalData.apiBaseUrl,
    page: 1,
    pageSize: 10,
  },

  onLoad: function () {
    this.loadMessages();
  },

  loadMessages: function () {
    const {
      baseUrl,
      page,
      pageSize
    } = this.data;
    wx.request({
      url: `${baseUrl}`,
      method: 'GET',
      data: {
        page: page,
        pageSize: pageSize
      },
      success: res => {
        if (res.data.success) {
          this.setData({
            messages: res.data.data.records
          });
        }
      },
      fail: err => {
        console.error(err);
      }
    });
  },

  loadChildMessages: function (messageId) {
    const {
      baseUrl
    } = this.data;
    wx.request({
      url: `${baseUrl}/message/child`,
      method: 'GET',
      data: {
        messageId: messageId
      },
      success: res => {
        if (res.data.success) {
          // 处理子留言逻辑
        }
      },
      fail: err => {
        console.error(err);
      }
    });
  },

  handleInputChange: function (e) {
    this.setData({
      newMessage: e.detail.value
    });
  },

  submitMessage: function () {
    const {
      baseUrl,
      newMessage
    } = this.data;
    const userId = 'your-bizUserId'; // 获取用户ID的逻辑

    wx.request({
      url: `${baseUrl}`,
      method: 'POST',
      data: {
        content: newMessage,
        bizUserId: userId
      },
      success: res => {
        if (res.data.success) {
          this.setData({
            newMessage: ''
          });
          this.loadMessages();
        }
      },
      fail: err => {
        console.error(err);
      }
    });
  }
});