<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="运动会资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/referee/list', 'get')"
				:list="result_referee_full_name"
				title="裁判姓名"
				source_table="referee"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/referee/list', 'get')"
				:list="result_referee_gender"
				title="裁判性别"
				source_table="referee"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/referee/list', 'get')"
				:list="result_referee_referee_number"
				title="裁判裁判编号"
				source_table="referee"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_name"
				title="学生学生姓名"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_gender"
				title="学生性别"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_number"
				title="学生学生编号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/project_type/list', 'get')"
				:list="result_project_type_project_type"
				title="项目类型项目类型"
				source_table="project_type"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/event/list', 'get')"
				:list="result_event_entry_name"
				title="比赛项目项目名称"
				source_table="event"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/event/list', 'get')"
				:list="result_event_project_type"
				title="比赛项目项目类型"
				source_table="event"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/entry_information/list', 'get')"
				:list="result_entry_information_entry_name"
				title="参赛信息项目名称"
				source_table="entry_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/entry_information/list', 'get')"
				:list="result_entry_information_student_name"
				title="参赛信息学生姓名"
				source_table="entry_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/entry_results/list', 'get')"
				:list="result_entry_results_entry_name"
				title="参赛成绩项目名称"
				source_table="entry_results"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/entry_results/list', 'get')"
				:list="result_entry_results_student_name"
				title="参赛成绩学生姓名"
				source_table="entry_results"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/score_ranking/list', 'get')"
				:list="result_score_ranking_entry_name"
				title="成绩排名项目名称"
				source_table="score_ranking"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/score_ranking/list', 'get')"
				:list="result_score_ranking_student_name"
				title="成绩排名学生姓名"
				source_table="score_ranking"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_referee_full_name":[],
			"result_referee_gender":[],
			"result_referee_referee_number":[],
			"result_student_student_name":[],
			"result_student_gender":[],
			"result_student_student_number":[],
			"result_project_type_project_type":[],
			"result_event_entry_name":[],
			"result_event_project_type":[],
			"result_entry_information_entry_name":[],
			"result_entry_information_student_name":[],
			"result_entry_results_entry_name":[],
			"result_entry_results_student_name":[],
			"result_score_ranking_entry_name":[],
			"result_score_ranking_student_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_referee_full_name(){
		this.$get("~/api/referee/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_referee_full_name = json.result.list;
			result_referee_full_name.map(o => o.title = o['full_name'])
	  			this.result_referee_full_name = result_referee_full_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_referee_gender(){
		this.$get("~/api/referee/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_referee_gender = json.result.list;
			result_referee_gender.map(o => o.title = o['gender'])
	  			this.result_referee_gender = result_referee_gender
		 	}
		});
	},
	/**
	 * 获取referee_number
	 */
	get_referee_referee_number(){
		this.$get("~/api/referee/get_list?like=0", { page: 1, size: 10, "referee_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_referee_referee_number = json.result.list;
			result_referee_referee_number.map(o => o.title = o['referee_number'])
	  			this.result_referee_referee_number = result_referee_referee_number
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_student_student_name(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_name = json.result.list;
			result_student_student_name.map(o => o.title = o['student_name'])
	  			this.result_student_student_name = result_student_student_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_student_gender(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_gender = json.result.list;
			result_student_gender.map(o => o.title = o['gender'])
	  			this.result_student_gender = result_student_gender
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_student_student_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_number = json.result.list;
			result_student_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_student_number = result_student_student_number
		 	}
		});
	},
	/**
	 * 获取project_type
	 */
	get_project_type_project_type(){
		this.$get("~/api/project_type/get_list?like=0", { page: 1, size: 10, "project_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_project_type_project_type = json.result.list;
			result_project_type_project_type.map(o => o.title = o['project_type'])
	  			this.result_project_type_project_type = result_project_type_project_type
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_event_entry_name(){
		this.$get("~/api/event/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_event_entry_name = json.result.list;
			result_event_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_event_entry_name = result_event_entry_name
		 	}
		});
	},
	/**
	 * 获取project_type
	 */
	get_event_project_type(){
		this.$get("~/api/event/get_list?like=0", { page: 1, size: 10, "project_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_event_project_type = json.result.list;
			result_event_project_type.map(o => o.title = o['project_type'])
	  			this.result_event_project_type = result_event_project_type
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_entry_information_entry_name(){
		this.$get("~/api/entry_information/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_entry_information_entry_name = json.result.list;
			result_entry_information_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_entry_information_entry_name = result_entry_information_entry_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_entry_information_student_name(){
		this.$get("~/api/entry_information/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_entry_information_student_name = json.result.list;
			result_entry_information_student_name.map(o => o.title = o['student_name'])
	  			this.result_entry_information_student_name = result_entry_information_student_name
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_entry_results_entry_name(){
		this.$get("~/api/entry_results/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_entry_results_entry_name = json.result.list;
			result_entry_results_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_entry_results_entry_name = result_entry_results_entry_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_entry_results_student_name(){
		this.$get("~/api/entry_results/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_entry_results_student_name = json.result.list;
			result_entry_results_student_name.map(o => o.title = o['student_name'])
	  			this.result_entry_results_student_name = result_entry_results_student_name
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_score_ranking_entry_name(){
		this.$get("~/api/score_ranking/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_score_ranking_entry_name = json.result.list;
			result_score_ranking_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_score_ranking_entry_name = result_score_ranking_entry_name
		 	}
		});
	},
	/**
	 * 获取student_name
	 */
	get_score_ranking_student_name(){
		this.$get("~/api/score_ranking/get_list?like=0", { page: 1, size: 10, "student_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_score_ranking_student_name = json.result.list;
			result_score_ranking_student_name.map(o => o.title = o['student_name'])
	  			this.result_score_ranking_student_name = result_score_ranking_student_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_referee_full_name();
		this.get_referee_gender();
		this.get_referee_referee_number();
		this.get_student_student_name();
		this.get_student_gender();
		this.get_student_student_number();
		this.get_project_type_project_type();
		this.get_event_entry_name();
		this.get_event_project_type();
		this.get_entry_information_entry_name();
		this.get_entry_information_student_name();
		this.get_entry_results_entry_name();
		this.get_entry_results_student_name();
		this.get_score_ranking_entry_name();
		this.get_score_ranking_student_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_referee_full_name();
	  this.get_referee_gender();
	  this.get_referee_referee_number();
	  this.get_student_student_name();
	  this.get_student_gender();
	  this.get_student_student_number();
	  this.get_project_type_project_type();
	  this.get_event_entry_name();
	  this.get_event_project_type();
	  this.get_entry_information_entry_name();
	  this.get_entry_information_student_name();
	  this.get_entry_results_entry_name();
	  this.get_entry_results_student_name();
	  this.get_score_ranking_entry_name();
	  this.get_score_ranking_student_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
