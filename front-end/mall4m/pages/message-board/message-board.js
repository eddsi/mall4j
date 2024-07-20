Page({
    data: {
        newMessage: '',
        messages: []
    },

    onInput(e) {
        this.setData({
            newMessage: e.detail.value
        });
    },

    submitMessage() {
        const that = this;
        const {newMessage} = this.data;

        if (!newMessage.trim()) {
            wx.showToast({
                title: '留言内容不能为空',
                icon: 'none'
            });
            return;
        }

        wx.request({
            url: 'https://your-api-url/p/message-board', // 替换为实际的API URL
            method: 'POST',
            data: {
                content: newMessage
            },
            success(res) {
                if (res.statusCode === 200) {
                    that.setData({
                        newMessage: ''
                    });
                    that.fetchMessages();
                } else {
                    wx.showToast({
                        title: '提交失败',
                        icon: 'none'
                    });
                }
            }
        });
    },

    fetchMessages() {
        const that = this;

        wx.request({
            url: 'https://your-api-url/p/message-board', // 替换为实际的API URL
            method: 'GET',
            success(res) {
                if (res.statusCode === 200) {
                    that.setData({
                        messages: res.data.map(message => ({
                            ...message,
                            showReplies: false,
                            children: []
                        }))
                    });
                } else {
                    wx.showToast({
                        title: '加载失败',
                        icon: 'none'
                    });
                }
            }
        });
    },

    toggleReplies(e) {
        const id = e.currentTarget.dataset.id;
        const messages = this.data.messages.map(message => {
            if (message.id === id) {
                if (message.showReplies) {
                    return {...message, showReplies: false};
                } else {
                    wx.request({
                        url: `https://your-api-url/p/message-board/message/child?messageId=${id}`, // 替换为实际的API URL
                        method: 'GET',
                        success(res) {
                            if (res.statusCode === 200) {
                                const children = res.data;
                                const updatedMessages = this.data.messages.map(msg =>
                                    msg.id === id ? {...msg, children, showReplies: true} : msg
                                );
                                this.setData({
                                    messages: updatedMessages
                                });
                            } else {
                                wx.showToast({
                                    title: '加载回复失败',
                                    icon: 'none'
                                });
                            }
                        }
                    });
                    return {...message, showReplies: true};
                }
            }
            return message;
        });

        this.setData({
            messages
        });
    },

    onLoad() {
        this.fetchMessages();
    }
});
