<template>
  <!--  顶部导航栏-->
  <Top/>

  <div class="container">
    <div class="main">
      <!-- 顶部轮播图 -->
      <div class="title-block">
        <el-carousel :interval="10000" height="500px">
          <el-carousel-item v-for="item in imageList" :key="item">
            <img :src="imgHost + item" alt="轮播图"/>
          </el-carousel-item>
        </el-carousel>
      </div>

      <div class="tags">
        <div class="tag-item">
          <el-icon size="40px">
            <Cpu/>
          </el-icon>
          <p class="tag-text">AI协助</p>
        </div>
        <div class="tag-item">
          <el-icon size="40px">
            <VideoPlay/>
          </el-icon>
          <p class="tag-text">配套游戏</p>
        </div>
        <div class="tag-item">
          <el-icon size="40px">
            <ChromeFilled/>
          </el-icon>
          <p class="tag-text">支持联机</p>
        </div>
        <div class="tag-item">
          <el-icon size="40px">
            <DocumentChecked/>
          </el-icon>
          <p class="tag-text">巩固练习</p>
        </div>
        <div class="tag-item">
          <el-icon size="40px">
            <Document/>
          </el-icon>
          <p class="tag-text">生成论文</p>
        </div>
        <div class="tag-item">
          <el-icon size="40px">
            <DataAnalysis/>
          </el-icon>
          <p class="tag-text">数据可视</p>
        </div>
      </div>

      <div class="body">
        <!--        <div class="left">-->
        <!--        </div>-->
        <div class="right">
          <!--知识点推荐（可左右翻页）-->
          <template class="block">
            <div class="block-title"><p>您可能感兴趣的知识</p></div>
            <div class="block-title eng">
              <p>Knowledge That May Interest You</p>
            </div>
            <div class="block-main">
              <div class="item1" @click="klgDetailBtnClick(1)">
                <div class="image">
                  <img
                      alt="知识点图片"
                      src="https://images.pexels.com/photos/45072/pexels-photo-45072.jpeg?auto=compress&cs=tinysrgb&w=800"
                  />
                </div>
                <div class="cover">
                  <span>
                    <p class="p1">基础电路分析</p>
                    <el-tag>作者：南桥几晴秋</el-tag>
                  </span>
                  <p class="p2">
                    你有想过家里的灯、空调等电器是怎么亮起来的吗？让我们一起来学习一下基础电路结构的设计吧！
                  </p>
                </div>
              </div>
              <div class="item1" @click="klgDetailBtnClick(6)">
                <div class="image">
                  <img
                      alt="知识点图片"
                      src="https://images.pexels.com/photos/442150/pexels-photo-442150.jpeg?auto=compress&cs=tinysrgb&w=800"
                  />
                </div>
                <div class="cover">
                  <span>
                    <p class="p1">台式电脑电路连接</p>
                    <el-tag>作者：硬件找我</el-tag>
                  </span>
                  <p class="p2">
                    上计算机课的时候有注意过台式电脑的主机是怎么跟显示器相连的吗？一起来了解一下吧！
                  </p>
                </div>
              </div>
            </div>
            <template class="block-cards">
              <div
                  v-for="item in knowledgeList"
                  :key="item.knowledgeId"
                  class="card"
                  @click="klgDetailBtnClick(item.knowledgeId)"
              >
                <div class="card-image">
                  <img :src="imgHost + item.imgSrc" alt="背景图片"/>
                </div>
                <div class="card-details">
                  <p class="text-title">{{ item.title }}</p>
                  <p class="text-body">{{ item.description }}</p>
                  <el-tag class="text-author">作者：{{ item.author }}</el-tag>
                </div>
                <button class="card-button">知识</button>
              </div>
            </template>
          </template>

          <!--游戏推荐（可左右翻页）-->
          <template class="block">
            <div class="block-title" style="margin-top: 50px">
              <p>您可能感兴趣的游戏</p>
            </div>
            <div class="block-title eng">
              <p>Games That May Interest You</p>
            </div>
            <div class="block-main" style="height: 330px">
              <div class="item2">
                <el-carousel :interval="10000" height="300px">
                  <el-carousel-item v-for="item in imageList2" :key="item">
                    <img :src="imgHost + item" alt="轮播图"/>
                  </el-carousel-item>
                </el-carousel>
              </div>
            </div>
            <template class="block-cards">
              <div
                  v-for="item in gameList"
                  :key="item.id"
                  class="card"
                  @click="klgDetailBtnClick(item.id)"
              >
                <div class="card-image">
                  <img :src="imgHost + item.imgSrc" alt="背景图片"/>
                </div>
                <div class="card-details">
                  <p class="text-title">{{ item.title }}</p>
                  <p class="text-body">{{ item.description }}</p>
                  <el-tag class="text-author">作者：{{ item.author }}</el-tag>
                </div>
                <button class="card-button">游戏</button>
              </div>
            </template>
          </template>
        </div>
      </div>

      <div class="interest">
        <el-dialog
            v-model="chooseInterestVisible"
            center
            title="选择你感兴趣的知识点"
            top="130px"
            width="32%"
        >
          <div class="content">
            <div
                v-for="(kind, kindIndex) in kindItemList"
                :key="kindIndex"
                class="kind"
            >
              <p class="kind-name">{{ kind.kindName }}</p>
              <div class="item">
                <p
                    v-for="(item, itemIndex) in kind.items"
                    :key="itemIndex"
                    :class="item.isSelected === true ? 'selected' : ''"
                    class="item-name"
                    @click="interestItemClick(kindIndex, itemIndex)"
                >
                  {{ item.itemName }}
                </p>
              </div>
            </div>
          </div>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="chooseInterestVisible = false">取消</el-button>
              <el-button type="primary" @click="postInterest()">
                确认
              </el-button>
            </span>
          </template>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance} from "vue";
import Top from "../../components/top.vue";
import {getGame, getKnowledge, postInterest} from "../../api/knowledge/index";
import {getCategory} from "../../api/knowledge/kind";

export default defineComponent({
  name: "index",
  data() {
    return {
      imgHost:
      getCurrentInstance()?.appContext.config.globalProperties.$imgHost,
      //用户是否已选择感兴趣的知识点
      isSelectedInterest: 0,
      //选择感兴趣的知识点
      chooseInterestVisible: true,
      kindItemList: [
        {
          kindId: 1,
          kindName: "编程",
          items: [
            {
              itemId: 1,
              itemName: "排序算法",
              isSelected: true,
            },
            {
              itemId: 2,
              itemName: "链表",
              isSelected: false,
            },
            {
              itemId: 3,
              itemName: "数组",
              isSelected: true,
            },
            {
              itemId: 4,
              itemName: "对象",
              isSelected: false,
            },
            {
              itemId: 5,
              itemName: "排序算法",
              isSelected: true,
            },
            {
              itemId: 6,
              itemName: "链表",
              isSelected: false,
            },
            {
              itemId: 7,
              itemName: "数组",
              isSelected: true,
            },
          ],
        },
        {
          kindId: 2,
          kindName: "材料",
          items: [
            {
              itemId: 1,
              itemName: "排序算法",
              isSelected: false,
            },
            {
              itemId: 2,
              itemName: "链表",
              isSelected: false,
            },
            {
              itemId: 3,
              itemName: "数组",
              isSelected: false,
            },
            {
              itemId: 4,
              itemName: "对象",
              isSelected: false,
            },
          ],
        },
        {
          kindId: 3,
          kindName: "物理",
          items: [
            {
              itemId: 1,
              itemName: "排序算法",
              isSelected: false,
            },
            {
              itemId: 2,
              itemName: "链表",
              isSelected: false,
            },
            {
              itemId: 3,
              itemName: "数组",
              isSelected: false,
            },
            {
              itemId: 4,
              itemName: "对象",
              isSelected: false,
            },
          ],
        },
        {
          kindId: 4,
          kindName: "化学",
          items: [
            {
              itemId: 1,
              itemName: "排序算法",
              isSelected: false,
            },
            {
              itemId: 2,
              itemName: "链表",
              isSelected: false,
            },
            {
              itemId: 3,
              itemName: "数组",
              isSelected: false,
            },
            {
              itemId: 4,
              itemName: "对象",
              isSelected: false,
            },
          ],
        },
      ],

      interest: [],
      //当前页
      pageNum: 1,
      //一页的个数
      pageSize: 5,
      imageList: ["item1.jpg", "item2.jpg", "item3.jpg"],
      imageList2: ["game1.png", "game2.png", "game3.jpg"],
      knowledgeList: [
        {
          knowledgeId: 1,
          title: "string",
          author: "string",
          kindName: "编程",
          description: "string",
          imgSrc: "/src/assets/images/灯泡.jpg",
        },
      ],
      gameList: [
        {
          id: 1,
          imgSrc: "/src/assets/images/demo/game1.png",
          title: "法庭疑案",
          kind: 2,
          kindName: "材料",
          description:
              "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
          author: "机智的皇冠",
        },
        {
          id: 2,
          imgSrc: "/src/assets/images/demo/game2.png",
          title: "电路排查",
          kind: 3,
          kindName: "物理",
          description:
              "莉莉在正常工作，办公室的台灯却突然爆炸了！快来帮帮她吧！",
          author: "看手机东方红",
        },
        {
          id: 3,
          imgSrc: "/src/assets/images/demo/game3.png",
          title: "三态变化",
          kind: 4,
          kindName: "化学",
          description:
              "空气里究竟都有些什么状态的物质呢？小白走在路上发出了这样的疑问。",
          author: "梵蒂冈",
        },
        {
          id: 4,
          imgSrc: "/src/assets/images/demo/game1.png",
          title: "法庭疑案",
          kind: 2,
          kindName: "材料",
          description:
              "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
          author: "机智的皇冠",
        },
        {
          id: 5,
          imgSrc: "/src/assets/images/demo/game2.png",
          title: "电路排查",
          kind: 3,
          kindName: "物理",
          description:
              "莉莉在正常工作，办公室的台灯却突然爆炸了！快来帮帮她吧！",
          author: "看手机东方红",
        },
      ],
    };
  },
  components: {Top},
  mounted() {
    this.ready();
  },
  methods: {
    ready() {
      let username = sessionStorage.getItem("username");
      let isSelectedInterest = sessionStorage.getItem("isSelectedInterest");
      this.isSelectedInterest = Number(isSelectedInterest);
      this.knowledgeList = [];
      this.gameList = [];
      if (this.isSelectedInterest == 1 && username !== "") {
        this.chooseInterestVisible = false;
        this.showKnowledges();
        this.showGames();
      } else {
        sessionStorage.setItem("isSelectedInterest", "1");
        this.chooseInterestVisible = true;
        this.category();
      }
    },

    showKnowledges() {
      let that = this;
      getKnowledge()
          .then((res: any) => {
            console.log(res);
            that.pageNum++;
            that.knowledgeList = res.data;
          })
          .catch((err: any) => {
            console.log(err);
          });
    },

    showGames() {
      let that = this;
      getGame()
          .then((res: any) => {
            console.log(res);
            that.gameList = res.data;
          })
          .catch((err: any) => {
            console.log(err);
          });
    },

    postInterest() {
      let that = this;
      this.kindItemList.forEach((kind: any, i: any) => {
        kind.items.forEach((item: any, i: any) => {
          if (item.isSelected) {
            this.interest.push(item.itemId);
          }
        });
      });
      postInterest(this.interest)
          .then((res: any) => {
            console.log("postInterest");
            this.chooseInterestVisible = false;
            this.showKnowledges();
            this.showGames();
          })
          .catch((err: any) => {
            console.log(err);
          });
    },

    category() {
      let that = this;
      getCategory()
          .then((res: any) => {
            console.log("getInterest");
            console.log(res);
            that.kindItemList = res.data;
            that.kindItemList.forEach((kind: any, i: any) => {
              kind.items.forEach((item: any, i: any) => {
                item.isSelected = false;
              });
            });
          })
          .catch((err: any) => {
            console.log(err);
          });
    },

    interestItemClick(kindIndex: number, itemIndex: number) {
      let isSelected = this.kindItemList[kindIndex].items[itemIndex].isSelected;
      this.kindItemList[kindIndex].items[itemIndex].isSelected = !isSelected;
    },

    //点击进入知识点详情
    klgDetailBtnClick(id: number) {
      this.$router.push("/knowledge/detail/" + id);
    },
  },
});
</script>

<style lang="scss" scoped>
@import "../../assets/style/css/knowledge/index.scss";
</style>
