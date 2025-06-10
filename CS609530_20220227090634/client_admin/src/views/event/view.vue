<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','entry_name')) || (!form['event_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-select id="project_type" v-model="form['project_type']"
						v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','project_type')) || (!form['event_id'] && $check_field('add','project_type'))">
						<el-option v-for="o in list_project_type" :key="o['project_type']" :label="o['project_type']"
							:value="o['project_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','competition_place') || $check_field('add','competition_place') || $check_field('set','competition_place')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="比赛地点" prop="competition_place">
					<el-input id="competition_place" v-model="form['competition_place']" placeholder="请输入比赛地点"
							  v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','competition_place')) || (!form['event_id'] && $check_field('add','competition_place'))" :disabled="disabledObj['competition_place_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','competition_place')">{{form['competition_place']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','game_time') || $check_field('add','game_time') || $check_field('set','game_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="比赛时间" prop="game_time">
					<el-date-picker :disabled="disabledObj['game_time_isDisabled']" v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','game_time')) || (!form['event_id'] && $check_field('add','game_time'))" id="game_time"
						v-model="form['game_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','game_time')">{{form['game_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','competition_cover') || $check_field('add','competition_cover') || $check_field('set','competition_cover')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="比赛封面" prop="competition_cover">
					<el-upload :disabled="disabledObj['competition_cover_isDisabled']" id="competition_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_competition_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','competition_cover')) || (!form['event_id'] && $check_field('add','competition_cover'))">
						<img v-if="form['competition_cover']" :src="$fullUrl(form['competition_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','competition_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['competition_cover'])" :preview-src-list="[$fullUrl(form['competition_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','competition_introduction') || $check_field('add','competition_introduction') || $check_field('set','competition_introduction')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="比赛简介" prop="competition_introduction">
					<el-input type="textarea" id="competition_introduction" v-model="form['competition_introduction']" placeholder="请输入比赛简介"
						v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','competition_introduction')) || (!form['event_id'] && $check_field('add','competition_introduction'))" :disabled="disabledObj['competition_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','competition_introduction')">{{form['competition_introduction']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="计时器标题" prop="timer_title">
					<el-input id="timer_title" v-model="form['timer_title']" placeholder="请输入计时器标题"
							  v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','timer_title')) || (!form['event_id'] && $check_field('add','timer_title'))" :disabled="disabledObj['timer_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','timer_title')">{{form['timer_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="计时开始时间" prop="timing_start_time">
					<el-date-picker id="timing_start_time" v-model="form['timing_start_time']" placeholder="选择计时器开始时间"
						v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','timing_start_time')) || (!form['event_id'] && $check_field('add','timing_start_time'))" :disabled="disabledObj['timing_start_time_isDisabled']" type="datetime" >
					</el-date-picker>
					<div v-else-if="$check_field('get','timing_start_time')">{{form['timing_start_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="计时结束时间" prop="timing_end_time">
					<el-date-picker id="timing_end_time" v-model="form['timing_end_time']" placeholder="选择计时器开始时间"
						v-if="user_group === '管理员' || (form['event_id'] && $check_field('set','timing_end_time')) || (!form['event_id'] && $check_field('add','timing_end_time'))" :disabled="disabledObj['timing_end_time_isDisabled']" type="datetime" >
					</el-date-picker>
					<div v-else-if="$check_field('get','timing_end_time')">{{form['timing_end_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "event_id",
				url_add: "~/api/event/add?",
				url_set: "~/api/event/set?",
				url_get_obj: "~/api/event/get_obj?",
				url_upload: "~/api/event/upload?",

				query: {
					"event_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"competition_place":'', // 比赛地点
					"game_time":'', // 比赛时间
					"competition_cover":'', // 比赛封面
					"competition_introduction":'', // 比赛简介
					"event_id": 0, // ID
					"timer_title":'', // 计时器标题
					"timing_start_time":'', // 计时开始时间
					"timing_end_time":'', // 计时结束时间

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"competition_place_isDisabled": false,
					"game_time_isDisabled": false,
					"competition_cover_isDisabled": false,
					"competition_introduction_isDisabled": false,
					"timer_title_isDisabled": false,
					"timing_start_time_isDisabled": false,
					"timing_end_time_isDisabled": false,
				},
				//项目类型选项列表
				list_project_type: [],

			}
		},
		methods: {
			/**
			 * 获取项目类型列表
			 */
			async get_list_project_type() {
				var json = await this.$get("~/api/project_type/get_list?");
				if(json.result && json.result.list){
					this.list_project_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传比赛封面
			 * @param {Object} param图片参数
			 */
			upload_competition_cover(param){
				this.uploadFile(param.file, "competition_cover");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["game_time"] = this.$toTime(parseInt(this.form["game_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["game_time"]) > 9999){
					this.form["game_time"] = this.$toTime(parseInt(this.form["game_time"]),"yyyy-MM-dd hh:mm:ss")
				}
				this.form["timing_start_time"] = this.$toTime(parseInt(this.form["timing_start_time"]),"yyyy-MM-dd hh:mm:ss")
				this.form["timing_end_time"] = this.$toTime(parseInt(this.form["timing_end_time"]),"yyyy-MM-dd hh:mm:ss")

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/event/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/event/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/event/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/event/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/event/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_project_type();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
