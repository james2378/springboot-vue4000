<template>
	<div class="diy_edit page_entry_information" id="entry_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','entry_name') || $check_field('add','entry_name') || $check_field('get','entry_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>项目名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_entry_name" v-model="form['entry_name']" placeholder="请输入项目名称" v-if="(form['entry_name'] && $check_field('set','entry_name')) || (!form['entry_name'] && $check_field('add','entry_name'))"  :disabled="disabledObj['entry_name_isDisabled']"/>
							<span v-else-if="$check_field('get','entry_name')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','project_type') || $check_field('add','project_type') || $check_field('get','project_type')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>项目类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_project_type" v-model="form['project_type']" placeholder="请输入项目类型" v-if="(form['project_type'] && $check_field('set','project_type')) || (!form['project_type'] && $check_field('add','project_type'))"  :disabled="disabledObj['project_type_isDisabled']"/>
							<span v-else-if="$check_field('get','project_type')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','competition_place') || $check_field('add','competition_place') || $check_field('get','competition_place')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>比赛地点:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_competition_place" v-model="form['competition_place']" placeholder="请输入比赛地点" v-if="(form['competition_place'] && $check_field('set','competition_place')) || (!form['competition_place'] && $check_field('add','competition_place'))"  :disabled="disabledObj['competition_place_isDisabled']"/>
							<span v-else-if="$check_field('get','competition_place')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','game_time') || $check_field('add','game_time') || $check_field('get','game_time')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>比赛时间:
							</span>
						</div>
						<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['game_time_isDisabled']" id="form_game_time" v-model="form['game_time']" placeholder="请输入比赛时间" v-if="(form['game_time'] && $check_field('set','game_time')) || (!form['game_time'] && $check_field('add','game_time'))" />
							<span v-else-if="$check_field('get','game_time')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_number') || $check_field('add','student_number') || $check_field('get','student_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>学生编号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_number" :disabled="disabledObj['student_number_isDisabled']" v-model="form['student_number']" v-if="(form['student_number'] && $check_field('set','student_number')) || (!form['student_number'] && $check_field('add','student_number'))" >
								<option v-for="o in list_user_student_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student_number')">{{ form['student_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_name') || $check_field('add','student_name') || $check_field('get','student_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>学生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_name" v-model="form['student_name']" placeholder="请输入学生姓名" v-if="(form['student_name'] && $check_field('set','student_name')) || (!form['student_name'] && $check_field('add','student_name'))"  :disabled="disabledObj['student_name_isDisabled']"/>
							<span v-else-if="$check_field('get','student_name')">{{ form['student_name'] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/entry_information/get_obj?",
				url_add: "~/api/entry_information/add?",
				url_set: "~/api/entry_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"entry_name": "",
					"project_type": "",
					"competition_place": "",
					"game_time": "",
					"student_number": 0,
					"student_name": "",
					"entry_information_id": 0,
				},

				obj: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"competition_place":'', // 比赛地点
					"game_time": "1970-01-01 00:00:00",
					"student_number": 0, // 学生编号
					"student_name":'', // 学生姓名
					"entry_information_id": 0,
				},

				// 表单字段
				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"competition_place":'', // 比赛地点
					"game_time": "1970-01-01 00:00:00",
					"student_number": 0, // 学生编号
					"student_name":'', // 学生姓名
					"entry_information_id": 0,

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"competition_place_isDisabled": false,
					"game_time_isDisabled": false,
					"student_number_isDisabled": false,
					"student_name_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],

				// ID字段
				field: "entry_information_id",
			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_number() {
				// if(this.user_group !== "管理员" && this.form["student_number"] === 0) {
				//     this.form["student_number"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=学生");
				if(json.result && json.result.list){
					this.list_user_student_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_student_number(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student_number"] = user_id
								_this.disabledObj['student_number' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="student_number") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/entry_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["game_time"] = this.$toTime(parseInt(this.form["game_time"]),"yyyy-MM-dd hh:mm:ss")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_student_number();
			this.get_list_user_student_number();
		}
	}
</script>

<style>
</style>
