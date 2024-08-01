// pages/messageBoardList/messageBoardList.js
var config = require("../../utils/config.js");
Page({
  data: {
    messages: []
  },

  onLoad() {
    this.getMessages();
  },

  getMessages() {
    wx.request({
      url: config.domain + '/p/message-board', // 替换为实际的接口地址
      method: 'GET',
      success: (res) => {
        this.setData({
          messages: res.data.messages
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
    const {
      newTitle,
      newContent
    } = this.data;
    if (!newTitle || !newContent) return;

    wx.request({
      url: 'your-api-endpoint-to-create-message', // 替换为实际的接口地址
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
      }
    });
  },

  toMessageDetail(e) {
    const {
      id
    } = e.currentTarget.dataset;
    wx.navigateTo({
      url: `/pages/message-detail/message-detail?id=${id}`
    });
  }
});