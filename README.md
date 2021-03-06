# timo-wars-gaming

## 简介

> demo名：**提莫大作战**
>
> 基于《英雄联盟》提莫这个英雄为原型的一个设计游戏。
>
> 初学Java时花费12天做的一款小游戏。时间比较久远了。

### 游戏特色

- 以《英雄联盟》提莫为原型的设计游戏
- 初始出现炸弹，碰到四周墙壁，以不同角度反弹，碰到炸弹即游戏结束
- 消灭怪物分数统计

### 技术栈

- Java的Swing作为UI
- 使用纯Java技术实现的一个小游戏。

### 操作

控制方向：键盘上下左右/WASD

发射弓箭：按空格

得分：消灭怪物

### 部分示意图



![开始界面](https://cos.duktig.cn/typora/20210818094529.png)

![游戏界面](https://cos.duktig.cn/typora/20210818094741.png)

## 文件夹结构

```
- src 源代码
    -- images 图片资源
    -- timoGame 代码
    	--- Main.java 启动类（开始游戏界面）
    	--- TimoGame.java 主游戏界面
    	--- GuaiWu.java 敌方怪物类
    	--- Explode.java 爆炸动效类
    	--- Character.java 提莫角色类
    	--- Shell.java 炸弹类
    	--- Arrow.java 弓箭类
```



## 注意事项

本项目（demo）是初学Java时完成的实战案例。当时开发的比较早，存在以下问题：

1. 学习阶段的作品，很多代码不够规范（后来学习《阿里巴巴Java开发手册》后，严格要求自己，写优雅、规范的代码）。
2. 当时使用的Eclipse开发，编码默认使用的GBK，非UTF8，记得在编译器修改编码呦，否则部分代码和注释会有乱码现象。
