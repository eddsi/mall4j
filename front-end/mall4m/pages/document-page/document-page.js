var config = require('../../utils/config.js');
import {
    encrypt
} from '../../utils/crypto.js';

Page({
    data: {
        description: '', // 文档简介
        keyword: '', // 文档关键字
        file: null,
        isChecked: false, // 用户是否勾选了同意协议
        price: '',
        priceError: false,
        priceErrorMessage: '请输入有效的价格',
        minPrice: 1, // 最小价格
        maxPrice: 1000, // 最大价格
        parentCategories: [],
        childCategories: [],
        selectedParentCategory: null,
        selectedChildCategory: null
    },

    onLoad() {
        this.fetchCategories();
    },

    fetchCategories() {
        wx.request({
            url: config.domain + "/category/categoryInfo", // 替换为你的后端接口地址
            method: 'GET',
            success: res => {
                if (res.data.success) {
                    const categories = res.data.data;
                    const parentCategories = categories.filter(category => category.parentId === 0);
                    this.setData({
                        parentCategories: parentCategories
                    });
                }
            }
        });
    },

    pickerParentChange(e) {
        const selectedParent = this.data.parentCategories[e.detail.value];
        const childCategories = this.data.parentCategories.filter(category => category.parentId === selectedParent.categoryId);
        this.setData({
            selectedParentCategory: selectedParent,
            childCategories: childCategories,
            selectedChildCategory: null
        });
    },

    pickerChildChange(e) {
        const selectedChild = this.data.childCategories[e.detail.value];
        this.setData({
            selectedChildCategory: selectedChild
        });
    },

    // 处理复选框变更
    checkboxChange: function (e) {
        this.setData({
            isChecked: e.detail.value.includes('agree')
        });
    },

    // 绑定输入框数据
    bindInput: function (e) {
        this.setData({
            [e.currentTarget.dataset.field]: e.detail.value
        });
    },

    // 价格输入框数据
    handlePriceInput: function (e) {
        let price = e.detail.value;
        if (!isNaN(price) && price >= this.data.minPrice && price <= this.data.maxPrice) {
            this.setData({
                price: price,
                priceError: false
            });
        } else {
            this.setData({
                priceError: true
            });
        }
    },

    // 用户选择文件
    chooseFile: function () {
        const that = this;
        wx.chooseMessageFile({
            count: 1,
            type: 'file',
            success(res) {
                if (res.tempFiles[0].size <= 20 * 1024 * 1024) {
                    // Save selected file for later upload
                    that.setData({
                        file: res.tempFiles[0]
                    });
                    wx.showToast({
                        title: '文件选择成功',
                        icon: 'success'
                    });
                } else {
                    wx.showToast({
                        title: '文件大小不能超过20MB',
                        icon: 'none'
                    });
                }
            },
            fail() {
                wx.showToast({
                    title: '文件选择失败',
                    icon: 'none'
                });
            }
        });
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
        const {
            file,
            description,
            keyword,
            price,
        } = this.data;
        if (!file) {
            wx.showToast({
                title: '请先选择文件',
                icon: 'none'
            });
            return;
        }

        wx.showLoading({
            title: '上传中...',
            mask: true
        });

        // 使用 wx.getFileSystemManager().readFile 方法读取文件内容
        wx.getFileSystemManager().readFile({
            filePath: file.path,
            encoding: 'base64',
            success: function (res) {
                const base64Data = res.data;
                const md5Hash = encrypt(base64Data);
                // 构建 formData 对象
                const formData = {
                    file: base64Data
                };

                const accessToken = wx.getStorageSync('token');
                wx.uploadFile({
                    url: config.uploadUrl, // 上传接口地址
                    filePath: file.path,
                    name: 'file', // 文件表单域名称
                    formData: formData,
                    header: {
                        "content-type": "multipart/form-data;charset=UTF-8",
                        "Authorization": `${accessToken}`
                    },
                    success: function (res) {
                        let data = JSON.parse(res.data);
                        if (data.success === true) {
                            let requestData = {
                                name: md5Hash,
                                key: data.data,
                                oriPrice: price,
                                brief: description,
                                keyword: keyword
                            };
                            wx.request({
                                url: config.createDocumentUrl,
                                method: 'POST',
                                header: {
                                    "content-type": "application/json",
                                    "Authorization": `${accessToken}`
                                },
                                data: requestData,
                                success: function () {
                                    wx.showToast({
                                        title: '上传成功，请稍等，页面即将跳转',
                                        icon: 'success'
                                    });
                                    setTimeout(function () {
                                        wx.navigateBack();
                                    }, 1000);
                                },
                                fail: function () {
                                    wx.showToast({
                                        title: '调用新建文档接口失败，请稍后重试',
                                        icon: 'none'
                                    });
                                }
                            });

                        } else {
                            wx.showToast({
                                title: '上传失败',
                                icon: 'none'
                            });
                        }
                    },
                    fail: function () {
                        wx.showToast({
                            title: '调用上传文件接口失败',
                            icon: 'none'
                        });
                    },
                    complete: function () {
                        wx.hideLoading();
                        that.setData({
                            file: null, // Reset selected file after upload
                            description: '', // Optionally reset other form fields
                            keyword: ''
                        });
                    }
                });
            },
            fail: function () {
                wx.showToast({
                    title: '读取文件失败',
                    icon: 'none'
                });
                wx.hideLoading();
            }
        });
    }
});