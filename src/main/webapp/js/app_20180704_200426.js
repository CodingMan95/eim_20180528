var app = new Vue({
	el: '#app',
	data() {
		return {
			ruleMask: false,
			slideStatus: 0,
			jyyList: [{
				txt: '仪表整洁/引导车辆',
				status: false,
				file: null
			}, {
				txt: '微笑欢迎/熟练加油',
				status: false,
				file: null
			}, {
				txt: '主动促销/增值服务',
				status: false,
				file: null
			}, {
				txt: '引导付款',
				status: false,
				file: null
			}, {
				txt: '致谢道别',
				status: false,
				file: null
			}],
			syyList: [{
				txt: '仪表整洁/微笑欢迎',
				status: false,
				file: null
			}, {
				txt: '确认油款/主动促销/唱收唱付',
				status: false,
				file: null
			}, {
				txt: '致谢道别',
				status: false,
				file: null
			}, {
				txt: '创意销售',
				status: false,
				file: null
			}],
			videoSubmit: '请选择文件!',
			videoStatus: false,
			testData: null,
			messageInfo: {
				txt: '请选择文件！',
				btn: '确定'
			},
			alertStatus: false,
			userId: null,
			doubleClick: false
		}
	},
	created() {
		this.userId = window.location.search.substr(8)
	},
	methods: {
		changeSlideStatus(index) {
			this.slideStatus = index
		},
		inputJyyChange(index, e) {
			e = e || window.event
			let files = e.target.files[0]
			this.testData = files
			this.jyyList[index].status = true;
			this.jyyList[index].file = files;
			this.videoSelect(this.jyyList)
		},
		inputSyyChange(index, e) {
			e = e || window.event
			let files = e.target.files[0]
			this.syyList[index].status = true;
			this.syyList[index].file = files;
			this.syyList[index].status = true;
			this.videoSelect(this.syyList)
		},
		videoSelect(list) {
			for(let i = 0; i < list.length; i++) {
				if(!list[i].status) {
					this.videoStatus = false
					this.videoSubmit = '请选择文件!'
				} else {
					this.videoStatus = true
					this.videoSubmit = '点击上传文件!'
				}
			}
		},
		submit() {
			if(this.doubleClick) {
				this.alertStatus = true;
				this.messageInfo.txt = '请勿重复上传！'
				return
			}
			let list = null;
			let submitList = [];
			if(this.slideStatus === 0) {
				list = this.jyyList
			} else {
				list = this.syyList
			}
			for(let i = 0; i < list.length; i++) {
				if(list[i].file === null) {
					submitList = null;
					this.alertStatus = true;
					break;
				} else {
					console.log(list[i].file.name)
					var rgx = /(?:mp4|avi|rmvb|wmv|flv|mov|3gp)$/
					if(list[i].file.name.match(rgx)) {
					submitList.push({
						file: list[i].file,
						tag: list[i].txt
					})
				} else {
					this.alertStatus = true;
					this.messageInfo.txt = '当前文件格式不正确!'
					return
				}
			}
		}
		if(submitList !== null) {
			this.doubleClick = true
			this.videoSubmit = '正在上传<a class="dian1">.</a><a class="dian2">.</a><a class="dian3">.</a><a class="dian4">.</a><a class="dian5">.</a><a class="dian6">.</a>'
			var self = this
			var params = new FormData();
			var tag = [];
			for(let i = 0; i < list.length; i++) {
				params.append('file', submitList[i].file)
				tag.push(submitList[i].tag)
			}
			params.append('tag', tag)
			console.log(this.userId)
			params.append('userId', this.userId)

			console.log(params.get('tag'))
			console.log(params.get('file'))
			axios.post('http://app.i-mineral.cn/eim_20180528/fileUpload.do', params, {
				headers: {
					'Content-Type': 'multipart/form-data'
				}
			}).then(function(response) {
				console.log(response.status)
				if(response.status === 200) {
					self.videoSubmit = '上传成功！'
				}
			})
		}
	},
	changeRuleMask(status) {
		status === 0 ? this.ruleMask = true : this.ruleMask = false
	},
hrefVideo(index) {
		if(index === 0) {
			window.location.href="http://app.i-mineral.cn/qp/shouyin.mp4" 
		} else if(index === 1) {
			window.location.href="http://app.i-mineral.cn/qp/shouyin.mp4" 
		}
	},
	changeAlert() {
		this.alertStatus = false;
	}
}
})