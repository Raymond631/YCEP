<template>
  <!--  顶部导航栏-->
  <Top/>
  <div class="container">
    <div class="main">
      <div class="left">
        <div class="self-card">
          <div class="card">
            <div class="card__img">
              <img alt="背景图片" src="../../assets/images/techBack.jpg"/>
              <el-icon class="edit-bg" size="26">
                <Edit/>
              </el-icon>
            </div>
            <div class="card__avatar">
              <el-avatar
                  alt="背景图片"
                  src="https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F1110%2F0be1778cj00rl4jow0010c000hs00hsg.jpg&thumbnail=660x2147483647&quality=80&type=jpg"
              />
            </div>
            <div class="card__title">
              {{ user.username }}
              <el-icon v-if="user.sex === '女'" class="sex" color="#ef20de">
                <Female/>
              </el-icon>
              <el-icon v-if="user.sex === '男'" class="sex" color="#2062ef">
                <Male/>
              </el-icon>
            </div>
            <div class="card__subtitle">{{ user.email }}</div>
            <div class="card__wrapper">
              <button class="card__btn" @click="editInfoClick">编辑信息</button>
              <button class="card__btn" @click="signOutClick">退出系统</button>
            </div>
          </div>
        </div>
        <div class="left-nav">
          <el-row class="tac">
            <el-col>
              <el-menu
                  class="el-menu-vertical-demo"
                  default-active="1"
                  @select="leftNavClick"
              >
                <el-menu-item index="1">
                  <el-icon>
                    <Circle-Check/>
                  </el-icon>
                  <span>已学习知识</span>
                </el-menu-item>
                <el-menu-item index="2">
                  <el-icon>
                    <Document/>
                  </el-icon>
                  <span>学习论文</span>
                </el-menu-item>
                <el-menu-item index="3">
                  <el-icon>
                    <View/>
                  </el-icon>
                  <span>浏览记录</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <el-icon>
                    <Star/>
                  </el-icon>
                  <span>我的感兴趣</span>
                </el-menu-item>
                <el-menu-item index="5">
                  <el-icon>
                    <User/>
                  </el-icon>
                  <span>个人账户</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="right">
        <div v-if="currentNav == 1" class="detail page1">
          <div class="title">已学习知识>></div>
          <br/>
          <hr/>
          <div class="kind">
            <div
                v-for="(kind, index) in kindList"
                :key="index"
                :class="{ selected: selectedKind == index }"
                class="title-item"
                @click="kindClick(index)"
            >
              <p class="text">{{ kind }}</p>
            </div>
          </div>
          <div class="content">
            <div
                v-for="item in knowledgeInfoList"
                :key="item.id"
                class="item"
                @click="klgDetailBtnClick(item.id)"
            >
              <div class="knowledge">
                <div class="image">
                  <img :src="imgHost + item.imgSrc" alt="知识点图片"/>
                </div>
                <div class="info">
                  <p class="title-tag">{{ item.title }}</p>
                  <p class="desc">{{ item.content }}</p>
                  <p class="author">作者：{{ item.author }}</p>
                </div>
              </div>
              <!--游戏-->
              <div class="game">
                <div class="card" @click="klgDetailBtnClick(item.id)">
                  <div class="card-image">
                    <img :src="imgHost + item.game.imgSrc" alt="背景图片"/>
                  </div>
                  <div class="card-details">
                    <p class="text-title">{{ item.game.title }}</p>
                    <el-tag class="text-author"
                    >作者：{{ item.game.author }}
                    </el-tag>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="pagination">
            <el-pagination
                v-model:currentPage="paginationConfig.currentPage"
                :page-size="paginationConfig.pageSize"
                :total="paginationConfig.total"
                layout="total, prev, pager, next"
                @current-change="handlePageChange"
            />
          </div>
        </div>
        <div v-if="currentNav == 2" class="detail page2">
          <div class="title">学习论文>></div>
          <br/>
          <hr/>
          <div class="content">
            <div v-for="(thesis, index) in thesisList" class="item">
              <div class="card_box" @click="thesisDetailClick(index)">
                <span></span>
                <p class="text1">{{ thesis.title }}</p>
                <p class="text2">
                  {{ thesis.keywords[0] }};{{ thesis.keywords[1] }}
                </p>
                <p class="text3">{{ thesis.brief }}</p>
                <p class="text4">{{ thesis.time }}</p>
              </div>
            </div>
          </div>
          <div class="pagination">
            <el-pagination
                v-model:currentPage="paginationConfig.currentPage"
                :page-size="paginationConfig.pageSize"
                :total="paginationConfig.total"
                layout="total, prev, pager, next"
                @current-change="handlePageChange"
            />
          </div>
        </div>
        <div v-if="currentNav == 3" class="detail page1">
          <div class="title">浏览记录>></div>
          <br/>
          <hr/>
          <div class="kind">
            <div
                v-for="(kind, index) in kindList"
                :key="index"
                :class="{ selected: selectedKind == index }"
                class="title-item"
                @click="kindClick(index)"
            >
              <p class="text">{{ kind }}</p>
            </div>
          </div>
          <div class="content">
            <div
                v-for="item in knowledgeInfoList"
                :key="item.id"
                class="item"
                @click="klgDetailBtnClick(item.id)"
            >
              <div class="knowledge">
                <div class="image">
                  <img :src="item.imgSrc" alt="知识点图片"/>
                </div>
                <div class="info">
                  <p class="title-tag">{{ item.title }}</p>
                  <p class="desc">{{ item.content }}</p>
                  <p class="author">作者：{{ item.author }}</p>
                </div>
              </div>
              <!--游戏-->
              <div class="game">
                <div class="card" @click="klgDetailBtnClick(item.id)">
                  <div class="card-image">
                    <img :src="item.game.imgSrc" alt="背景图片"/>
                  </div>
                  <div class="card-details">
                    <p class="text-title">{{ item.game.title }}</p>
                    <el-tag class="text-author"
                    >作者：{{ item.game.author }}
                    </el-tag>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="pagination">
            <el-pagination
                v-model:currentPage="paginationConfig.currentPage"
                :page-size="paginationConfig.pageSize"
                :total="paginationConfig.total"
                layout="total, prev, pager, next"
                @current-change="handlePageChange"
            />
          </div>
        </div>
        <div v-if="currentNav == 4" class="detail page1">
          <div class="title">我的感兴趣>></div>
          <br/>
          <hr/>
          <div class="kind">
            <div
                v-for="(kind, index) in kindList"
                :key="index"
                :class="{ selected: selectedKind == index }"
                class="title-item"
                @click="kindClick(index)"
            >
              <p class="text">{{ kind }}</p>
            </div>
          </div>
          <div class="content">
            <div
                v-for="item in knowledgeInfoList"
                :key="item.id"
                class="item"
                @click="klgDetailBtnClick(item.id)"
            >
              <div class="knowledge">
                <div class="image">
                  <img :src="item.imgSrc" alt="知识点图片"/>
                </div>
                <div class="info">
                  <p class="title-tag">{{ item.title }}</p>
                  <p class="desc">{{ item.content }}</p>
                  <p class="author">作者：{{ item.author }}</p>
                </div>
              </div>
              <!--游戏-->
              <div class="game">
                <div class="card" @click="klgDetailBtnClick(item.id)">
                  <div class="card-image">
                    <img :src="item.game.imgSrc" alt="背景图片"/>
                  </div>
                  <div class="card-details">
                    <p class="text-title">{{ item.game.title }}</p>
                    <el-tag class="text-author"
                    >作者：{{ item.game.author }}
                    </el-tag>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="pagination">
            <el-pagination
                v-model:currentPage="paginationConfig.currentPage"
                :page-size="paginationConfig.pageSize"
                :total="paginationConfig.total"
                layout="total, prev, pager, next"
                @current-change="handlePageChange"
            />
          </div>
        </div>
        <div v-if="currentNav == 5" class="detail page3">
          <div class="title">个人账户>></div>
          <br/>
          <hr/>
          <div class="title2">个人信息>></div>
          <el-form :inline="true" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="user.username" type="text"/>
            </el-form-item>
            <el-form-item label="学校">
              <el-input v-model="user.school" type="text"/>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="user.phone" type="text"/>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="user.sex">
                <el-radio label="男"/>
                <el-radio label="女"/>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="user.email" style="width: 500px" type="text"/>
            </el-form-item>
            <el-form-item label="个人简介">
              <el-input
                  v-model="user.description"
                  :autosize="{ minRows: 3 }"
                  style="width: 500px"
                  type="textarea"
              />
            </el-form-item>
            <el-button type="primary" @click="userinfoEditClick"
            >保存
            </el-button
            >
          </el-form>
          <div class="title2" style="margin-top: 50px">感兴趣的知识点>></div>
          <div class="interest">
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
            <el-button type="primary" @click="interestEditClick"
            >保存
            </el-button
            >
          </div>
        </div>
        <div v-if="thesisVisible" class="detail thesis">
          <div class="thesisTop">
            <div class="title">论文详情>></div>
            <div class="btn-container">
              <button style="margin-right: 20px" @click="downloadThesis()">
                下载论文
              </button>
              <button @click="closeThesisClick">关闭</button>
            </div>
          </div>
          <el-scrollbar height="830px">
            <div id="printDiv" class="paper-content">
              <div class="block1">
                <p class="text1">{{ thesisList[this.currentThesis].time }}</p>
                <p class="text2">青少年创新教育平台</p>
                <p class="text1">
                  {{ thesisList[this.currentThesis].kind }}/{{
                    thesisList[this.currentThesis].item
                  }}
                </p>
              </div>
              <hr/>
              <hr/>
              <div class="block2">
                <p class="text1">{{ thesisList[this.currentThesis].title }}</p>
                <p class="text2">{{ thesisList[this.currentThesis].author }}</p>
                <p class="text3">
                  （{{ thesisList[this.currentThesis].address }}）
                </p>
              </div>
              <br/>
              <div class="block3">
                <p class="text1">摘要：</p>
                <p class="text2">
                  &emsp;&emsp;{{ thesisList[this.currentThesis].brief }}
                </p>
                <p class="text1">
                  关键词：&emsp;
                  <template
                      v-for="keyword in thesisList[this.currentThesis].keywords"
                  >{{ keyword }};&emsp;
                  </template
                  >
                </p>
              </div>
              <br/><br/>
              <div class="block4">
                <template
                    v-for="chapter in thesisList[this.currentThesis].content"
                >
                  <p class="text1">{{ chapter.chapter }}</p>
                  <p class="text2">&emsp;&emsp;{{ chapter.text }}</p>
                </template>
              </div>
              <br/><br/>
              <hr/>
              <hr/>
              <div class="block1">
                <p class="text1">{{ thesisList[this.currentThesis].time }}</p>
                <p class="text2">青少年创新教育平台</p>
                <p class="text1">
                  {{ thesisList[this.currentThesis].kind }}/{{
                    thesisList[this.currentThesis].item
                  }}
                </p>
              </div>
            </div>
          </el-scrollbar>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, getCurrentInstance} from "vue";
import Top from "../../components/top.vue";
import {ElMessage} from "element-plus";
import {getInterest, getUserInfo} from "../../api/user/selfCenter";
import {getCategory} from "@/api/knowledge/kind";
import {postInterest} from "@/api/knowledge";
import {getPdf} from "@/utils/htmlToPdf";

export default defineComponent({
  name: "selfCenter",
  data() {
    return {
      imgHost:
      getCurrentInstance()?.appContext.config.globalProperties.$imgHost,
      data: "",
      currentNav: 1,
      paginationConfig: {
        currentPage: 1, // 当前页码
        pageSize: 4, // 每页显示的条数
        pageCount: 1, //总共有多少页
        total: 10, // 总条数
      },
      user: {
        account: 3,
        username: "pr",
        sex: "男",
        phone: "18312341234",
        email: "asdfghhjkl@test.com",
        school: "中南",
        description: "无",
      },
      knowledgeInfoList: [
        {
          id: 1,
          imgSrc: "knowledgeTest.jpg",
          title: "Spring Boot 单元测试",
          content:
              "Spring Boot 中进行单元测试是一个常见的做法，可以帮助你验证应用程序的各个组件是否按预期工作。所以我们有必要去学习一番！一、什么是单元测试？🍭 单元测试（unit testing），是指对软件中的最小可测试单元进行检查和验证的过程就叫单元测试。单元测试是开发者编写的一小段代码，用于检验被测代码的⼀个很小的、很明确的（代码）功能是否正确。执行单元测试就是为了证明某段代码的执行结果是否符合我们的预期。如果测试结果符合我们的预期，称之为测试通过，否则就是测试未通过（或者叫测试失败）。",
          author: "冷雪兮",
          game: {
            id: 1,
            imgSrc: "game1.png",
            title: "法庭疑案",
            kind: 2,
            kindName: "材料",
            body: "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
            author: "机智的皇冠",
          },
        },
        {
          id: 2,
          imgSrc: "knowledgeTest.jpg",
          title: "Spring Boot 单元测试",
          content:
              "Spring Boot 中进行单元测试是一个常见的做法，可以帮助你验证应用程序的各个组件是否按预期工作。所以我们有必要去学习一番！一、什么是单元测试？🍭 单元测试（unit testing），是指对软件中的最小可测试单元进行检查和验证的过程就叫单元测试。单元测试是开发者编写的一小段代码，用于检验被测代码的⼀个很小的、很明确的（代码）功能是否正确。执行单元测试就是为了证明某段代码的执行结果是否符合我们的预期。如果测试结果符合我们的预期，称之为测试通过，否则就是测试未通过（或者叫测试失败）。",
          author: "冷雪兮",
          game: {
            id: 1,
            imgSrc: "game2.png",
            title: "法庭疑案",
            kind: 2,
            kindName: "材料",
            body: "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
            author: "机智的皇冠",
          },
        },
        {
          id: 1,
          imgSrc: "knowledgeTest.jpg",
          title: "Spring Boot 单元测试",
          content:
              "Spring Boot 中进行单元测试是一个常见的做法，可以帮助你验证应用程序的各个组件是否按预期工作。所以我们有必要去学习一番！一、什么是单元测试？🍭 单元测试（unit testing），是指对软件中的最小可测试单元进行检查和验证的过程就叫单元测试。单元测试是开发者编写的一小段代码，用于检验被测代码的⼀个很小的、很明确的（代码）功能是否正确。执行单元测试就是为了证明某段代码的执行结果是否符合我们的预期。如果测试结果符合我们的预期，称之为测试通过，否则就是测试未通过（或者叫测试失败）。",
          author: "冷雪兮",
          game: {
            id: 1,
            imgSrc: "game3.jpg",
            title: "法庭疑案",
            kind: 2,
            kindName: "材料",
            body: "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
            author: "机智的皇冠",
          },
        },
        {
          id: 1,
          imgSrc: "knowledgeTest.jpg",
          title: "Spring Boot 单元测试",
          content:
              "Spring Boot 中进行单元测试是一个常见的做法，可以帮助你验证应用程序的各个组件是否按预期工作。所以我们有必要去学习一番！一、什么是单元测试？🍭 单元测试（unit testing），是指对软件中的最小可测试单元进行检查和验证的过程就叫单元测试。单元测试是开发者编写的一小段代码，用于检验被测代码的⼀个很小的、很明确的（代码）功能是否正确。执行单元测试就是为了证明某段代码的执行结果是否符合我们的预期。如果测试结果符合我们的预期，称之为测试通过，否则就是测试未通过（或者叫测试失败）。",
          author: "冷雪兮",
          game: {
            id: 1,
            imgSrc: "game4.jpg",
            title: "法庭疑案",
            kind: 2,
            kindName: "材料",
            body: "放学回家的小军路上做了一件事，竟然犯了法！法官有点发愁！",
            author: "机智的皇冠",
          },
        },
      ],
      thesisList: [
        {
          id: 1,
          time: "2023/8/6",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
        {
          id: 2,
          time: "2023/8/6",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
        {
          id: 3,
          time: "2023/8/6",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
        {
          id: 4,
          time: "2023/8/6",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
        {
          id: 5,
          time: "2023/8/6",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
        {
          id: 6,
          time: "2023/8/7",
          kind: "物理",
          item: "电路",
          title: "一般家庭电路的连接分析",
          author: "蛄蛹者",
          address: "优胜北路第三小学，湖南 长沙，410000",
          brief:
              "针对传统的无线传感器网络电源电路在电流能量的存储与分配方面存在的不足，提出了无线传感器网络电源电路优化改进方法。首先，构建电源电路优化配置模型，获取网络节点约束平衡功率指标；其次，结合网络电源开关种类，设计网络电源电路拓扑结构；再次，对传感器中所有的电源网络进行标号处理，采用双锂电池供电，设计传感器网络节点管理电路；最后，根据脉冲宽度与脉冲频率的特点，共同优化改进开关电源的调制模式。",
          keywords: ["无线传感器", "网络", "电源电路", "电源效率"],
          content: [
            {
              chapter: "一、项目背景",
              text: "目前，越来越多的行业应用无线传感器网络技术，使网络电源电路的节点功能不断增加。无线传感器网络电源电路的工作原理包括电源管理系统中能量流动原理、功率跟踪原理及充电原理。电源电路中的能量流动控制主要通过管理开关元件的导通与关断功能，实现能量流动方向控制与引导的目标。在电路管理中，根据无线传感器网络电源电路能量流动类型的不同，分为能量流动负载模型和电源模型。在电源管理电路中包括两种负载，分别为电池和电路节点电路的输出能量在为铅酸电池充电的同时，还能为超级电容充电，保证了无线传感器负载电路的稳定运行。",
            },
            {
              chapter: "二、设计网络电源电路拓扑结构",
              text: "在开关电源技术快速发展的趋势下，网络电源开关的种类越来越多，电路的拓扑结构也越来越复杂。采用 Buck-Boost 拓扑结构，再结合隔离变压器的调节控制作用，生成电源电路中常用的变换器。开关稳压电源与开关降压电源串联，将电源电压进行有功转换，控制晶体管的导通与关断时间，从而调节输出电压。该电路用于无线传感器网络的电源电路，其中包括电容和负载。",
            },
            {
              chapter: "三、仿真实验",
              text: "本次实验选择具有无线接入功能的网关抓包工具与具有无线传感器网络节点的设备，在单片机开发的 C 语言集成开发环境下，硬件配置为 3.20GHz的 CPU 和 4.00 GB 内存的 PC 机，软件配置为Windows7SP1 操作系统，运行环境为 Visual Stu‐dio2010。利用NS2网络仿真软件进行无线传感器网络仿真，实现无线传感器自动生成与可视化操作。实验采用集成电路充电管理芯片，型号为ML2496，具有铅酸电池充电管理的功能。",
            },
            {
              chapter: "四、结语",
              text: "本文提出的无线传感器网络电源电路优化改进方法，能够提供稳定的电源电压输出，其设计指标满足无线传感器基本性能指标要求。经过实验证明，本文设计的优化改进方法整体运行性能较好，电源的运行效率更加具有优势。但是，本文在无线传感器网络稳压模块方面的研究仍然存在一定的不足，网络稳压模块仅可接收 3 种传感器类型，在未来的研究中，应当进一步改进。",
            },
          ],
        },
      ],
      currentThesis: 0,
      thesisVisible: false,
      kindList: ["全部", "编程", "材料", "物理", "化学"],
      selectedKind: 0,
      isSelectedInterest: 0,
      //感兴趣的知识点
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
          ],
        },
      ],
      interest: [],
    };
  },
  components: {Top},
  mounted() {
    this.ready();
  },
  methods: {
    ready() {
      this.showUserinfo();
      let isSelectedInterest = sessionStorage.getItem("isSelectedInterest");
      this.isSelectedInterest = isSelectedInterest - 0; //字符串转数字
      this.showCategory();
    },
    editInfoClick() {
      this.leftNavClick(5);
    },
    signOutClick() {
      sessionStorage.clear();
      this.$router.push("/knowledge/index");
    },
    showUserinfo() {
      let that = this;
      getUserInfo()
          .then((res: any) => {
            console.log(res);
            that.user = res.data;
          })
          .catch((err: any) => {
            console.log(err);
          });
    },
    showCategory() {
      let that = this;
      getCategory()
          .then((res: any) => {
            console.log("interest");
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
      if (this.isSelectedInterest == 1) {
        getInterest().then((res: any) => {
          let selectedItems = res.data;
          //判断用户选择的兴趣点
          for (let i = 0; i < selectedItems.length; i++) {
            for (let j = 0; j < this.kindItemList.length; j++) {
              for (let k = 0; k < this.kindItemList[j].items.length; k++) {
                if (
                    selectedItems[i].itemId ==
                    this.kindItemList[j].items[k].itemId
                ) {
                  this.kindItemList[j].items[k].isSelected = true;
                }
              }
            }
          }
        });
      }
    },

    // 翻页
    handlePageChange(val: number) {
      this.paginationConfig.currentPage = val;
      console.log("当前页面数为：" + val);
      // this.getThesis();
    },
    leftNavClick(index: number) {
      this.currentNav = index;
      this.selectedKind = 0;
    },
    //点击进入知识点详情
    klgDetailBtnClick(id: number) {
      this.$router.push("/knowledge/detail/" + id);
    },
    kindClick(index: number) {
      this.selectedKind = index;
    },
    interestItemClick(kindIndex: number, itemIndex: number) {
      let isSelected = this.kindItemList[kindIndex].items[itemIndex].isSelected;
      this.kindItemList[kindIndex].items[itemIndex].isSelected = !isSelected;
    },
    //修改用户个人信息
    userinfoEditClick() {
      ElMessage({
        message: "个人信息修改成功！",
        type: "success",
      });
    },
    //修改感兴趣的知识点
    interestEditClick() {
      this.postInterest();
      ElMessage({
        message: "感兴趣的知识点修改成功！",
        type: "success",
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
            console.log(res);
          })
          .catch((err: any) => {
            console.log(err);
          });
    },
    thesisDetailClick(index: number) {
      this.currentThesis = index;
      this.currentNav = 0;
      this.thesisVisible = true;
    },
    closeThesisClick() {
      this.thesisVisible = false;
      this.currentNav = 2;
    },
    //下载论文pdf
    downloadThesis() {
      getPdf(this.thesisList[this.currentThesis]);
    },
  },
});
</script>

<style lang="scss" scoped>
@import "../../assets/style/css/user/selfCenter.scss";
</style>
