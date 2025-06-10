<template>
	<div>
		<el-form-item v-if="$check_register_field('add','full_name','/referee/view')" label="姓名" prop="full_name">
		<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"></el-input>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','gender','/referee/view')" label="性别" prop="gender">
		<el-select id="gender" v-model="form['gender']">
			<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
			</el-option>
		</el-select>
		</el-form-item>
		<el-form-item v-if="$check_register_field('add','referee_number','/referee/view')" label="裁判编号" prop="referee_number">
		<el-input id="referee_number" v-model="form['referee_number']" placeholder="请输入裁判编号"></el-input>
		</el-form-item>

	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		props:{

			form:{
				type: Object,
				default: function(){
					return {
						"full_name":'',
						"gender":'',
						"referee_number":'',
					}
				}
			},

		},
		data() {
			return {
				field: "referee_id",
				url_add: "~/api/referee/add?",
				url_set: "~/api/referee/set?",
				url_upload: "~/api/referee/upload?",
				list_gender: ['男','女'],

				rules: {
					"full_name": [ ],
					"gender": [ ],
					"referee_number": [ {required: true,message: '裁判编号不能为空'}, ],
				}

			}
		},
		methods: {

		},
		created() {
		}
	}
</script>
