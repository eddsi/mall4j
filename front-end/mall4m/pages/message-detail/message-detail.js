var config = require("../../utils/config.js");
Page({
  data: {
    message: {},
    childMessages: [],
    replyContent: ''
  },

  onLoad(options) {
    const {
      id
    } = options;
    this.getMessageDetail(id);
    this.getChildMessages(id);
  },

  getMessageDetail(id) {
    wx.request({
      url: config.domain + `/p/message-board?id=${id}`, // 替换为实际的接口地址
      method: 'GET',
      success: (res) => {
        this.setData({
          message: res.data.message
        });
      }
    });
  },

  getChildMessages(id) {
    wx.request({
      url: config.domain + `/p/message-board/message/child?id=${id}`, // 替换为实际的接口地址
      method: 'GET',
      success: (res) => {
        this.setData({
          childMessages: res.data.childMessages
        });
      }
    });
  },

  handleReplyInputChange(e) {
    this.setData({
      replyContent: e.detail.value
    });
  },

  submitReply() {
    const {
      replyContent
    } = this.data;
    if (!replyContent) return;

    wx.request({
      url: config.domain + '/p/message-board', // 替换为实际的接口地址
      method: 'POST',
      data: {
        content: replyContent,
        parentId: this.data.message.id
      },
      success: () => {
        this.setData({
          replyContent: ''
        });
        this.getChildMessages(this.data.message.id);
      }
    });
  }
});