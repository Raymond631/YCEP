<template>
  <!--  顶部导航栏-->
  <Top />
  <div class="container">
    <div class="main">
      <div class="congratulations" v-if="testVisible">
        <div class="card">
          恭喜你完成知识点和游戏的全部过程！来巩固一下知识吧！
        </div>
      </div>
      <div class="congratulations" v-if="reviewVisible">
        <div class="card">恭喜你完成试题！来查看一下错题和解析吧！</div>
      </div>
      <div class="test" v-if="testVisible">
        <div class="item" v-for="(item, itemIndex) in test" :key="itemIndex">
          <div class="question">
            <p class="index">{{ itemIndex + 1 }}、</p>
            <p class="text">{{ item.question }}</p>
          </div>
          <div class="question-image">
            <img :src="imgHost + item.imgSrc" alt="" />
          </div>
          <div class="choices">
            <div
              class="choice-item"
              v-for="(choice, choiceIndex) in item.choice"
              :key="choiceIndex"
              @click="choiceItemClick(itemIndex, choiceIndex)"
              :class="
                test[itemIndex].selected === choiceIndex + 1 ? 'selected' : ''
              "
            >
              <div class="choice-icon" v-if="choiceIndex === 0">A</div>
              <div class="choice-icon" v-if="choiceIndex === 1">B</div>
              <div class="choice-icon" v-if="choiceIndex === 2">C</div>
              <div class="choice-icon" v-if="choiceIndex === 3">D</div>
              <p class="choice-name">{{ choice }}</p>
            </div>
          </div>
        </div>
      </div>
      <div class="actions" v-if="testVisible">
        <button class="button cancel" @click="backToKlg">返回知识点</button>
        <button class="button confirm" @click="confirmTest">提交试卷</button>
      </div>

      <div class="review" v-if="reviewVisible">
        <div class="item" v-for="(item, itemIndex) in test" :key="itemIndex">
          <div class="question">
            <p class="index">{{ itemIndex + 1 }}、</p>
            <p class="text">{{ item.question }}</p>
          </div>
          <div class="choices">
            <div
              class="choice-item"
              v-for="(choice, choiceIndex) in item.choice"
              :key="choiceIndex"
              :class="[
                test[itemIndex].answer === choiceIndex + 1 ? 'right' : '',
                test[itemIndex].answer !== choiceIndex + 1 &&
                choiceIndex + 1 === test[itemIndex].selected
                  ? 'wrong'
                  : '',
              ]"
            >
              <div class="choice-icon" v-if="choiceIndex === 0">A</div>
              <div class="choice-icon" v-if="choiceIndex === 1">B</div>
              <div class="choice-icon" v-if="choiceIndex === 2">C</div>
              <div class="choice-icon" v-if="choiceIndex === 3">D</div>
              <p class="choice-name">{{ choice }}</p>
            </div>
          </div>
          <div class="explain">
            <p class="title">解析：</p>
            <p class="content text">{{ item.explain }}</p>
          </div>
        </div>
      </div>
      <div class="actions" v-if="reviewVisible">
        <button class="button cancel" @click="backToKlg">返回知识点</button>
        <button class="button confirm" @click="toThesis">生成论文</button>
      </div>
    </div>
    <div class="mask" v-if="scoreVisible"></div>
    <div class="score" v-if="scoreVisible">
      <!-- <el-icon class="close" size="30"><Close /></el-icon> -->
      <div class="content">
        <div class="results-summary-container">
          <div class="confetti">
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
            <div class="confetti-piece"></div>
          </div>
          <div class="results-summary-container__result">
            <div class="heading-tertiary">恭喜您</div>
            <div class="result-box">
              <div class="heading-primary">{{ userScore }}</div>
              <p class="result">of {{ fullScore }}</p>
            </div>
            <div class="result-text-box">
              <div
                class="heading-secondary"
                v-if="userScore / fullScore >= 0.9"
              >
                Excellent
              </div>
              <div
                class="heading-secondary"
                v-else-if="userScore / fullScore >= 0.8"
              >
                Great
              </div>
              <div
                class="heading-secondary"
                v-else-if="userScore / fullScore >= 0.6"
              >
                Good
              </div>
              <div class="heading-secondary" v-else>Sorry</div>
              <p class="paragraph" v-if="userScore / fullScore >= 0.9">
                在这次考试中，您的得分超过大部分同学！真棒，希望你在接下来的学习中再接再厉！
              </p>
              <p class="paragraph" v-else-if="userScore / fullScore < 0.6">
                在这次考试中，您还有所欠缺！在接下来的学习中要加油哦！
              </p>
              <p class="paragraph" v-else-if="userScore / fullScore >= 0.6">
                在这次考试中，您已掌握大部分知识！真棒，在接下来的学习中继续努力，更上一层楼！
              </p>
            </div>
            <div class="summary__cta">
              <button class="btn btn__continue" @click="toThesis">
                生成论文
              </button>
              <button class="btn btn__continue" @click="testReview">
                查看错题
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance } from "vue";
import Top from "../../components/top.vue";
import { getTest } from "../../api/game/review";

export default defineComponent({
  name: "review",
  data() {
    return {
      imgHost:
        getCurrentInstance()?.appContext.config.globalProperties.$imgHost,
      id: "",
      scoreVisible: false,
      test: [
        {
          question: "你说的国家大事不回家",
          choice: ["对", "错", "都行", "我不知道"],
          answer: 0,
          explain:
            "束带结发宏观环境水电费合计打撒,束带结发宏观环境水电费合计打撒,束带结发宏观环境水电费合计打撒,束带结发宏观环境水电费合计打撒,束带结发宏观环境水电费合计打撒",
          score: 20,
          selected: 0,
          imgSrc: "",
        },
      ],
      fullScore: 0,
      userScore: 90,
      testVisible: true,
      reviewVisible: false,
    };
  },
  components: { Top },
  mounted() {
    this.ready();
  },
  methods: {
    ready() {
      let gameID = sessionStorage.getItem("gameID")!;
      this.id = gameID;
      this.testContent();
    },
    backToKlg() {
      this.$router.go(-2);
    },
    backToGame() {
      this.$router.go(-1);
    },
    confirmTest() {
      let userScore = 0;
      for (let i = 0; i < this.test.length; i++) {
        if (this.test[i].selected == this.test[i].answer)
          userScore += this.test[i].score;
      }
      console.log("test:", this.test);
      this.userScore = userScore;
      this.scoreVisible = true;
      //返回顶部
      document.documentElement.scrollTop = 0;
    },
    testContent() {
      let that = this;
      getTest(this.id)
        .then((res: any) => {
          console.log("getTest");
          that.test = res.data;
          let score = 0;
          that.test.forEach((item, i) => {
            score += item.score;
            item.selected = 0;
          });
          that.fullScore = score;
          //添加写死的图片
          that.test[0].imgSrc = "question1.png";
          that.test[1].imgSrc = "question2.png";
          that.test[2].imgSrc = "question3.png";
          console.log(that.test);
        })
        .catch((err: any) => {
          console.log(err);
        });
    },
    testReview() {
      this.testVisible = false;
      this.reviewVisible = true;
      this.scoreVisible = false;
    },
    toThesis() {
      this.$router.push("/user/thesis");
    },
    choiceItemClick(itemIndex: number, choiceIndex: number) {
      this.test[itemIndex].selected = choiceIndex + 1;
    },
  },
});
</script>

<style lang="scss" scoped>
@import "../../assets/style/css/game/review.scss";
</style>
