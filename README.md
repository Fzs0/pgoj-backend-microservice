# 下载脚手架

```shell
npm install -g @vue/cli
```

# 新建vue项目

```shell
vue create 项目名
```

![image-20240528182815853](/Users/xiaogeer/Library/Application Support/typora-user-images/image-20240528182815853.png)

![image-20240528182903871](/Users/xiaogeer/Library/Application Support/typora-user-images/image-20240528182903871.png)

![image-20240528182952180](/Users/xiaogeer/Library/Application Support/typora-user-images/image-20240528182952180.png)

# 在webstorm中开启代码美化插件

![image-20240528185747151](/Users/xiaogeer/Library/Application Support/typora-user-images/image-20240528185747151.png)

# 引入组件Arco Design

## 安装

```shell
npm install --save-dev @arco-design/web-vue
```

## 改变main.ts文件

```tsx
import { createApp } from "vue";
import App from "./App.vue";
import ArcoVue from "@arco-design/web-vue";
import "@arco-design/web-vue/dist/arco.css";
import router from "./router";
import store from "./store";

createApp(App).use(ArcoVue).use(store).use(router).mount("#app");
```

# 项目通用布局

新建一个布局 在app.vue中引入

选用 arco design 的 layout 组件, 先把上中下布局编排好, 然后在填充内容



# 实现通用菜单

https://arco.design/vue/component/menu

把菜单上的路由改成读取路由文件, 实现更通用的动态配置



1) 提取通用路由文件
2) 菜单组件读取路由, 动态渲染菜单项
3) 绑定跳转事件
4) 同步路由到菜单项



菜单路由执行过程:

首先点击菜单项   => 跳转更新路由 => 更新路由后, 同步去更新菜单栏的高亮状态

```tsx
const router = useRouter();

// 默认主页
const selectedKeys = ref(["/"]);

// 路由更新后 更新选中的菜单项 进行高亮显示
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});
```



# 全局状态管理

vuex:https://vuex.vuejs.org/zh/

所有页面全局共享的变量, 而不是局限在某一个页面中

适合作为全局状态的数据: 已登录用户信息

本质上: 给你提供了一套增删改查全局变量的 API , 只不过可能多了一些功能(比如时间旅行)



state: 存储的状态信息, 比如用户信息

mutation(尽量同步): 定义了对变量进行增删改(更新)的方法

actions(支持异步): 执行异步操作, 并且出发 mutation 的更改(actions 调用 mutation)



先定义 user 模块:

```tsx
import { StoreOptions } from "vuex";

export default {
  namespaced: true,
  state: () => ({
    loginUser: {
      userName: "未登录",
    },
  }),
  actions: {
    getLoginUser({ commit, state }, payload) {
      commit("updateUser", { userName: "Fzs" });
    },
  },
  mutations: {
    updateUser(state, payload) {
      state.loginUser = payload;
    },
  },
} as StoreOptions<any>;

```

```tsx
import { createStore } from "vuex";
import user from "@/store/user";

export default createStore({
  mutations: {},
  actions: {},
  modules: {
    user,
  },
});

```

## 获取状态变量

```tsx
const store = useStore();
console.log(store.state.user.loginUser.userName);  // 打印结果为 “未登录”
```

## 修改状态变量

```tsx
store.dispatch("user/getLoginUser", {
    userName: "小明",
});
```

# 权限管理

我能够直接以一套通用的机制，去定义哪个页面需要那些权限。

思路：

1. ﻿﻿在路由配置文件，定义某个路由的访问权限
2. ﻿﻿在全局页面组件中，绑定一个全局路由监听。每次访问页面时，根据用户要访问页面的路由信息，先判断用户是否有对应的访问权限。
3. ﻿﻿如果有，跳转到原页面；如果没有，拦截或跳转到 401 鉴权或登录页

# 根据权限隐藏菜单

1、routes.ts 给路由新增一个标志位，用于判断路由是否显隐

```tsx
{
    path: "/hide",
    name: "隐藏页面",
    component: HomeView,
    meta: {
      hideInMenu: true,
    },
 },
```

2、不要用 v-for + v-if 去条件渲染元素，这样会先循环所有的元素，导致性能的浪费

推荐: 先过滤只需要展示的元素数组

```tsx
const visibleRoutes = routes.filter((item, index) => {
  if (item.meta?.hideInMenu) {
    return false;
  }
  return true;
});
```



# 安装OpenAPI

```shell
# 配置 SSL 证书
xiaogeer@xiaogeerdeMacBook-Air pgoj-frontend % npm config set registry https://registry.npmjs.org/

xiaogeer@xiaogeerdeMacBook-Air pgoj-frontend % npm install openapi-typescript-codegen --save-dev

```

## 生成后端请求

```shell
xiaogeer@xiaogeerdeMacBook-Air pgoj-frontend % openapi --input http://localhost:8101/api//v2/api-docs --output ./generated --client axios
```

![image-20240531151122866](/Users/xiaogeer/Library/Application Support/typora-user-images/image-20240531151122866.png)

直接使用生成的 Service 代码, 调用函数发送请求即可, 比如获取登录信息

```tsx
async getLoginUser({ commit, state }, payload) {
      // 从远程获取登录信息
      const res = await UserControllerService.getLoginUserUsingGet();
      if (res.code === 0) {
        commit("updateUser", res.data);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          userRole: AccessEnum.NOT_LOGIN,
        });
      }
    },
```

# 全局权限管理优化

新建 access/index.ts 文件, 把原有的路由拦截、权限校验逻辑放在独立的文件中

优势: 只要不引入, 就不会开启, 不会对项目有影响



access/index.tsx

```tsx
import router from "@/router";
import store from "@/store";
import AccessEnum from "@/access/accessEnum";
import checkAccess from "@/access/checkAccess";

router.beforeEach(async (to, from, next) => {
  console.log("登录用户信息", store.state.user.loginUser);

  const loginUser = store.state.user.loginUser;
  // 如果之前没登录 自动登录
  if (!loginUser || loginUser.userRole) {
    await store.dispatch("user/getLoginUser");
  }
  const needAccess = to.meta?.access ?? AccessEnum.NOT_LOGIN;
  // 要跳转的页面必须登录
  if (needAccess !== AccessEnum.NOT_LOGIN) {
    // 如果没登录 跳转到登录页
    if (!loginUser) {
      next(`/user/login?redirect=${to.fullPath}`);
      return;
    }
    // 如果已经登录
    if (!checkAccess(loginUser, needAccess)) {
      next("/noAuth");
      return;
    }
  }
  next();
});

```

# 支持多套布局

1） 在 routes路由文件中新建一套用户路由，使用 vue-router 自带的子路由机制，实现布局和嵌套路由

```tsx
{
    path: "/user",
    name: "用户",
    // 使用用户布局
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "用户登录",
        component: UserLoginView,
      },
      {
        path: "/user/register",
        name: "用户注册",
        component: UserRegisterView,
      },
    ],
  },
```

# MdEditor 示例代码

```vue
/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
  handleChange: (v: string) => void;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  handleChange: (v: string) => {
    console.log(v);
  },
});
```

# monaco-editor

## 安装编辑器

```shell
npm install monaco-editor
```

## vue-cli 项目 (webpack 项目)整合 monaco-editor

先安装 monaco-editor-webpack-plugin

```shell
npm install monaco-editor-webpack-plugin
```

在 vue.config.js 配置 webpack插件

```ts
const { defineConfig } = require("@vue/cli-service");
const MonacoWebpackPlugin = require("monaco-editor-webpack-plugin");

module.exports = defineConfig({
  transpileDependencies: true,
  chainWebpack(config) {
    config.plugin("monaco").use(new MonacoWebpackPlugin());
  },
});

```

